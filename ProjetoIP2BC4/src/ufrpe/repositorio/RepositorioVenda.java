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


	private static final long serialVersionUID = 3552918299042485851L;

	// ATRIBUTOS
	private ArrayList<ItemVenda> itensvenda = new ArrayList<>();
	private ArrayList<NotaFiscal> notas = new ArrayList<>();
	private int contadorCodigoNota = 1000;
	
	// SINGLETON / CONSTRUTOR

	private static RepositorioVenda instancia;

	private RepositorioVenda() {
		itensvenda = new ArrayList<>();
		notas = new ArrayList<>();
	}

	public static RepositorioVenda getInstancia() {
		if (instancia == null) {
			instancia = RepositorioVenda.carregarArquivo();
		}
		return instancia;
	}
	

	public int getContadorCodigoNota() {
		return contadorCodigoNota;
	}

	// METODOS
	public List<ItemVenda> listar() {
		return itensvenda;
	}

	public ArrayList<ItemVenda> getItensvenda() {
		return itensvenda;
	}

	public List<NotaFiscal> listarNotasFiscais() {
		return notas;
	}

	public void adicionarNotaFiscal(NotaFiscal notaFiscal) {
		notas.add(notaFiscal);
		contadorCodigoNota++;
	}

	public void limparHistoricoNotasFiscais() {
		this.notas.clear();
	}

	public void inserirItemVenda(ItemVenda item) {
		itensvenda.add(item);
	}

	public void removerItemVenda(int posicao) {
		itensvenda.remove(posicao);
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
					System.out.println("Nao foi possivel fechar o arquivo!");
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
