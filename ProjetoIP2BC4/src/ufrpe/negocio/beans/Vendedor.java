package ufrpe.negocio.beans;

import java.io.Serializable;

public class Vendedor extends Funcionario implements Serializable{
	private double comissaoVenda; // a comissão aumenta de acordo com as vendas efetuadas pelo vendedor

	public Vendedor(String rua, String cidade, String cep, String numero, String nome, String cpf,
			double salario, int identificacao, boolean recebeuSalario, double comissaoVenda, Login login) {
		super(rua, cidade, cep, numero, nome, cpf, 1, salario, identificacao, recebeuSalario, login);
		this.comissaoVenda = comissaoVenda;
	}


	public void setComissaoVenda(double comissaoVenda) {
		this.comissaoVenda = comissaoVenda;
	}

	@Override
	public String toString() {
		return  "\nVendedor\n\nIdentificacao: "+getIdentificacao()+"\nSalario: R$ "+getSalario()+
				"\ncomissao: R$ "+ comissaoVenda +"\n"+"\nStatus de pagamento: "+ getRecebeuSalario()+
				"\n\nNome: "+ getNome() +"\nCpf:"+getCpf()+"\n\nLogradouro: "+ getRua()+"\nCep: "
				+getCep()+"\nNumero: "+getNumero()+"\nCidade: "+getCidade()+"\n";
	}
}
