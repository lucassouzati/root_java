CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    nota_final DOUBLE DEFAULT 0
);