package com.GreenLearning.greenlearning.service;

import com.GreenLearning.greenlearning.dto.SalaDTO;
import com.GreenLearning.greenlearning.entity.Sala;
import com.GreenLearning.greenlearning.repository.SalaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    public SalaRepository repository;

    @Transactional
    public Sala cadastrar(SalaDTO salaDTO){
        Sala sala = new Sala();

        BeanUtils.copyProperties(salaDTO,sala);

        return repository.save(sala);
    }

    public Sala buscarPorId(Long id) {
        Optional<Sala> sala = repository.findById(id);
        if (!sala.isPresent()) {
            throw new RuntimeException("não foi possivel localizar a sala informada!");

        } else {
            return sala.get();
        }
    }

    public List<Sala> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException("não foi possivel localizar nenhuma sala cadastrada!");

        } else {
            return repository.findAll();
        }
    }

    @Transactional
    public Sala editar(Long id, SalaDTO salaNovo){
        Sala sala = this.buscarPorId(id);

        if(!sala.getId().equals(salaNovo.getId())){
            throw new RuntimeException("Não foi possivel localizar a sala informada!");
        }

        sala.setNome(salaNovo.getNome());
        sala.setProfessor(salaNovo.getProfessor());

        return repository.save(sala);
    }

    @Transactional
    public void delete(Long id){
        Optional<Sala> sala = repository.findById(id);

        repository.delete(sala.get());
    }

    public Boolean existsByNome(String nome){
        return repository.existsByNome(nome);
    }
}
