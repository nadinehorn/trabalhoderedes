package redes.servidor.core.filewalker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProcuradorArquivos {
	
	
	private static void caminhaNosArquivos(ArrayList<String> caminhos,
			String caminhoBase, String caminhoAbsoluto, String extensao) {
		File root = new File( caminhoBase );
		//adiciona barras porque sempre a primeira escapa
		String caminhoAbsolutoEscapado = caminhoAbsoluto.replaceAll("\\\\", "\\\\\\\\");
		
        for ( File arquivoBase : root.listFiles() ) {
            String caminhoAbsolutoDoArquivoSendoIterado = "";
			caminhoAbsolutoDoArquivoSendoIterado = arquivoBase.getAbsolutePath();
			if ( arquivoBase.isDirectory() ) {
            	caminhaNosArquivos(caminhos, caminhoAbsolutoDoArquivoSendoIterado, caminhoAbsoluto, extensao);
            } else if(caminhoAbsolutoDoArquivoSendoIterado.endsWith(extensao)){
            	
            	String caminhoArquivo = caminhoAbsolutoDoArquivoSendoIterado.replaceFirst(caminhoAbsolutoEscapado+"\\\\", "");
				caminhos.add(caminhoArquivo);
            }
        }
	}
	
	public static ArrayList<String> listaArquivos(String caminhoBase, String extensao) {
		ArrayList<String> caminhosArquivos = new ArrayList<String>();
		caminhaNosArquivos(caminhosArquivos, caminhoBase, caminhoBase, extensao);
		return caminhosArquivos;
	}
}
