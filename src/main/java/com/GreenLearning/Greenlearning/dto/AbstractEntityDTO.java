package com.GreenLearning.Greenlearning.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public abstract class AbstractEntityDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private LocalDateTime cadastro;

    @Getter @Setter
    private LocalDateTime edicao;

    public AbstractEntityDTO(Long id) {
        this.id = id;
    }
}
