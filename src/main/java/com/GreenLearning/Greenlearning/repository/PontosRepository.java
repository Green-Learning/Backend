package com.GreenLearning.Greenlearning.repository;

import com.GreenLearning.Greenlearning.entity.Pontos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PontosRepository extends JpaRepository<Pontos, Long> {
}
