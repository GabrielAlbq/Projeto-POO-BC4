package ufrpe.negocio;

import ufrpe.beans.Funcionario;
import ufrpe.repositorio.IRepositorioFinanceiro;
import ufrpe.repositorio.IRepositorioFuncionario;
import ufrpe.repositorio.RepositorioFinanceiro;
import ufrpe.repositorio.RepositorioFuncionario;

public class ControladorFinanceiro {

	// ATRIBUTOS

	// TODO interface do repositorio funcionario e financeiro a serem
	// implementadas
	private ControladorFuncionario controladorFuncionario;
	private IRepositorioFuncionario repoFuncionario;
	private IRepositorioFinanceiro repoFinanceiro;

	// SINGLETON

	private static ControladorFinanceiro instancia;

	public static ControladorFinanceiro getInstancia() {
		if (instancia == null) {
			instancia = new ControladorFinanceiro();
		}
		return instancia;
	}

	// CONSTRUTOR

	private ControladorFinanceiro() {
		controladorFuncionario = ControladorFuncionario.getInstancia();
		repoFinanceiro = RepositorioFinanceiro.getInstancia();
		repoFuncionario = RepositorioFuncionario.getInstancia();
	}

	// METODOS

	public String exibirFinancas() {
		return repoFinanceiro.exibirFinancas();
	}

	public boolean pagarFuncionario(int identificacao) {
		Funcionario func = controladorFuncionario.buscar(identificacao);
		if (func == null) {
			return false;
		}
		if (repoFinanceiro.getRendaBruta() - func.getSalario() >= 0) {
			if (func.getRecebeuSalario() == false) {
				repoFinanceiro.pagarFuncionario(func);
				return true;
			}
		}
		return false;
	}

	public boolean receberDinheiroVenda(double valor) {
		if (valor <= 0) {
			System.out.println("\n\n\tErro! Valor invalido\n");
			return false;
		}
		repoFinanceiro.receberDinheiroVenda(valor);
		return true;
	}
}
