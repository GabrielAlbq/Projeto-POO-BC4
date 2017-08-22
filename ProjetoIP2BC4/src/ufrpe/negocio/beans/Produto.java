package ufrpe.negocio.beans;

import java.io.Serializable;

public class Produto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1644589701048471052L;
	// ATRIBUTOS 
	
	private String nome;
	private int codigo;
	private int quantidade;
	private double preco;
	
	// CONSTRUTORES
	
	public Produto(String nome,int codigo, int quantidade, double preco){
		this.nome = nome;
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	public Produto(){
	}
	
	// GET / SET 
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	// TO STRING 	
	
	public String toString() {
		return "\tNome: " + nome + "\n\tCodigo: " + codigo + "\n\tQuantidade: " + quantidade + "\n\tPreco: R$ " + preco + "\n";
	}
}
