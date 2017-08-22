package ufrpe.negocio.beans;

import java.io.Serializable;

public class Endereco implements Serializable{
	
	
	private static final long serialVersionUID = -4623980324799023116L;
	// ATRIBUTOS 

	
	private String rua;
	private String cidade;
	private String cep;
	private String numero;
	
	// CONSTRUTOR
	
	public Endereco(String rua, String cidade, String cep, String numero) {
		this.rua = rua;
		this.cidade = cidade;
		this.cep = cep;
		this.numero = numero;
	}
	
	// GET / SET

	public Endereco() {
	}

	public String getRua() {
		return rua;
	}

	public String getCidade() {
		return cidade;
	}

	public String getCep() {
		return cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
