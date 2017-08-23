package ufrpe.repositorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ufrpe.negocio.beans.ItemVenda;
import ufrpe.negocio.beans.NotaFiscal;

public class RepositorioVenda implements IRepositorioVenda, Serializable {

	// ATRIBUTOS

	private ArrayList<ItemVenda> itensvenda = new ArrayList<>();
	private ArrayList<NotaFiscal> notas = new ArrayList<>();

	// SINGLETON / CONSTRUTOR

	private static RepositorioVenda instancia;

	private RepositorioVenda() {
		itensvenda = new ArrayList<>();
		notas = new ArrayList<>();
	}

	public static RepositorioVenda getInstancia() {
		if (instancia == null) {
	//		instancia = new RepositorioVenda();
			instancia = RepositorioVenda.carregarArquivo();
		}
		return instancia;
	}

	// METODOS
	public List<ItemVenda> listar() {
		return itensvenda;
	}

	public ArrayList<ItemVenda> getItensvenda() {
		return itensvenda;
	}

	public String listaItensVenda() {
		String text = "";
		for (int i = 0; i < this.itensvenda.size(); i++) { // Imprimir na tela
															// todos os itens de
															// venda
			text += itensvenda.get(i).toString();
		}
		return text;
	}

	public List<NotaFiscal> listarNotasFiscais() {
		return notas;
	}

	public void adicionarNotaFiscal(NotaFiscal notaFiscal) { // Adiciona nota
																// fiscal
		notas.add(notaFiscal);
	}

	public void limparHistoricoNotasFiscais() { // Limpa todas as notas fiscais
		this.notas.clear();
	}

	public void inserirItemVenda(ItemVenda item) { // Adiciona um ItemVenda para
													// o mercadinho
		itensvenda.add(item);
	}

	public void limparArrayItemVenda() {
		this.itensvenda.clear();
	}
	
	private static RepositorioVenda carregarArquivo() {

		RepositorioVenda repositorio = null;

		File carregar = new File("RepositorioVenda.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {

			fis = new FileInputStream(carregar);
			ois = new ObjectInputStream(fis);

			repositorio = (RepositorioVenda) ois.readObject();
		} catch (Exception e) {
			repositorio = new RepositorioVenda();

			try {
				if (!carregar.exists()) {
					carregar.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(carregar);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(repositorio);
				oos.flush();
				oos.close();
				fos.flush();
				fos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("N�o foi poss�vel fechar o arquivo!");
					e.printStackTrace();
				}
			}
		}

		return repositorio;
	}

	@Override
	public void salvarArquivo() {
		if (!(instancia == null)) {
			File salvar = new File("RepositorioVenda.dat");
			try {
				if (!salvar.exists()) {
					salvar.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(salvar);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(instancia);
				oos.flush();
				oos.close();
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
