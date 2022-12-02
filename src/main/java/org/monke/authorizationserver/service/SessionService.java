package org.monke.authorizationserver.service;

import org.monke.authorizationserver.exception.InvalidCredentialsException;
import org.monke.authorizationserver.exception.InvalidSessionID;
import org.monke.authorizationserver.entity.request.RequestSessionCreate;
import org.monke.authorizationserver.entity.response.SessionResponse;
import org.monke.authorizationserver.entity.response.ValidatedSessionResponse;

public interface SessionService {
    SessionResponse createSession(RequestSessionCreate requestSessionCreate) throws InvalidCredentialsException;
    ValidatedSessionResponse validateSession(String sessionID) throws InvalidSessionID;
}
