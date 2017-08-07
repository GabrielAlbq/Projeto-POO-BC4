package ufrpe.repositorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ufrpe.beans.Login;

public class RepositorioLogin implements IRepositorioLogin{

	// ATRIBUTO
	private List<Login> lista;
	
	// SINGLETON
	private static RepositorioLogin instancia;
	
	public static RepositorioLogin getInstancia() {
		if(instancia == null) {
			instancia = new RepositorioLogin();
		}
		return instancia;
	}
	
	private RepositorioLogin() {
		lista = new ArrayList<>();
	}
	
	// METODOS
	
	public void inserirLogin(Login login) {
		lista.add(login);
	}

	public void removerLogin(int posicao) {
		lista.remove(posicao);
	}

	public void alterarSenha(Login login, int posicao) {
		lista.set(posicao, login);
	}

	public Login buscarLogin(int posicao) {
		return lista.get(posicao);
	}

	public List<Login> listar() {
		return lista;
	}
	
	
	private static RepositorioLogin carregarArquivo() {

		RepositorioLogin repositorio = null;

		File carregar = new File("RepositorioEstoque.Mercadinho");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {

			fis = new FileInputStream(carregar);
			ois = new ObjectInputStream(fis);

			repositorio = (RepositorioLogin) ois.readObject();
		} catch (Exception e) {
			repositorio = new RepositorioLogin();

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
					System.out.println("Não foi possível fechar o arquivo!");
					e.printStackTrace();
				}
			}
		}

		return repositorio;
	}
	
	public void salvarArquivo() {
		if (!(instancia == null)) {
			File salvar = new File("RepositorioEstoque.Mercadinho");
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
