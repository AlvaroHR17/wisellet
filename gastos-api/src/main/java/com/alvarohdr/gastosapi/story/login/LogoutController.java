package com.alvarohdr.gastosapi.story.login;

import com.google.common.net.HttpHeaders;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/oauth")
public class LogoutController {

    @GetMapping("/logout")
    public ResponseEntity<?> LogoutUser(HttpServletResponse response) {

        SecurityContextHolder.clearContext();
        ResponseCookie cookie = ResponseCookie.from("AUTH-TOKEN", "")
                .httpOnly(true)
                .maxAge(0)
                .path("/")
                .secure(false)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok("ok");
    }

}
