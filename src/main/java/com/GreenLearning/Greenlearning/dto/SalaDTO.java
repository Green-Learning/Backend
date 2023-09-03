package com.GreenLearning.Greenlearning.dto;

import com.GreenLearning.Greenlearning.entity.Professor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class SalaDTO extends AbstractEntityDTO{

    @Getter @Setter
    @NotBlank(message = "Nome é um campo obrigatorio!")
    @Size(max = 25, message = "Nome deve conter até 25 caracteres!")
    private String nome;

    @Getter @Setter
    private Professor professor;
}
