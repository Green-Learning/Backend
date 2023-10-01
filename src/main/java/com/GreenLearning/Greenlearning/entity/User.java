package com.GreenLearning.Greenlearning.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@Getter @Setter
@NoArgsConstructor
public class User extends AbstractEntity{

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    public User(Long id, String email, String senha) {
        super(id);
        this.email = email;
        this.senha = senha;
    }
}
