package com.simco.simplecommerce.service;

import com.simco.simplecommerce.auth.JwtUtil;
import com.simco.simplecommerce.dto.TokenDTO;
import com.simco.simplecommerce.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public TokenDTO authenticate(UserDTO user){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.username(), user.password()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String jwt = jwtUtil.generateToken(userDetails);
        return new TokenDTO(jwt);
    }
}
