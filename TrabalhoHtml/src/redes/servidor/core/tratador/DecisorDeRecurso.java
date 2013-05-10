package redes.servidor.core.tratador;

import java.io.ObjectInputStream;
import java.net.Socket;

import redes.common.HttpRequest;
import redes.servidor.core.tratador.providers.UploaderDeArquivos;
import redes.servidor.core.tratador.providers.UploaderDeListagem;

public class DecisorDeRecurso implements Runnable {

	private Socket socket;
	private final String caminhoPasta;
	private UploaderDeArquivos providerDeArquivos;
	private UploaderDeListagem providerDeListagem;

	public DecisorDeRecurso(String caminho, Socket socket) {
		this.caminhoPasta = caminho;
		this.socket = socket;
		this.providerDeArquivos = new UploaderDeArquivos();
		this.providerDeListagem = new UploaderDeListagem();
	}
	
	@Override
	public void run() {
		try {
			
			while (socket != null && socket.isConnected() && socket.isBound()) {
				
				ObjectInputStream objectInStream = new ObjectInputStream(this.socket.getInputStream());
				//aguarda httpRequest do cliente
				HttpRequest httpRequest = (HttpRequest) objectInStream.readObject();
				
				switch (httpRequest.getHttpRequestType()) {
					case GET_ARQUIVOS:
						this.providerDeArquivos.mandaArquivos(httpRequest.getArquivoSelecionado(),
								caminhoPasta, socket);
						break;
					case GET_LISTAGEM:
						this.providerDeListagem.mandaListagem(caminhoPasta, socket);
						break;
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
