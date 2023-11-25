package com.greenLearning.greenlearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PONTOS")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Pontos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @Column
    private String jogo;

    @Column
    private Integer score;

    @Column(name = "pontuacao_maior")
    private boolean maior;
}
