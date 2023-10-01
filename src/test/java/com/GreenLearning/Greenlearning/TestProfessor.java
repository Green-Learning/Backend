package com.GreenLearning.Greenlearning;

import com.GreenLearning.Greenlearning.controller.ProfessorController;
import com.GreenLearning.Greenlearning.dto.ProfessorDTO;
import com.GreenLearning.Greenlearning.entity.*;
import com.GreenLearning.Greenlearning.repository.ProfessorRepository;
import com.GreenLearning.Greenlearning.service.ProfessorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class TestProfessor {


    @MockBean
    ProfessorRepository repository;

    @Autowired
    ProfessorController controller;

    @Autowired
    ProfessorService service;

    @BeforeEach
    void injectData(){

        User user = new User(1L,"pedrohenrique2023@gmail.com","123");

        //BANCO DE DADOS
        Professor professor = new Professor(1L,user,"Pedro Henrique");

        //INSERÇÃO MANUAL PARA TESTAR CADASTRAR
        when(repository.save(Mockito.any(Professor.class))).thenAnswer(invocation -> {
            Professor professorSalvo = invocation.getArgument(0);
            professorSalvo.setId(1L);
            return professorSalvo;
        });

        List<Professor> professores = new ArrayList<>();
        professores.add(professor);

        //TESTAR BUSCAR POR ID
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(professor));

        //TESTAR LISTAR TODOS
        when(repository.findAll()).thenReturn(professores);

        //TESTAR ATUALIZAR
        Professor professorNovo = new Professor(1L,user,"Jhonson e Jhonson");
        when(repository.save(professorNovo)).thenReturn(professores.get(0));
    }

    @Test
    @DisplayName("Cadastrou professor com sucesso!")
    void salvarTeste(){

        User user = new User(1L,"pedrohenrique2023@gmail.com","123");

        var professor = controller.cadastrar(new ProfessorDTO(1L,user,"Pedro Henrique"));

        Assertions.assertEquals(1L,professor.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED,professor.getStatusCode());
    }

    @Test
    @DisplayName("Buscou professor por id")
    void buscarPorIdTest(){

        var professor = controller.buscarPorId(1L);

        Assertions.assertEquals(1L, professor.getBody().getId());
        Assertions.assertEquals(HttpStatus.OK,professor.getStatusCode());
    }

    @Test
    @DisplayName("Listar todos os professores")
    void listarTodosTest(){

        var professor = controller.listar();
        List<Professor> professores = professor.getBody();

        Assertions.assertEquals(HttpStatus.OK, professor.getStatusCode());
        Assertions.assertEquals("Pedro Henrique", professores.get(0).getNome());
        Assertions.assertEquals(1,professores.size());
    }

    @Test
    @DisplayName("Editou o professor com sucesso!")
    void atualizarTeste(){

        User user = new User(1L,"pedrohenrique2023@gmail.com","123");
        ProfessorDTO professorDTO = new ProfessorDTO(1L,user,"Jhonson e Jhonson");

        var professorNovo = controller.editar(professorDTO.getId(),professorDTO);

        Assertions.assertEquals(HttpStatus.OK,professorNovo.getStatusCode());
        Assertions.assertEquals("Jhonson e Jhonson", professorNovo.getBody().getNome());

    }
}
