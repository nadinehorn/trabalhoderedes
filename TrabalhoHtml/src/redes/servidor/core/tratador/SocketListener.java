package redes.servidor.core.tratador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener implements Runnable{

	private final String caminhoPasta;
	private ServerSocket server;
	
	public SocketListener(String caminhoPasta, int porta) throws IOException {
		this.server = new ServerSocket(porta);
		System.out.println("Escutando conexões na porta " + porta);
		this.caminhoPasta = caminhoPasta;
	}
	
	public void killServer() throws IOException {
		server.close();
		System.out.println("Fechando conexões abertas, se há...");
	}

	@Override
	public void run() {
		while (server != null && !server.isClosed() && server.isBound()) {
			try {
				Socket socket = this.server.accept();
				System.out.println("Cliente conectou ao servidor..");
				new Thread(new DecisorDeRecurso(caminhoPasta, socket)).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
