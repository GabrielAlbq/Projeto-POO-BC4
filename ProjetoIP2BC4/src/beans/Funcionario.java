package beans;

public abstract class Funcionario extends Pessoa{
	
	// ATRIBUTOS 

	private String funcao; // vendedor(1) / gerente(2) / chefe(3)
	private double salario;
	private int identificacao;
	private boolean recebeuSalario;
	
	// CONSTRUTOR

	public Funcionario(String rua, String cidade, String cep, String numero, String nome, String cpf, String funcao,
			double salario, int identificacao, boolean recebeuSalario) {
		super(rua, cidade, cep, numero, nome, cpf);
		this.funcao = funcao;
		this.salario = salario;// O if que garante um salario positivo fica no contorlador
		this.identificacao = identificacao; // Nao pode ser menor ou igual a zero!
		this.recebeuSalario = recebeuSalario;// Indica se o funcionario ja recebeu o salario "False: não" "True: sim"
	}

	// GET / SET 
	public String getFuncao() {
		return funcao;
	}

	public double getSalario() {
		return salario;
	}

	public int getIdentificacao() {
		return identificacao;
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
		if(funcionario == null) 
			return false;
		if(identificacao == funcionario.identificacao) 
			return true;
		return false;
	}

	// TO STRING 
	
	public String toString() {
		return "Funcionario:\n\nfuncao: "+funcao+"\nsalario: R$ "+salario+"\nidentificacao: "
				+identificacao+"\nStatus do Pagamento: "+recebeuSalario+"\n\n"+toStringPessoa();
	}
}
