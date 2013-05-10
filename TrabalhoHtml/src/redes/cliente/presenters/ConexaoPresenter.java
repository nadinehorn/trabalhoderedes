package redes.cliente.presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JOptionPane;

import redes.cliente.view.ConexaoWindow;
import redes.cliente.view.ListarWindow;

/**
 * Controla a janela de conexao com o servidor.
 * @author Nadine
 *
 */
public class ConexaoPresenter implements ActionListener {
	
	private ConexaoWindow view;

	public ConexaoPresenter(ConexaoWindow conexaoWindow) {
		this.view = conexaoWindow;
		this.view.addButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//pega as informações da janela e abre um socket
		Socket socket;
		String enderecoDoServidor = this.view.getEndereco();
		int porta = this.view.getPorta();
		
		try {
			socket = new Socket(enderecoDoServidor, porta);
		}catch (Exception exception) {
			JOptionPane.showMessageDialog(this.view, "Falha na conexão!\nVerifique o endereço correto do servidor.");
			return;
		}
		
		new ListarPresenter(new ListarWindow(), socket);
		this.view.dispose();
	}
	
	
}
