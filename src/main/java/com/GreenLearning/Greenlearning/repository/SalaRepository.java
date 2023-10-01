package com.GreenLearning.Greenlearning.repository;

import com.GreenLearning.Greenlearning.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    boolean existsByNome(String nome);
}
