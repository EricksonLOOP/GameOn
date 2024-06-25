package com.br.gameon.api.GameOn.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRecordDto(
        @NotBlank @Size(min = 3, max = 50) String name,
        @NotNull @Size(min = 8, max = 100) String password
) {}
