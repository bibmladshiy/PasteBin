package com.example.kal_l.exception;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CustomDeletingException extends RuntimeException{

    public String getMessage() {
        return "There is no user to delete";
    }
}
