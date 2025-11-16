package com.rahul.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException {

    private String userId;

    public UserNotFoundException(String userId) {
        super(String.format("User Not Found: %s", userId));
        this.userId = userId;
    }
}
