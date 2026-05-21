package com.adilzhanabdambayev.ecommerce2.security;

import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevUser;
import com.adilzhanabdambayev.ecommerce2.repository.AdilzhanAbdambayevUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdilzhanAbdambayevUserDetailsService implements UserDetailsService {

    private final AdilzhanAbdambayevUserRepository userRepository;

    public AdilzhanAbdambayevUserDetailsService(AdilzhanAbdambayevUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdilzhanAbdambayevUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        String roleName = user.getRole().getName();
        String authority = roleName.startsWith("ROLE_") ? roleName : "ROLE_" + roleName;

        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(authority))
        );
    }
}
