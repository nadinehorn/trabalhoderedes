package redes.servidor.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ServerWindow extends JFrame{
	private static final long serialVersionUID = 988857644705057453L;
	private JTextField porta;
	private JTextField caminho;
	
	private ActionListener escolherCaminhoListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.showDialog(ServerWindow.this, "Selecionar");
			File selectedFile = chooser.getSelectedFile();
			if (selectedFile != null)
				ServerWindow.this.caminho.setText(selectedFile.getAbsolutePath());
		}
	};
	
	private JButton btnEscolher;
	private JButton btnIniciarServidor;

	public ServerWindow() {
		setTitle("Servidor");
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setSize(580, 114);
		
		JLabel lblEscutarNaPorta = new JLabel("Escutar na porta:");
		lblEscutarNaPorta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEscutarNaPorta.setBounds(98, 49, 125, 14);
		getContentPane().add(lblEscutarNaPorta);
		
		porta = new JTextField();
		porta.setBounds(233, 46, 70, 20);
		getContentPane().add(porta);
		porta.setColumns(10);
		
		btnIniciarServidor = new JButton("Iniciar servidor");
		btnIniciarServidor.setBounds(413, 44, 131, 23);
		getContentPane().add(btnIniciarServidor);
		
		JLabel lblCaminhoDaPasta = new JLabel("Caminho da pasta dos arquivos:");
		lblCaminhoDaPasta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaminhoDaPasta.setBounds(21, 15, 202, 14);
		getContentPane().add(lblCaminhoDaPasta);
		
		caminho = new JTextField();
		caminho.setBounds(233, 12, 170, 20);
		getContentPane().add(caminho);
		caminho.setColumns(10);
		
		btnEscolher = new JButton("Escolher...");
		btnEscolher.setBounds(413, 10, 131, 23);
		getContentPane().add(btnEscolher);
		
		this.btnEscolher.addActionListener(escolherCaminhoListener);
		
		this.setVisible(true);
	}
	

	public void disableFields() {
		this.btnIniciarServidor.setText("Parar servidor");
		this.btnEscolher.setEnabled(false);
		this.caminho.setEnabled(false);
		this.porta.setEnabled(false);
	}

	public void enableFields() {
		this.btnIniciarServidor.setText("Iniciar servidor");
		this.btnEscolher.setEnabled(true);
		this.caminho.setEnabled(true);
		this.porta.setEnabled(true);
	}
	
	public String getCaminhoPasta() {
		return this.caminho.getText();
	}
	
	public int getPorta() throws Exception{
		return Integer.valueOf(this.porta.getText());
	}

	public void addBtnClickListenerDoServidor(ActionListener listener) {
		this.btnIniciarServidor.addActionListener(listener);
	}
	
}
