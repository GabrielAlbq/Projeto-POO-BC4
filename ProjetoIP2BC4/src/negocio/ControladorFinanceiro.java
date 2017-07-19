package negocio;

import beans.Funcionario;
import repositorios.RepositorioFinanceiro;
import repositorios.RepositorioFuncionario;

public class ControladorFinanceiro {
	
	// ATRIBUTOS 
	
	// TODO interface do repositorio funcionario e financeiro a serem implementadas
	private ControladorFuncionario controladorFuncionario;
	private RepositorioFuncionario repositorioFuncionario;
	private RepositorioFinanceiro repositorioFinanceiro;
	
	
	// SINGLETON
	
	private static ControladorFinanceiro instancia;
		
	public static ControladorFinanceiro getInstancia() {
		if( instancia == null ) {
			instancia = new ControladorFinanceiro();
		}
		return instancia;
	}
		
	// CONSTRUTOR
		
	private ControladorFinanceiro () {
		controladorFuncionario = ControladorFuncionario.getInstancia();
		repositorioFinanceiro = RepositorioFinanceiro.getInstancia();
		repositorioFuncionario = RepositorioFuncionario.getInstancia();
	}
	
	// METODOS 
	
	public String exibirFinancas () {
		return repositorioFinanceiro.exibirFinancas();
	}
	
	
	// TODO alterar o metodo pagarFuncionario. O parametro deve ser a identificacao
	public boolean pagarFuncionario (int identificacao){
		if( identificacao <= 0 ) {
			System.out.println("\n\n\tErro! Idenficacao invalida!");
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
