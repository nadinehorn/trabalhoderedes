package redes.cliente.presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;

import redes.cliente.core.servico.handling.arquivos.TratadorDeHtmls;
import redes.cliente.core.servico.handling.arquivos.TratadorDeListagem;
import redes.cliente.view.ListarWindow;



public class ListarPresenter {

	private final Socket socket;
	private ListarWindow view;
	private TratadorDeListagem tratadorDeListagem;

	private ActionListener listarArquivosListener = new ActionListener() {
		@Override

		public void actionPerformed(ActionEvent e) {
			try {
				ListarPresenter.this.view.setStatusMessage("Pegando listagem dos arquivos..");
				ListarPresenter.this.tratadorDeListagem.baixaListagem();
				ArrayList<String> lista = ListarPresenter.this.tratadorDeListagem.getListaRetornada();
				ListarPresenter.this.view.setaListaDeArquivos(lista);
				ListarPresenter.this.view.setStatusMessage("Pronto.");
			} catch (Exception exception) {
				ListarPresenter.this.view.setStatusMessage("Problemas na comunicação com servidor!");
			}
		}
	};
	
	private ActionListener pegarArquivosListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> listaDeArquivosHtmlSelecionados = ListarPresenter.this.view.pegaHtmlsSelecionados();
			new Thread(new TratadorDeHtmls(ListarPresenter.this.socket, 
					listaDeArquivosHtmlSelecionados)).start();
		}
	};
	
	public ListarPresenter(ListarWindow listarWindow, Socket socket) {
		this.view = listarWindow;
		this.socket = socket;
		this.tratadorDeListagem = new TratadorDeListagem(socket);
		this.view.addPegarListagemListener(listarArquivosListener);
		this.view.addPegarArquivosListener(pegarArquivosListener);
	
	}
	
	
}
