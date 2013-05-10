package redes.cliente.core.servico.handling.arquivos;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserDeHtml {
	
	public Set<String> pegaCaminhosDasImagens(List<File> arquivosHtml) throws IOException {
		 Set<String> listaDeImagens = new HashSet<String>();
		 // pega apenas arquivos .JPG
		 for (File arquivoHtml : arquivosHtml) {
			 Document parse = Jsoup.parse(arquivoHtml, "UTF-8");
			 Elements elementsByTag = parse.getElementsByTag("img");
			 for (Element element : elementsByTag) {
				 String imageName = element.attr("src");
				 if (imageName.endsWith(".jpg")){
					 listaDeImagens.add(imageName);
				 }
			 }
		 }
		 return listaDeImagens;
	}
	
}
