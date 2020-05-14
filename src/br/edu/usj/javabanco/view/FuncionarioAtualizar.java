package br.edu.usj.javabanco.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.usj.javabanco.dao.FuncionarioDAO;
import br.edu.usj.javabanco.entity.Empresa;
import br.edu.usj.javabanco.entity.Funcionario;

 
/**
 *
 * @author Ulysses Werlich
 */
public class FuncionarioAtualizar extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo = 
    new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    
    private JLabel lbId;
    private JTextField txId;
    private JLabel lbNome;
    private JTextField txNome;
    private JLabel lbDataNascimento;
    private JTextField txDataNascimento;
    private JLabel lbAnoInicio;
    private JTextField txAnoInicio;
    private JLabel lbFuncao;
    private JTextField txFuncao;
    private JLabel lbIdEmpresa;
    private JTextField txIdEmpresa;
    
    private Funcionario funcionario;
 
    public FuncionarioAtualizar(DefaultTableModel md, int id, int linha) {
        super("Contatos");
        criaJanela();
        modelo = md;
        FuncionarioDAO dao = new FuncionarioDAO();
        funcionario = dao.buscarFuncionario(id);
        txId.setText(Integer.toString(funcionario.getId()));
        txNome.setText(funcionario.getNome());
        txDataNascimento.setText(String.valueOf(funcionario.getNascimento()));
        txAnoInicio.setText(String.valueOf(funcionario.getAnoInicioTrabalho()));
        txFuncao.setText(funcionario.getFuncao());
        txIdEmpresa.setText(String.valueOf(funcionario.getEmpresa().getId()));
    }
 
    public void criaJanela() {
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
 
        lbNome = new JLabel("Nome");
        lbDataNascimento= new JLabel("Data de Nascimento (AAAA-MM-DD):");
        lbAnoInicio = new JLabel("Ano de inicio de serviço");
        lbFuncao = new JLabel("Função");
        lbIdEmpresa = new JLabel("Id da Empresa");
        lbId = new JLabel("Id");
        txNome = new JTextField(10);
        txDataNascimento = new JTextField();
        txAnoInicio = new JTextField();
        txFuncao = new JTextField();
        txIdEmpresa = new JTextField();
        txId = new JTextField();
        txId.setEditable(false);
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(7, 2, 2, 4));
        painelFundo.add(lbId);
        painelFundo.add(txId);
        painelFundo.add(lbNome);
        painelFundo.add(txNome);
        painelFundo.add(lbDataNascimento);
        painelFundo.add(txDataNascimento);
        painelFundo.add(lbAnoInicio);
        painelFundo.add(txAnoInicio);
        painelFundo.add(lbFuncao);
        painelFundo.add(txFuncao);
        painelFundo.add(lbIdEmpresa);
        painelFundo.add(txIdEmpresa);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);
 
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 230);
        setVisible(true);
 
        btSalvar.addActionListener(new
        		FuncionarioAtualizar.BtSalvarListener());
        btLimpar.addActionListener(new
        		FuncionarioAtualizar.BtLimparListener());
    }
 
    private class BtSalvarListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
        	try {
	            Funcionario f = new Funcionario();
	            f.setNome(txNome.getText());
	            
	            String dataString = txDataNascimento.getText();
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date data;
				data = format.parse(dataString);
				f.setNascimento(new java.sql.Date(data.getTime()));
	                        
	            f.setAnoInicioTrabalho(Integer.parseInt(txAnoInicio.getText()));
	            f.setFuncao(txFuncao.getText());
	            
				Empresa empresa = new Empresa();
				empresa.setId(Integer.parseInt(txIdEmpresa.getText()));
				f.setEmpresa(empresa);
	 
	            FuncionarioDAO dao = new FuncionarioDAO();
	            
				if (dao.atualizarFuncionario(f)) {
					System.out.println("Funcionário gravado com sucesso!");
				} else {
					System.out.println("Erro ao gravar funcionário!");
				}
				
				FuncionarioListar.pesquisar(modelo);
	            setVisible(false);
	            
	        } catch (ParseException e1) {
				System.out.println("formato inválido da data");
				e1.printStackTrace();
			}
        }
    }
 
    private class BtLimparListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
            txNome.setText("");
            txDataNascimento.setText("");
            txAnoInicio.setText("");
            txFuncao.setText("");
            txIdEmpresa.setText("");
        }
    }
}
