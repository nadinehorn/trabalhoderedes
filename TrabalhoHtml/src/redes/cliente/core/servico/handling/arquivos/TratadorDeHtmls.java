package redes.cliente.core.servico.handling.arquivos;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TratadorDeHtmls implements Runnable {

	private final List<String> htmlsABaixar;
	private final Socket socket;
	private ParserDeHtml parser = new ParserDeHtml();
	private BaixadorDeArquivos baixador = new BaixadorDeArquivos();

	public TratadorDeHtmls(Socket socket, List<String> htmlsABaixar) {
		this.socket = socket;
		this.htmlsABaixar = htmlsABaixar;
	}

	@Override
	public void run() {
		String caminhoAbsolutoOndeSalvarNoCliente = "C:\\temp";
		
		//baixa os arquivos HTML
		ArrayList<File> htmlsBaixados = new ArrayList<File>();
		for (String htmlABaixar : htmlsABaixar) {
			try {
				htmlsBaixados.add(baixador.baixaArquivo(htmlABaixar, 
						caminhoAbsolutoOndeSalvarNoCliente, socket));
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		
		Set<String> caminhosDasImagens;
		// Uma vez que baixou os HTMLs, faz o parsing dos mesmos
		try {
			caminhosDasImagens = parser.pegaCaminhosDasImagens(htmlsBaixados);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	

		//Baixa as imagens encontradas dentro dos arquivos HTML
		for (String caminhoDaImagem : caminhosDasImagens) {
			try {
				baixador.baixaArquivo(caminhoDaImagem, caminhoAbsolutoOndeSalvarNoCliente, socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
