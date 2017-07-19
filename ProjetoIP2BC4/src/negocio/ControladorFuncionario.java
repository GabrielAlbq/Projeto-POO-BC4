
package negocio;

import beans.Funcionario;
import repositorios.IRepositorioFuncionario;
import repositorios.RepositorioFuncionario;

public class ControladorFuncionario {
	
	// ATRIBUTO 
	
	private IRepositorioFuncionario repoFuncionario;
	
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
	// TODO implementar o cadastro referente a vendedores e gerentes utilizando polimorfismo
	public int retornaPosicao (int identificacao) {
		if( identificacao <= 0 )
			return -1; // Retorna -1 se a identificacao for negativa ou se nao achar a posicao
		
		for (Funcionario func : ((RepositorioFuncionario)repoFuncionario).getFuncionarios()) {
			if(func.getIdentificacao() == identificacao) 
				return ((RepositorioFuncionario)repoFuncionario).getFuncionarios().indexOf(func);
		}
		return -1;
	}
	
	public String listarFuncionarios() {
		return ((RepositorioFuncionario)repoFuncionario).listarFuncionarios();
	}
	
	public boolean inserir (Funcionario funcionarioInserir) {
		if(funcionarioInserir == null) {
			System.out.println("\n\n\tErro! Funcionario nulo!\n\n");
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
		System.out.println("\tFuncionario Inserido com sucesso!");
		return repoFuncionario.inserir(funcionarioInserir);
	}
	
	
	public boolean remover (int identificacao) {
		if(identificacao <= 0) {
			System.out.println("\n\tErro! Identificacao invalida!\n\n");
			return false;
		}
		if( ((RepositorioFuncionario)repoFuncionario).getFuncionarios().size() == 0) {
			System.out.println("\n\tErro! Nao ha funcionario para remover!");
			return false;
		}
		int checagem = retornaPosicao(identificacao);
		if(checagem == -1) {
			System.out.println("\n\tErro! Funcionario nao exite!\n\n");
			return false;
		}
		System.out.println("\tFuncionario removido com sucesso!");
		return ((RepositorioFuncionario)repoFuncionario).remover(checagem);
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
		return ((RepositorioFuncionario)repoFuncionario).alterar(funcionarioAlterar, checagem);
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
	
	public String listarTodos() {
		return ((RepositorioFuncionario)repoFuncionario).listarFuncionarios();
	}
}
