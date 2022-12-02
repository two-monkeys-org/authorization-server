package org.monke.authorizationserver.exception;

public class InvalidSessionID extends Exception{
    public InvalidSessionID(String message) {
        super(message);
    }
}
