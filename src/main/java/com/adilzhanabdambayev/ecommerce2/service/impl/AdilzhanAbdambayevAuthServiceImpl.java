package com.adilzhanabdambayev.ecommerce2.service.impl;

import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevAuthResponse;
import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevLoginRequest;
import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevRegisterRequest;
import com.adilzhanabdambayev.ecommerce2.dto.AdilzhanAbdambayevUserResponse;
import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevRole;
import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevUser;
import com.adilzhanabdambayev.ecommerce2.repository.AdilzhanAbdambayevRoleRepository;
import com.adilzhanabdambayev.ecommerce2.repository.AdilzhanAbdambayevUserRepository;
import com.adilzhanabdambayev.ecommerce2.security.AdilzhanAbdambayevJwtUtil;
import com.adilzhanabdambayev.ecommerce2.service.AdilzhanAbdambayevAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdilzhanAbdambayevAuthServiceImpl implements AdilzhanAbdambayevAuthService {

    private static final String DEFAULT_ROLE = "USER";
    private static final String TOKEN_TYPE = "Bearer";

    private final AdilzhanAbdambayevUserRepository userRepository;
    private final AdilzhanAbdambayevRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AdilzhanAbdambayevJwtUtil jwtUtil;

    public AdilzhanAbdambayevAuthServiceImpl(
            AdilzhanAbdambayevUserRepository userRepository,
            AdilzhanAbdambayevRoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            AdilzhanAbdambayevJwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public AdilzhanAbdambayevAuthResponse register(AdilzhanAbdambayevRegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        AdilzhanAbdambayevRole role = roleRepository.findByName(DEFAULT_ROLE)
                .orElseGet(() -> roleRepository.save(AdilzhanAbdambayevRole.builder()
                        .name(DEFAULT_ROLE)
                        .build()));

        AdilzhanAbdambayevUser user = AdilzhanAbdambayevUser.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(role)
                .build();

        AdilzhanAbdambayevUser savedUser = userRepository.save(user);
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(savedUser.getEmail())
                .password(savedUser.getPassword())
                .authorities("ROLE_" + savedUser.getRole().getName())
                .build();

        return buildAuthResponse(savedUser, jwtUtil.generateToken(userDetails));
    }

    @Override
    @Transactional(readOnly = true)
    public AdilzhanAbdambayevAuthResponse login(AdilzhanAbdambayevLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AdilzhanAbdambayevUser user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        return buildAuthResponse(user, jwtUtil.generateToken(userDetails));
    }

    private AdilzhanAbdambayevAuthResponse buildAuthResponse(AdilzhanAbdambayevUser user, String token) {
        return new AdilzhanAbdambayevAuthResponse(token, TOKEN_TYPE, toUserResponse(user));
    }

    private AdilzhanAbdambayevUserResponse toUserResponse(AdilzhanAbdambayevUser user) {
        return new AdilzhanAbdambayevUserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().getName(),
                user.getCreatedAt()
        );
    }
}
