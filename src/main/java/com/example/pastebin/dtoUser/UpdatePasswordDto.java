package com.example.pastebin.dtoUser;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdatePasswordDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Length(min = 4, max = 9, message = "New password should be between 4 to 9 characters")
    @NotNull(message = "New password cannot be blank")
    private String userPasswordNew;

    @NotNull(message = "Password cannot be blank")
    private String userPassword;
}
