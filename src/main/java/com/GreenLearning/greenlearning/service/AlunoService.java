package com.GreenLearning.greenlearning.service;

import com.GreenLearning.greenlearning.dto.AlunoDTO;
import com.GreenLearning.greenlearning.entity.Aluno;
import com.GreenLearning.greenlearning.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    public AlunoRepository repository;

    @Transactional
    public Aluno cadastrar(AlunoDTO alunoDTO){
        Aluno aluno = new Aluno();

        BeanUtils.copyProperties(alunoDTO,aluno);

        return repository.save(aluno);
    }

    public Aluno buscarPorId(Long id){
        Optional<Aluno> alunoOptional = repository.findById(id);
        /*
        if (!alunoOptional.isPresent()){
            throw new RuntimeException("Aluno informado não foi localizado!");
        }
        */
        Assert.isTrue(alunoOptional.isEmpty(), "Aluno informado nao foi localizado!");
        return alunoOptional.get();
    }

    public List<Aluno> listar(){

        Assert.isTrue(repository.findAll().isEmpty(), "não foi possivel localizar nenhum aluno cadastrado!");
        /*
        if (repository.findAll().isEmpty()){
            throw new RuntimeException("não foi possivel localizar nenhum aluno cadastrado!");

        } else {
            return repository.findAll();
        }*/

        return repository.findAll();

    }

    @Transactional
    public Aluno editar(Long id, AlunoDTO alunoNovo){
        Aluno aluno = this.buscarPorId(id);

       /* if (!aluno.getId().equals(alunoNovo.getId())) {
            throw new RuntimeException("Não foi possivel localizar o aluno informado!");
        }
        */

        Assert.isTrue(aluno !=null,"Não foi possivel localizar o aluno informado!");

        aluno.setNome(alunoNovo.getNome());
        aluno.setIdade(alunoNovo.getIdade());

        return repository.save(aluno);
    }

    @Transactional
    public void delete(Long id){
        Aluno aluno = this.buscarPorId(id);

        repository.delete(aluno);
    }
}

