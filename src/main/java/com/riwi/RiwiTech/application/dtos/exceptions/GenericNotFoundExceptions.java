package com.riwi.RiwiTech.application.dtos.exceptions;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericNotFoundExceptions extends RuntimeException {

    public GenericNotFoundExceptions(String message) {
        super(message);
    }

}
