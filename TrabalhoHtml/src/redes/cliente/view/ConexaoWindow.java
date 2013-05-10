package redes.cliente.view;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ConexaoWindow extends JFrame{
	public ConexaoWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Conectar ao servidor");
		this.setResizable(false);
		this.setSize(398, 127);
		getContentPane().setLayout(null);
		
		JLabel lblEndereoDoServidor = new JLabel("Endere\u00E7o do servidor:");
		lblEndereoDoServidor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereoDoServidor.setBounds(0, 21, 147, 14);
		getContentPane().add(lblEndereoDoServidor);
		
		enderecoJTextField = new JTextField();
		enderecoJTextField.setBounds(167, 18, 184, 20);
		getContentPane().add(enderecoJTextField);
		enderecoJTextField.setColumns(10);
		
		portaJTextField = new JTextField();
		portaJTextField.setBounds(167, 49, 86, 20);
		getContentPane().add(portaJTextField);
		portaJTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Porta:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(74, 52, 64, 14);
		getContentPane().add(lblNewLabel);
		
		conectarButton = new JButton("Conectar");
		conectarButton.setBounds(262, 49, 89, 23);
		getContentPane().add(conectarButton);
		
		this.setVisible(true);
		
	}
	
	private static final long serialVersionUID = -2963114495067720196L;
	private JButton conectarButton;
	private JTextField enderecoJTextField;
	private JTextField portaJTextField;
	
	
	public void addButtonListener(ActionListener listener) {
		conectarButton.addActionListener(listener);
	}

	public int getPorta(){
		try {
			return Integer.valueOf(this.portaJTextField.getText());
		}catch (Exception e) {
			return -1;
		}
	}
	
	public String getEndereco() {
		return this.enderecoJTextField.getText();
	}
	
}
