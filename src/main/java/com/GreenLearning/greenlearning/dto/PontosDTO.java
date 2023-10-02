package com.GreenLearning.greenlearning.dto;

import com.GreenLearning.greenlearning.entity.Aluno;
import lombok.Getter;
import lombok.Setter;

public class PontosDTO extends AbstractEntityDTO{

    @Getter @Setter
    private Aluno aluno;

    @Getter @Setter
    private String jogo;

    @Getter @Setter
    private Integer score;

    @Getter @Setter
    private Boolean maior;

    public PontosDTO(Long id, Aluno aluno, String jogo, Integer score, Boolean maior) {
        super(id);
        this.aluno = aluno;
        this.jogo = jogo;
        this.score = score;
        this.maior = maior;
    }
}
