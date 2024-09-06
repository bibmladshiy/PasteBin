package com.example.pastebin.exception;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CustomNoSuchPasteException extends NoSuchFieldException{
    public String getMessage() {
        return "There is no paste for this URL";
    }
}
