package principal;

import java.util.Scanner;

import beans.Endereco;
import beans.Funcionario;
import beans.ItemVenda;
import beans.Pessoa;
import beans.Produto;

public class PrincipalTeste {
	public static void main(String[] args) {
		
		// SCANNER
		
		Scanner scanf = new Scanner(System.in);
		
		//FACHADA 
				Fachada fachada = Fachada.getInstancia();
		
		// OBJETOS DE TESTE - Armazena-los em arquivo depois!
		
		String[] nomes = {"Salgadinho","Biscoito","Sorvete","Arroz","Coca-Cola","Feijao","Macarrao","Acucar","Agua","Farinha"};
		double[] precos = {1.50, 1.50, 13.00, 4.00, 8.00, 7.00, 2.50, 3.00, 1.00, 4.00};
		int codigoProduto[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		Produto[] produto = new Produto[10];
		ItemVenda[] itens = new ItemVenda[10];
		
		for (int i = 0; i < produto.length; i++) {
			produto[i] = new Produto( nomes[i], codigoProduto[i],1000, precos[i]);
			itens[i] = new ItemVenda(codigoProduto[i], nomes[i], precos[i], i);
			fachada.inserirProduto(produto[i]);
		}
		
		Endereco e1 = new Endereco("Rua A", "Cidade A", "12345-100", "155");
		Endereco e2 = new Endereco("Rua B", "Cidade B", "98765-100", "344");
		Endereco e3 = new Endereco("Rua C", "Cidade C", "15657-887", "511");
		Endereco e4 = new Endereco("Rua D", "Cidade D", "78877-002", "361");
		
		Endereco enderecoTeste;
		
		Pessoa p1 = new Pessoa(e1, "Fabio", "11111111111");
		Pessoa p2 = new Pessoa(e2, "Elthon", "11111111111");
		Pessoa p3 = new Pessoa(e3, "Gabriel", "99999999999");
		Pessoa p4 = new Pessoa(e4, "Luciano", "11111111111");
		
		Pessoa pessoaTeste;
		
		Funcionario f1 = new Funcionario(p1,"vendedor",2001,1);
		Funcionario f2 = new Funcionario(p2,"vendedor",2000,2);
		Funcionario f3 = new Funcionario(p3,"vendedor",2000,3);
		Funcionario f4 = new Funcionario(p4,"vendedor",2000,4);
		
		Funcionario funcionarioTeste;

		fachada.inserirFuncionario(f1);
		fachada.inserirFuncionario(f2);
		fachada.inserirFuncionario(f3);
		fachada.inserirFuncionario(f4);
		
		int opcao, identificacao, codigo, quantidade;
		boolean parar = false, vender = true, terminarVenda = false;
		String nome, cep, cidade, rua, logradouro, numeroCasa, cpf, funcao;
		double preco, salario;
		Funcionario f;
		
		
		while(!parar) {
			System.out.println("\n\n=========================================\n\n\tMercadinho mil grau\n\n(1) - Sistema de Vendas\n"
							 + "(2) - Sistema de Estoque\n(3) - Sistema de Funcionario\n(4) - Sistema Financeiro\n(5) - Fechar programa\n\n==> ");
			opcao = scanf.nextInt();
			
			switch(opcao) {
			case 1: {
				System.out.println("\n\n=========================================\n\n\tSistema de Vendas\n\n(1) - Registrar Venda"
						         + "\n(2) - Listar Vendas\n(3) - Deletar Historico\n(4) - Menu principal");
				opcao = scanf.nextInt();
				
				if( opcao == 1 ) {
					int i = 1;
					System.out.println("Digite a identificacao do funcionario");
					identificacao = scanf.nextInt();
					f = fachada.buscarFuncionario(identificacao);
					System.out.println("Funcionario: "+f.getPessoa().getNome());
					while( !terminarVenda ){
						
						System.out.println(fachada.listarItensVenda());
						System.out.println("\n\n\t(0) - Encerrar a venda\n\t(-1) - Cancelar venda\n\n");
						System.out.println("Digite o codigo do produto");
						codigo = scanf.nextInt();
						
						if(codigo == 0) {
							System.out.println("\n\n\tTem certeza de que quer ENCERRAR a compra?\n\t (1) - SIM \n\t(2) - NAO\n\n");
							 int charizard = scanf.nextInt();
							 if( charizard == 1 ) {
								 fachada.encerrarPedido();
								 terminarVenda = true;
							 }
							 else if(codigo == 2) {
								
							 }
						}
						if(codigo == -1) {
							System.out.println("\n\n\tTem certeza de que quer CANCELAR a compra?\n\t (1) - SIM \n\t(2) - NAO\n\n");
							 int charizard = scanf.nextInt();
							 if( charizard == 1 ) {
								 fachada.cancelarPedido();
								 terminarVenda = true;
							 }
							 else if(codigo == 2) {
								
							 }
						}
						
						System.out.println("Digite a quantidade");
						quantidade = scanf.nextInt();
						fachada.vender(codigo, quantidade, fachada.buscarFuncionario(identificacao));
					}
					
					
				}
				else if (opcao == 2) {
					fachada.listarVendas();
				} 
				else if(opcao == 3) {
					fachada.limparHistorico();
				}
				else if (opcao == 4){
					// já volta automaticamente
				}
				break;
			}
			case 2: {
				System.out.println("\n\n=========================================\n\n\tSistema de estoque\n\n(1) - Listar Produtos"
				         + "\n(2) - Adicionar produto\n(3) - Atualizar produtor\n(4) - Remover produto\n(5) - Buscar produto\n(6) - Menu principal\n\n");
				
				opcao = scanf.nextInt();
				
				if( opcao == 1){
					fachada.getControladorEstoque().listarProduto();				
				}
				else if( opcao == 2 ) {
					
					System.out.println("Digite o codigo do produto");
					codigo = scanf.nextInt();
					System.out.println("Digite o nome do produto");
					scanf.nextLine();
					nome = scanf.nextLine();
					System.out.println("Digite o preco do produto");
					preco = scanf.nextDouble();
					System.out.println("Digite a sua quantidade");
					quantidade = scanf.nextInt();
					Produto prod = new Produto(nome,codigo,quantidade,preco);
					fachada.inserirProduto(prod);				
				}
				else if (opcao == 3) {
					opcao = 0;
					System.out.println("Digite o codigo do produto que sera atualizado");
					codigo = scanf.nextInt();
					System.out.println("O que voce deseja alterar?\n(1)Quantidade\n(2)Preco\n(3)Nome");
					opcao = scanf.nextInt();
					if(opcao == 1){
						Produto prod = fachada.buscarProduto(codigo);
						System.out.println("Digite a nova quantidade");
						quantidade = scanf.nextInt();
						prod.setQuantidade(quantidade);
						fachada.atualizarProduto(prod);
					}
					else if(opcao == 2){
						Produto prod = fachada.buscarProduto(codigo);
						System.out.println("Digite o novo preco");
						preco = scanf.nextDouble();
						prod.setPreco(preco);
						fachada.atualizarProduto(prod);
					}
					else if(opcao == 3){
						Produto prod = fachada.buscarProduto(codigo);
						System.out.println("Digite o novo nome");
						nome = scanf.nextLine();
						prod.setNome(nome);
						fachada.atualizarProduto(prod);
					}
				} 
				else if(opcao == 4) {
					System.out.println("Digite o codigo do produto a ser removido");
					codigo = scanf.nextInt();
					fachada.removerProduto(codigo);
				}
				
				else if (opcao == 5){
					System.out.println("Digite o codigo do produto a ser buscado");
					codigo = scanf.nextInt();
					System.out.println(fachada.buscarProduto(codigo));
				}
				else if (opcao == 6){
					// já volta automaticamente
				}
				break;
			}
			case 3: {
				System.out.println("\n\n=========================================\n\n\tSistema de Funcionario\n\n(1) - Adicionar funcionario"
				         + "\n(2) - Atualizar funcionario\n(3) - Remover funcionario\n(4) - Buscar funcionario\n(5) - Menu principal\n\n");
				
				opcao = scanf.nextInt();
				
				if( opcao == 1 ) {
					System.out.println("\n\n\tDigite a identificacao do funcionario: \n");
					identificacao = scanf.nextInt();
					System.out.println("\n\n\tNome: ");
					scanf.nextLine();
					nome = scanf.nextLine();
					System.out.println("\n\n\tCPF: ");
					cpf = scanf.nextLine();
					System.out.println("\n\n\tLogradouro: ");
					logradouro = scanf.nextLine();
					System.out.println("\n\n\tCidade: ");
					cidade = scanf.nextLine();
					System.out.println("\n\n\tCEP: ");
					cep = scanf.nextLine();
					System.out.println("\n\n\tNumero da casa: ");
					numeroCasa = scanf.nextLine();
					System.out.println("\n\n\tSalario: ");
					salario = scanf.nextDouble();
					System.out.println("\n\n\tFuncao: ");
					funcao = scanf.nextLine();
					enderecoTeste = new Endereco(logradouro, cidade, cep, numeroCasa);
					pessoaTeste = new Pessoa(enderecoTeste, nome, cpf);
					fachada.inserirFuncionario(new Funcionario(pessoaTeste, funcao, salario, identificacao));
				} 
				else if (opcao == 2) {
					System.out.println("\n\n\tDigite a identificacao do funcionario: \n");
					identificacao = scanf.nextInt();
					System.out.println("\n\n\tNome: ");
					scanf.nextLine();
					nome = scanf.nextLine();
					System.out.println("\n\n\tCPF: ");
					cpf = scanf.nextLine();
					System.out.println("\n\n\tLogradouro: ");
					logradouro = scanf.nextLine();
					System.out.println("\n\n\tCidade: ");
					cidade = scanf.nextLine();
					System.out.println("\n\n\tCEP: ");
					cep = scanf.nextLine();
					System.out.println("\n\n\tNumero da casa: ");
					numeroCasa = scanf.nextLine();
					enderecoTeste = new Endereco(logradouro, cidade, cep, numeroCasa);
					pessoaTeste = new Pessoa(enderecoTeste, nome, cpf);
					fachada.atualizarFuncionario(new Funcionario(pessoaTeste, identificacao));
				} 
				else if(opcao == 3) {
					System.out.println("\n\n\tDigite a identificacao do funcionario: \n");
					fachada.removerFuncionario(scanf.nextInt());
				}
				
				else if (opcao == 4){
					System.out.println("\n\n\tDigite a identificacao do funcionario: \n");
					f = fachada.buscarFuncionario(scanf.nextInt());
					System.out.println(f);
				}
				else if(opcao == 5) {
					// já volta automaticamente
				}
				break;
			}
			case 4: {
				System.out.println("\n\n=========================================\n\n\tSistema Financeiro\n\n(1) - Pagar funcionario"
				         + "\n(2) - Contatar fornecedor\n(3) - Informacoes das financas\n(4) - Menu principal\n\n");
				
				opcao = scanf.nextInt();
				
				if( opcao == 1 ) {
					System.out.println("\n\n\tDigite a identificacao do funcionario: \n");
					fachada.pagarFuncionario(fachada.buscarFuncionario(scanf.nextInt()));
				} 
				else if (opcao == 2) {
					// TODO
				} 
				else if(opcao == 3) {
					fachada.exibirFinancas();
				}
				
				else if (opcao == 4){
					// já volta automaticamente
				}
				break;
			}	
			case 5: {
				parar = true;				
				break;
			}	
		  }
		} // fechamento do switch externo
	}
}