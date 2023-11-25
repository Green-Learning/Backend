package com.greenLearning.greenlearning.repository;

import com.greenLearning.greenlearning.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {

}
