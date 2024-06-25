package com.br.gameon.api.GameOn.Repositorys;

import com.br.gameon.api.GameOn.Entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByname(String name);
}
