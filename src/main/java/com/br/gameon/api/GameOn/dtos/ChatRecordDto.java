package com.br.gameon.api.GameOn.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatRecordDto(@NotBlank String message, @NotNull String messageProper) {

}
