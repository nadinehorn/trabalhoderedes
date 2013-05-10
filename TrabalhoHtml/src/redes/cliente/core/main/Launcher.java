package redes.cliente.core.main;

import redes.cliente.presenters.ConexaoPresenter;
import redes.cliente.view.ConexaoWindow;

public class Launcher {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		new ConexaoPresenter(new ConexaoWindow());
	}

}
