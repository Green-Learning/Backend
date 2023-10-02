package com.GreenLearning.greenlearning.dto;

import com.GreenLearning.greenlearning.entity.Professor;
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

    public SalaDTO(Long id, String nome, Professor professor) {
        super(id);
        this.nome = nome;
        this.professor = professor;
    }
}
