package com.lucassouzati.rootjava.main.domain.entity;

public class Aluno {

    private Long id;
    
    private String nome;

    private Double notaFinal;

    public Aluno(){}

    public Aluno(String nome, Double notaFinal){
        this.nome = nome;
        this.notaFinal = notaFinal;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public Double getNotaFinal(){
        return this.notaFinal;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setNotaFinal(Double notaFinal){
        this.notaFinal = notaFinal;
    }
}
