package br.com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		Connection c = new DataBase().getConnection();
		Statement statement = c.createStatement();
		statement.execute("insert into Produto (nome, descricao) values ('Notebook', 'Notebook i5')", Statement.RETURN_GENERATED_KEYS);
		ResultSet resultSet = statement.getGeneratedKeys();
		while(resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println(id + " gerado");
		}
		resultSet.close();
		statement.close();
		c.close();
	};
};
