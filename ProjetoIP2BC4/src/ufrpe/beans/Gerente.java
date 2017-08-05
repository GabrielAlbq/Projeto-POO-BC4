package ufrpe.beans;

public class Gerente extends Funcionario{
	private double comissaoMensal; // a comissão do gerente é calculada de acordo com o lucro total mensal

	public Gerente(String rua, String cidade, String cep, String numero, String nome, String cpf, String funcao,
			double salario, int identificacao, boolean recebeuSalario, double comissaoMensal) {
		super(rua, cidade, cep, numero, nome, cpf, funcao, salario, identificacao, recebeuSalario);
		this.comissaoMensal = comissaoMensal;
	}

	public double getComissaoMensal() {
		return comissaoMensal;
	}

	public void setComissaoMensal(double comissaoMensal) {
		this.comissaoMensal = comissaoMensal;
	}
	
	@Override
	public String toString() {
		return  "\nGerente\n\nIdentificacao: "+getIdentificacao()+"\nSalario: R$ "+getSalario()+
				"\ncomissao: R$ "+ comissaoMensal +"\n"+"\nStatus de pagamento: "+ getRecebeuSalario()+
				"\n\nNome: "+ getNome() +"\nCpf:"+getCpf()+"\n\nLogradouro: "+ getRua()+"\nCep: "
				+getCep()+"\\nNumero: "+getNumero()+"\nCidade: "+getCidade()+"\n";
	}
}
