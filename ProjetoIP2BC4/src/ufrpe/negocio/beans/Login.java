package ufrpe.negocio.beans;

import java.io.Serializable;

public class Login implements Serializable{
	
	private static final long serialVersionUID = 5696955181283437225L;
	
	private String user;
	private String senha;
	private String palavraSeguranca; // eh usado para validar o usuario
									   // no caso de esquecer
									   // sua senha ou nome de usuario
	
	public Login(String user, String senha, String palavraSeguranca) {
		this.user = user;
		this.senha = senha;
		this.palavraSeguranca = palavraSeguranca;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getPalavraSeguranca() {
		return palavraSeguranca;
	}

	public boolean equals(Login l) {
		if(l == null || l.user == null || l.senha == null) {
			return false;
		}
		if(l.senha == this.senha && l.user == this.user) {
			return true;
		}
		return false;
	}
}
