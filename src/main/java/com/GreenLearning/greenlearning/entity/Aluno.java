package com.greenLearning.greenlearning.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ALUNO")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    @NotBlank(message = "Nome é um campo obrigatorio!")
    @Size(min = 3, max = 80, message = "Nome deve conter de 3 a 80 caracteres!")
    private String nome;

    @Column
    @Min(value = 4, message = "Idade é um campo obrigatorio, e devem ter no mínimo 4 anos!")

    private Integer idade;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;
}
