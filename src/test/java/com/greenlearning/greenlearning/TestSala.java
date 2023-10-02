package com.greenlearning.greenlearning;

import com.greenlearning.greenlearning.controller.SalaController;
import com.greenlearning.greenlearning.dto.SalaDTO;
import com.greenlearning.greenlearning.entity.Professor;
import com.greenlearning.greenlearning.entity.Sala;
import com.greenlearning.greenlearning.entity.User;
import com.greenlearning.greenlearning.repository.SalaRepository;
import com.greenlearning.greenlearning.service.SalaService;
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
class TestSala {

    @MockBean
    SalaRepository repository;

    @Autowired
    SalaController controller;

    @Autowired
    SalaService service;

    @BeforeEach
    void injectData(){

        User user = new User(1L,"pedrohenrique2023@gmail.com","123");
        Professor professor = new Professor(1L,user,"1 A");

        //BANCO DE DADOS
        Sala sala = new Sala(1L,"1 A",professor);

        //INSERÇÃO MANUAL PARA TESTAR CADASTRAR
        when(repository.save(Mockito.any(Sala.class))).thenAnswer(invocation -> {
            Sala salaSalva = invocation.getArgument(0);
            salaSalva.setId(1L);
            return salaSalva;
        });

        List<Sala> salas = new ArrayList<>();
        salas.add(sala);

        //TESTAR BUSCAR POR ID
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(sala));

        //TESTAR LISTAR TODOS
        when(repository.findAll()).thenReturn(salas);

        //TESTAR ATUALIZAR
        Sala salaNova = new Sala(1L,"1 B",professor);
        when(repository.save(salaNova)).thenReturn(salas.get(0));
    }

    @Test
    @DisplayName("Cadastrou sala com sucesso!")
    void salvarTeste(){

        User user = new User(1L,"pedrohenrique2023@gmail.com","123");
        Professor professor = new Professor(1L,user,"1 A");

        var sala = controller.cadastrar(new SalaDTO(1L,"1 A",professor));

        Assertions.assertEquals(1L,sala.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED,sala.getStatusCode());
    }

    @Test
    @DisplayName("Buscou sala por id")
    void buscarPorIdTest(){

        var professor = controller.buscarPorId(1L);

        Assertions.assertEquals(1L, professor.getBody().getId());
        Assertions.assertEquals(HttpStatus.OK,professor.getStatusCode());
    }

    @Test
    @DisplayName("Listar todas os salas")
    void listarTodosTest(){

        var sala = controller.listar();
        List<Sala> salas = sala.getBody();

        Assertions.assertEquals(HttpStatus.OK, sala.getStatusCode());
        Assertions.assertEquals("1 A", salas.get(0).getNome());
        Assertions.assertEquals(1,salas.size());
    }

    @Test
    @DisplayName("Editou o sala com sucesso!")
    void atualizarTeste(){

        User user = new User(1L,"pedrohenrique2023@gmail.com","123");
        Professor professor = new Professor(1L,user,"1 A");

        //BANCO DE DADOS
        SalaDTO salaDTO = new SalaDTO(1L,"1 B",professor);

        var professorNovo = controller.editar(salaDTO.getId(),salaDTO);

        Assertions.assertEquals(HttpStatus.OK,professorNovo.getStatusCode());
        Assertions.assertEquals("1 B", professorNovo.getBody().getNome());

    }
}
