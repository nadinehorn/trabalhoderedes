package redes.cliente.view;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class ListarWindow extends JFrame {
	public ListarWindow() {
		setTitle("Browser de HTML");
		setResizable(false);
		this.setSize(455, 324);
		getContentPane().setLayout(null);
		
		btnListarArquivos = new JButton("Listar arquivos");
		btnListarArquivos.setBounds(10, 11, 161, 23);
		getContentPane().add(btnListarArquivos);
		
		listaDeArquivos = new JList();
		listaDeArquivos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listaDeArquivos.setBounds(10, 45, 430, 202);
		getContentPane().add(listaDeArquivos);
		
		btnPegarArquivosHtml = new JButton("Pegar Htmls selecionados");
		btnPegarArquivosHtml.setBounds(244, 11, 196, 23);
		getContentPane().add(btnPegarArquivosHtml);
		
		label = new JLabel("");
		label.setBounds(10, 271, 430, 14);
		getContentPane().add(label);
		
		separator = new JSeparator();
		separator.setBounds(10, 258, 430, 2);
		getContentPane().add(separator);
		
		this.setVisible(true);
	}
	private static final long serialVersionUID = -2113708663479365979L;
	private JButton btnPegarArquivosHtml;
	private JButton btnListarArquivos;
	private JList listaDeArquivos;
	private JLabel label;
	private JSeparator separator;
	
	public void addPegarListagemListener(ActionListener listener) {
		this.btnListarArquivos.addActionListener(listener);
	}
	
	public void addPegarArquivosListener(ActionListener listener) {
		this.btnPegarArquivosHtml.addActionListener(listener);
	}
	
	public ArrayList<String> pegaHtmlsSelecionados() {
		ArrayList<String> listagemDeArquivosSelecionados = new ArrayList<String>();
		
		for (Object object : this.listaDeArquivos.getSelectedValues()) {
			listagemDeArquivosSelecionados.add((String) object);
		}
		return listagemDeArquivosSelecionados;
	}
	
	public void setaListaDeArquivos (ArrayList<String> lista ) {
		this.listaDeArquivos.setListData(lista.toArray());
	}
	
	public void setStatusMessage(String message) {
		label.setText(message);
	}
	
}
