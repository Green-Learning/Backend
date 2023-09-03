package com.GreenLearning.Greenlearning.service;

import com.GreenLearning.Greenlearning.dto.AlunoDTO;
import com.GreenLearning.Greenlearning.entity.Aluno;
import com.GreenLearning.Greenlearning.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Transactional
    public Aluno cadastrar(AlunoDTO alunoDTO){
        Aluno aluno = new Aluno();

        BeanUtils.copyProperties(alunoDTO,aluno);

        return repository.save(aluno);
    }

    public Aluno buscarPorId(Long id){
        Optional<Aluno> alunoOptional = repository.findById(id);
        if (!alunoOptional.isPresent()){
            throw new RuntimeException("Aluno informado não foi localizado!");
        }

        return alunoOptional.get();
    }

    public List<Aluno> listar(){
        if (repository.findAll().isEmpty()){
            throw new RuntimeException("não foi possivel localizar nenhum aluno cadastrado!");
        } else {
            return repository.findAll();
        }
    }

    @Transactional
    public Aluno editar(Long id, AlunoDTO alunoNovo){
        Aluno aluno = this.buscarPorId(id);

        if (!aluno.getId().equals(alunoNovo.getId())) {
            throw new RuntimeException("Não foi possivel localizar o aluno informado!");
        }

        aluno.setNome(alunoNovo.getNome());
        aluno.setIdade(alunoNovo.getIdade());

        return repository.save(aluno);
    }

    @Transactional
    public void delete(Long id){
        Optional<Aluno> aluno = repository.findById(id);

        repository.delete(aluno.get());
    }
}

