package redes.servidor.core.tratador.providers;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import redes.servidor.core.filewalker.ProcuradorArquivos;

public class UploaderDeListagem {

	public void mandaListagem(String caminhoPasta, Socket socket) {
		//usa o socket e o caminho da pasta para mandar a listagem dos arquivos.
		ArrayList<String> caminhosDosHtmls = ProcuradorArquivos.listaArquivos(caminhoPasta, "html");
		
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			outStream.writeObject(caminhosDosHtmls);
			outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
	}

}
