package com.greenlearning.greenlearning.service;

import com.greenlearning.greenlearning.dto.AlunoDTO;
import com.greenlearning.greenlearning.entity.Aluno;
import com.greenlearning.greenlearning.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;

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

        if(alunoOptional.isEmpty()){
            Assert.isTrue(alunoOptional.isEmpty(), "Aluno informado nao foi localizado!");
            throw new NotFoundException("erro!!!");
        }
        return alunoOptional.get();
    }

    public List<Aluno> listar(){

        List<Aluno> alunos = repository.findAll();
        Assert.isTrue(alunos !=null, "não foi possivel localizar nenhum aluno cadastrado!");
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
        /*
        if (!aluno.getId().equals(alunoNovo.getId())) {
            throw new RuntimeException("Não foi possivel localizar o aluno informado!");
        }*/


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

