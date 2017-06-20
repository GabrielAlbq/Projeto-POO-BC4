
package negocio;

import beans.Funcionario;
import repositorios.RepositorioFuncionario;

public class ControladorFuncionario {
	
	private RepositorioFuncionario repoFuncionario;
	
	// SINGLETON
	
	private static ControladorFuncionario instancia;
	
	public static ControladorFuncionario getInstancia () {
		if( instancia == null ) {
			instancia = new ControladorFuncionario();
		}
		return instancia;
	}
	
	// CONSTRUTOR
	
	private ControladorFuncionario () {
		repoFuncionario = RepositorioFuncionario.getInstancia();
	}
	
	// METODOS - As validacoes sao feitas nesta classe para chamar os metodos do repositorio

	public int retornaPosicao (int identificacao) {
		Funcionario[] funcionarioTeste = repoFuncionario.getFuncionario();
		
		if( identificacao <= 0 ) {
			return -1; // Retorna -1 se a identificacao for negativa ou se nao achar a posicao
		}
		for(int n = 0; n < repoFuncionario.getQtdFuncionario() ; n++ ) {
			if( identificacao == funcionarioTeste[n].getIdentificacao() ) {
				return n;
			}
		}
		return -1;
	}
	
	public String listarFuncionarios() {
		return repoFuncionario.listarFuncionarios();
	}
	
	public boolean inserir (Funcionario funcionarioInserir) {
		if(funcionarioInserir == null) {
			System.out.println("\n\n\tErro! Funcionario nulo!\n\n");
			return false;
		}
		if(repoFuncionario.getQtdFuncionario() == 10) {
			System.out.println("\n\tErro! Maximo de funcionarios ja atingido!\n\n");
			return false;
		}
		if(funcionarioInserir.getIdentificacao() <= 0) {
			System.out.println("\n\tErro! Identificacao invalida!");
			return false;
		}
		int checagem = retornaPosicao(funcionarioInserir.getIdentificacao());
		
		if(checagem != -1) {
			System.out.println("\n\tErro! Funcionario ja cadastrado!\n\n");
			return false;
		}
		
		System.out.println("\tInserido com sucesso!");
		repoFuncionario.inserir(funcionarioInserir);
		
		return true;
	}
	
	
	public boolean remover (int identificacao) {
		if(repoFuncionario.getQtdFuncionario() == 0) {
			System.out.println("\n\tErro! Nao ha funcionario para remover!");
			return false;
		}
		if(identificacao <= 0) {
			System.out.println("\n\tErro! Identificacao invalida!\n\n");
			return false;
		}
		
		int checagem = retornaPosicao(identificacao);
		
		if(checagem == -1) {
			System.out.println("\n\tErro! Funcionario nao exite!\n\n");
			return false;
		}
		
		System.out.println("\tFuncionario removido com sucesso!");
		repoFuncionario.remover(checagem);
		
		return true;
	}
	
	public boolean alterar (Funcionario funcionarioAlterar) {
		if(funcionarioAlterar == null) {
			System.out.println("\tErro! Funcionario nulo!");
			return false;
		}
		if(funcionarioAlterar.getIdentificacao() <= 0) {
			System.out.println("\n\tErro! Identificacao invalida!");
			return false;
		}

		int checagem = retornaPosicao(funcionarioAlterar.getIdentificacao());
		
		if(checagem == -1) {
			System.out.println("\n\tErro! Funcionario nao exite!\n\n");
			return false;
		}
		
		System.out.println("\n\tFuncionario alterado com sucesso!\n\n");
		repoFuncionario.alterar(funcionarioAlterar, checagem);
		
		return true;
	}
	
	public Funcionario buscar (int identificacao) {
		if(identificacao <= 0) {
			System.out.println("\n\tErro! Identificacao invalida!\n\n");
			return null;
		}
		
		int checagem = retornaPosicao(identificacao);
		
		if(checagem == -1) {
			System.out.println("\tErro! Funcionario inexistente!");
			return null;
		}
		
		return repoFuncionario.buscar(checagem);
	}
	
	public void imprimirTodos() {
		repoFuncionario.imprimirTodos();
	}
	
	public RepositorioFuncionario getRepositorioFuncionario () {
		return repoFuncionario;
	}
	
	//TODO método pagar funcionario
	//TODO método calcular adicional
	//TODO listar produtos vendidos
}
