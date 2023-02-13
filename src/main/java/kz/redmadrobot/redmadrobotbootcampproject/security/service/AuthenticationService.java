package kz.redmadrobot.redmadrobotbootcampproject.security.service;

import kz.redmadrobot.redmadrobotbootcampproject.security.dto.AuthenticationRequest;
import kz.redmadrobot.redmadrobotbootcampproject.security.dto.AuthenticationResponse;
import kz.redmadrobot.redmadrobotbootcampproject.security.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
