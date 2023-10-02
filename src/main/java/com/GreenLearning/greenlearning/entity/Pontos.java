package com.greenlearning.greenlearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pontos")
@NoArgsConstructor
@Getter @Setter
public class Pontos extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @Column(name = "jogo")
    private String jogo;

    @Column(name = "score")
    private Integer score;

    @Column(name = "pontuacao_maior")
    private boolean maior;

    public Pontos(Long id, Aluno aluno, String jogo, Integer score, boolean maior) {
        super(id);
        this.aluno = aluno;
        this.jogo = jogo;
        this.score = score;
        this.maior = maior;
    }
}
