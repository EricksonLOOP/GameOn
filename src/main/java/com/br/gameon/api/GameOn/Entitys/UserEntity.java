package com.br.gameon.api.GameOn.Entitys;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Size(min = 6, max = 100)
    private String password;
    private boolean isOnOrOff;

    @ElementCollection
    private List<String> friends;

    public void addFriend(String friendId) {
        this.friends.add(friendId);
    }
}
