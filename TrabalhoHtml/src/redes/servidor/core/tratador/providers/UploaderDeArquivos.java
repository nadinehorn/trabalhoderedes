package redes.servidor.core.tratador.providers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.google.common.io.Files;
import com.google.common.io.OutputSupplier;

public class UploaderDeArquivos {

	public void mandaArquivos(String arquivoDesejado, String caminhoPastaServidor, final Socket socket) {
		File arquivoAEnviar = new File(caminhoPastaServidor+"\\"+arquivoDesejado.replaceAll("/", "\\"));
//		try {
//			socket.getOutputStream().flush();
//			Files.copy(arquivoAEnviar, socket.getOutputStream());
//			socket.getOutputStream().flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		try {
			Files.copy(arquivoAEnviar, new OutputSupplier<OutputStream>() {
				@Override
				public OutputStream getOutput() throws IOException {
					return socket.getOutputStream();
				}
			});
			Files.copy(arquivoAEnviar, socket.getOutputStream());
			socket.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
