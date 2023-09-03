package com.GreenLearning.Greenlearning.dto;

import com.GreenLearning.Greenlearning.entity.Sala;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class AlunoDTO extends AbstractEntityDTO {

    @Getter @Setter
    @NotBlank(message = "Nome é um campo obrigatorio!")
    @Size(min = 3, max = 80, message = "Nome deve conter de 3 a 80 caracteres!")
    private String nome;

    @Getter @Setter
    @Min(value = 4, message = "Idade é um campo obrigatorio, e devem ter no mínimo 4 anos!")
    private Integer idade;

    @Getter @Setter
    private Sala sala;
}
