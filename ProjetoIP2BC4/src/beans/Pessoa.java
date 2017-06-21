package beans;

public class Pessoa {
	
	// ATRIBUTOS 
	
	private Endereco endereco;
	private String nome;
	private String cpf;
	
	// CONSTRUTOR 
	
	public Pessoa(Endereco endereco, String nome, String cpf) {
		this.endereco = endereco;
		this.nome = nome;
		this.cpf = cpf;
	}
	
	// GET / SET

	public Endereco getEndereco() {
		return endereco;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// TO STRING 
	
	public String toString() {
		return "Pessoa:\n\nnome: "+nome+"\ncpf: "+cpf+"\n\n"+endereco;
	}	
	
	
}
