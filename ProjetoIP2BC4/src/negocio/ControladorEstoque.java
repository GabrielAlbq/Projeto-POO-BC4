package negocio;

import beans.Produto;
import repositorios.IRepositorioEstoque;
import repositorios.RepositorioEstoque;
import repositorios.RepositorioVenda;

public class ControladorEstoque {

	// ATRIBUTOS

	RepositorioVenda repoVenda;
	private static ControladorEstoque instancia;
	private IRepositorioEstoque repoestoque;

	// SINGLETON

	private ControladorEstoque() {
		repoestoque = RepositorioEstoque.getInstancia();
		repoVenda = RepositorioVenda.getInstancia();
	}

	public static ControladorEstoque getInstancia() {
		if (instancia == null) {
			instancia = new ControladorEstoque();
		}
		return instancia;
	}

	// METODOS COM CONTROLE DE NEGOCIOS

	public String listarProduto() {
		String texto = "";
		for (Produto prod : repoestoque.listar()) {
			if (repoestoque.listar().isEmpty() == true) {
				texto = "\n\n\tNao ha produtos cadastrados!\n\n";
			} else {
				texto += "\n" + prod.toString();
			}
		}
		return texto;
	}

	public boolean subtrairProduto(int codigo, int quantidade) {
		int pos = retornarPosicao(codigo);
		if (pos == -1) {
			return false;
		}
		for (Produto prod : repoestoque.listar()) {
			if (codigo == prod.getCodigo()) {
				if (quantidade <= prod.getQuantidade()) {
					repoestoque.subtrairProduto(pos, quantidade);
					return true;
				}
			}
		}
		return false;
	}

	public boolean inserir(Produto prod) {
		if (prod == null || prod.getCodigo() <= 0 || prod.getNome() == null)
			return false;
		if (retornarPosicao(prod.getCodigo()) != -1) {
			System.out.println("Ja existe um produto com esse codigo!");
			return false;
		}
		repoestoque.inserir(prod);
		// repoVenda.inserir( new ItemVenda(prod.getCodigo(), prod.getNome(),
		// prod.getPreco(), 0) ); ///////
		System.out.println("Produto adicionado com sucesso!");
		return true;
	}

	public Produto buscar(int cod) {
		int posicao;
		posicao = this.retornarPosicao(cod);

		if (posicao != -1) {
			return repoestoque.buscar(posicao);
		}
		System.out.println("Produto nao existe");
		return null;
	}

	public boolean alterar(Produto novoProduto) {
		int posicao;
		if (novoProduto == null) {
			return false;
		}
		posicao = this.retornarPosicao(novoProduto.getCodigo());
		if (posicao == -1) {
			return false;
		}
		if (repoestoque.listar().get(posicao).getCodigo() == novoProduto.getCodigo()) {
			repoestoque.alterar(novoProduto, posicao);
			System.out.println("Produto alterado com sucesso!");
			return true;
		}
		return false;
	}

	public boolean remover(int cod) {
		if (repoestoque.listar().isEmpty() == true) {
			System.out.println("\n\tErro! Nao ha produtos para remover!");
			return false;
		}
		int posicao = this.retornarPosicao(cod);
		if (posicao == -1) {
			System.out.println("Codigo nao encontrado!");
			return false;
		}
		repoestoque.remover(posicao);
		// repoVenda.remover(posicao);
		System.out.println("Produto removido com sucesso!");
		return true;
	}

	private int retornarPosicao(int codigo) {

		if (codigo <= 0) {
			return -1;
		}
		for (Produto prod : repoestoque.listar()) {
			if (codigo == prod.getCodigo()) {
				return repoestoque.listar().indexOf(prod);
			}
		}
		// aqui devolve o indexof(produto)
		// for (int i = 0; i < ((RepositorioEstoque)repoestoque).getProdutos();
		// i++){
		// if (codigo == repoestoque.getProdutos()[i].getCodigo()) {
		// return i;
		// }
		// }
		return -1;
	}
}
