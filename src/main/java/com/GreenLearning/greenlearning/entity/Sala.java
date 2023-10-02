package com.greenLearning.greenlearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_sala")
@Getter @Setter
@NoArgsConstructor
public class Sala extends AbstractEntity{


    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    public Sala(Long id, String nome, Professor professor) {
        super(id);
        this.nome = nome;
        this.professor = professor;
    }
}
