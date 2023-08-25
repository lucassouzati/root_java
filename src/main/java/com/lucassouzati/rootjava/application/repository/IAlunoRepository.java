    package com.lucassouzati.rootjava.application.repository;

import java.util.List;

import com.lucassouzati.rootjava.domain.entity.Aluno;

public interface IAlunoRepository {

    public Aluno create(Aluno aluno);

    public void delete(Aluno aluno);

    public Aluno find(Long id);

    public Aluno findByName(String nome);

    public List<Aluno> findAll();
}
