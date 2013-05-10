package redes.common;

import java.io.File;
import java.io.InputStream;

public class FileUtils {
	
	public static void ensureFileCreation(String caminhoArquivo) {
		//coloca duas barras por causa do escape
		String [] pastas = caminhoArquivo.replaceAll("/", "\\").split("\\\\");
		String caminhoCompleto = "";
		//percorre pastas e subpastas
		for (String subPasta : pastas) {
			caminhoCompleto += subPasta;
			File file = new File(caminhoCompleto);
			if (!file.exists() && file.isDirectory()) {
				file.mkdir();
			}
		}
	}

}
