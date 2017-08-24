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

import ufrpe.negocio.beans.Admin;
import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.Login;

public class RepositorioFuncionario implements IRepositorioFuncionario, Serializable {

	// ATRIBUTOS

	private List<Funcionario> funcs;

	// SINGLETON

	private static RepositorioFuncionario instancia;

	public static RepositorioFuncionario getInstancia() {
		if (instancia == null) {
			instancia = RepositorioFuncionario.carregarArquivo();
		}
		return instancia;
	}

	// CONSTRUTOR

	private RepositorioFuncionario() {
		funcs = new ArrayList<>();
	}

	// METODOS

	public void pagarFancionario(int posicao) {
		funcs.get(posicao).setRecebeuSalario(true);
	}

	public void inserir(Funcionario funcionario) {
		funcs.add(funcionario);
	}

	public void remover(int posicao) {
		funcs.remove(posicao);
	}

	public void alterar(Funcionario funcionario, int posicao) {
		funcs.set(posicao, funcionario);
	}

	public Funcionario buscar(int posicao) {
		return funcs.get(posicao);
	}

	public Funcionario retornarFuncionario(int identificacao) {
		return funcs.get(identificacao);
	}

	// GETS

	public List<Funcionario> getFuncionarios() {
		return funcs;
	}

	private static RepositorioFuncionario carregarArquivo() {

		RepositorioFuncionario repositorio = null;

		File carregar = new File("RepositorioFuncionario.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {

			fis = new FileInputStream(carregar);
			ois = new ObjectInputStream(fis);

			repositorio = (RepositorioFuncionario) ois.readObject();
		} catch (Exception e) {
			repositorio = new RepositorioFuncionario();

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
			File salvar = new File("RepositorioFuncionario.dat");
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

	public void alterarLogin(Login log, int posicao) {
		this.funcs.get(posicao).setLogin(log);
	}
}
