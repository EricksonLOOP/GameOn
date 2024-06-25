package com.br.gameon.api.GameOn.Repositorys;
import com.br.gameon.api.GameOn.Entitys.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, UUID> {
}
