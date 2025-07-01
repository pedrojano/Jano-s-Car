// src/main/java/com/janoscars/janoscars/controller/UserController.java
package com.janoscars.janoscars.controller;

import com.janoscars.janoscars.model.User;
import com.janoscars.janoscars.service.UserService;


// import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // Define que esta classe é um controlador REST
@RequestMapping("/api/users") // Define o caminho base para os endpoints deste controlador
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint para criar um novo usuário
    // POST /api/users
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // Retorna 201 Created
        } catch(IllegalArgumentException e) {
            // Se o username ou email já existirem, retorna 400 Bad Request
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para obter um usuário por ID (ADICIONADO)
    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Endpoint para obter todos os usuários
    // GET /api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Endpoint para atualizar um usuário existente
    // PUT /api/users/{id}
    @PutMapping("/{id}")
    // CORREÇÃO: Era @PatchVariable, o correto é @PathVariable
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            // CORREÇÃO: Era userSevice (minúsculo), o correto é userService (o nome da sua instância)
            User updatedUser = userService.updateUser(id, userDetails);
            return new ResponseEntity<>(updatedUser , HttpStatus.OK); // Retorna 200 OK
        } catch (IllegalArgumentException e) { // Captura a exceção de validação (username/email duplicado na atualização)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Retorna 400 Bad Request
        } catch (RuntimeException e) { // Captura a exceção de "User not found"
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);// Retorna 404 Not Found
        }
    }

    // Endpoint para deletar um usuario
    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);// Retorna 404 Not Found
        }
    }
}