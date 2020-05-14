package br.edu.usj.javabanco.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.usj.javabanco.dao.EmpresaDAO;
import br.edu.usj.javabanco.entity.Empresa;

 
/**
 *
 * @author Ulysses Werlich
 */
public class EmpresaAtualizar extends JFrame {

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
    private JLabel lbAnoFundacao;
    private JTextField txAnoFundacao;
    private JLabel lbRamoAtuacao;
    private JTextField txRamoAtuacao;
    private JLabel lbCapital;
    private JTextField txCapital;
    
    private Empresa empresa;
 
    public EmpresaAtualizar(DefaultTableModel md, int id, int linha) {
        super("Contatos");
        criaJanela();
        modelo = md;
        EmpresaDAO dao = new EmpresaDAO();
        empresa = dao.buscarEmpresa(id);
        txId.setText(Integer.toString(empresa.getId()));
        txNome.setText(empresa.getNome());
        txAnoFundacao.setText(String.valueOf(empresa.getAnoFundacao()));
        txRamoAtuacao.setText(empresa.getRamoAtuacao());
        txCapital.setText(String.valueOf(empresa.getCapital()));
    }
 
    public void criaJanela() {
        btSalvar = new JButton("Alterar");
        btLimpar = new JButton("Limpar");
 
        lbNome = new JLabel("Nome da Empresa");
        lbAnoFundacao= new JLabel("Ano de fundação");
        lbRamoAtuacao = new JLabel("Ramo de atuação");
        lbCapital = new JLabel("Capital");
        lbId = new JLabel("Id da Empresa");
        
        txNome = new JTextField(10);
        txAnoFundacao = new JTextField();
        txRamoAtuacao = new JTextField();
        txCapital = new JTextField();
        txId = new JTextField();
        txId.setEditable(false);
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(6, 2, 2, 4));
        painelFundo.add(lbId);
        painelFundo.add(txId);
        painelFundo.add(lbNome);
        painelFundo.add(txNome);
        painelFundo.add(lbAnoFundacao);
        painelFundo.add(txAnoFundacao);
        painelFundo.add(lbRamoAtuacao);
        painelFundo.add(txRamoAtuacao);
        painelFundo.add(lbCapital);
        painelFundo.add(txCapital);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);
 
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 200);
        setVisible(true);
 
        btSalvar.addActionListener(new
        		EmpresaAtualizar.BtSalvarListener());
        btLimpar.addActionListener(new
        		EmpresaAtualizar.BtLimparListener());
    }
 
    private class BtSalvarListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
        	try {
        		
	            Empresa empresa = new Empresa();
	            empresa.setNome(txNome.getText());
				empresa.setAnoFundacao(Integer.parseInt(txAnoFundacao.getText()));
	            empresa.setRamoAtuacao(txRamoAtuacao.getText());
	            empresa.setCapital(Float.parseFloat(txCapital.getText()));
	            empresa.setId(Integer.parseInt(txId.getText()));
	            EmpresaDAO dao = new EmpresaDAO();
	         
				if (dao.atualizarEmpresa(empresa)) {
					System.out.println("Empresa alterada com sucesso!");
				} else {
					System.out.println("Erro ao alterar empresa!");
				}
				
				EmpresaListar.pesquisar(modelo);
	            setVisible(false);
	            
	        } catch (Exception e1) {
				System.out.println("formato inválido da data");
				e1.printStackTrace();
			}
        }
    }
 
    private class BtLimparListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
            txNome.setText("");
            txAnoFundacao.setText("");
            txRamoAtuacao.setText("");
            txCapital.setText("");
        }
    }
}
