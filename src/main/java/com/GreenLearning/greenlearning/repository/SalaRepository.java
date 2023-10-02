package com.greenLearning.greenlearning.repository;

import com.greenLearning.greenlearning.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    boolean existsByNome(String nome);
}
