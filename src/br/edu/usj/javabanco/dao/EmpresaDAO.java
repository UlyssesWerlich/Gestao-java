package br.edu.usj.javabanco.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.edu.usj.javabanco.entity.Empresa;

public class EmpresaDAO {

	public List<Empresa> listarEmpresas() {

		List<Empresa> empresas = new ArrayList<Empresa>();

		Connection conexao = Conexao.getConexao();

		try {
			Statement stm = conexao.createStatement();
			String sql = "SELECT * FROM EMPRESA";
			ResultSet rs = stm.executeQuery(sql);
			//
			while (rs.next()) {
				Empresa empresa = new Empresa();
				empresa.setId(rs.getInt("id"));
				empresa.setNome(rs.getString("nome"));
				empresa.setRamoAtuacao(rs.getString("ramo_atuacao"));
				empresa.setAnoFundacao(rs.getInt("ano_fundacao"));
				empresa.setCapital(rs.getFloat("capital"));
				empresas.add(empresa);
			}

		} catch (SQLException e) {

			System.err.println("Erro ao criar as empresas!");
			e.printStackTrace();
		}

		return empresas;
	}
	
	public Empresa buscarEmpresa(int id) {
		Empresa empresa = new Empresa();
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement stm = conexao.prepareStatement("SELECT * from EMPRESA WHERE ID = ?");
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				empresa.setId(rs.getInt("id"));
				empresa.setNome(rs.getString("nome"));
				empresa.setRamoAtuacao(rs.getString("ramo_atuacao"));
				empresa.setAnoFundacao(rs.getInt("ano_fundacao"));
				empresa.setCapital(rs.getFloat("capital"));
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar empresa!");
			e.printStackTrace();
		}

		return empresa;
	}

	public boolean gravarEmpresa(Empresa e) {
		int resultado = 0;
		Connection conexao = Conexao.getConexao();
		String sql = "INSERT INTO EMPRESA VALUES(?,?,?,?,?)";

		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, e.getId());
			stm.setString(2, e.getNome());
			stm.setInt(3, e.getAnoFundacao());
			stm.setString(4, e.getRamoAtuacao());
			stm.setFloat(5, e.getCapital());

			resultado = stm.executeUpdate();

		} catch (SQLException e1) {
			System.err.println("Erro ao gravar a empresa!");
			System.out.println(e1.getMessage());
		}
		return resultado > 0;
	}
	
	public boolean atualizarEmpresa(Empresa empresa) {
		boolean resultado = false;
		if (empresa != null) {
			Connection conexao = Conexao.getConexao();
			try {
				PreparedStatement stm;
				stm = conexao.prepareStatement("UPDATE EMPRESA SET NOME=?, RAMO_ATUACAO=?, ANO_FUNDACAO=?,"
						+ "CAPITAL=? WHERE ID=?");

				stm.setString(1, empresa.getNome());
				stm.setInt(2, empresa.getAnoFundacao());
				stm.setString(3, empresa.getRamoAtuacao());
				stm.setFloat(4, empresa.getCapital());
				stm.setInt(5, empresa.getId());

				resultado = stm.execute();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao atualizar Empresa " + e.getMessage());
				System.err.println("Erro ao atualizar Empresa!");
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "A empresa enviado por parâmetro está vazio");
		}
		
		return resultado;
	}

	public boolean excluirEmpresa(int codigo) {

		int resultado = 0;
		Connection conexao = Conexao.getConexao();
		String sql = "DELETE FROM EMPRESA WHERE id = ?";
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, codigo);
			resultado = stm.executeUpdate();
		} catch (SQLException e1) {

			System.err.println("Erro ao excluir a empresa!");
			e1.printStackTrace();
		}
		return resultado > 0;
	}
}
