package com.GreenLearning.Greenlearning.controller;

import com.GreenLearning.Greenlearning.dto.UserDTO;
import com.GreenLearning.Greenlearning.entity.User;
import com.GreenLearning.Greenlearning.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    public UserService service;

    @PostMapping
    private ResponseEntity<User> cadastrar(@Valid @RequestBody final UserDTO userDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(userDTO));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, " + e.getMessage());
        }
    }

    @GetMapping(value = "/buscar")
    private ResponseEntity<User> buscarPorId(@RequestParam("id") final Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, " + e.getMessage());
        }
    }

    @GetMapping(value = "/listar")
    private ResponseEntity<List<User>> listar(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.listar());

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Error, " + e.getMessage());
        }
    }

    @PutMapping(value = "/editar")
    private ResponseEntity<User> editar(@RequestParam("id") final Long id, @Valid @RequestBody final UserDTO userNovo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editar(id,userNovo));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error, " + e.getMessage());
        }
    }
}
