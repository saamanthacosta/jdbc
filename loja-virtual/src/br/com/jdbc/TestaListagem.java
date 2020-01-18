package br.com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		Connection c = new DataBase().getConnection();
		Statement statement = c.createStatement();
		statement.execute("select * from Produto");
		ResultSet resultSet = statement.getResultSet();
		while(resultSet.next()) {
		    int id = resultSet.getInt("id");
		    String nome = resultSet.getString("nome");
		    String descricao = resultSet.getString("descricao");
		    System.out.println(id);
		    System.out.println(nome);
		    System.out.println(descricao);
		}
		resultSet.close();
		statement.close();
		c.close();
	}

}
