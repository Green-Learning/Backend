package com.GreenLearning.Greenlearning.controller;

import com.GreenLearning.Greenlearning.dto.SalaDTO;
import com.GreenLearning.Greenlearning.entity.Sala;
import com.GreenLearning.Greenlearning.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/sala")
public class SalaController {

    @Autowired
    public SalaService service;


    @PostMapping
    public ResponseEntity<Sala> cadastrar(@Valid @RequestBody final SalaDTO salaDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(salaDTO));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, " + e.getMessage());
        }
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<Sala> buscarPorId(@RequestParam("id") final Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Error, " + e.getMessage());
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Sala>> listar(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.listar());

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, " + e.getMessage());
        }
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<Sala> editar(@RequestParam("id") final Long id, @Valid @RequestBody final SalaDTO salaDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editar(id,salaDTO));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<String> deletar(@RequestParam("id") final Long id){
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Sala deletada com sucesso!");

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, não foi possivel localizar a sala informada");
        }
    }
}
