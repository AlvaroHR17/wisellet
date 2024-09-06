package com.alvarohdr.gastosapi.domain.service.impl;

import com.alvarohdr.framework.config.JwtUtils;
import com.alvarohdr.gastosapi.domain.dao.UserDao;
import com.alvarohdr.gastosapi.domain.dto.IdTokenRequestDto;
import com.alvarohdr.gastosapi.domain.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private final GoogleIdTokenVerifier verifier;

    public UserService(@Value("${app.googleClientId}") String clientId, UserDao userDao,
                          JwtUtils jwtUtils) {
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    public String loginOAuthGoogle(IdTokenRequestDto requestBody) {
        User user = verifyIDToken(requestBody.getIdToken());
        if (user == null) {
            throw new IllegalArgumentException();
        }
        user = createOrUpdateUser(user);
        return jwtUtils.createToken(user, false);
    }

    public User createOrUpdateUser(User user) {
        User existingUser = userDao.findByEmail(user.getEmail()).orElse(null);
        if (existingUser == null) {
            user.setRoles("ROLE_USER");
            userDao.saveOrUpdate(user);
            return user;
        }
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        userDao.saveOrUpdate(existingUser);
        return existingUser;
    }

    private User verifyIDToken(String idToken) {
        try {
            GoogleIdToken idTokenObj = verifier.verify(idToken);
            if (idTokenObj == null) {
                return null;
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();
            String firstName = (String) payload.get("given_name");
            String lastName = (String) payload.get("family_name");
            String email = payload.getEmail();

            return new User(firstName, lastName, email);
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }

    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public Optional<User> getCurrentUser() {
        return userDao.getCurrentUser();
    }
}
