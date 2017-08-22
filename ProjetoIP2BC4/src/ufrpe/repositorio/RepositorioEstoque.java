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

import ufrpe.negocio.beans.Produto;

public class RepositorioEstoque implements IRepositorioEstoque, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8798820522911040420L;
	// ATRIBUTOS
	private List<Produto> produtos;
	private static RepositorioEstoque instancia;

	// CONSTRUTOR

	private RepositorioEstoque() {
		produtos = new ArrayList<>();
	}

	public static RepositorioEstoque getInstancia() {
		if (instancia == null) {
			//instancia = new RepositorioEstoque();
			instancia = RepositorioEstoque.carregarArquivo();

		}
		return instancia;
	}

	// GET

	public List<Produto> listar() {
		return produtos;
	}

	// METODOS

	public void subtrairProduto(int posicao, int quantidade) {
		this.produtos.get(posicao).setQuantidade(produtos.get(posicao).getQuantidade() - quantidade);
	}

	public void inserir(Produto prod) {
		this.produtos.add(prod);
	}

	public Produto buscar(int posicao) {
		return this.produtos.get(posicao);
	}

	public void remover(int posicao) {
		this.produtos.remove(posicao);
	}

	public void alterar(Produto novoProduto, int posicao) {
		this.produtos.set(posicao, novoProduto);
	}
		
	private static RepositorioEstoque carregarArquivo() {

		RepositorioEstoque repositorio = null;

		File carregar = new File("RepositorioEstoque.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {

			fis = new FileInputStream(carregar);
			ois = new ObjectInputStream(fis);

			repositorio = (RepositorioEstoque) ois.readObject();
		} catch (Exception e) {
			repositorio = new RepositorioEstoque();

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
			File salvar = new File("RepositorioEstoque.dat");
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
