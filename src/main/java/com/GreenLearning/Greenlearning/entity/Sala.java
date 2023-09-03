package com.GreenLearning.Greenlearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_sala")
@NoArgsConstructor
public class Sala extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome")
    private String nome;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
}
