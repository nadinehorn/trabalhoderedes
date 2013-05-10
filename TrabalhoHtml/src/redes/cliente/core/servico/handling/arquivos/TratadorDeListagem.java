package redes.cliente.core.servico.handling.arquivos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import redes.common.HttpRequest;
import redes.common.RequestType;

public class TratadorDeListagem {

	private ArrayList<String> listagemDeArquivos = new ArrayList<String>();
	private Socket socket;

	public TratadorDeListagem(Socket socket) {
		this.socket = socket;
	}

	@SuppressWarnings("unchecked")
	public void baixaListagem() throws IOException, ClassNotFoundException {
		listagemDeArquivos.clear();
		//manda request para o servidor para listar todos os arquivos
		HttpRequest request = new HttpRequest();
		request.setHttpRequestType(RequestType.GET_LISTAGEM);
		ObjectOutputStream objectOutStream = new ObjectOutputStream(socket.getOutputStream());
		objectOutStream.writeObject(request);
		objectOutStream.flush();
		
		//pega listagem de arquivos do servidor
		ObjectInputStream objectInStream = new ObjectInputStream(socket.getInputStream());
		this.listagemDeArquivos = (ArrayList<String>) objectInStream.readObject();
	}

	public ArrayList<String> getListaRetornada() {
		return listagemDeArquivos;
	}

}
