package com.greenlearning.greenlearning.controller;

import com.greenlearning.greenlearning.dto.ProfessorDTO;
import com.greenlearning.greenlearning.entity.Professor;
import com.greenlearning.greenlearning.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/professor")
public class ProfessorController {

    @Autowired
    public ProfessorService service;

    @PostMapping
    public ResponseEntity<Professor> cadastrar(@Valid @RequestBody final ProfessorDTO professorDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(professorDTO));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error cadastrar, " + e.getMessage());
        }
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<Professor> buscarPorId(@RequestParam("id") final Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error buscar, " + e.getMessage());
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Professor>> listar(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.listar());

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Error listar, " + e.getMessage());
        }
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<Professor> editar(@RequestParam("id") final Long id, @Valid @RequestBody final ProfessorDTO professorNovo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editar(id,professorNovo));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error editar, " + e.getMessage());
        }
    }
}
