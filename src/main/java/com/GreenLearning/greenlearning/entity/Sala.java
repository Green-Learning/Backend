package com.greenLearning.greenlearning.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_SALA")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    @NotBlank(message = "Nome é um campo obrigatorio!")
    @Size(max = 25, message = "Nome deve conter até 25 caracteres!")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

}
