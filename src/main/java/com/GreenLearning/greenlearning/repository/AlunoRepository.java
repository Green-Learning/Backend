package com.greenlearning.greenlearning.repository;

import com.greenlearning.greenlearning.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
