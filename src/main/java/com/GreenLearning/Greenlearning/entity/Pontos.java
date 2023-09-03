package com.GreenLearning.Greenlearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pontos")
@NoArgsConstructor
public class Pontos extends AbstractEntity{


    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @Getter @Setter
    @Column(name = "jogo")
    private String jogo;

    @Getter @Setter
    @Column(name = "score")
    private Integer score;

    @Getter @Setter
    @Column(name = "pontuacao_maior")
    private boolean maior;
}
