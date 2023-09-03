package com.GreenLearning.Greenlearning.controller;

import com.GreenLearning.Greenlearning.dto.PontosDTO;
import com.GreenLearning.Greenlearning.service.PontosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
    {
        "id": 1,
        "cadastro": "2023-09-02T23:24:28.773427",
        "edicao": "2023-09-02T23:25:54.768124",
        "aluno": {
            "id": 1,
            "cadastro": "2023-09-02T23:23:10.360371",
            "edicao": "2023-09-02T23:24:12.228341",
            "nome": "Felinha",
            "idade": 4,
            "sala": {
                "id": 1,
                "cadastro": "2023-09-02T23:23:03.762258",
                "edicao": null,
                "nome": "Maternal 1",
                "professor": {
                    "id": 1,
                    "cadastro": "2023-09-02T23:22:56.94624",
                    "edicao": null,
                    "usuario": {
                        "id": 1,
                        "cadastro": "2023-09-02T23:22:41.872444",
                        "edicao": null,
                        "email": "pedrohenri1606@gmail.com",
                        "senha": "123"
                    },
                    "nome": "Pedro Henrique"
                }
            }
        },
        "jogo": "Jhonson Adventure",
        "score": 1000,
        "maior": true
    }
 */

@RestController
@RequestMapping(value = "/pontos")
public class PontosController {

    @Autowired
    private PontosService service;

    @PostMapping
    private ResponseEntity<Object> cadastrar(@Valid @RequestBody final PontosDTO pontosDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(pontosDTO));

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
    private ResponseEntity<Object> editar(@RequestParam("id") final Long id, @Valid @RequestBody final PontosDTO pontosDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editar(id,pontosDTO));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, " + e.getMessage());
        }
    }
}
