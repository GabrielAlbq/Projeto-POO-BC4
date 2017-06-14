package Beans;

public class Endereco {
	private String rua;
	private String cidade;
	private String cep;
	private String numero;
	
	public Endereco(String rua, String cidade, String cep, String numero) {
		this.rua = rua;
		this.cidade = cidade;
		this.cep = cep;
		this.numero = numero;
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

	@Override
	public String toString() {
		return "Endereco\n\nrua: "+rua+"\ncidade: "+cidade+"\ncep: "+cep+"\nnumero: "+numero;
	}
	
	
}
