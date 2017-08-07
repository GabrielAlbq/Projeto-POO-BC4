package ufrpe.negocio;

import javax.management.RuntimeErrorException;

import ufrpe.beans.Login;
import ufrpe.negocio.exception.InstanciaInexistenteException;
import ufrpe.negocio.exception.InstanciaRepetidaException;
import ufrpe.negocio.exception.NegocioException;
import ufrpe.repositorio.IRepositorioLogin;
import ufrpe.repositorio.RepositorioLogin;

/*
	O FUNCIONAMENTO SO LOGIN OCORRE EM RELACAO AO CONTROLADOR DE FUNCIONARIO!
	
	QUANTO UM FUNCIONARIO EH CADASTRADO UM NOVO LOGIN E GERADO E ARMAZENADO NA INSTANCIA DE FUNCIONARIO E NO REPOSITORIO DE LOGIN
	
	AS VALIDACOES DE LOGIN NO MAIN SAO FEITAS A PARTIR DAS COMPARACOES DAS INFORMACOES DE LOGIN DO REPOSITORIO E DA INSTANCIA DE FUNCIONARIO
	
	A ALTERACAO DAS INFOMACOES DO LOGIN SEGUEM A MEMSA LOGICA, SO EH PERMITIDA A ALTERACAO DA SENHA  E ESTA INFORMACAO EH ENVIADA
	AO REPOSITORIO DE LOGIN E A INSTANCIA DE FUNCIONARIO PARA FUTURAS VALIDACOES
	
	A REMOCAO DE UM LOGIN OCORRE EM SIMULTANEO COM A REMOCAO DO FUNCIONARIO
*/

public class ControladorLogin {
	
	// ATRIBUTO
	
	private IRepositorioLogin repologin;
	
	// SINGLETON
	
	private static ControladorLogin instancia;
	
	public static ControladorLogin getInstancia() {
		if(instancia == null) {
			instancia = new ControladorLogin();
		}
		return instancia;
	}
	
	private ControladorLogin() {
		repologin = RepositorioLogin.getInstancia();
	}
	
	// METODOS
	
	public int retornarPosicao (String user) {
		if(user == null) {
			return -1;
		}
		for (Login l: repologin.listar()) {
			if(l.getUser().equals(user)) {
				return repologin.listar().indexOf(l);
			}
		}
		return -1;
	}
	
	public boolean existe (String user) {
		if(user == null) {
			return false;
		}
		for (Login l : repologin.listar()) {
			if(l.getUser().equals(user)) {
				return true;
			}
		}
		return false;
	}
	
	public void inserirLogin(Login login) throws NegocioException{
		if(login == null) {
			throw new RuntimeException("\nInstancia de Login nula\n");
		}
		if(login.getSenha() == null) {
			throw new RuntimeException("\nAtributo senha de login nulo\n");
		}
		if(login.getUser() == null) {
			throw new RuntimeException("\nAtributo user de login nulo\n");
		}
		if(existe(login.getUser())) {
			throw new InstanciaRepetidaException("\nLogin ja foi cadastrada\n");
		}
		repologin.inserirLogin(login);
	}
	
	public void removerLogin(String user) throws NegocioException{
		if(user == null) {
			throw new RuntimeException("\nParametro String User nulo\n");
		}
		if(!existe(user)) {
			throw new InstanciaInexistenteException("\nLogin de user ("+user+") nao existe\n");
		}
		// como a verificacao da existencia da instancia foi efetuada acima, entao o retornarPosicao nao vai retorna -1, pois a instancia existe
		repologin.removerLogin(retornarPosicao(user));
	}
	
	public void alterarSenha (Login login) throws NegocioException{
		if(login == null) {
			throw new RuntimeException("\nInstancia de Login nula\n");
		}
		if(login.getSenha() == null) {
			throw new RuntimeException("\nAtributo senha de login nulo\n");
		}
		if(login.getUser() == null) {
			throw new RuntimeException("\nAtributo user de login nulo\n");
		}
		if(!existe(login.getUser())) {
			throw new InstanciaInexistenteException("\nLogin de user ("+login.getUser()+") nao existe\n");
		}
		repologin.alterarSenha(login, retornarPosicao(login.getUser()));
	}
	
	public Login buscarLogin (String user) throws NegocioException{
		if(user == null) {
			throw new RuntimeException("\nParametro String User nulo\n");
		}
		if(!existe(user)) {
			throw new InstanciaInexistenteException("\nLogin de user ("+user+") nao existe\n");
		}
		return repologin.listar().get(retornarPosicao(user));
	}
}
