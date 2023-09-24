package com.workshop.demojwt.controller;


import com.workshop.demojwt.config.utils.JwtUtils;
import com.workshop.demojwt.dto.AuthenticationRequest;

import com.workshop.demojwt.service.Impl.utils.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public Map<String, String> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        //TODO  传输 token info
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        String jwtToken = jwtUtils.generateToken(userDetails);

        Map<String, String> map = new HashMap<>();
        map.put("jwt", jwtToken);
        map.put("error_message", "success");

        return map;
    }
}
