  package negocio;

import java.util.Arrays;
import java.util.Scanner;

import beans.Funcionario;
import beans.ItemVenda;
import beans.NotaFiscal;
import beans.Produto;
import repositorios.RepositorioEstoque;
import repositorios.RepositorioVenda;


public class ControladorVenda {
	private RepositorioEstoque repoEstoque;
	private RepositorioVenda repoVenda;
	private Pedido pedido;
	ItemVenda[] itens = new ItemVenda[10];
	
	// SINGLETON
	
	private static ControladorVenda instancia;

	private ControladorVenda() {
		repoVenda = RepositorioVenda.getInstancia();
		repoEstoque = RepositorioEstoque.getInstancia();
		pedido = Pedido.getInstancia();
	}
	
	public static ControladorVenda getInstancia() {
		if( instancia == null ) {
			instancia = new ControladorVenda();
		}
		return instancia;
	}
	
	// CRUD E METODOS
	
	public int retornaPosicao(int codigo) {
		if( codigo <= 0 ) {
			return -1;
		}
		
		Produto[] teste = repoEstoque.getProdutos();
		
		for (int i = 0; i < repoEstoque.getQuantSKU() ; i++) {
			if( codigo == teste[i].getCodigo() ) {
				return i;
			}
		}
		return -1;
	}
	
	// Este metodo checa se existe o produto com este codigo e quantidade no repositorioEstoque
	// Se houver ele retorna verdadeiro, para validar a subtra��o deste produto na quantidade
	public boolean checarQuantidade(int codigo, int quantidade) {
		if( codigo <= 0 || quantidade <= 0) {
			return false;
		}
		Produto[] teste = repoEstoque.getProdutos();
		for (int i = 0; i < repoEstoque.getQuantSKU() ; i++) {
			if( codigo == teste[i].getCodigo() ) {
				if( teste[i].getQuantidade() >= quantidade ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String listarNotasFiscais() {
		return repoVenda.listarNotasFiscais();
	}
	
	public boolean efetuarPedido (int codigo, int quantidade, Funcionario funcionario) {
		if( codigo <= 0 ) {
			System.out.println("\n\n\tErro! c�digo invalido!");
			return false;
		}
		if( quantidade <= 0 ) {
			System.out.println("\n\n\tErro! quantidade inv�lida!");
			return false;
		}
		int posicao = retornaPosicao(codigo);
		if( posicao == -1 ) {
			System.out.println("Erro! item inexistente!");
			return false;
		}
		boolean checagem = checarQuantidade(codigo, quantidade);
		if( checagem == false ) {
			System.out.println("Erro! numero de itens insuficiente!");
			return false;
		}
		pedido.acrescentarPedido(codigo, quantidade, funcionario);
		return true;
	}
	
	public void adicionarNotaFiscal (NotaFiscal notaFiscal) {
		if( notaFiscal == null ) {
			return;
		}
		repoVenda.adicionarNotaFiscal( pedido.gerarNotaFiscal() );
	}
	
	// apaga as notas fiscais armazenadas no repositorioVenda
	public boolean limparHistoricoNotasFiscais () {
		if(repoVenda.getQtdNotaFiscal() == 0) {
			return false;
		}
		repoVenda.setQtdNotaFiscal(0);
		for (int i = 0 ; i < repoVenda.getQtdNotaFiscal(); i++){
			repoVenda.limparHistoricoNotasFiscais(i);
		}
		return true;
	}
	
	// imprimir todas os produtos - nome(preco)\nqtd\ncodigo
	public String listarProdutos () {
		Produto[] teste = repoEstoque.getProdutos();
		String texto = "";
		for (int i = 0; i < repoEstoque.getQuantSKU(); i++) {
			texto += "\n"+teste[i].toString();
		}
		return texto;
	}
	
	public int procuraProduto()	{
	    System.out.println("Informe o codigo do produto a ser vendido: ");
	    int encontrou = 0;
	    int i=1;
	    int codigo;
	    Scanner scanf = new Scanner(System.in);
	    while(encontrou!=i) {
	    codigo = scanf.nextInt();
	    for (i = 0; i < repoEstoque.getQuantSKU(); i++){
	    	if(itens[i].getCodigo() != codigo){
	         return 0;
	        }
	        encontrou = i;
        }
	    if(encontrou !=i)
	        {
	            System.out.println("Codigo nao encontrado, digite um novo: ");
	        }
	    }
	    System.out.println("\n\nEncontrou: "+encontrou);
	    return encontrou;
	}
	
	public boolean alterar(Produto produto) {
		if( produto == null ) {
			System.out.println("\n\n\tErro! itemVenda invalido\n\n");
			return true;
		}
		if(produto.getCodigo() <= 0) {
			System.out.println("\n\n\tErro! codigo invalido!\n\n");
			return false;
		}
		
		int posicao = retornaPosicao(produto.getCodigo());
		
		if(posicao == -1) {
			System.out.println("\n\n\tErro! produto inexistente\n\n");
			return false;
		}
		repoVenda.alterar(posicao, new ItemVenda(produto.getCodigo(), produto.getNome(), produto.getPreco(), produto.getQuantidade()));
		return true;
		
	}
 	
	public String listarVendas () {
		String teste = "";
		NotaFiscal[] notas = repoVenda.getNotaFiscal();
		for (int i = 0; i < repoVenda.getQtdNotaFiscal(); i++) {
			teste = teste+"\n\n"+notas[i].toString();
		}
		return teste;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(itens);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControladorVenda other = (ControladorVenda) obj;
		if (!Arrays.equals(itens, other.itens))
			return false;
		return true;
	}
}
