package com.lucassouzati.rootjava.infrasctructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.lucassouzati.rootjava.application.repository.IAlunoRepository;
import com.lucassouzati.rootjava.domain.entity.Aluno;
import com.lucassouzati.rootjava.infrasctructure.database.DatabaseConnection;

public class DatabaseAlunoRepository implements IAlunoRepository {

    private static final Logger logger = Logger.getLogger(DatabaseAlunoRepository.class.getName());

    @Override
    public Aluno create(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, nota_final) values (?, ?) RETURNING id";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, aluno.getNome());
            statement.setDouble(2, aluno.getNotaFinal());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long idGerado = resultSet.getLong("id");
                aluno.setId(idGerado);
                return aluno;
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Aluno aluno) {
        String sql = "DELETE FROM aluno WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, aluno.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Aluno find(Long id) {
        String sql = "SELECT * FROM alunos where id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Aluno(resultSet.getLong("id"), resultSet.getString("nome"),
                        resultSet.getDouble("nota_final"));
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public Aluno findByName(String nome) {
        String sql = "SELECT * FROM aluno where nome = ? ";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Aluno(resultSet.getLong("id"), resultSet.getString("nome"),
                        resultSet.getDouble("nota_final"));

            }
        } catch (SQLException e) {
            logger.info(sql);
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Aluno> findAll() {
        var alunos = new ArrayList<Aluno>();
        String sql = "SELECT * FROM aluno";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                Double notaFinal = resultSet.getDouble("nota_final");

                alunos.add(new Aluno(id, nome, notaFinal));
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        return alunos;
    }

}
