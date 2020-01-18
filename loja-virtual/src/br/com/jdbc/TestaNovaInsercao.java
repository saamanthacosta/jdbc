package br.com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaNovaInsercao {

	public static void main(String[] args) {
		
		try (Connection c = new DataBase().getConnection()) {
			c.setAutoCommit(false);
			String sql = "insert into Produto (nome, descricao) values(?, ?)";
			
			try (PreparedStatement statement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				adiciona("Notebook'i5", "Notebook i5", statement);
				adiciona("TV LCD", "32 polegadas", statement);
				adiciona("Blueray", "FULLHDMI", statement);
				c.commit();
			} catch(Exception ex) {
				ex.printStackTrace();
				c.rollback();
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	private static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		
		if (nome.equals("Blueray")) {
			throw new IllegalArgumentException("Problema Ocorrido");
		}
		
		statement.setString(1, nome);
		statement.setString(2, descricao);
		
		statement.execute();
		ResultSet resultSet = statement.getGeneratedKeys();
		while(resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println(id + " gerado");
		}
		resultSet.close();
	}

}
