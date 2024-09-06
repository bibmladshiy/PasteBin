package com.example.kal_l.dtoUser;

import jakarta.validation.constraints.Email;
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
public class RegDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull(message = "User name cannot be blank")
    @Length(min = 3, max = 10, message = "User name should be between 3 to 10 characters")
    private String userName;
    @NotNull(message = "Email cannot be blank")
    @Email(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "This email is invalid")
    @Length(max = 50, message = "Email is too big")
    private String userEmail;
    @Length(min = 4, max = 9, message = "Password should be between 4 to 9 characters")
    @NotNull(message = "Password cannot be blank")
    private String userPassword;
}
