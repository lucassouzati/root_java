package com.lucassouzati.rootjava.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.lucassouzati.rootjava.application.dto.InputAlunoDto;
import com.lucassouzati.rootjava.application.dto.OutputAlunoDto;
import com.lucassouzati.rootjava.infrasctructure.files.FileCsvReader;
import com.lucassouzati.rootjava.infrasctructure.repositories.InMemoryAlunoRepository;

public class AlunoServiceTest {

    @Test
    public void deveCriarAlunoComSucesso() {

        var inputAlunoDto = new InputAlunoDto("teste", 100);
        var alunoService = new AlunoService(new InMemoryAlunoRepository(), new FileCsvReader());

        OutputAlunoDto outputAlunoDto = alunoService.create(inputAlunoDto);

        assertEquals(inputAlunoDto.nome(), outputAlunoDto.nome());
        assertEquals(inputAlunoDto.notaFinal(), outputAlunoDto.notaFinal());
        assertNotNull(outputAlunoDto.id());

    }

}
