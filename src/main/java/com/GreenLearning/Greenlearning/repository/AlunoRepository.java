package com.GreenLearning.Greenlearning.repository;

import com.GreenLearning.Greenlearning.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
