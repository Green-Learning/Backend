package com.greenlearning.greenlearning;

import com.greenLearning.greenlearning.controller.PontosController;
import com.greenLearning.greenlearning.dto.PontosDTO;
import com.greenLearning.greenlearning.entity.*;
import com.greenLearning.greenlearning.entity.role.Roles;
import com.greenLearning.greenlearning.repository.PontosRepository;
import com.greenLearning.greenlearning.service.PontosService;
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
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
class TestPontos {

    @MockBean
    PontosRepository repository;

    @Autowired
    PontosController controller;

    @Autowired
    PontosService service;

    @BeforeEach
    void injectData(){

        UserEntity userEntity = new UserEntity(UUID.fromString("0afe7168-8c55-11ee-b9d1-0242ac120002"), "pedrohenrique2023@gmail.com", "123", Roles.DIRETOR);
        Professor professor = new Professor(UUID.fromString("573b9f4c-8c5a-11ee-b9d1-0242ac120002"), userEntity, "1 A");
        Sala sala = new Sala(UUID.fromString("6510ed20-8c5a-11ee-b9d1-0242ac120002"), "1 A", professor);
        Aluno aluno = new Aluno(UUID.fromString("6c47d374-8c5a-11ee-b9d1-0242ac120002"), "Pedro Henrique", 2, sala);

        //BANCO DE DADOS
        Pontos pontuacao = new Pontos(UUID.fromString("4688a432-8c5b-11ee-b9d1-0242ac120002"),aluno,"JOGA O LIXO NA LATA",1000,true);

        //INSERÇÃO MANUAL PARA TESTAR CADASTRAR
        when(repository.save(Mockito.any(Pontos.class))).thenAnswer(invocation -> {
            Pontos pontoSalvo = invocation.getArgument(0);
            pontoSalvo.setId(UUID.fromString("4688a432-8c5b-11ee-b9d1-0242ac120002"));
            return pontoSalvo;
        });

        List<Pontos> pontos = new ArrayList<>();
        pontos.add(pontuacao);

        //TESTAR BUSCAR POR ID
        Long id = 1L;
        when(repository.findById(UUID.fromString("4688a432-8c5b-11ee-b9d1-0242ac120002"))).thenReturn(Optional.of(pontuacao));

        //TESTAR LISTAR TODOS
        when(repository.findAll()).thenReturn(pontos);

        //TESTAR ATUALIZAR
        Pontos pontuacaoNova = new Pontos(UUID.fromString("4688a432-8c5b-11ee-b9d1-0242ac120002"),aluno,"JOGA O LIXO NA LATA",2000,true);
        when(repository.save(pontuacaoNova)).thenReturn(pontos.get(0));
    }

    @Test
    @DisplayName("Cadastrou pontuacao com sucesso!")
    void salvarTeste(){

        UserEntity userEntity = new UserEntity(UUID.fromString("0afe7168-8c55-11ee-b9d1-0242ac120002"), "pedrohenrique2023@gmail.com", "123", Roles.DIRETOR);
        Professor professor = new Professor(UUID.fromString("573b9f4c-8c5a-11ee-b9d1-0242ac120002"), userEntity, "1 A");
        Sala sala = new Sala(UUID.fromString("6510ed20-8c5a-11ee-b9d1-0242ac120002"), "1 A", professor);
        Aluno aluno = new Aluno(UUID.fromString("6c47d374-8c5a-11ee-b9d1-0242ac120002"), "Pedro Henrique", 2, sala);

        var pontos = controller.cadastrar(new PontosDTO(UUID.fromString("4688a432-8c5b-11ee-b9d1-0242ac120002"),aluno,"JOGA O LIXO NA LATA",1000,true));

        Assertions.assertEquals(UUID.fromString("4688a432-8c5b-11ee-b9d1-0242ac120002"),pontos.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED,pontos.getStatusCode());
    }

    @Test
    @DisplayName("Buscou pontuacao por id")
    void buscarPorIdTest(){

        var pontos = controller.buscarPorId(UUID.fromString("4688a432-8c5b-11ee-b9d1-0242ac120002"));

        Assertions.assertEquals("JOGA O LIXO NA LATA", pontos.getBody().getJogo());
        Assertions.assertEquals(HttpStatus.OK,pontos.getStatusCode());
    }

    @Test
    @DisplayName("Listar todos os pontos")
    void listarTodosTest(){

        var pontuacao = controller.listar();
        List<Pontos> pontos = pontuacao.getBody();

        Assertions.assertEquals(HttpStatus.OK, pontuacao.getStatusCode());
        Assertions.assertEquals("JOGA O LIXO NA LATA", pontos.get(0).getJogo());
        Assertions.assertEquals(1,pontos.size());
    }

    @Test
    @DisplayName("Editou a pontuacao com sucesso!")
    void atualizarTeste(){

        UserEntity userEntity = new UserEntity(UUID.fromString("0afe7168-8c55-11ee-b9d1-0242ac120002"), "pedrohenrique2023@gmail.com", "123", Roles.DIRETOR);
        Professor professor = new Professor(UUID.fromString("573b9f4c-8c5a-11ee-b9d1-0242ac120002"), userEntity, "1 A");
        Sala sala = new Sala(UUID.fromString("6510ed20-8c5a-11ee-b9d1-0242ac120002"), "1 A", professor);
        Aluno aluno = new Aluno(UUID.fromString("6c47d374-8c5a-11ee-b9d1-0242ac120002"), "Pedro Henrique", 2, sala);

        PontosDTO pontosDTO = new PontosDTO(UUID.fromString("4688a432-8c5b-11ee-b9d1-0242ac120002"),aluno,"JOGA O LIXO NA LATA",2000,true);

        var pontos = controller.editar(pontosDTO.id(),pontosDTO);

        Assertions.assertEquals(HttpStatus.OK,pontos.getStatusCode());
        Assertions.assertEquals(2000, pontos.getBody().getScore());

    }
}