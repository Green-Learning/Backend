package com.greenLearning.greenlearning.dto;

import com.greenLearning.greenlearning.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class ProfessorDTO extends AbstractEntityDTO{

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @Getter @Setter
    @NotBlank(message = "Nome é um campo obrigatorio!")
    @Size(min = 3, max = 80, message = "Nome deve conter de 3 a 80 caracteres!")
    private String nome;

    public ProfessorDTO(Long id, User usuario, String nome) {
        super(id);
        this.usuario = usuario;
        this.nome = nome;
    }

    public ProfessorDTO(){}
}
