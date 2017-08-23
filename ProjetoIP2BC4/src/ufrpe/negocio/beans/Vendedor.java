package ufrpe.negocio.beans;

import java.io.Serializable;

public class Vendedor extends Funcionario implements Serializable{
	
	
	//private static final long serialVersionUID = -3030566477850150267L;

	/**
	 * 
	 */
	private static final long serialVersionUID = -9117716157661526333L;

	public Vendedor(String funcao, double salario, int identificacao, boolean recebeuSalario, Login login, String nome, String cpf,
			Endereco endereco) {
		super(funcao, salario, identificacao, recebeuSalario, login, nome, cpf, endereco);
	}

	@Override
	public String toString() {
		return  "\nVendedor\n\nIdentificacao: "+getIdentificacao()+"\nSalario: R$ "+getSalario()+
				"\n"+"\nStatus de pagamento: "+ getRecebeuSalario()+"\n\nNome: "+ getNome() +
				"\nCpf:"+getCpf()+"\n\nLogradouro: "+ getEndereco().getRua()+"\nCep: "+getEndereco().getCep()+
				"\nNumero: "+getEndereco().getNumero()+"\nCidade: "+getEndereco().getCidade()+"\n";
	}
}
