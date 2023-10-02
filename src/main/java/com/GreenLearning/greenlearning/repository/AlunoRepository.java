package com.greenLearning.greenlearning.repository;

import com.greenLearning.greenlearning.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
