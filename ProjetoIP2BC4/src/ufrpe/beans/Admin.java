package ufrpe.beans;

public class Admin extends Funcionario {
	public Admin(Login login, int identificacao) {
		super("ADMIN","ADMIN","ADMIN","ADMIN","ADMIN","ADMIN",3,1,identificacao,false,login);
	}
	
	@Override
	public String toString() {
		return "\nAdmin do sistema!\nidentificacao: "+getIdentificacao()+"\n";
	}
}
