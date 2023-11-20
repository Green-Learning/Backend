package com.greenLearning.greenlearning.dto;

import com.greenLearning.greenlearning.entity.UserEntity;

public record ProfessorDTO(UserEntity usuario, String nome) {}
