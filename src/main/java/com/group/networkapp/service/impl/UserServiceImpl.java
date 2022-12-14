package com.group.networkapp.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.networkapp.configuration.JwtTokenProvider;
import com.group.networkapp.domain.RoleEnum;
import com.group.networkapp.domain.entity.NetworkUser;
import com.group.networkapp.domain.entity.Role;
import com.group.networkapp.domain.exception.InvalidEmailException;
import com.group.networkapp.domain.exception.NetworkAppException;
import com.group.networkapp.domain.exception.UserAlreadyExistsException;
import com.group.networkapp.domain.exception.UserNotFoundException;
import com.group.networkapp.dto.UserDto;
import com.group.networkapp.dto.request.UserRequest;
import com.group.networkapp.repository.RoleRepository;
import com.group.networkapp.repository.UserRepository;
import com.group.networkapp.service.UserService;
import com.group.networkapp.utils.EmailValidator;
import com.group.networkapp.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public NetworkUser getCurrentUser() {
        String currentUsername = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authHeader.substring("Bearer ".length());
                Algorithm algorithm = jwtTokenProvider.getAlgorithm();
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                NetworkUser networkUser = userRepository.findByEmail(username).orElseThrow();
                User user = new User(networkUser.getUsername(), networkUser.getPassword(), networkUser.getAuthorities());
                String accessToken = jwtTokenProvider.generateAccessToken(user, request);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }
            catch (Exception e) {
                log.debug(e.getMessage());
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else
            throw new NetworkAppException("Refresh token is missing", HttpStatus.NOT_FOUND);
    }

    @Override
    public UserDto registerNewUser(UserRequest userRequest) throws UserAlreadyExistsException {
        if(isEmailTaken(userRequest.getEmail()))
            throw new UserAlreadyExistsException("User with such email already exists", HttpStatus.INTERNAL_SERVER_ERROR);

        if(!EmailValidator.isValid(userRequest.getEmail()))
            throw new InvalidEmailException("Invalid email", HttpStatus.INTERNAL_SERVER_ERROR);

        Role role = roleRepository.findByName(RoleEnum.ROLE_USER) == null? roleRepository.save(new Role(RoleEnum.ROLE_USER)): roleRepository.findByName(RoleEnum.ROLE_USER);

        var newUser = NetworkUser.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(Set.of(role))
                .build();

        return userMapper.toDto(userRepository.save(newUser));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserDto.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    private boolean isEmailTaken(String email) {return userRepository.findByEmail(email).isPresent();}
}
