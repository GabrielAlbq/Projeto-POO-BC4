package controladores;

import beans.Funcionario;
import repositorios.RepositorioFuncionario;

public class ControladorFuncionario {
	
	private RepositorioFuncionario repoFuncionario = RepositorioFuncionario.instanciarRepoFuncionario();
	
	// singleton
	
	private static ControladorFuncionario instancia;
	
	public static ControladorFuncionario instanciarControlFuncionario () {
		if( instancia == null ) {
			instancia = new ControladorFuncionario();
		}
		return instancia;
	}
	
	// construtor
	
	private ControladorFuncionario () {
		
	}
	
	// métodos - As validações sao feitas nesta classe para chamar os metodos do repositorio

	public int retornaPosicao (int identificacao) {
		Funcionario[] funcionarioTeste = repoFuncionario.getFuncionario();
		
		if( identificacao <= 0 ) {
			return -1; // retorna -1 se a identificação for negativa ou se nao achar a posicao
		}
		for(int n = 0; n < repoFuncionario.getQtdFuncionario() ; n++ ) {
			if( identificacao == funcionarioTeste[n].getIdentificacao() ) {
				return n;
			}
		}
		return -1;
	}
	
	public boolean inserir (Funcionario funcionarioInserir) {
		if(funcionarioInserir == null) {
			System.out.println("\n\n\tErro, funcionário nulo!\n\n");
			return false;
		}
		if(repoFuncionario.getQtdFuncionario() == 10) {
			System.out.println("\n\tErro, máximo de funcionarios já atingido!\n\n");
			return false;
		}
		if(funcionarioInserir.getIdentificacao() <= 0) {
			System.out.println("\n\tErro, identificação inválida!");
			return false;
		}
		int checagem = retornaPosicao(funcionarioInserir.getIdentificacao());
		
		if(checagem != -1) {
			System.out.println("\n\tErro, funcionário já cadastrado!\n\n");
			return false;
		}
		
		System.out.println("\n\tInserido com sucesso!\n\n");
		repoFuncionario.inserir(funcionarioInserir);
		
		return true;
	}
	
	
	public boolean remover (int identificacao) {
		if(repoFuncionario.getQtdFuncionario() == 0) {
			System.out.println("\n\tErro, não há funcionário para remover!");
			return false;
		}
		if(identificacao <= 0) {
			System.out.println("\n\tErro, identifica��o inválida!\n\n");
			return false;
		}
		
		int checagem = retornaPosicao(identificacao);
		
		if(checagem == -1) {
			System.out.println("\n\tErro, funcionário nao exite!\n\n");
			return false;
		}
		
		System.out.println("\n\tRemovido com sucesso!\n\n");
		repoFuncionario.remover(identificacao);
		
		return true;
	}
	
	public boolean alterar (Funcionario funcionarioAlterar) {
		if(funcionarioAlterar == null) {
			System.out.println("\n\n\tErro, funcionário nulo!\n\n");
			return false;
		}
		if(funcionarioAlterar.getIdentificacao() <= 0) {
			System.out.println("\n\tErro, identifica��o inválida!");
			return false;
		}
		
		int checagem = retornaPosicao(funcionarioAlterar.getIdentificacao());
		
		if(checagem == -1) {
			System.out.println("\n\tErro, funcionário nao exite!\n\n");
			return false;
		}
		
		System.out.println("\n\tAlterado com sucesso!\n\n");
		repoFuncionario.alterar(funcionarioAlterar, checagem);
		
		return true;
	}
	
	public Funcionario buscar (int identificacao) {
		if(identificacao <= 0) {
			System.out.println("\n\tErro, identifica��o inválida!\n\n");
			return null;
		}
		
		int checagem = retornaPosicao(identificacao);
		
		if(checagem == -1) {
			System.out.println("\n\tErro, conta inexistente\n\n");
			return null;
		}
		
		return repoFuncionario.buscar(checagem);
	}
	
	public void imprimirTodos() {
		repoFuncionario.imprimirTodos();
	}
	
	//TODO método pagar funcionario
	//TODO método calcular adicional
	//TODO listar produtos vendidos
}
