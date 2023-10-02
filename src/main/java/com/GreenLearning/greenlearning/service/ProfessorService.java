package com.greenlearning.greenlearning.service;

import com.greenlearning.greenlearning.dto.ProfessorDTO;
import com.greenlearning.greenlearning.entity.Professor;
import com.greenlearning.greenlearning.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    public ProfessorRepository repository;

    @Transactional
    public Professor cadastrar(ProfessorDTO professorDTO) {
        Professor professor = new Professor();

        BeanUtils.copyProperties(professorDTO, professor);

        return repository.save(professor);
    }

    public Professor buscarPorId(Long id) {
        Optional<Professor> professor = repository.findById(id);

        if (professor.isEmpty()){
            Assert.isTrue(professor.isEmpty(), "Professor informado não foi localizado!");
            throw new NotFoundException("erro!!!");
        }
        return professor.get();
    }

    public List<Professor> listar() {
        /*
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException("não foi possivel localizar nenhum professor cadastrado!");

        } else {
            return repository.findAll();
        }
         */
        List<Professor> professors = repository.findAll();
        Assert.isTrue(professors !=null, "não foi possivel localizar nenhum professor cadastrado!");
        return repository.findAll();
    }


    @Transactional
    public Professor editar(Long id, ProfessorDTO professorNovo) {
        Professor professor = this.buscarPorId(id);
        /*
        if (!professor.getId().equals(professorNovo.getId())) {
            throw new RuntimeException("Não foi possivel localizar o professor informado!");
        }
          */

        Assert.isTrue(professor !=null, "Não foi possivel localizar o professor informado!");
        professor.setNome(professorNovo.getNome());

        return repository.save(professor);
    }
}
