package com.GreenLearning.Greenlearning.service;

import com.GreenLearning.Greenlearning.dto.ProfessorDTO;
import com.GreenLearning.Greenlearning.entity.Professor;
import com.GreenLearning.Greenlearning.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (!professor.isPresent()) {
            throw new RuntimeException("Professor informado não foi localizado!");

        } else {
            return professor.get();
        }
    }

    public List<Professor> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException("não foi possivel localizar nenhum professor cadastrado!");

        } else {
            return repository.findAll();
        }
    }


    @Transactional
    public Professor editar(Long id, ProfessorDTO professorNovo) {
        Professor professor = this.buscarPorId(id);

        if (!professor.getId().equals(professorNovo.getId())) {
            throw new RuntimeException("Não foi possivel localizar o professor informado!");
        }

        professor.setNome(professorNovo.getNome());

        return repository.save(professor);
    }
}
