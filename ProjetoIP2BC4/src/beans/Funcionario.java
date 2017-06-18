package beans;

public class Funcionario {
	
	// ATRIBUTOS 
	
	private Pessoa pessoa;
	private String funcao; // vendedor(1) / gerente(2) / chefe(3)
	private double salario;
	private int identificacao;
	private boolean recebeuSalario;
	
	// CONSTRUTOR
	
	public Funcionario(Pessoa pessoa, String funcao, double salario, int identificacao) {
		this.pessoa = pessoa;
		this.funcao = funcao;
		this.salario = salario; // O if que garante um salario positivo fica no contorlador
		this.identificacao = identificacao; // Nao pode ser menor ou igual a zero!
		this.recebeuSalario = false; // Indica se o funcionario ja recebeu o salario "False: não" "True: sim"
	}

	// GET E SET 
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public String getFuncao() {
		return funcao;
	}

	public double getSalario() {
		return salario;
	}

	public int getIdentificacao() {
		return identificacao;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setFuncao(String funcao) {
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
	
	// EQUALS
	
	public boolean equals (Funcionario funcionario) {
		if(funcionario == null) {
			return false;
		}
		if( pessoa.getCpf() == funcionario.getPessoa().getCpf() ) {
			return true;
		}
		return false;
	}

	// TO STRING 
	
	public String toString() {
		return "Funcionario:\n\nfuncao: "+funcao+"\nsalario: "+salario+"\nidentificacao: "
				+identificacao+"\n\n"+pessoa;
	}
	
	
}
