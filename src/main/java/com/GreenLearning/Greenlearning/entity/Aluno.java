package com.GreenLearning.Greenlearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_aluno")
@NoArgsConstructor
public class Aluno extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome")
    private String nome;

    @Getter @Setter
    @Column(name = "idade")
    private Integer idade;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;
}
