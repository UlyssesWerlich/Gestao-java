package br.edu.usj.javabanco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
	
	public class Conexao {

	public static Connection getConexao() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(
				"jdbc:mysql://localhost/gestao_empresa"
				+ "?useTimezone=true&serverTimezone=UTC"
				, "root", "password");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Erro ao carregar driver!");
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao conectar com o banco de dados!");
		}
			return conexao;
	}
}
