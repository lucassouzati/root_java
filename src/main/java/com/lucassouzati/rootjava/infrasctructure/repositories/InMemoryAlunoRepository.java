package com.lucassouzati.rootjava.infrasctructure.repositories;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lucassouzati.rootjava.application.repository.IAlunoRepository;
import com.lucassouzati.rootjava.domain.entity.Aluno;

public class InMemoryAlunoRepository implements IAlunoRepository {

    private Long nextId = 1L;
    private Map<Long, Aluno> alunos = new LinkedHashMap<>();

    @Override
    public Aluno create(Aluno aluno) {
        aluno.setId(nextId);
        alunos.put(nextId, aluno);
        nextId++;

        return aluno;
    }

    @Override
    public void delete(Aluno aluno) {
        alunos.remove(aluno.getId(), aluno);
    }

    @Override
    public Aluno find(Long id) {
        return alunos.get(id);
    }

    @Override
    public Aluno findByName(String nome) {
        return alunos.values().stream().filter(aluno -> aluno.getNome().equalsIgnoreCase(nome)).findFirst()
                .orElse(null);
    }

    @Override
    public List<Aluno> findAll() {
        return new ArrayList<>(alunos.values());
    }

}
