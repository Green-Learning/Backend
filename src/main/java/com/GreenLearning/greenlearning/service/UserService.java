package com.greenLearning.greenlearning.service;

import com.greenLearning.greenlearning.dto.UserEntityDTO;
import com.greenLearning.greenlearning.entity.UserEntity;
import com.greenLearning.greenlearning.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Transactional
    public UserEntity cadastrar(UserEntityDTO userEntityDTO){
        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(userEntityDTO, userEntity);

        return repository.save(userEntity);
    }

    public UserEntity buscarPorId(UUID id){
        Optional<UserEntity> user = repository.findById(id);

        if (user.isEmpty()){
            Assert.isTrue(user.isEmpty(), "não foi possivel localizar o user informado!");
            throw new NotFoundException("erro!!!");
        }
        return user.get();
    }

    public List<UserEntity> listar() {
        List<UserEntity> userEntities = repository.findAll();

        Assert.isTrue(userEntities !=null, "não foi possivel localizar nenhum usuario cadastrado!");

        return repository.findAll();
    }

    @Transactional
    public UserEntity editar(UUID id, UserEntityDTO userNovo){
        UserEntity userEntity = this.buscarPorId(id);

        Assert.isTrue(userEntity !=null, "Não foi possivel localizar o usuario informado!");

        userEntity.setUsername(userNovo.username());
        userEntity.setPassword(userNovo.password());

        return repository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userEntity = repository.findByUsername(username);

        return new User(userEntity.getUsername(),userEntity.getPassword(), true,true,true,true,userEntity.getAuthorities());
    }
}
