package com.rahul.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostNotFoundException extends RuntimeException {

    private String message;

    public PostNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
