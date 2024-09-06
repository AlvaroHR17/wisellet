package com.alvarohdr.gastosapi.story.login;

import com.alvarohdr.gastosapi.domain.dto.IdTokenRequestDto;
import com.alvarohdr.gastosapi.domain.service.impl.UserService;
import com.google.common.net.HttpHeaders;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/v1/oauth")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> LoginWithGoogleOauth2(@RequestBody IdTokenRequestDto requestBody, HttpServletResponse response) {
        String authToken = userService.loginOAuthGoogle(requestBody);
        final ResponseCookie cookie = ResponseCookie.from("AUTH-TOKEN", authToken)
                .httpOnly(true)
                .maxAge(7 * 24 * 3600)
                .path("/")
                .secure(false)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/auth")
    public boolean getCurrentUser(UsernamePasswordAuthenticationToken principal) {
        return Objects.nonNull(principal) && principal.isAuthenticated();
    }

}
