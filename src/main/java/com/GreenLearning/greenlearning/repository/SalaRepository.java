package com.GreenLearning.greenlearning.repository;

import com.GreenLearning.greenlearning.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    boolean existsByNome(String nome);
}
