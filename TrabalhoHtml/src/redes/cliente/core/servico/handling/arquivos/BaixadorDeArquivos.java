package redes.cliente.core.servico.handling.arquivos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import redes.common.FileUtils;
import redes.common.HttpRequest;
import redes.common.RequestType;

import com.google.common.io.Files;
import com.google.common.io.InputSupplier;

public class BaixadorDeArquivos{

	public File baixaArquivo(String caminhoDoArquivoNoServidor, String caminhoLocal, final Socket socket) throws IOException {
		HttpRequest request = new HttpRequest();
		request.setHttpRequestType(RequestType.GET_ARQUIVOS);
		request.setArquivoSelecionado(caminhoDoArquivoNoServidor);

		
		ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
		outStream.writeObject(request);
			
		outStream.flush();
 
		File arquivo = new File(caminhoLocal+"\\"+caminhoDoArquivoNoServidor);
		try{
			
		
		FileUtils.ensureFileCreation(arquivo.getAbsolutePath());
		Files.copy(new InputSupplier<InputStream>() {
			@Override
			public InputStream getInput() throws IOException {
				return socket.getInputStream();
			}
		}, arquivo);
		}
		catch (Exception e) { 
			e.printStackTrace(); 
			}
		return arquivo;
	}
	
	
}
