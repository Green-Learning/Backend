package com.greenLearning.greenlearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_aluno")
@Getter @Setter
@NoArgsConstructor
public class Aluno extends AbstractEntity{

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private Integer idade;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    public Aluno(Long id, String nome, Integer idade, Sala sala) {
        super(id);
        this.nome = nome;
        this.idade = idade;
        this.sala = sala;
    }
}
