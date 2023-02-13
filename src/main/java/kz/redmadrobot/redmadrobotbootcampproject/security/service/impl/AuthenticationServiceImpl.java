package kz.redmadrobot.redmadrobotbootcampproject.security.service.impl;

import kz.redmadrobot.redmadrobotbootcampproject.security.config.JwtService;
import kz.redmadrobot.redmadrobotbootcampproject.security.dto.AuthenticationRequest;
import kz.redmadrobot.redmadrobotbootcampproject.security.dto.AuthenticationResponse;
import kz.redmadrobot.redmadrobotbootcampproject.security.dto.RegisterRequest;
import kz.redmadrobot.redmadrobotbootcampproject.exception.ApiRequestException;
import kz.redmadrobot.redmadrobotbootcampproject.security.model.Role;
import kz.redmadrobot.redmadrobotbootcampproject.security.model.User;
import kz.redmadrobot.redmadrobotbootcampproject.security.repository.UserRepository;
import kz.redmadrobot.redmadrobotbootcampproject.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new ApiRequestException("Пользователь с почтой " + request.getEmail() + " уже существует", BAD_REQUEST);
        }
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(request.getEmail())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (repository.findByEmail(request.getEmail()).isEmpty()) {
            throw new ApiRequestException("Пользователь с почтой " + request.getEmail() + " не найден", BAD_REQUEST);
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(request.getEmail())
                .build();
    }
}
