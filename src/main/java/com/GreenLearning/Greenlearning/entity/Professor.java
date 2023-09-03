package com.GreenLearning.Greenlearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_professor")
@NoArgsConstructor
public class Professor extends AbstractEntity{

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "user_id")
    private User usuario;

    @Getter @Setter
    @Column(name = "nome")
    private String nome;
}
