package com.example.pastebin.exception;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CustomLoggingInException extends RuntimeException{

    public String getMessage() {
        return "There is no such user";
    }
}
