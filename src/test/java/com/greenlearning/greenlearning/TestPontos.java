package com.greenlearning.greenlearning;

import com.greenLearning.greenlearning.controller.PontosController;
import com.greenLearning.greenlearning.dto.PontosDTO;
import com.greenLearning.greenlearning.entity.*;
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

        User user = new User(1L,"pedrohenrique2023@gmail.com","123");
        Professor professor = new Professor(1L,user,"1 A");
        Sala sala = new Sala(1L,"1 A",professor);
        Aluno aluno = new Aluno(1L,"Pedro Henrique",2,sala);

        //BANCO DE DADOS
        Pontos pontuacao = new Pontos(1L,aluno,"JOGA O LIXO NA LATA",1000,true);

        //INSERÇÃO MANUAL PARA TESTAR CADASTRAR
        when(repository.save(Mockito.any(Pontos.class))).thenAnswer(invocation -> {
            Pontos pontoSalvo = invocation.getArgument(0);
            pontoSalvo.setId(1L);
            return pontoSalvo;
        });

        List<Pontos> pontos = new ArrayList<>();
        pontos.add(pontuacao);

        //TESTAR BUSCAR POR ID
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(pontuacao));

        //TESTAR LISTAR TODOS
        when(repository.findAll()).thenReturn(pontos);

        //TESTAR ATUALIZAR
        Pontos pontuacaoNova = new Pontos(1L,aluno,"JOGA O LIXO NA LATA",2000,true);
        when(repository.save(pontuacaoNova)).thenReturn(pontos.get(0));
    }

    @Test
    @DisplayName("Cadastrou pontuacao com sucesso!")
    void salvarTeste(){

        User user = new User(1L,"pedrohenrique2023@gmail.com","123");
        Professor professor = new Professor(1L,user,"1 A");
        Sala sala = new Sala(1L,"1 A",professor);
        Aluno aluno = new Aluno(1L,"Pedro Henrique",2,sala);

        var pontos = controller.cadastrar(new PontosDTO(1L,aluno,"JOGA O LIXO NA LATA",1000,true));

        Assertions.assertEquals(1L,pontos.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED,pontos.getStatusCode());
    }

    @Test
    @DisplayName("Buscou pontuacao por id")
    void buscarPorIdTest(){

        var pontos = controller.buscarPorId(1L);

        Assertions.assertEquals(1L, pontos.getBody().getId());
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

        User user = new User(1L,"pedrohenrique2023@gmail.com","123");
        Professor professor = new Professor(1L,user,"1 A");
        Sala sala = new Sala(1L,"1 A",professor);
        Aluno aluno = new Aluno(1L,"Pedro Henrique",2,sala);

        PontosDTO pontosDTO = new PontosDTO(1L,aluno,"JOGA O LIXO NA LATA",2000,true);

        var pontos = controller.editar(pontosDTO.getId(),pontosDTO);

        Assertions.assertEquals(HttpStatus.OK,pontos.getStatusCode());
        Assertions.assertEquals(2000, pontos.getBody().getScore());

    }
}
