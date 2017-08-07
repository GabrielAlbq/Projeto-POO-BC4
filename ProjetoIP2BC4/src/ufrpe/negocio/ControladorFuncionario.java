package ufrpe.negocio;

import ufrpe.beans.Funcionario;
import ufrpe.negocio.exception.IdentificacaoInvalidaException;
import ufrpe.negocio.exception.InstanciaInexistenteException;
import ufrpe.negocio.exception.NegocioException;
import ufrpe.repositorio.IRepositorioFuncionario;
import ufrpe.repositorio.RepositorioFuncionario;

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
	
	public void inserir (Funcionario funcInserir) throws NegocioException{
		if(funcInserir == null) {
			//System.out.println("\n\n\tErro! Funcionario nulo!\n\n");
			throw new RuntimeException("\nInstancia de Funcionario nula!\n");
		}
		if(funcInserir.getIdentificacao() <= 0) {
			//System.out.println("\n\tErro! Identificacao invalida!");
			throw new IdentificacaoInvalidaException("\nIdentificacao invalida: " +
													 funcInserir.getIdentificacao() + "\n");
		}
		if(repoFuncionario.getFuncionarios().contains(funcInserir) == false) {
			repoFuncionario.inserir(funcInserir);
			// TODO arquivo inserir funcionario
			//instancia.repoFuncionario.salvarArquivo();
		}
	}
	
	public void remover (int identificacao) throws NegocioException{
		if(identificacao <= 0) {
			//System.out.println("\n\tErro! Identificacao invalida!\n\n");
			throw new IdentificacaoInvalidaException("\nIdentificacao invalida: " +
					 identificacao + "\n");
		}
		if( ((RepositorioFuncionario)repoFuncionario).getFuncionarios().size() == 0) {
			//System.out.println("\n\tErro! Nao ha funcionario para remover!");
			throw new InstanciaInexistenteException("\nNao ha funcionario para remover\n");
		}
		int checagem = retornaPosicao(identificacao);
		if(checagem == -1) {
			//System.out.println("\n\tErro! Funcionario nao exite!\n\n");
			throw new InstanciaInexistenteException("\nFuncionario nao exite!\n");
		}
		System.out.println("\tFuncionario removido com sucesso!");
		((RepositorioFuncionario)repoFuncionario).remover(checagem);
			instancia.repoFuncionario.salvarArquivo();
	}
	
	public void alterar (Funcionario funcionarioAlterar) throws NegocioException{
		if(funcionarioAlterar == null) {
			//System.out.println("\tErro! Funcionario nulo!");
			throw new RuntimeException("\nInstancia de Funcionario nula!\n");
		}
		if(funcionarioAlterar.getIdentificacao() <= 0) {
			//System.out.println("\n\tErro! Identificacao invalida!");
			throw new IdentificacaoInvalidaException("\nIdentificacao invalida: " +
					 funcionarioAlterar.getIdentificacao() + "\n");
		}
		int checagem = retornaPosicao(funcionarioAlterar.getIdentificacao());
		if(checagem == -1) {
			//System.out.println("\n\tErro! Funcionario nao exite!\n\n");
			throw new InstanciaInexistenteException("\nFuncionario nao exite!\n");
		}
		System.out.println("\n\tFuncionario alterado com sucesso!\n\n");
		repoFuncionario.alterar(funcionarioAlterar, checagem);
			instancia.repoFuncionario.salvarArquivo();
	}
	
	public Funcionario buscar (int identificacao){
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
		if( identificacao <= 0 ) {
			return -1;
		}
			
		for (Funcionario f : repoFuncionario.getFuncionarios()) {
			if(f.getIdentificacao() == identificacao) 
				return repoFuncionario.getFuncionarios().indexOf(f);
		}
		
		return -1;
	}
	
	public String listarFuncionarios() throws NegocioException{
		String texto = "";
		if(repoFuncionario.getFuncionarios().isEmpty()) {
			throw new InstanciaInexistenteException("\nNao ah funcionarios cadastrados\n");
		}
		for (Funcionario f : repoFuncionario.getFuncionarios()) {
			texto += "\n_______________________________________\n"+f.toString();
		}
		return texto;
	}
}