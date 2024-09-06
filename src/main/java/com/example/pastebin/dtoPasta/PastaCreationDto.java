package com.example.pastebin.dtoPasta;

import com.example.pastebin.entityPasta.Lifetime;
import com.example.pastebin.entityPasta.PastaStatus;
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
public class PastaCreationDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Title can't be empty")
    @Length(min = 5, max = 25, message = "Pasta name should be between 5 to 25 characters")
    private String pastaName;

    @NotNull(message = "Pasta body can't be empty")
    @Length(min = 10, max = 100, message = "Text of the pasta should be between 10 to 100 characters")
    private String pastaText;

    private PastaStatus pastaStatus;
    @NotNull(message = "Pasta status can't be empty")
    private String status;

    private Lifetime pastaLifetime;
    @NotNull(message = "Pasta lifetime can't be empty")
    private String lifetime;

    @NotNull(message = "User name can't be empty")
    private String userName;

    @NotNull(message = "User password can't be empty")
    private String userPassword;
}
