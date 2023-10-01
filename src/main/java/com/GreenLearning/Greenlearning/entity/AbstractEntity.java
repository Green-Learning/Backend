package com.GreenLearning.Greenlearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hora_cadastro")
    private LocalDateTime cadastro;

    @Column(name = "hora_edicao")
    private LocalDateTime edicao;

    public AbstractEntity(Long id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist(){
        this.cadastro = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.edicao = LocalDateTime.now();
    }
}
