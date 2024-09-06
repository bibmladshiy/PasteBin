package com.example.pastebin.dtoUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class LogInDto implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    private String userName;

    private String userPassword;
}
