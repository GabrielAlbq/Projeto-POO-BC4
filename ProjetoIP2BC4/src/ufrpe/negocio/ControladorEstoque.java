package ufrpe.negocio;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ufrpe.negocio.beans.Produto;
import ufrpe.negocio.exception.IdentificacaoInvalidaException;
import ufrpe.negocio.exception.InstanciaInexistenteException;
import ufrpe.negocio.exception.InstanciaRepetidaException;
import ufrpe.negocio.exception.NegocioException;
import ufrpe.negocio.exception.QuantidadeInvalidaException;
import ufrpe.repositorio.IRepositorioEstoque;
import ufrpe.repositorio.RepositorioEstoque;
import ufrpe.repositorio.RepositorioVenda;

public class ControladorEstoque {

	// ATRIBUTOS
	RepositorioVenda repoVenda;
	private static ControladorEstoque instancia;
	private IRepositorioEstoque repoestoque;
	Alert alert = new Alert(AlertType.INFORMATION);
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
	// TODO usar collections neste metodo
	public List<Produto> listarProduto() throws NegocioException{
		if (repoestoque.listar().isEmpty() == true) {
			throw new InstanciaInexistenteException("\nNao ha produtos cadastrados!\n");
		}
		return repoestoque.listar();
	}

	public void subtrairProduto(int codigo, int quantidade) throws NegocioException{
		int pos = retornarPosicao(codigo);
		if (pos == -1) {
			throw new InstanciaInexistenteException("\nProduto de codigo("+codigo+") nao existe\n");
		}
		for (Produto prod : repoestoque.listar()) {
			if (codigo == prod.getCodigo()) {
				if (quantidade <= prod.getQuantidade()) {
					repoestoque.subtrairProduto(pos, quantidade);
				}
				else {
					throw new QuantidadeInvalidaException("\nQuantidade insuficiente para subtracao!\n");
				}
				break;
			}
		}
	}

	public void inserir(Produto prod) throws NegocioException{
		if (prod == null) {
			throw new RuntimeException("\nInstancia de Produto nula\n");
		}
		if(prod.getNome().isEmpty() == true) {
			alert.setTitle("Nome invalido");
			alert.setHeaderText(null);
			alert.setContentText("Adicione um nome!");
			alert.showAndWait();
			throw new RuntimeException("\nString do Nome do Produto nula\n");
		}
		if(prod.getCodigo() <= 0) {
			alert.setTitle("Codigo negativo");
			alert.setHeaderText(null);
			alert.setContentText("Codigo não pode ser negativo, adicione outro!");
			alert.showAndWait();
			throw new IdentificacaoInvalidaException("\nCodigo ("+prod.getCodigo()+") invalido\n");
		}
		if (retornarPosicao(prod.getCodigo()) != -1) {
			alert.setTitle("Produto existente");
			alert.setHeaderText(null);
			alert.setContentText("Já existe um produto com esse código, tente outro!");
			alert.showAndWait();
			throw new InstanciaRepetidaException("\nProduto de codigo("+prod.getCodigo()+") ja cadastrado\n");
		}
		repoestoque.inserir(prod);
		instancia.repoestoque.salvarArquivo();
		
		alert.setTitle("Confirmacao de adicao");
		alert.setHeaderText(null);
		alert.setContentText("Produto adicionado com sucesso!");
		alert.showAndWait();
	}

	public Produto buscar(int cod) {
		int posicao;
		posicao = this.retornarPosicao(cod);

		if (posicao != -1) {
			return repoestoque.buscar(posicao);
		}
		alert.setTitle("Produto não encontrado");
		alert.setHeaderText(null);
		alert.setContentText("Produto não existe em estoque, verifique se o código foi digitado corretamente!");
		alert.showAndWait();
		return null;
	}

	public void alterar(Produto novoProduto) throws NegocioException{
		int posicao;
		if (novoProduto == null) {
			throw new RuntimeException("\nInstancia de Produto nula\n");
		}
		if(novoProduto.getNome() == null) {
			throw new RuntimeException("\nString do Nome do Produto nula\n");
		}
		posicao = this.retornarPosicao(novoProduto.getCodigo());
		if (posicao == -1) {
			throw new InstanciaInexistenteException("\nInstancia de Produto ("+novoProduto.getCodigo()+") nao existe\n");
		}
		if (repoestoque.listar().get(posicao).getCodigo() == novoProduto.getCodigo()) {
			repoestoque.alterar(novoProduto, posicao);
			instancia.repoestoque.salvarArquivo();
			System.out.println("Produto alterado com sucesso!");
		}
	}

	public void remover(int cod) throws NegocioException{
		if(cod <= 0) {
			throw new IdentificacaoInvalidaException("\nCodigo ("+cod+") invalido\n");
		}
		if (repoestoque.listar().isEmpty() == true) {
			alert.setTitle("Erro!");
			alert.setHeaderText(null);
			alert.setContentText("Não há produtos para remover!");
			alert.showAndWait();
			throw new InstanciaInexistenteException("\nNao ha produtos cadastrados no array\n");
		}
		int posicao = this.retornarPosicao(cod);
		if (posicao == -1) {
			alert.setTitle("Erro!");
			alert.setHeaderText(null);
			alert.setContentText("Não existe produto com esse código!");
			alert.showAndWait();
			throw new InstanciaInexistenteException("\nNao ha produto de codigo ("+cod+") para remover\n");
		}
		repoestoque.remover(posicao);
		instancia.repoestoque.salvarArquivo();
		alert.setTitle("Confirmacao de remoção");
		alert.setHeaderText(null);
		alert.setContentText("Produto removido com sucesso!");
		alert.showAndWait();
		
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
		return -1;
	}
}