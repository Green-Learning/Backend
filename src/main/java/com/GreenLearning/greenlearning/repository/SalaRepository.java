package com.greenlearning.greenlearning.repository;

import com.greenlearning.greenlearning.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    boolean existsByNome(String nome);
}
