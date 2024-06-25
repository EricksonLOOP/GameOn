package com.br.gameon.api.GameOn.Controllers;

import com.br.gameon.api.GameOn.Entitys.PublishEntity;
import com.br.gameon.api.GameOn.Repositorys.PublishRepository;
import com.br.gameon.api.GameOn.dtos.PublishRecordDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PublishController {

    @Autowired
    PublishRepository publishRepository;

    @PostMapping("/publishs")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> publicarPostagem(@RequestBody @Valid PublishRecordDto publishRecordDto) {
        PublishEntity publish = new PublishEntity();
        publish.setUserName(publishRecordDto.userName());
        publish.setIdUser(publishRecordDto.idUser());
        publish.setComment(publishRecordDto.comment());
        publish.setData(new Date()); // Defina a data atual ou a data fornecida no DTO, conforme necessário

        PublishEntity savedPublish = publishRepository.save(publish);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublish);
    }
    @GetMapping("/publishs/{id}")
    @CrossOrigin(origins = "https://localhost:3000")
    public ResponseEntity<List<PublishEntity>> listarPublicacoes(@PathVariable String id) {
        List<PublishEntity> publishs = publishRepository.findAll();
        if (!publishs.isEmpty()) {
            for (PublishEntity publish : publishs) {
                //publish.add(linkTo(methodOn(UserController.class).obterUmUsuario(uuid)).withSelfRel());
                Boolean auth = publish.getIdUser().toString().equals(id);
                publish.setAuthorized(auth);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(publishs);
    }
    @CrossOrigin(origins = "https://localhost:3000")
    @DeleteMapping("/publishs/{id}")
    public ResponseEntity<Object> deletarPublicacao(@PathVariable(value = "id") UUID id){
        Optional<PublishEntity> publish0 = publishRepository.findById(id);
        if (publish0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicação não encontrada.");
        }
        publishRepository.delete(publish0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Publicação Deletado");
    }
    @PostMapping("/checkAuthority/{loggedUser}/{publishUser}")
    @CrossOrigin(origins = "https://localhost:3000")
    public ResponseEntity<?> checarAutoridade(@PathVariable String loggedUser, @PathVariable String publishUser) {

        if ( loggedUser.equals(publishUser)){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }
}

