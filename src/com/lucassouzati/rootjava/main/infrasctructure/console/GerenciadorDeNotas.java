package com.lucassouzati.rootjava.main.infrasctructure.console;

import java.util.Scanner;

import com.lucassouzati.rootjava.main.application.dto.InputAlunoDto;
import com.lucassouzati.rootjava.main.application.dto.OutputAlunoDto;
import com.lucassouzati.rootjava.main.application.services.AlunoService;
import com.lucassouzati.rootjava.main.infrasctructure.repositories.InMemoryAlunoRepository;

public class GerenciadorDeNotas {

    private static final AlunoService alunoService = new AlunoService(new InMemoryAlunoRepository());

    public static void runSystem() {

        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (keepRunning) {
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Adicionar novo aluno ");
            System.out.println("2 - Remover aluno pelo nome ");
            System.out.println("3 - Mostrar nota do aluno pelo nome ");
            System.out.println("4 - Calcular média dos alunos ");
            System.out.println("5 - Sair ");

            String opcao = scanner.next();
            String nome;

            switch (opcao) {
                case ("1"):
                    System.out.println("Digite o nome do novo aluno:");
                    nome = scanner.next();
                    System.out.println("Digite a nota do novo aluno:");
                    Double nota = scanner.nextDouble();

                    OutputAlunoDto novoAluno = alunoService.create(new InputAlunoDto(nome, nota));

                    System.out.println("Novo aluno criado:" + novoAluno);

                    break;

                case ("2"): {
                    System.out.println("Digite o nome do aluno a ser removido:");
                    nome = scanner.next();

                    if (alunoService.removeAlunoByNome(nome)) {
                        System.out.println("Aluno excluído com sucesso!");
                    } else {
                        System.out.println("Houve um erro ao remover ao aluno.");
                    }

                    break;
                }
                case ("3"): {
                    System.out.println("Digite o nome do aluno:");
                    nome = scanner.next();

                    try {
                        Double notaAluno = alunoService.showNotaByName(nome);
                        System.out.println("Nota do aluno " + nome + ":" + notaAluno.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case ("4"): {
                    Double mediaAlunos = alunoService.retornaMediaDaTurma();

                    System.out.println("Média dos alunos: " + mediaAlunos.toString());

                    break;
                }
                case("5"): {
                    System.out.println("Saindo do sistema...");
                    keepRunning = false;
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }

        }
        scanner.close();
    }
}
