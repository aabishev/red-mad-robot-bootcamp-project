package kz.redmadrobot.redmadrobotbootcampproject.security.controller;

import kz.redmadrobot.redmadrobotbootcampproject.security.dto.AuthenticationRequest;
import kz.redmadrobot.redmadrobotbootcampproject.security.dto.AuthenticationResponse;
import kz.redmadrobot.redmadrobotbootcampproject.security.dto.RegisterRequest;
import kz.redmadrobot.redmadrobotbootcampproject.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    private static final String REGISTER = "/register";
    private static final String AUTHENTICATE = "/authenticate";

    @ResponseStatus(OK)
    @PostMapping(REGISTER)
    public AuthenticationResponse register(
            @RequestBody RegisterRequest request
    ) {
        return service.register(request);
    }

    @ResponseStatus(OK)
    @PostMapping(AUTHENTICATE)
    public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return service.authenticate(request);
    }
}
