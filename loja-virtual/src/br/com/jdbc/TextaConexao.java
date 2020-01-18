package br.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

// java -cp hsqldb.jar  org.hsqldb.util.DatabaseManager
// java -cp hsqldb.jar org.hsqldb.server.Server --dbname.0 loja-virtual --database.0 file:loja-virtual

public class TextaConexao {

	public static void main(String[] args) throws SQLException {
		Connection c = new DataBase().getConnection();
		System.out.println("Testando!");
		c.close();
	};
};