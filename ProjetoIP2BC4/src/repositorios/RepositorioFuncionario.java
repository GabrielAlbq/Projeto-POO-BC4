package repositorios;

import beans.Funcionario;

public class RepositorioFuncionario {
	private final int TAMMAX = 10;
	private Funcionario[] funcionario;
	private int qtdFuncionario; // é o int proxima do class conta
	
	// singleton
	
	private static RepositorioFuncionario instancia;
	
	public static RepositorioFuncionario instanciarRepoFuncionario () {
		if( instancia == null ) {
			instancia = new RepositorioFuncionario();
		}
		return instancia;
	}
	
	// construtor
	
	private RepositorioFuncionario() {
		funcionario = new Funcionario[TAMMAX];
		qtdFuncionario = 0;
	}
	
	// metodos - as validações serão feitas no controlador utilizando estes metodos
	
	public void inserir (Funcionario funcionario) {
		this.funcionario[qtdFuncionario] = funcionario;
		qtdFuncionario++;
	}
	
	public void remover (int posicao) {
		funcionario[posicao] = funcionario[qtdFuncionario-1];
		funcionario[qtdFuncionario-1] = null;
		qtdFuncionario--;
	}
	
	public void alterar (Funcionario funcionario, int posicao) {
		this.funcionario[posicao] = funcionario;
	}
	
	public Funcionario buscar (int posicao) {
		return this.funcionario[posicao];
	}
	
	// gets / sets
	
	public Funcionario[] getFuncionario() {
		return funcionario;
	}

	public int getQtdFuncionario() {
		return qtdFuncionario;
	}

	public int getTAMMAX() {
		return TAMMAX;
	}
	
	public Funcionario retornarFuncionario(int identificacao) { // Foi usado na Fachada
		
		return funcionario[identificacao];
	}
	
	public void imprimirTodos() {
		for (int i = 0; i < qtdFuncionario; i++) {
			System.out.println(funcionario[i]);
		}
	}
}
