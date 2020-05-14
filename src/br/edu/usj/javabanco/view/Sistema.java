package br.edu.usj.javabanco.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.edu.usj.javabanco.dao.EmpresaDAO;
import br.edu.usj.javabanco.dao.FuncionarioDAO;
import br.edu.usj.javabanco.entity.Empresa;
import br.edu.usj.javabanco.entity.Funcionario;

public class Sistema {

	EmpresaDAO empresaDAO;
	FuncionarioDAO funcionarioDAO;
	
	public Sistema() {
		empresaDAO = new EmpresaDAO();
		funcionarioDAO = new FuncionarioDAO();
	}
	
	@SuppressWarnings("resource")
	public void gravarEmpresa() {
		Scanner scanner = new Scanner(System.in);
		Empresa empresa = new Empresa();
		System.out.println("Digite o nome da empresa:");
		empresa.setNome(scanner.next());
		System.out.println("Digite o ano de fundação da empresa:");
		empresa.setAnoFundacao(scanner.nextInt());
		System.out.println("Digite o ramo de atuação da empresa:");
		empresa.setRamoAtuacao(scanner.next());
		System.out.println("Digite o capital da empresa:");
		empresa.setCapital(scanner.nextFloat());
		
		boolean gravou = empresaDAO.gravarEmpresa(empresa);
		if (gravou) {
			System.out.println("Empresa gravada com sucesso!");
		}
		else {
			System.out.println("Erro ao gravar empresa!");
		}
	}

	@SuppressWarnings("resource")
	public void gravarFuncionario() {
		Scanner scanner = new Scanner(System.in);
		Funcionario funcionario = new Funcionario();

		try {
			System.out.println("Digite o nome do funcion�rio:");
			funcionario.setNome(scanner.next());
			System.out.println("Digite a data de nascimento (no formato AAAA-MM-DD):");
			String dataString = scanner.next();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date data;
			data = format.parse(dataString);
			funcionario.setNascimento(new java.sql.Date(data.getTime()));
			System.out.println("Digite o ano de inicio:");
			funcionario.setAnoInicioTrabalho(scanner.nextInt());
			System.out.println("Digite a função do funcionário:");
			funcionario.setFuncao(scanner.next());
			System.out.println("Digite o identificador da empresa:");
			Empresa empresa = new Empresa();
			empresa.setId(scanner.nextInt());
			funcionario.setEmpresa(empresa);
			
			boolean gravou = funcionarioDAO.gravarFuncionario(funcionario);
			if (gravou) {
				System.out.println("Funcionário gravado com sucesso!");
			}
			else {
				System.out.println("Erro ao gravar funcionário!");
			}
		} catch (ParseException e) {
			System.out.println("formato inválido da data");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public void removerEmpresa(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o código da empresa a ser excluída:");
		int codigo = scanner.nextInt();		
		
		boolean excluiu = empresaDAO.excluirEmpresa(codigo);
		if (excluiu) {
			System.out.println("Empresa removida com sucesso!");
		} else {
			System.out.println("Erro ao remover empresa!");
		}
	}
	
	@SuppressWarnings("resource")
	public void removerFuncionario(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o código do funcionário a ser excluído:");
		int codigo = scanner.nextInt();		
		
		boolean excluiu = funcionarioDAO.excluirFuncionario(codigo);
		if (excluiu) {
			System.out.println("Funcionário removido com sucesso!");
		}
		else {
			System.out.println("Erro ao remover funcionário!");
		}
	}
	
	public void listarEmpresas() {
		List<Empresa> lista = empresaDAO.listarEmpresas();
		
		System.out.println("Lista de empresas:");
		for(Empresa e: lista) {
			System.out.println("Id: "+e.getId());
			System.out.println("Nome: "+e.getNome());
			System.out.println("Fundação: "+e.getAnoFundacao());
			System.out.println("Ramo de Atuação: "+e.getRamoAtuacao());
			System.out.println("Capital: "+e.getCapital());
			System.out.println("");
		}
	}

	public void listarFuncionarios() {
		List<Funcionario> lista = funcionarioDAO.listarFuncionarios();
		
		System.out.println("Lista de Funcion�rios:");
		for(Funcionario f: lista) {
			System.out.println("Id: "+f.getId());
			System.out.println("Nome: "+f.getNome());
			System.out.println("Nascimento: "+f.getNascimento());
			System.out.println("Ano de inicio: "+f.getAnoInicioTrabalho());
			System.out.println("Função: "+f.getFuncao());
			System.out.println("Empresa: "+f.getEmpresa().getNome());
			System.out.println("");
		}
	}
	
	public void start() {
		@SuppressWarnings("unused")
		FuncionarioListar fl = new FuncionarioListar();
		@SuppressWarnings("unused")
		EmpresaListar el = new EmpresaListar();
	}
}
