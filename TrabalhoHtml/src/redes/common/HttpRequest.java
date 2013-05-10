package redes.common;
import java.io.Serializable;
import java.util.List;


public class HttpRequest implements Serializable{
	private static final long serialVersionUID = -6269863306833091938L;
	
	private RequestType httpRequestType;
	private String arquivoSelecionado;
	
	public HttpRequest() {
	}

	public RequestType getHttpRequestType() {
		return httpRequestType;
	}

	public void setHttpRequestType(RequestType httpRequestType) {
		this.httpRequestType = httpRequestType;
	}

	public void setArquivoSelecionado(String listaDeArquivo) {
		this.arquivoSelecionado = listaDeArquivo;
	}

	public String getArquivoSelecionado() {
		return arquivoSelecionado;
	}

}
