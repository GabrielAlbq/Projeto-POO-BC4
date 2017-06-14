package Beans;

public class Funcionario {
	private Pessoa pessoa;
	private String funcao; // vendedor(1) / gerente(2) / chefe(3)
	private double salario;
	private int identificacao;
	
	public Funcionario(Pessoa pessoa, String funcao, double salario, int identificacao) {
		this.pessoa = pessoa;
		this.funcao = funcao;
		this.salario = salario; // o if que garante um salario positivo fica no contorlador
		this.identificacao = identificacao; // nao pode ser menor ou igual a zero!
	}

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
	
	public boolean equals (Funcionario funcionario) {
		if(funcionario == null) {
			return false;
		}
		if( pessoa.getCpf() == funcionario.getPessoa().getCpf() ) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Funcionario:\n\nfuncao: "+funcao+"\nsalario: "+salario+"\nidentificacao: "
				+identificacao+"\n\n"+pessoa;
	}
	
	
}
