package org.monke.authorizationserver.repository;

import org.monke.authorizationserver.entity.SessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository<SessionEntity, String> {
    boolean existsByEmail(String email);
    SessionEntity findByEmail(String email);
}
