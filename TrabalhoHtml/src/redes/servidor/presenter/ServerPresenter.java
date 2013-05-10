package redes.servidor.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import redes.servidor.core.tratador.SocketListener;
import redes.servidor.view.ServerWindow;

public class ServerPresenter implements ActionListener, WindowListener{

	private SocketListener listener;
	private Thread listenerThread;
	private final ServerWindow view;
	private boolean serverEnabled = false;
	
	public ServerPresenter(ServerWindow view) {
		this.view = view;
		this.view.addBtnClickListenerDoServidor(this);
		this.view.addWindowListener(this);
	}
	
	private void startServer(String caminho, Integer porta) throws Exception {
		this.stopServer();
		this.listener = new SocketListener(caminho, porta);
		this.listenerThread = new Thread(listener);
		this.listenerThread.start();
	}

	private void stopServer() throws IOException {
		if (this.listener != null) {
			this.listener.killServer();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!validaCampos())
			return;

		this.toggleServidor();
	}

	
	protected boolean validaCampos() {
		String caminhoPasta = "";
		try {
			caminhoPasta = this.view.getCaminhoPasta();
			this.view.getPorta();
			File ponteiroDoArquivo = new File(caminhoPasta);
			if (!ponteiroDoArquivo.isDirectory())
				throw new Exception();
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					this.view, "Favor completar o campo de caminho e porta do servidor corretamente!");
			return false;
		}
	}

	//Inicia ou para o servidor, dependendo se estava previamente iniciado ou nao.
	protected void toggleServidor() {
		if (this.serverEnabled) {
			try {
				stopServer();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this.view, "Erro ao fechar o servidor..");
				e.printStackTrace();
			}
			this.view.enableFields();
			this.serverEnabled = false;
		} else {
			try {
				String caminho = this.view.getCaminhoPasta();
				Integer porta = this.view.getPorta();
				this.startServer(caminho, porta);
				this.view.disableFields();
				this.serverEnabled = true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.view, "Aconteceu um erro ao iniciar o servidor..\n" +
						"Detalhes: " + e.getMessage());
			}
			
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		//lixo do listener
	}

	@Override
	public void windowClosed(WindowEvent e) {
		//lixo do listener
	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			this.stopServer();
			this.view.dispose();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		//lixo do listener
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		//lixo do listener
	}

	@Override
	public void windowIconified(WindowEvent e) {
		//lixo do listener
	}

	@Override
	public void windowOpened(WindowEvent e) {
		//lixo do listener
	}
	
}
