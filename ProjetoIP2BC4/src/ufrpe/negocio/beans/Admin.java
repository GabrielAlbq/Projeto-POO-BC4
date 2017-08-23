package ufrpe.negocio.beans;

public class Admin extends Funcionario {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6877505692178301342L;

	//private static final long serialVersionUID = -7352103853567010218L;

	public Admin(Login login, int identificacao) {
		super("Administrador", 1, 100, false, login, "adm", "453257433", new Endereco("adm", "adm", "adm", "adm"));
	}
	public Admin(){
		
	}
	
	@Override
	public String toString() {
		return "\nAdmin do sistema!\nidentificacao: "+getIdentificacao()+"\n";
	}
}
