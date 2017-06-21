package principal;

import beans.Funcionario;
import beans.Produto;
import negocio.ControladorEstoque;
import negocio.ControladorFinanceiro;
import negocio.ControladorFuncionario;
import negocio.ControladorVenda;
import negocio.Pedido;

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
	
	public int procuraProduto (){
		return controladorVenda.procuraProduto();
	}
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
	
	public boolean limparHistorico (){
		return controladorVenda.limparHistoricoNotasFiscais();
	}
	
	// SISTEMA ESTOQUE
	
	public void inserirProduto (Produto produto){
		controladorEstoque.inserir(produto);
	}
	
	public String listarProdutos() {
		return controladorEstoque.listarProduto();
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
	
	public String listarFuncionarios() {
		return controladorFuncionario.listarFuncionarios();
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
