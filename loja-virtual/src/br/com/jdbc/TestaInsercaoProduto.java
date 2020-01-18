package br.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.jdbc.modelo.Produto;

public class TestaInsercaoProduto {

	public static void main(String[] args) throws SQLException {
	
        Produto mesa = new Produto("Mesa Azul", "Mesa com 4 pés");

        try (Connection c = new DataBase().getConnection()) {
        	ProdutosDAO dao = new ProdutosDAO(c);
        	dao.salva(mesa);
        	
        	 List<Produto> produtos = dao.lista();
             for (Produto produto : produtos) {
                 System.out.println("Existe o produto: " + produto);
             }
        }

    };

};
