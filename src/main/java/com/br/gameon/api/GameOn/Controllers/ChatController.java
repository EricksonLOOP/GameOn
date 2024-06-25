package com.br.gameon.api.GameOn.Controllers;


import com.br.gameon.api.GameOn.Entitys.ChatEntity;
import com.br.gameon.api.GameOn.Repositorys.ChatRepository;
import com.br.gameon.api.GameOn.dtos.ChatRecordDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class ChatController {

    private final ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatEntity> publicarMsg(@RequestBody @Valid ChatRecordDto chatRecordDto) {
        ChatEntity chat = new ChatEntity();
        BeanUtils.copyProperties(chatRecordDto, chat);
        ChatEntity savedChat = chatRepository.save(chat);
        return ResponseEntity.status(HttpStatus.OK).body(savedChat);

    }

    @GetMapping("/chat")
    public ResponseEntity<List<ChatEntity>> obterTodasMensagensDeChat() {
        try {
            List<ChatEntity> listaDeMsgs = chatRepository.findAll();
            return ResponseEntity.ok(listaDeMsgs); // Usando ResponseEntity.ok() para retornar HTTP 200
        } catch (Exception erro) {
            // Logar o erro para fins de depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Retorna HTTP 500 em caso de erro
        }
    }
    @DeleteMapping("/chat/{id}")
    public ResponseEntity<Object> deletarMsg(@PathVariable(value = "id") UUID id){
        Optional<ChatEntity> msg0 = chatRepository.findById(id);
        if (msg0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Msg não encontrado.");
        }
        chatRepository.delete(msg0.get());
        return ResponseEntity.status(HttpStatus.OK).body("MSg Deletado");
    }
}
