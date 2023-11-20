package com.greenLearning.greenlearning.dto;

import com.greenLearning.greenlearning.entity.Aluno;

public record PontosDTO(Aluno aluno, String jogo, Integer score, Boolean maior) {}
