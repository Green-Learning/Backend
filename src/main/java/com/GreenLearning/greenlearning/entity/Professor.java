package com.greenLearning.greenlearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_professor")
@NoArgsConstructor
@Getter @Setter
public class Professor extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "user_id")
    private User usuario;

    @Column(name = "nome")
    private String nome;

    public Professor(Long id, User usuario, String nome) {
        super(id);
        this.usuario = usuario;
        this.nome = nome;
    }
}
