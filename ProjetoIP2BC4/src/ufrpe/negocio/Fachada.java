package ufrpe.negocio;

import java.util.List;

import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.ItemVenda;
import ufrpe.negocio.beans.Login;
import ufrpe.negocio.beans.NotaFiscal;
import ufrpe.negocio.beans.Produto;
import ufrpe.negocio.exception.NegocioException;

public class Fachada {

	// CONTROLADORES

	private ControladorVenda controladorVenda;
	private ControladorEstoque controladorEstoque;
	private ControladorFuncionario controladorFuncionario;
	private ControladorFinanceiro controladorFinanceiro;

	// SINGLETON

	private static Fachada instancia;

	private Fachada() {
		controladorVenda = ControladorVenda.getInstancia();
		controladorEstoque = ControladorEstoque.getInstancia();
		controladorFuncionario = ControladorFuncionario.getInstancia();
		controladorFinanceiro = ControladorFinanceiro.getInstancia();
	}

	public static Fachada getInstancia() {
		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}

	public ControladorEstoque getControladorEstoque() {
		return controladorEstoque;
	}

	// SISTEMA VENDA

	public void encerrarPedido() throws NegocioException {
		controladorVenda.encerrarPedido();
	}

	public void gerarNotaFiscal(Funcionario funcionario) throws NegocioException {
		controladorVenda.gerarNotaFiscal(funcionario);;
	}

	public void cancelarPedido() {
		controladorVenda.resetarPedido();
	}

	public List<ItemVenda> listarItensVenda() throws NegocioException {
		return controladorVenda.listarItensVenda();
	}

	public List<NotaFiscal> listarVendas() {
		return controladorVenda.listarNotasFiscais();
	}

	public void limparHistorico() {
		controladorVenda.limparHistoricoNotasFiscais();
	}

	public void inserirItem(ItemVenda itemvenda) throws NegocioException {
		controladorVenda.inserir(itemvenda);
	}

	public void remover(int cod) throws NegocioException {
		controladorVenda.remover(cod);
	}

	// SISTEMA ESTOQUE

	public void inserirProduto(Produto produto) throws NegocioException {
		controladorEstoque.inserir(produto);
	}

	public List<Produto> listarProdutos() throws NegocioException {
		return controladorEstoque.listarProduto();
	}

	public void atualizarProduto(Produto novoProduto) throws NegocioException {
		controladorEstoque.alterar(novoProduto);
	}

	public void removerProduto(int indentificacao) throws NegocioException {
		controladorEstoque.remover(indentificacao);
	}

	public Produto buscarProduto(int codigo) {
		return controladorEstoque.buscar(codigo);
	}

	// SISTEMA FUNCIONARIO

	public List<Funcionario> listarFuncionarios() throws NegocioException {
		return controladorFuncionario.listarFuncionarios();
	}

	public void inserirFuncionario(Funcionario funcionario) throws NegocioException {
		controladorFuncionario.inserir(funcionario);
	}

	public void atualizarFuncionario(Funcionario funcionario) throws NegocioException {
		controladorFuncionario.alterar(funcionario);
	}

	public void removerFuncionario(int identificacao) throws NegocioException {
		controladorFuncionario.remover(identificacao);
	}

	public Funcionario buscarFuncionario(int identificacao) {
		return controladorFuncionario.buscar(identificacao);
	}

	// SISTEMA DE LOGIN

	public Funcionario validarLogin(Login log) throws NegocioException {
		return controladorFuncionario.validarLogin(log);
	}

	// SISTEMA FINANCEIRO

	public void pagarFuncionario(int identificacao) throws NegocioException {
		controladorFinanceiro.pagarFuncionario(identificacao);
	}


	public String exibirFinancas() {
		return controladorFinanceiro.exibirFinancas();
	}

	// FUNCIONARIO
	public int retornaPosicaoLogin(String user) {
		return controladorFuncionario.retornaPosicaoLogin(user);
	}

	public void esqueceuSenha(String user, String novaSenha, String palavra) throws NegocioException {
		controladorFuncionario.esqueceuSenha(user, novaSenha, palavra);
	}

}
