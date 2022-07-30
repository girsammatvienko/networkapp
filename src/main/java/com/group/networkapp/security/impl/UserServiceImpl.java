package com.group.networkapp.security.impl;

import com.group.networkapp.domain.RoleEnum;
import com.group.networkapp.dto.UserDto;
import com.group.networkapp.dto.request.SignInRequest;
import com.group.networkapp.dto.response.SignInResponse;
import com.group.networkapp.domain.entity.NetworkUser;
import com.group.networkapp.domain.entity.Role;
import com.group.networkapp.domain.exception.InvalidEmailException;
import com.group.networkapp.domain.exception.UserAlreadyExistsException;
import com.group.networkapp.repository.RoleRepository;
import com.group.networkapp.repository.UserRepository;
import com.group.networkapp.security.UserService;
import com.group.networkapp.utils.EmailValidator;
import com.group.networkapp.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;


    @Override
    public SignInResponse login(SignInRequest request) {
        Authentication auth = getAuthentication(request.getEmail(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return generateSignInResponse(auth);
    }

    private SignInResponse generateSignInResponse(Authentication authentication) {
        return SignInResponse.builder()
                .accessToken("HELLO")
                .build();
    }

    private Authentication getAuthentication(String email, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) throws UserAlreadyExistsException {
        if(isEmailTaken(userDto.getEmail()))
            throw new UserAlreadyExistsException("User with such email already exists", HttpStatus.INTERNAL_SERVER_ERROR);

        if(!EmailValidator.isValid(userDto.getEmail()))
            throw new InvalidEmailException("Invalid email", HttpStatus.INTERNAL_SERVER_ERROR);

        Role role = roleRepository.findByName(RoleEnum.ROLE_USER) == null? roleRepository.save(new Role(RoleEnum.ROLE_USER)): roleRepository.findByName(RoleEnum.ROLE_USER);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        var newUser = NetworkUser.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(Set.of(role))
                .build();

        return userMapper.entityToDto(userRepository.save(newUser));
    }

    private boolean isEmailTaken(String email) {return userRepository.findByEmail(email).isPresent();}
}
