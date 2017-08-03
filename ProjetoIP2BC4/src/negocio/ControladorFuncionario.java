
package negocio;

import beans.Funcionario;
import repositorios.IRepositorioFuncionario;
import repositorios.RepositorioFuncionario;

public class ControladorFuncionario {
	
	// ATRIBUTO 
	
	private IRepositorioFuncionario repoFuncionario;
	private static ControladorFuncionario instancia;
	
	// SINGLETON
	
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
	
	// METODOS
	
	public void inserir (Funcionario funcionarioInserir) {
		if(funcionarioInserir == null) {
			System.out.println("\n\n\tErro! Funcionario nulo!\n\n");
		}
		if(funcionarioInserir.getIdentificacao() <= 0) {
			System.out.println("\n\tErro! Identificacao invalida!");
		}
		if(!repoFuncionario.getFuncionarios().contains(funcionarioInserir)) {
			repoFuncionario.inserir(funcionarioInserir);
			//instancia.repoFuncionario.salvarArquivo();
		}
	}
	
	
	public void remover (int identificacao) {
		if(identificacao <= 0) {
			System.out.println("\n\tErro! Identificacao invalida!\n\n");
		}
		if( ((RepositorioFuncionario)repoFuncionario).getFuncionarios().size() == 0) {
			System.out.println("\n\tErro! Nao ha funcionario para remover!");
		}
		int checagem = retornaPosicao(identificacao);
		if(checagem == -1) {
			System.out.println("\n\tErro! Funcionario nao exite!\n\n");
		}
		System.out.println("\tFuncionario removido com sucesso!");
		((RepositorioFuncionario)repoFuncionario).remover(checagem);
			instancia.repoFuncionario.salvarArquivo();
	}
	
	public void alterar (Funcionario funcionarioAlterar) {
		if(funcionarioAlterar == null) {
			System.out.println("\tErro! Funcionario nulo!");
		}
		if(funcionarioAlterar.getIdentificacao() <= 0) {
			System.out.println("\n\tErro! Identificacao invalida!");
		}
		int checagem = retornaPosicao(funcionarioAlterar.getIdentificacao());
		if(checagem == -1) {
			System.out.println("\n\tErro! Funcionario nao exite!\n\n");
		}
		System.out.println("\n\tFuncionario alterado com sucesso!\n\n");
		repoFuncionario.alterar(funcionarioAlterar, checagem);
			instancia.repoFuncionario.salvarArquivo();
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
	
	public int retornaPosicao (int identificacao) {
		if( identificacao <= 0 )
			return -1;
		
		for (Funcionario f : repoFuncionario.getFuncionarios()) {
			if(f.getIdentificacao() == identificacao) 
				return repoFuncionario.getFuncionarios().indexOf(f);
		}
		
		return -1;
	}
	
	public String listarFuncionarios() {
		return repoFuncionario.listarFuncionarios();
	}
}
