package com.br.gameon.api.GameOn.Repositorys;

import com.br.gameon.api.GameOn.Entitys.PublishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublishRepository extends JpaRepository<PublishEntity, UUID> {
}


