package ru.kir.sm.sensormeasurementrestapp.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kir.sm.sensormeasurementrestapp.dto.ApiError;
import ru.kir.sm.sensormeasurementrestapp.dto.AuthRequest;
import ru.kir.sm.sensormeasurementrestapp.dto.AuthResponse;
import ru.kir.sm.sensormeasurementrestapp.jwt.JwtService;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());

        String refreshToken = jwtService.generateJwtRefreshToken(userDetails);

        ResponseCookie cookie = ResponseCookie
                .from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/jwt/refresh")
                .maxAge(Duration.ofDays(7))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(new AuthResponse(jwtService.generateJwtAccessToken(userDetails)));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest  request, HttpServletResponse response) {

        String refreshToken = Arrays.stream(Optional.ofNullable(request.getCookies())
                .orElse(new Cookie[0]))
                .filter(c -> "refresh_token".equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiError(401, "Missing refresh token"));
        }

        UserDetails user = userDetailsService.loadUserByUsername(jwtService.extractUsername(refreshToken));

        if (!jwtService.isTokenValid(refreshToken, user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiError(401, "Invalid refresh token"));
        }

        return ResponseEntity.ok(new AuthResponse(jwtService.generateJwtAccessToken(user)));
    }
}
