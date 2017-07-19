package beans;

public abstract class Pessoa extends Endereco{
	
	// ATRIBUTOS 
	
	private String nome;
	private String cpf;
	
	// CONSTRUTOR 
	
	public Pessoa(String rua, String cidade, String cep, String numero, String nome, String cpf) {
		super(rua, cidade, cep, numero);
		this.nome = nome;
		this.cpf = cpf;
	}
	
	// GET / SET

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
