package negocio;

import java.util.ArrayList;

import beans.Funcionario;
import repositorios.*;

public class ControladorFinanceiro {
	
	// ATRIBUTOS 
	
	// TODO interface do repositorio funcionario e financeiro a serem implementadas
	private ControladorFuncionario controladorFuncionario;
	private IRepositorioFuncionario repoFuncionario;
	private IRepositorioFinanceiro repoFinanceiro;
	
	
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
		repoFinanceiro = RepositorioFinanceiro.getInstancia();
		repoFuncionario = RepositorioFuncionario.getInstancia();
	}
	
	// METODOS 
	
	public String exibirFinancas () {
		return repoFinanceiro.exibirFinancas();
	}
	
	public boolean pagarFuncionario (int identificacao){
		if( identificacao <= 0 ) {
			System.out.println("\n\n\tErro! Idenficacao invalida!");
			return false;
		}
		ArrayList<Funcionario> func = repoFuncionario.getFuncionarios();
		for (int i = 0; i < repoFuncionario.getFuncionarios().size(); i++) {
			if (func.get(i).getIdentificacao() == identificacao){
				if (repoFinanceiro.getRendaBruta() - func.get(i).getSalario() >= 0){
					if (func.get(i).getRecebeuSalario() == false){
						repoFinanceiro.pagarFuncionario(func.get(i), i);
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
		repoFinanceiro.receberDinheiroVenda(valor);
		return true;
	}
}
