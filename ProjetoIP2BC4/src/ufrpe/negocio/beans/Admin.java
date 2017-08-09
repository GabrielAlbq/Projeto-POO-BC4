package ufrpe.negocio.beans;

public class Admin extends Funcionario {

	private static final long serialVersionUID = 1L;

	public Admin(Login login, int identificacao) {
		super(1, 100, false, login, "adm", "453257433", new Endereco("adm", "adm", "adm", "adm"));
	}
	
	@Override
	public String toString() {
		return "\nAdmin do sistema!\nidentificacao: "+getIdentificacao()+"\n";
	}
}