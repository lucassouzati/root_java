package com.lucassouzati.rootjava.main.application.services;

import java.util.Optional;
import java.util.logging.Logger;

import com.lucassouzati.rootjava.main.application.dto.InputAlunoDto;
import com.lucassouzati.rootjava.main.application.dto.OutputAlunoDto;
import com.lucassouzati.rootjava.main.application.repository.IAlunoRepository;
import com.lucassouzati.rootjava.main.domain.entity.Aluno;

public class AlunoService {

    private IAlunoRepository alunoRepository;

    private static final Logger logger = Logger.getLogger(AlunoService.class.getName());

    public AlunoService(IAlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public OutputAlunoDto create(InputAlunoDto alunoDto) {
        Aluno aluno = new Aluno(alunoDto.nome(), alunoDto.notaFinal());
        Aluno alunoSalvo = this.alunoRepository.create(aluno);

        return new OutputAlunoDto(alunoSalvo.getId(), alunoSalvo.getNome(), alunoSalvo.getNotaFinal());
    }

    public boolean removeAlunoByNome(String nome) {
        try {
            Aluno aluno = Optional.ofNullable(this.alunoRepository.findByName(nome))
                    .orElseThrow(() -> new Exception("Aluno não encontrado!", null));

            this.alunoRepository.delete(aluno);
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    public double showNotaByName(String nome) throws Exception {
        Aluno aluno = Optional.ofNullable(this.alunoRepository.findByName(nome))
                .orElseThrow(() -> new Exception("Aluno não encontrado!", null));
        return aluno.getNotaFinal();
    }

    public Double retornaMediaDaTurma() {

        return this.alunoRepository.findAll()
                .stream()
                .mapToDouble(Aluno::getNotaFinal)
                .average()
                .orElse(0.0);
    }
}
