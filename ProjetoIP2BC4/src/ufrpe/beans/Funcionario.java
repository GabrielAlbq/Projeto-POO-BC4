package ufrpe.beans;

import java.io.Serializable;

public abstract class Funcionario extends Pessoa implements Serializable{
	
	// ATRIBUTOS 

	private int funcao; // vendedor(1) / gerente(2) / chefe(3)
	private double salario;
	private int identificacao;
	private boolean recebeuSalario;
	private Login login;
	
	// CONSTRUTOR

	// este construtor eh usado pelo admin
	public Funcionario(Login login) {
		super();
		this.login = login;
	}
	
	public Funcionario(String rua, String cidade, String cep, String numero, String nome, String cpf, int funcao,
			double salario, int identificacao, boolean recebeuSalario, Login login) {
		
		super(rua, cidade, cep, numero, nome, cpf);
		this.funcao = funcao;
		this.salario = salario;// O if que garante um salario positivo fica no contorlador
		this.identificacao = identificacao; // Nao pode ser menor ou igual a zero!
		this.recebeuSalario = recebeuSalario;// Indica se o funcionario ja recebeu o salario "False: n�o" "True: sim"
		this.login = login;
	}

	// GET / SET 
	public int getFuncao() {
		return funcao;
	}

	public double getSalario() {
		return salario;
	}

	public int getIdentificacao() {
		return identificacao;
	}

	public void setFuncao(int funcao) {
		this.funcao = funcao;
	}
	
	public boolean getRecebeuSalario() {
		return recebeuSalario;
	}

	public void setRecebeuSalario(boolean teste) {
		this.recebeuSalario = teste;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	// EQUALS
	
	public boolean equals (Funcionario funcionario) {
		if(funcionario == null) 
			return false;
		if(identificacao == funcionario.identificacao) 
			return true;
		return false;
	}
}
