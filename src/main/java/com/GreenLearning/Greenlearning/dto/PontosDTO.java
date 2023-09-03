package com.GreenLearning.Greenlearning.dto;

import com.GreenLearning.Greenlearning.entity.Aluno;
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
}
