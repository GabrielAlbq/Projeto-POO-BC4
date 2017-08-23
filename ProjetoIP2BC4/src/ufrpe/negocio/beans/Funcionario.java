package ufrpe.negocio.beans;

import java.io.Serializable;

public abstract class Funcionario implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3482784901792220501L;
	/**
	 * 
	 */
	//private static final long serialVersionUID = 3569337822406696593L;
	//ATRIBUTOS

	//private static final long serialVersionUID = -1773429005319533966L;
	//private static final long serialVersionUID = 2886632581022532992L;
	private double salario;
	private int identificacao;
	private boolean recebeuSalario;
	private Login login;
	private String nome;
	private String cpf;
	private Endereco endereco;
	private String funcao;
	// CONSTRUTOR
	
	public Funcionario(String funcao, double salario, int identificacao, boolean recebeuSalario, Login login, String nome, String cpf,
			Endereco endereco) {
		this.funcao = funcao;
		this.salario = salario;
		this.identificacao = identificacao;
		this.recebeuSalario = recebeuSalario;
		this.login = login;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
	}
	public Funcionario(){
		
	}
	
	// EQUALS
	
	public boolean equals (Funcionario funcionario) {
		if(funcionario == null) 
			return false;
		if(identificacao == funcionario.identificacao) 
			return true;
		return false;
	}

	// GET / SET 
	
	public double getSalario() {
		return salario;
	}

	public void setIdentificacao(int identificacao) {
		this.identificacao = identificacao;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

	public boolean getRecebeuSalario() {
		return recebeuSalario;
	}

	public void setRecebeuSalario(boolean recebeuSalario) {
		this.recebeuSalario = recebeuSalario;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getIdentificacao() {
		return identificacao;
	}

	public String getCpf() {
		return cpf;
	}
}
