package org.monke.authorizationserver.service.impl;

import org.monke.authorizationserver.converter.SessionConverter;
import org.monke.authorizationserver.entity.SessionEntity;
import org.monke.authorizationserver.exception.InvalidCredentialsException;
import org.monke.authorizationserver.exception.InvalidSessionID;
import org.monke.authorizationserver.repository.SessionRepository;
import org.monke.authorizationserver.service.SessionService;
import org.monke.authorizationserver.entity.response.ValidatedSessionResponse;
import org.monke.authorizationserver.entity.request.RequestSessionCreate;
import org.monke.authorizationserver.entity.response.SessionResponse;
import org.monke.authorizationserver.entity.response.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final UserService userService;
    private final SessionRepository sessionRepository;
    private final SessionConverter sessionConverter;

    @Override
    public SessionResponse createSession(RequestSessionCreate requestSessionCreate) throws InvalidCredentialsException {
        final HttpStatus status = userService.checkUserCredentials(requestSessionCreate);
        if(status.is4xxClientError())
            throw new InvalidCredentialsException("Invalid credentials!");

        if(sessionRepository.existsByEmail(requestSessionCreate.getEmail()))
            return sessionConverter.sessionResponseOf(sessionRepository.findByEmail(requestSessionCreate.getEmail()));

        final SessionEntity sessionEntity = new SessionEntity()
                .setSessionId(generateSessionId())
                .setEmail(requestSessionCreate.getEmail())
                .setExpirationDate(LocalDateTime.now().plusDays(1));

        sessionRepository.save(sessionEntity);
        return sessionConverter.sessionResponseOf(sessionEntity);
    }

    @Override
    public ValidatedSessionResponse validateSession(String sessionID) throws InvalidSessionID {
        final SessionEntity sessionEntity = sessionRepository.findById(sessionID)
                .orElseThrow(()-> new InvalidSessionID("There is no active session with given id"));

        // TODO add date and mac / IP validation

        return sessionConverter.validatedSessionResponseOf(sessionEntity);
    }

    private String generateSessionId() {
        String randomSessionID;
        do {
            randomSessionID = UUID.randomUUID().toString().replaceAll("-", "");
        } while(sessionRepository.existsById(randomSessionID));

        return randomSessionID;
    }
}
