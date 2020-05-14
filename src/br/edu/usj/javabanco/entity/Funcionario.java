package br.edu.usj.javabanco.entity;

import java.sql.Date;

public class Funcionario {
	private int id;
	private String nome;
	private Date nascimento;
	private int anoInicioTrabalho;
	private String funcao;
	private Empresa empresa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public int getAnoInicioTrabalho() {
		return anoInicioTrabalho;
	}

	public void setAnoInicioTrabalho(int anoInicioTrabalho) {
		this.anoInicioTrabalho = anoInicioTrabalho;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
