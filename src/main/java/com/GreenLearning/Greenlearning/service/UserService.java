package com.GreenLearning.Greenlearning.service;

import com.GreenLearning.Greenlearning.dto.UserDTO;
import com.GreenLearning.Greenlearning.entity.User;
import com.GreenLearning.Greenlearning.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository repository;

    @Transactional
    public User cadastrar(UserDTO userDTO){

        User user = new User();

        BeanUtils.copyProperties(userDTO,user);

        return repository.save(user);
    }

    public User buscarPorId(Long id){
        Optional<User> user = repository.findById(id);
        if(!user.isPresent()){
            throw new RuntimeException("não foi possivel localizar o user informado!");
            
        } else {
            return user.get();
        }
    }

    public List<User> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException("não foi possivel localizar nenhum usuario cadastrado!");

        } else {
            return repository.findAll();
        }
    }

    @Transactional
    public User editar(Long id, UserDTO userNovo){
        User user = this.buscarPorId(id);

        if(!userNovo.getId().equals(user.getId())){
            throw new RuntimeException("Não foi possivel localizar o usuario informado!");
        }

        user.setEmail(userNovo.getEmail());
        user.setSenha(userNovo.getSenha());

        return repository.save(user);
    }

    public Boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }
}
