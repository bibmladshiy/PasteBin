package com.example.kal_l.exception;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
@Data
@Builder
@RequiredArgsConstructor
public class CustomTenException extends NoSuchElementException {
    public String getMessage() {
        return "There are no more pastes";
    }
}
