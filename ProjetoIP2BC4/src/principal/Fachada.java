package principal;

import repositorios.*;
import beans.*;
import negocio.*;

public class Fachada {
	
	//CONTROLADORES
	
	private ControladorVenda controladorVenda;
	private ControladorEstoque controladorEstoque;
	private ControladorFuncionario controladorFuncionario;
	private ControladorFinanceiro controladorFinanceiro;
	private Pedido pedido;
	
	//SINGLETON
	
	private static Fachada instancia;
	
	private Fachada(){
		controladorVenda = ControladorVenda.getInstancia();
		controladorEstoque = ControladorEstoque.getInstancia();
		controladorFuncionario = ControladorFuncionario.getInstancia();
		controladorFinanceiro = ControladorFinanceiro.getInstancia();
		pedido = Pedido.getInstancia();
	}
	public static Fachada getInstancia(){
		if(instancia == null){
			instancia = new Fachada();
		}
		return instancia;
	}
	
	public ControladorEstoque getControladorEstoque() {
		return controladorEstoque;
	}
	
	// SISTEMA VENDA
	
	public void encerrarPedido () {
		pedido.encerrarPedido();
	}
	
	public void vender (int codigo, int quantidade, Funcionario funcionario){
		controladorVenda.efetuarPedido(codigo, quantidade, funcionario);
	}
	
	public void cancelarPedido() {
		pedido.resetarPedido();
	}
	
	public String listarItensVenda() {
		return controladorVenda.listarProdutos();
	}
	
	public String listarVendas(){
		return controladorVenda.listarNotasFiscais();
	}
	
	public void limparHistorico (){
		controladorVenda.limparHistoricoNotasFiscais();
	}
	
	// SISTEMA ESTOQUE
	
	public void inserirProduto (Produto produto){
		controladorEstoque.inserir(produto);
	}
	
	public void atualizarProduto (Produto novoProduto){
		controladorEstoque.alterar(novoProduto);
	}
	
	public void removerProduto (int indentificacao){
		controladorEstoque.remover(indentificacao);
	}
	
	public Produto buscarProduto (int codigo){
		return controladorEstoque.buscar(codigo);
	}
	
	// SISTEMA FUNCIONARIO
	
	public ControladorFuncionario getControladorFuncionario() {
		return controladorFuncionario;
	}
	public void inserirFuncionario (Funcionario funcionario) {
		controladorFuncionario.inserir(funcionario);
	}
	
	public void atualizarFuncionario (Funcionario funcionario) {
		controladorFuncionario.alterar(funcionario);
	}
	
	public void removerFuncionario (int identificacao) {
		controladorFuncionario.remover(identificacao);
	}
	
	public Funcionario buscarFuncionario (int identificacao) {
		return controladorFuncionario.buscar(identificacao);
	}
	
	// SISTEMA FINANCEIRO
	
	public void pagarFuncionario (Funcionario funcionario) {
		controladorFinanceiro.pagarFuncionario(funcionario);
	}
	
	// TODO public void solicitarFornecedor() { }
	
	public String exibirFinancas () {
		return controladorFinanceiro.exibirFinancas();
	}
}
