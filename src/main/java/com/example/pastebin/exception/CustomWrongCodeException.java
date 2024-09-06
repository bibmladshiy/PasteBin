package com.example.kal_l.exception;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CustomWrongCodeException extends RuntimeException{
    public String getMessage() {
        return "This code is invalid";
    }
}
