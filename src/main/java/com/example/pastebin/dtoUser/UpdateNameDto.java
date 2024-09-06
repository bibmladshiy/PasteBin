package com.example.kal_l.dtoUser;

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
public class UpdateNameDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "New user name cannot be blank")
    @Length(min = 3, max = 10, message = "New user name should be between 3 to 10 characters")
    private String userNameNew;

    @NotNull(message = "User name cannot be blank")
    private String userName;
}
