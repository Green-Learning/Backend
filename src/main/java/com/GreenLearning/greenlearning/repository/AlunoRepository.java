package com.GreenLearning.greenlearning.repository;

import com.GreenLearning.greenlearning.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
