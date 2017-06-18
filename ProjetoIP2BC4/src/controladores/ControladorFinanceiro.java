package controladores;

import beans.Funcionario;
import repositorios.RepositorioFinanceiro;
import repositorios.RepositorioFuncionario;

public class ControladorFinanceiro {
	
	ControladorFuncionario controladorFuncionario = ControladorFuncionario.instanciarControlFuncionario();
	RepositorioFuncionario repositorioFuncionario = RepositorioFuncionario.instanciarRepoFuncionario();
	RepositorioFinanceiro repositorioFinanceiro = RepositorioFinanceiro.instanciarRepoFinanceiro();
	
	
	// SINGLETON
	
	private static ControladorFinanceiro instancia;
		
	public static ControladorFinanceiro instanciarControlFinanceiro () {
		if( instancia == null ) {
			instancia = new ControladorFinanceiro();
		}
		return instancia;
	}
		
	// CONSTRUTOR
		
	private ControladorFinanceiro () {
			
	}
	
	// MÉTODOS 
	
	public boolean pagarFuncionario (Funcionario func, boolean recebeuSalario){
		Funcionario [] funcionario = repositorioFuncionario.getFuncionario();
		for (int i = 0; i < repositorioFuncionario.getQtdFuncionario(); i++) {
			if (func.getIdentificacao() == funcionario[i].getIdentificacao()){
				if (repositorioFinanceiro.getRendaBruta() - func.getSalario() >= 0){
					if (func.getRecebeuSalario() == false){
						repositorioFinanceiro.pagarFuncionario(func, recebeuSalario, i);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean receberDinheiro (double valor){
		if (valor <= 0){
			System.out.println("\n\n\tErro! Valor invalido\n");
			return false;
		}
		repositorioFinanceiro.receberDinheiroVenda(valor);
		return true;
	}
	
	
	
	
	
}
