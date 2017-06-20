package negocio;

import beans.Funcionario;
import repositorios.RepositorioFinanceiro;
import repositorios.RepositorioFuncionario;

public class ControladorFinanceiro {
	
	private ControladorFuncionario controladorFuncionario;
	private RepositorioFuncionario repositorioFuncionario;
	private RepositorioFinanceiro repositorioFinanceiro;
	
	
	// SINGLETON
	
	private static ControladorFinanceiro instancia;
		
	public static ControladorFinanceiro getInstancia () {
		if( instancia == null ) {
			instancia = new ControladorFinanceiro();
		}
		return instancia;
	}
		
	// CONSTRUTOR
		
	private ControladorFinanceiro () {
		controladorFuncionario = ControladorFuncionario.getInstancia();
		repositorioFinanceiro = RepositorioFinanceiro.instanciarRepoFinanceiro();
		repositorioFuncionario = RepositorioFuncionario.instanciarRepoFuncionario();
	}
	
	// M�TODOS 
	
	public boolean pagarFuncionario (Funcionario func){
		if( func == null ) {
			System.out.println("\n\n\tErro! funcionario nulo!");
			return false;
		}
		Funcionario [] funcionario = repositorioFuncionario.getFuncionario();
		for (int i = 0; i < repositorioFuncionario.getQtdFuncionario(); i++) {
			if (func.getIdentificacao() == funcionario[i].getIdentificacao()){
				if (repositorioFinanceiro.getRendaBruta() - func.getSalario() >= 0){
					if (func.getRecebeuSalario() == false){
						repositorioFinanceiro.pagarFuncionario(func, i);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean receberDinheiroVenda (double valor){
		if (valor <= 0){
			System.out.println("\n\n\tErro! Valor invalido\n");
			return false;
		}
		repositorioFinanceiro.receberDinheiroVenda(valor);
		return true;
	}
}
