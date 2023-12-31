package com.lucassouzati.rootjava.application.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.lucassouzati.rootjava.application.dto.InputAlunoDto;
import com.lucassouzati.rootjava.application.dto.OutputAlunoDto;
import com.lucassouzati.rootjava.application.files.IFileReader;
import com.lucassouzati.rootjava.application.repository.IAlunoRepository;
import com.lucassouzati.rootjava.domain.entity.Aluno;

public class AlunoService {

    private IAlunoRepository alunoRepository;
    private IFileReader fileReader;

    private static final Logger logger = Logger.getLogger(AlunoService.class.getName());

    public AlunoService(IAlunoRepository alunoRepository, IFileReader fileReader) {
        this.alunoRepository = alunoRepository;
        this.fileReader = fileReader;
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

    public List<Aluno> retornaAlunosImportados(){
        List<String[]> retorno = fileReader.read();
        return retorno.stream().map(r -> new Aluno(r[0], Double.valueOf(r[1]))).toList();
    }
}
