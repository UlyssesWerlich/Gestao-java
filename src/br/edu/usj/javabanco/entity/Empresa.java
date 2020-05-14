package br.edu.usj.javabanco.entity;

public class Empresa {
	int id;
	String nome;
	int anoFundacao;
	String ramoAtuacao;
	float capital;

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

	public int getAnoFundacao() {
		return anoFundacao;
	}

	public void setAnoFundacao(int anoFundacao) {
		this.anoFundacao = anoFundacao;
	}

	public String getRamoAtuacao() {
		return ramoAtuacao;
	}

	public void setRamoAtuacao(String ramoAtuacao) {
		this.ramoAtuacao = ramoAtuacao;
	}

	public float getCapital() {
		return capital;
	}

	public void setCapital(float capital) {
		this.capital = capital;
	}

}
