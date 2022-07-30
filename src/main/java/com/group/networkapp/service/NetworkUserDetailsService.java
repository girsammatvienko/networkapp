package com.group.networkapp.service;

import com.group.networkapp.domain.exception.NetworkAppException;
import com.group.networkapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NetworkUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        var user = userRepository.findByEmail(email);
        if(user.isEmpty())
            throw new NetworkAppException("There is no user with such username", HttpStatus.INTERNAL_SERVER_ERROR);

        return new User(user.get().getEmail(), user.get().getPassword(), user.get().getAuthorities());
    }
}
