package org.monke.authorizationserver.controller;

import org.monke.authorizationserver.exception.InvalidCredentialsException;
import org.monke.authorizationserver.exception.InvalidSessionID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.monke.authorizationserver.entity.request.RequestSessionCreate;
import org.monke.authorizationserver.entity.response.SessionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.monke.authorizationserver.entity.response.ValidatedSessionResponse;
import org.monke.authorizationserver.service.impl.SessionServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SessionController {
    private final SessionServiceImpl sessionService;

    @PostMapping("/login")
    public SessionResponse createSession(@RequestBody @Valid RequestSessionCreate requestSessionCreate) {
        try {
            return sessionService.createSession(requestSessionCreate);
        } catch (InvalidCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/validate/{sessionID}")
    public ValidatedSessionResponse createSession(@PathVariable String sessionID) {
        try {
            return sessionService.validateSession(sessionID);
        } catch (InvalidSessionID e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
