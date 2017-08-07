package ufrpe.beans;

public class Login {
	private String user;
	private String senha;
	
	public Login(String user, String senha) {
		this.user = user;
		this.senha = senha;
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
