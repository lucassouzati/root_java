package application.repository;

import java.util.HashMap;
import java.util.List;

import domain.entity.Aluno;

public interface IAlunoRepository {

    public Aluno create(Aluno aluno);

    public void delete(Aluno aluno);

    public Aluno find(Long id);

    public Aluno findByName(String nome);

    public List<Aluno> findAll();
}
