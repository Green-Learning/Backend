package com.GreenLearning.Greenlearning.controller;

import com.GreenLearning.Greenlearning.dto.UserDTO;
import com.GreenLearning.Greenlearning.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
    {
        "id": 1,
        "cadastro": "2023-09-02T23:22:41.872444",
        "edicao": null,
        "email": "pedrohenri1606@gmail.com",
        "senha": "123"
    }
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    public UserService service;

    @PostMapping
    private ResponseEntity<Object> cadastrar(@Valid @RequestBody final UserDTO userDTO){
        try {

            if(service.existsByEmail(userDTO.getEmail())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error, email informado já possui cadastro!");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(userDTO));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, " + e.getMessage());
        }
    }

    @GetMapping(value = "/buscar")
    private ResponseEntity<Object> buscarPorId(@RequestParam("id") final Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, " + e.getMessage());
        }
    }

    @GetMapping(value = "/listar")
    private ResponseEntity<Object> listar(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.listar());

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, " + e.getMessage());
        }
    }

    @PutMapping(value = "/editar")
    private ResponseEntity<Object> editar(@RequestParam("id") final Long id, @Valid @RequestBody final UserDTO userNovo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editar(id,userNovo));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, " +e.getMessage());
        }
    }
}
