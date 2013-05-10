package redes.servidor.core.main;

import java.io.IOException;

import redes.servidor.presenter.ServerPresenter;
import redes.servidor.view.ServerWindow;

public class ServerLauncher {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new ServerPresenter(new ServerWindow());
	}

}
