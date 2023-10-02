package com.GreenLearning.greenlearning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class UserDTO extends AbstractEntityDTO{

    @Getter @Setter
    @NotBlank(message = "Preencha o campo email!")
    @Email(message = "Informe um email valido!")
    private String email;

    @Getter @Setter
    @NotBlank(message = "Preencha o campo senha!")
    @Size(max = 50, message = "Senha deve conter até 50 caracteres!")
    private String senha;

    public UserDTO(Long id, String email, String senha) {
        super(id);
        this.email = email;
        this.senha = senha;
    }
}
