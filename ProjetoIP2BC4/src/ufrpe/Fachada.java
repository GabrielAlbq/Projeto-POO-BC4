package ufrpe;

import ufrpe.beans.Funcionario;
import ufrpe.beans.ItemVenda;
import ufrpe.beans.Produto;
import ufrpe.negocio.ControladorEstoque;
import ufrpe.negocio.ControladorFinanceiro;
import ufrpe.negocio.ControladorFuncionario;
import ufrpe.negocio.ControladorVenda;
import ufrpe.negocio.Pedido;
import ufrpe.negocio.exception.NegocioException;

public class Fachada {

	// CONTROLADORES

	private ControladorVenda controladorVenda;
	private ControladorEstoque controladorEstoque;
	private ControladorFuncionario controladorFuncionario;
	private ControladorFinanceiro controladorFinanceiro;
	private Pedido pedido;

	// SINGLETON

	private static Fachada instancia;

	private Fachada() {
		controladorVenda = ControladorVenda.getInstancia();
		controladorEstoque = ControladorEstoque.getInstancia();
		controladorFuncionario = ControladorFuncionario.getInstancia();
		controladorFinanceiro = ControladorFinanceiro.getInstancia();
		pedido = Pedido.getInstancia();
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

	public void encerrarPedido() throws NegocioException{
		pedido.encerrarPedido();
	}

	public void gerarNotaFiscal(Funcionario funcionario) {
		pedido.gerarNotaFiscal(funcionario);
	}

	public void cancelarPedido() {
		pedido.resetarPedido();
	}

	public String listarItensVenda() {
		return controladorVenda.listarItensVenda();
	}

	public String listarVendas() {
		return controladorVenda.listarNotasFiscais();
	}

	public void limparHistorico() {
		controladorVenda.limparHistoricoNotasFiscais();
	}

	public void inserirItem(ItemVenda itemvenda) throws NegocioException{
		controladorVenda.inserir(itemvenda);
	}

	// SISTEMA ESTOQUE

	public void inserirProduto(Produto produto) throws NegocioException{
		controladorEstoque.inserir(produto);
	}

	public String listarProdutos() throws NegocioException{
		return controladorEstoque.listarProduto();
	}

	public void atualizarProduto(Produto novoProduto) throws NegocioException{
		controladorEstoque.alterar(novoProduto);
	}

	public void removerProduto(int indentificacao) throws NegocioException{
		controladorEstoque.remover(indentificacao);
	}

	public Produto buscarProduto(int codigo) {
		return controladorEstoque.buscar(codigo);
	}

	// SISTEMA FUNCIONARIO

	public String listarFuncionarios() throws NegocioException{
		return controladorFuncionario.listarFuncionarios();
	}

	public void inserirFuncionario(Funcionario funcionario) throws NegocioException{
		controladorFuncionario.inserir(funcionario);
	}

	public void atualizarFuncionario(Funcionario funcionario) throws NegocioException{
		controladorFuncionario.alterar(funcionario);
	}

	public void removerFuncionario(int identificacao) throws NegocioException{
		controladorFuncionario.remover(identificacao);
	}

	public Funcionario buscarFuncionario(int identificacao) {
		return controladorFuncionario.buscar(identificacao);
	}

	// SISTEMA FINANCEIRO

	public void pagarFuncionario(int identificacao) throws NegocioException{// (Funcionario
														// funcionario) {
		controladorFinanceiro.pagarFuncionario(identificacao);
	}

	// TODO public void solicitarFornecedor() { }

	public String exibirFinancas() {
		return controladorFinanceiro.exibirFinancas();
	}
}
