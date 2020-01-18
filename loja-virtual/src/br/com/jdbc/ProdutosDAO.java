package br.com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.jdbc.modelo.Categoria;
import br.com.jdbc.modelo.Produto;

public class ProdutosDAO {
	
	private Connection c;

	public ProdutosDAO(Connection c) {
		this.c = c;
	};
	
	public void salva(Produto produto) throws SQLException {
        String sql = "insert into Produto (nome, descricao) values (?,?)";

        try (PreparedStatement statement = c.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {

        	statement.setString(1, produto.getNome());
        	statement.setString(2, produto.getDescricao());
        	statement.execute();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    produto.setId(id);
                }
            }

        }
    };
    
//    public List<Produto> lista() throws SQLException {
//        List<Produto> produtos = new ArrayList<>();
//        String sql = "select * from Produto";
//
//        try (PreparedStatement statement = c.prepareStatement(sql)) {
//            statement.execute();
//            
//            try (ResultSet resultSet = statement.getResultSet()) {
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String nome = resultSet.getString("nome");
//                    String descricao = resultSet.getString("descricao");
//                    Produto produto = new Produto(nome, descricao);
//                    produto.setId(id);
//                    produtos.add(produto);
//                };
//            };
//        };
//        
//        return produtos;
//    };
    
    public List<Produto> lista() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "select * from Produto";

        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.execute();
            transformaResultadoEmProdutos(stmt, produtos);
        }
        return produtos;
    }

    private void transformaResultadoEmProdutos(PreparedStatement stmt, List<Produto> produtos)
            throws SQLException {
        try (ResultSet rs = stmt.getResultSet()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                Produto produto = new Produto(nome, descricao);
                produto.setId(id);
                produtos.add(produto);
            }
        }
    }

	public List<Produto> buscaProduto(Categoria categoria) throws SQLException {
		List<Produto> produtos = new ArrayList<>();
        String sql = "select * from Produto where categoria_id = ?";

        try (PreparedStatement stmt = c.prepareStatement(sql)) {
        	stmt.setInt(1, categoria.getId());
            stmt.execute();
            transformaResultadoEmProdutos(stmt, produtos);
        }
        return produtos;
	}
};
