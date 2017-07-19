package beans;

public class Vendedor extends Funcionario{
	private double comissaoVenda; // a comissão aumenta de acordo com as vendas efetuadas pelo vendedor

	public Vendedor(String rua, String cidade, String cep, String numero, String nome, String cpf, String funcao,
			double salario, int identificacao, boolean recebeuSalario, double comissaoVenda) {
		super(rua, cidade, cep, numero, nome, cpf, funcao, salario, identificacao, recebeuSalario);
		this.comissaoVenda = comissaoVenda;
	}

	public double getComissaoVenda() {
		return comissaoVenda;
	}

	public void setComissaoVenda(double comissaoVenda) {
		this.comissaoVenda = comissaoVenda;
	}

	@Override
	public String toString() {
		return  "\nVendedor\n\nIdentificacao: "+getIdentificacao()+"\nSalario: R$ "+getSalario()+
				"\ncomissao: R$ "+ comissaoVenda +"\n"+"\nStatus de pagamento: "+ getRecebeuSalario()+
				"\n\nNome: "+ getNome() +"\nCpf:"+getCpf()+"\n\nLogradouro: "+ getRua()+"\nCep: "
				+getCep()+"\\nNumero: "+getNumero()+"\nCidade: "+getCidade()+"\n";
	}
}
