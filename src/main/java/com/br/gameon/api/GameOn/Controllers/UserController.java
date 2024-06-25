package com.br.gameon.api.GameOn.Controllers;


import com.br.gameon.api.GameOn.Entitys.UserEntity;
import com.br.gameon.api.GameOn.Repositorys.UserRepository;
import com.br.gameon.api.GameOn.dtos.UserRecordDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> salvarUsuario(@RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserEntity> existingUser = userRepository.findByname(userRecordDto.name());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário existente.");
        }

        var userModel = new UserEntity();
        BeanUtils.copyProperties(userRecordDto, userModel);
        userModel.setPassword(passwordEncoder.encode(userRecordDto.password())); // Criptografando a senha
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }
    @GetMapping("/helloworld")
    public ResponseEntity<?> helloworld(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello World!");
    }
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> logarUsuario(@RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserEntity> existingUserOptional = userRepository.findByname(userRecordDto.name());

        if (existingUserOptional.isPresent()) {
            UserEntity user = existingUserOptional.get();

            if (passwordEncoder.matches(userRecordDto.password(), user.getPassword())) {
                user.setOnOrOff(true);
                userRepository.save(user);
                return ResponseEntity.accepted().body(user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sua senha está errada.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidas.");
        }
    }

    @PostMapping("/logoff/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> deslogarUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UserEntity> isOnOptional = userRepository.findById(id);

        if (isOnOptional.isPresent()) {
            UserEntity user = isOnOptional.get();
            user.setOnOrOff(false);
            userRepository.save(user);
            return ResponseEntity.ok("Usuário deslogado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao deslogar usuário.");
        }
    }

    @PutMapping("user/{id}/amigos/{idAmizade}")
    public ResponseEntity<?> adicionarAmizade(@PathVariable(value = "idAmizade") UUID idAmizade, @PathVariable(value = "id") UUID idUsuario) {
        Optional<UserEntity> optionalUsuarioAtual = userRepository.findById(idUsuario);
        Optional<UserEntity> optionalNovoAmigo = userRepository.findById(idAmizade);

        if (optionalUsuarioAtual.isPresent() && optionalNovoAmigo.isPresent()) {
            UserEntity usuarioAtual = optionalUsuarioAtual.get();
            UserEntity novoAmigo = optionalNovoAmigo.get();

            usuarioAtual.addFriend(idAmizade.toString());
            userRepository.save(usuarioAtual);

            novoAmigo.addFriend(idUsuario.toString());
            userRepository.save(novoAmigo);

            return ResponseEntity.ok("Amizade adicionada com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuarios/{name}")
    public ResponseEntity<?> pesquisarUserPeloNome(@PathVariable(value = "name") String name) {
        Optional<UserEntity> nomeDePesquisa = userRepository.findByname(name);
        if (nomeDePesquisa.isPresent()) {
            UserEntity usuario = nomeDePesquisa.get();
            return ResponseEntity.ok().body(Collections.singletonList(usuario));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/usuarios")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<UserEntity>> obterTodosUsuarios() {
        List<UserEntity> listaDeUsuarios = userRepository.findAll();
        if (!listaDeUsuarios.isEmpty()) {
            for (UserEntity usuario : listaDeUsuarios) {
                UUID uuid = usuario.getId();

            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaDeUsuarios);
    }

    @GetMapping("/usuarios/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Object> obterUmUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UserEntity> usuario0 = userRepository.findById(id);
        if (usuario0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuario0.get());
    }

    @GetMapping("/usuario/{id}/amigos")
    public ResponseEntity<?> listarAmizade(@PathVariable(value = "id") UUID id) {
        Optional<UserEntity> optionalUsuarioAtual = userRepository.findById(id);
        if (optionalUsuarioAtual.isPresent()) {
            UserEntity usuarioAtual = optionalUsuarioAtual.get();
            List<String> amigos = usuarioAtual.getFriends();
            if (amigos != null && !amigos.isEmpty()) {
                // Retorna a lista de amigos do usuário
                return ResponseEntity.ok(amigos);
            } else {
                // Retorna uma mensagem indicando que o usuário não tem amigos
                return ResponseEntity.ok("O usuário não tem amigos.");
            }
        } else {
            // Retorna uma resposta indicando que o usuário não foi encontrado
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        UserEntity user = userOptional.get();
        BeanUtils.copyProperties(userRecordDto, user, "id");
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UserEntity> user0 = userRepository.findById(id);
        if (user0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        userRepository.delete(user0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado");
    }
}
