package br.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {
	public static void main(String[] args) throws SQLException {
		Connection c = new DataBase().getConnection();
		Statement statement = c.createStatement();
		statement.execute("delete from Produto where id>3");
		int count = statement.getUpdateCount();
		System.out.println(count + " linhas atualizadas");
		statement.close();
		c.close();
	};
};
