package com.br.gameon.api.GameOn.Entitys;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "chat")

public class ChatEntity extends RepresentationModel<ChatEntity> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String message;
    private String messageProper;

    public String getMessageProper() {
        return messageProper;
    }

    public void setMessageProper(String messageProper) {
        this.messageProper = messageProper;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
