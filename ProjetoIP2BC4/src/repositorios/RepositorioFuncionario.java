package repositorios;

import java.util.ArrayList;

import beans.Funcionario;

public class RepositorioFuncionario implements IRepositorioFuncionario{
	
	// ATRIBUTOS 
	
	private ArrayList<Funcionario> funcs;
	
	// SINGLETON
	
	private static RepositorioFuncionario instancia;
	
	public static RepositorioFuncionario getInstancia() {
		if( instancia == null ) {
			instancia = new RepositorioFuncionario();
		}
		return instancia;
	}
	
	// CONSTRUTOR
	
	private RepositorioFuncionario() {
		funcs = new ArrayList<>();
	}
	
	// METODOS - as validacoes serao feitas no controlador utilizando estes metodos
	
	public String listarFuncionarios() {
		String t = "";
		for (int i = 0; i < funcs.size(); i++) {
			t += "\n_______________________________________\n"+funcs.get(i).toString();
		}
		return t;
	}
	
	public void pagarFancionario (int posicao){
		funcs.get(posicao).setRecebeuSalario(true);
	}
	
	public void inserir (Funcionario funcionario) {
		funcs.add(funcionario);
	}
	
	public void remover (int posicao) {
		funcs.remove(posicao);
	}
	
	public void alterar (Funcionario funcionario, int posicao) {
		funcs.set(posicao, funcionario);
	}
	
	public Funcionario buscar (int posicao) {
		return funcs.get(posicao);
	}
	
	public Funcionario retornarFuncionario(int identificacao) { // Foi usado na Fachada
		return funcs.get(identificacao);
	}
	
	// GETS
	
	public ArrayList<Funcionario> getFuncionarios() {
		return funcs;
	}
}
