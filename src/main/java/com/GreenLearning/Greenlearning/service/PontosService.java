package com.GreenLearning.Greenlearning.service;

import com.GreenLearning.Greenlearning.dto.PontosDTO;
import com.GreenLearning.Greenlearning.entity.Pontos;
import com.GreenLearning.Greenlearning.repository.PontosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PontosService {

    @Autowired
    public PontosRepository repository;

    @Transactional
    public Pontos cadastrar(PontosDTO pontosDTO){
        Pontos pontos = new Pontos();

        BeanUtils.copyProperties(pontosDTO,pontos);

        return repository.save(pontos);
    }

    public Pontos buscarPorId(Long id) {
        Optional<Pontos> pontos = repository.findById(id);
        if (!pontos.isPresent()) {
            throw new RuntimeException("não foi possivel acessar a pontuação informada!");

        } else {
            return pontos.get();
        }
    }

    public List<Pontos> listar() {
        if (repository.findAll().isEmpty()) {
            throw new RuntimeException("não foi possivel localizar nenhum historio de pontuação cadastrado no banco!");

        } else {
            return repository.findAll();
        }
    }

    @Transactional
    public Pontos editar(Long id, PontosDTO pontosNovo){
        Pontos pontos = this.buscarPorId(id);

        if (!pontos.getId().equals(pontosNovo.getId())) {
            throw new RuntimeException("Não foi possivel localizar o historico de pontos informado!");

        }

        pontos.setScore(pontosNovo.getScore());

        return repository.save(pontos);
    }
}
