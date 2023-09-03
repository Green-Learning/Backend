package com.GreenLearning.Greenlearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @Column(name = "hora_cadastro")
    private LocalDateTime cadastro;

    @Getter @Setter
    @Column(name = "hora_edicao")
    private LocalDateTime edicao;


    @PrePersist
    public void prePersist(){
        this.cadastro = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.edicao = LocalDateTime.now();
    }
}
