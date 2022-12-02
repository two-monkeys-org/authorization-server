package org.monke.authorizationserver.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestSessionCreate {
    private String email;
    private String password;
}
