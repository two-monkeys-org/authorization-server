package org.monke.authorizationserver.converter;

import org.monke.authorizationserver.entity.SessionEntity;
import org.springframework.stereotype.Component;
import org.monke.authorizationserver.entity.response.SessionResponse;
import org.monke.authorizationserver.entity.response.ValidatedSessionResponse;

@Component
public class SessionConverter {
    public SessionResponse sessionResponseOf(SessionEntity sessionEntity) {
        return new SessionResponse()
                .setSessionID(sessionEntity.getSessionId());
    }

    public ValidatedSessionResponse validatedSessionResponseOf(SessionEntity sessionEntity) {
        return new ValidatedSessionResponse()
                .setSessionId(sessionEntity.getSessionId())
                .setEmail(sessionEntity.getEmail())
                .setExpirationDate(sessionEntity.getExpirationDate());
    }
}
