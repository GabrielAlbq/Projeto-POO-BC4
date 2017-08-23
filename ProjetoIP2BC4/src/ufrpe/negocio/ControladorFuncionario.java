package ufrpe.negocio;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.Login;
import ufrpe.negocio.exception.IdentificacaoInvalidaException;
import ufrpe.negocio.exception.InstanciaInexistenteException;
import ufrpe.negocio.exception.InstanciaRepetidaException;
import ufrpe.negocio.exception.LoginInvalidoExecption;
import ufrpe.negocio.exception.NegocioException;
import ufrpe.repositorio.IRepositorioFuncionario;
import ufrpe.repositorio.RepositorioFuncionario;

public class ControladorFuncionario {
	
	// ATRIBUTO 
	
	private IRepositorioFuncionario repoFuncionario;
	private static ControladorFuncionario instancia;
	Alert alert = new Alert(AlertType.INFORMATION);
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
		if(retornaPosicao(funcInserir.getIdentificacao()) != -1) {
			throw new InstanciaRepetidaException("\nFuncionario de id("+funcInserir.getIdentificacao()
													+") ja foi cadastrado!\n");
		}
		if(retornaPosicaoLogin(funcInserir.getLogin().getUser()) != -1) {
			throw new InstanciaRepetidaException("\nNome de usuario("+
									funcInserir.getLogin().getUser()+") ja esta cadastrado!\n");
		}
		repoFuncionario.inserir(funcInserir);
		instancia.repoFuncionario.salvarArquivo();
	}
	
	public void remover (int identificacao) throws NegocioException{
		if(identificacao <= 0) {
			//System.out.println("\n\tErro! Identificacao invalida!\n\n");
			throw new IdentificacaoInvalidaException("\nIdentificacao invalida: " +
					 identificacao + "\n");
		}
		if( ((RepositorioFuncionario)repoFuncionario).getFuncionarios().size() == 0) {
			//System.out.println("\n\tErro! Nao ha funcionario para remover!");
			throw new InstanciaInexistenteException("\nNao ha funcionario para remover!\n");
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
	
	public List<Funcionario> listarFuncionarios() throws NegocioException{
		if(repoFuncionario.getFuncionarios().isEmpty()) {
			throw new InstanciaInexistenteException("\nNao ah funcionarios cadastrados\n");
		}
		return repoFuncionario.getFuncionarios();
	}
	
	// METODOS DO SISTEMA DE LOGIN
	
	public int retornaPosicaoLogin (String user) {
		if(user == null) {
			return -1;
		}
		for (Funcionario f: repoFuncionario.getFuncionarios()) {
			if(f.getLogin().getUser() == user) {
				return repoFuncionario.getFuncionarios().indexOf(f);
			}
		}
		return -1;
	}
	
	// se esquecer a senha o metodo pede a palavra de seguranca
	public void esqueceuSenha(String user, String novaSenha, String palavra) throws NegocioException{
		if(user == null) {
			throw new RuntimeException("\nNome de usuario nulo!\n");
		}
		if(novaSenha == null) {
			throw new RuntimeException("\nNova senha nula!\n");
		}
		if(palavra == null) {
			throw new RuntimeException("\nPalavra de seguranca nula!\n");
		}
		int posicao = retornaPosicaoLogin(user);
		if(posicao == -1) {
			throw new InstanciaInexistenteException("\nO login de username ("+user+") nao existe!\n");
		}
		if(!repoFuncionario.getFuncionarios().get(posicao).getLogin().getPalavraSeguranca().equals(palavra)) {
			throw new LoginInvalidoExecption("\nPalavra de seguranca incorreta!\n");
		}
		repoFuncionario.alterarLogin(new Login(user, novaSenha, palavra), posicao);
	}

	// eh usado durante o login de um usuario, checa se o user e senha conferem e se conferirem retorna a funcao do funcionario em questao
	// se retornar 1 = vendedor
	// se retornar 2 = gerente
	// se retornar 3 = admin (dono)
	// se retornar -1 = informacoes nao conferem
	public Funcionario validarLogin(Login log) throws NegocioException{

		if(log == null) {
			throw new RuntimeException("\nInstancia de Login nula!\n");
		}
		if(log.getSenha() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Falha de Login");
			alert.setHeaderText("Informações inválidas");
			alert.setContentText("Senha incorreta");
			alert.showAndWait();
			throw new RuntimeException("\nSenha do Login nula!\n");
		}
		if(log.getUser() == null) {
			throw new RuntimeException("\nUsername do Login nulo!\n");
		}
	
		for (Funcionario f: repoFuncionario.getFuncionarios()) {
			if(f.getLogin().getUser().equals(log.getUser())){
				if(f.getLogin().getSenha().equals(log.getSenha())){
					return f;
				}
			}
		//	if(f.getLogin().equals(log)) {
			//	return f;	
			//}
		}
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Falha de Login");
		alert.setHeaderText("Informações inválidas");
		alert.setContentText("Usuario ou senha incorretos");
		alert.showAndWait();
		
		throw new LoginInvalidoExecption("\nFalha no login! username ou password incorretos!\n");
	}
}