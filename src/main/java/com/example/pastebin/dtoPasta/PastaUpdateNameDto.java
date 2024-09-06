package com.example.pastebin.dtoPasta;

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
public class PastaUpdateNameDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "New pasta name can't be empty")
    @Length(min = 5, max = 25, message = "New pasta name should be between 5 to 25 characters")
    private String pastaNameNew;

    @NotNull(message = "Pasta name can't be empty")
    @Length(min = 5, max = 25, message = "Pasta name should be between 5 to 25 characters")
    private String pastaName;
}
