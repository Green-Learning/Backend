package com.GreenLearning.Greenlearning.repository;

import com.GreenLearning.Greenlearning.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
