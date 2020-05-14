package br.edu.usj.javabanco.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.usj.javabanco.dao.EmpresaDAO;
import br.edu.usj.javabanco.entity.Empresa;


public class EmpresaListar extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel painelFundo;
	private JPanel painelBotoes;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private JButton btInserir;
	private JButton btExcluir;
	private JButton btEditar;
	private JButton btAtualizar;
	private DefaultTableModel modelo = new DefaultTableModel();
	
	public EmpresaListar() {
		super("Empresas");
		criaJTable();
		criaJanela();
	}

	private void criaJanela() {
		btInserir = new JButton("Inserir");
		btExcluir = new JButton("Excluir");
		btEditar = new JButton("Editar");
		btAtualizar = new JButton("Atualizar");
		
		painelBotoes = new JPanel();
		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
		painelBotoes.add(btInserir);
		painelBotoes.add(btEditar);
		painelBotoes.add(btExcluir);
		painelBotoes.add(btAtualizar);
		painelFundo.add(BorderLayout.SOUTH, painelBotoes);
		
		getContentPane().add(painelFundo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(700, 700);
		setVisible(true);
		btInserir.addActionListener(new BtInserirListener());
		btEditar.addActionListener(new BtEditarListener());
		btExcluir.addActionListener(new BtExcluirListener());
		btAtualizar.addActionListener(new BtAtualizarListener());
	}

	private void criaJTable() {
		tabela = new JTable(modelo);
		modelo.addColumn("Id");
		modelo.addColumn("Nome da Empresa");
		modelo.addColumn("Ano de Fundação");
		modelo.addColumn("Ramo da Empresa");
		modelo.addColumn("Capital");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(120);
		pesquisar(modelo);
	}
	
	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		EmpresaDAO dao = new EmpresaDAO();
		
		for (Empresa e: dao.listarEmpresas()) {
			modelo.addRow(new Object[] {e.getId(), e.getNome(), e.getAnoFundacao(), e.getRamoAtuacao()
					, e.getCapital()});
		}
	}
	
	private class BtInserirListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			EmpresaInserir fi = new EmpresaInserir(modelo);
			fi.setVisible(true);
		}
	}
	
	private class BtEditarListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = tabela.getSelectedRow();
			if (linhaSelecionada >= 0) {
				int id = (Integer) tabela.getValueAt(linhaSelecionada, 0);
				EmpresaAtualizar ea = new EmpresaAtualizar(modelo, id, linhaSelecionada);
				ea.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
			}
		}
	}
	
	private class BtExcluirListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = tabela.getSelectedRow();
			if (linhaSelecionada >= 0) {
				int id = (Integer) tabela.getValueAt(linhaSelecionada, 0);
				EmpresaDAO dao = new EmpresaDAO();
				dao.excluirEmpresa(id);
				modelo.removeRow(linhaSelecionada);
			} else {
				JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
			}	
		}
	}
	
	private class BtAtualizarListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			pesquisar(modelo);
		}
	}
}
