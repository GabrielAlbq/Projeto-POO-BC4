package ufrpe.negocio.beans;

import java.io.Serializable;

public class Gerente extends Funcionario implements Serializable{


	private static final long serialVersionUID = 9052730732827370367L;

	

	public Gerente(double salario, int identificacao, boolean recebeuSalario, Login login, String nome, String cpf,
			Endereco endereco) {
		super(salario, identificacao, recebeuSalario, login, nome, cpf, endereco);
	}
	
	@Override
	public String toString() {
		return  "\nGerente\n\nIdentificacao: "+getIdentificacao()+"\nSalario: R$ "+getSalario()+
				"\n"+"\nStatus de pagamento: "+ getRecebeuSalario()+"\n\nNome: "+ getNome() +
				"\nCpf:"+getCpf()+"\n\nLogradouro: "+ getEndereco().getRua()+"\nCep: "+getEndereco().getCep()+
				"\nNumero: "+getEndereco().getNumero()+"\nCidade: "+getEndereco().getCidade()+"\n";
	}
}
