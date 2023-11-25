package com.greenLearning.greenlearning.entity.role;

public enum Roles {
    ADMIN("admin"),
    PROFESSOR("professor"),
    DIRETOR("diretor"),
    PROFESSORA("professora"),
    DIRETORA("diretora");

    private String role;

    Roles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
