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
 
public class EmpresaInserir extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;

    private JLabel lbNome;
    private JTextField txNome;
    private JLabel lbAnoFundacao;
    private JTextField txAnoFundacao;
    private JLabel lbRamoAtuacao;
    private JTextField txRamoAtuacao;
    private JLabel lbCapital;
    private JTextField txCapital;
    
    private JButton btSalvar;
    private JButton btLimpar;
 
    public EmpresaInserir(DefaultTableModel md) {
        super("Inserir Funcionário");
        criaJanela();
        modelo = md;
    }
 
    public void criaJanela() {
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        
        lbNome = new JLabel("Nome da Empresa");
        lbAnoFundacao= new JLabel("Ano de fundação");
        lbRamoAtuacao = new JLabel("Ramo de atuação");
        lbCapital = new JLabel("Capital");
        
        txNome = new JTextField(10);
        txAnoFundacao = new JTextField();
        txRamoAtuacao = new JTextField();
        txCapital = new JTextField();
 
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(5, 2, 2, 4));
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
        btSalvar.addActionListener(new BtSalvarListener());
        btLimpar.addActionListener(new BtLimparListener());
    }
 
    private class BtSalvarListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
        	try {
        		
	            Empresa empresa = new Empresa();
	            empresa.setNome(txNome.getText());
				empresa.setAnoFundacao(Integer.parseInt(txAnoFundacao.getText()));
	            empresa.setRamoAtuacao(txRamoAtuacao.getText());
	            empresa.setCapital(Float.parseFloat(txCapital.getText()));
	            EmpresaDAO dao = new EmpresaDAO();
	         
				if (dao.gravarEmpresa(empresa)) {
					System.out.println("Empresa gravada com sucesso!");
				} else {
					System.out.println("Erro ao gravar empresa!");
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