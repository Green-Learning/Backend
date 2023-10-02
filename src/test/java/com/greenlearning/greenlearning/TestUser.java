package com.greenlearning.greenlearning;

import com.greenLearning.greenlearning.controller.UserController;
import com.greenLearning.greenlearning.dto.UserDTO;
import com.greenLearning.greenlearning.entity.User;
import com.greenLearning.greenlearning.repository.UserRepository;
import com.greenLearning.greenlearning.service.UserService;
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
class TestUser {

    @MockBean
    UserRepository repository;

    @Autowired
    UserController controller;

    @Autowired
    UserService service;

    @BeforeEach
    void injectData(){

        //BANCO DE DADOS
        User user = new User(1L,"pedrohenrique2023@gmail.com","123");

        //INSERÇÃO MANUAL PARA TESTAR CADASTRAR
        when(repository.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            User userSalvo = invocation.getArgument(0);
            userSalvo.setId(1L);
            return userSalvo;
        });

        List<User> users = new ArrayList<>();
        users.add(user);

        //TESTAR BUSCAR POR ID
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(user));

        //TESTAR LISTAR TODOS
        when(repository.findAll()).thenReturn(users);

        //TESTAR ATUALIZAR
        User userNovo = new User(1L,"pedrohenrique2023@gmail.com","123456789");
        when(repository.save(userNovo)).thenReturn(users.get(0));
    }

    @Test
    @DisplayName("Cadastrou user com sucesso!")
    void salvarTeste(){

        var user = controller.cadastrar(new UserDTO(1L,"pedrohenrique2023@gmail.com","123"));

        Assertions.assertEquals(1L,user.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED,user.getStatusCode());
    }

    @Test
    @DisplayName("Buscou user por id")
    void buscarPorIdTest(){

        var user = controller.buscarPorId(1L);

        Assertions.assertEquals(1L, user.getBody().getId());
        Assertions.assertEquals(HttpStatus.OK,user.getStatusCode());
    }

    @Test
    @DisplayName("Listar todas os users")
    void listarTodosTest(){

        var user = controller.listar();
        List<User> users = user.getBody();

        Assertions.assertEquals(HttpStatus.OK, user.getStatusCode());
        Assertions.assertEquals("pedrohenrique2023@gmail.com", users.get(0).getEmail());
        Assertions.assertEquals(1,users.size());
    }

    @Test
    @DisplayName("Editou o user com sucesso!")
    void atualizarTeste(){

        UserDTO userDTO = new UserDTO(1L,"pedrohenrique2023@gmail.com","123456789");

        var professorNovo = controller.editar(userDTO.getId(),userDTO);

        Assertions.assertEquals(HttpStatus.OK,professorNovo.getStatusCode());
        Assertions.assertEquals("123456789", professorNovo.getBody().getSenha());

    }
}
