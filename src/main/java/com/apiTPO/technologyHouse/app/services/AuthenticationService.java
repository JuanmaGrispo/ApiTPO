package com.apiTPO.technologyHouse.app.services;

import com.apiTPO.technologyHouse.app.dtos.UserIdDTO;
import com.apiTPO.technologyHouse.app.models.ShoppingCart;
import com.apiTPO.technologyHouse.app.repositories.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apiTPO.technologyHouse.app.models.Role;
import com.apiTPO.technologyHouse.app.auth.AuthenticationRequest;
import com.apiTPO.technologyHouse.app.auth.AuthenticationResponse;
import com.apiTPO.technologyHouse.app.auth.RegisterRequest;
import com.apiTPO.technologyHouse.app.config.JwtService;
import com.apiTPO.technologyHouse.app.repositories.UserRepository;


import com.apiTPO.technologyHouse.app.models.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final ShoppingCartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User createdUser = userRepository.save(user);

        if (createdUser.getRole() == Role.CUSTOMER) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(createdUser);
            cartRepository.save(shoppingCart);
        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}
