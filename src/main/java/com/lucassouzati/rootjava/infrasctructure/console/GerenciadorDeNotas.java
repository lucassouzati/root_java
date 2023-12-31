package com.lucassouzati.rootjava.infrasctructure.console;

import java.util.Scanner;

import com.lucassouzati.rootjava.application.dto.InputAlunoDto;
import com.lucassouzati.rootjava.application.dto.OutputAlunoDto;
import com.lucassouzati.rootjava.application.services.AlunoService;
import com.lucassouzati.rootjava.domain.entity.Aluno;
import com.lucassouzati.rootjava.infrasctructure.files.FileCsvReader;
import com.lucassouzati.rootjava.infrasctructure.repositories.DatabaseAlunoRepository;
import com.lucassouzati.rootjava.infrasctructure.repositories.InMemoryAlunoRepository;

public class GerenciadorDeNotas {

    private static final AlunoService alunoService = new AlunoService(new DatabaseAlunoRepository(), new FileCsvReader());

    public static void runSystem() {

        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (keepRunning) {
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Adicionar novo aluno ");
            System.out.println("2 - Remover aluno pelo nome ");
            System.out.println("3 - Mostrar nota do aluno pelo nome ");
            System.out.println("4 - Calcular média dos alunos ");
            System.out.println("6 - Importar alunos CSV ");
            System.out.println("5 - Sair ");

            String opcao = scanner.next();
            String nome;

            switch (opcao) {
                case "1" ->{
                    System.out.println("Digite o nome do novo aluno:");
                    nome = scanner.next();
                    System.out.println("Digite a nota do novo aluno:");
                    Double nota = scanner.nextDouble();

                    OutputAlunoDto novoAluno = alunoService.create(new InputAlunoDto(nome, nota));

                    System.out.println("Novo aluno criado:" + novoAluno);
                }

                case "2" -> {
                    System.out.println("Digite o nome do aluno a ser removido:");
                    nome = scanner.next();

                    if (alunoService.removeAlunoByNome(nome)) {
                        System.out.println("Aluno excluído com sucesso!");
                    } else {
                        System.out.println("Houve um erro ao remover ao aluno.");
                    }
                }
                case "3" -> {
                    System.out.println("Digite o nome do aluno:");
                    nome = scanner.next();

                    try {
                        Double notaAluno = alunoService.showNotaByName(nome);
                        System.out.println("Nota do aluno " + nome + ":" + notaAluno.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }

                case "4" -> {
                    Double mediaAlunos = alunoService.retornaMediaDaTurma();

                    System.out.println("Média dos alunos: " + mediaAlunos.toString());
                }
                case"5" -> {
                    System.out.println("Saindo do sistema...");
                    keepRunning = false;
                }
                case "6" -> {
                    System.out.println("Alunos importados:");
                    alunoService.retornaAlunosImportados().stream().forEach(a-> System.out.println(a.toString()));
                }
                default -> {
                    System.out.println("Opção inválida!");
                }
            }

        }
        scanner.close();
    }
}
