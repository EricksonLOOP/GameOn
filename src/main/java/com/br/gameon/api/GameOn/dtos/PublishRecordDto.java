package com.br.gameon.api.GameOn.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PublishRecordDto(@NotBlank  String comment, UUID idUser, String userName) {
}
