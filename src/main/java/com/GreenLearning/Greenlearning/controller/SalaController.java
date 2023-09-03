package com.GreenLearning.Greenlearning.controller;

import com.GreenLearning.Greenlearning.dto.SalaDTO;
import com.GreenLearning.Greenlearning.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
    {
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
 */

@RestController
@RequestMapping(value = "/sala")
public class SalaController {

    @Autowired
    private SalaService service;


    @PostMapping
    private ResponseEntity<Object> cadastrar(@Valid @RequestBody final SalaDTO salaDTO){
        try {

            if(service.existsByNome(salaDTO.getNome())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error, nome escolhido já possui cadastro!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(salaDTO));

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
    private ResponseEntity<Object> editar(@RequestParam("id") final Long id, @Valid @RequestBody final SalaDTO salaDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editar(id,salaDTO));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<Object> deletar(@RequestParam("id") final Long id){
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Sala deletada com sucesso!");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, não foi possivel localizar a sala informada");
        }
    }
}
