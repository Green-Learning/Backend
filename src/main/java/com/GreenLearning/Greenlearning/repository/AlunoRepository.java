package com.GreenLearning.Greenlearning.repository;

import com.GreenLearning.Greenlearning.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
