package principal;

import java.util.Scanner;

import beans.*;
import controladores.*;
import repositorios.*;

public class PrincipalTeste {
	public static void main(String[] args) {
		
		// TODO todos os controladores serao instanciados pela fachada, trocar depois!
		// CONTROLADORES INSTANCIADOS
		
		ControladorFuncionario controladorFuncionario = ControladorFuncionario.instanciarControlFuncionario();
		ControladorEstoque controladorEstoque = ControladorEstoque.getInstancia();
		ControladorVenda controladorVenda = ControladorVenda.instanciar();
		
		// REPOSITORIOS INSTANCIADOS
				
		RepositorioFuncionario repositorioFuncionario = RepositorioFuncionario.instanciarRepoFuncionario();
		RepositorioEstoque repositorioEstoque = RepositorioEstoque.getInstancia();
		RepositorioVenda repositorioVenda = RepositorioVenda.intanciar();
		
		// SCANNER
		
		Scanner scanf = new Scanner(System.in);
		
		// OBJETOS DE TESTE - Armazena-los em arquivo depois!
		
		String[] nomes = {"Salgadinho","Biscoito","Sorvete","Arroz","Coca-Cola","Feij�o","Macarr�o","A��car","�gua","Farinha"};
		double[] precos = {1.50, 1.50, 3.50, 4.00, 8.00, 7.00, 2.50, 3.00, 1.00, 4.00};
		int codigoProduto[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		Produto[] produto = new Produto[10];
		ItemVenda[] itens = new ItemVenda[10];
		
		for (int i = 0; i < produto.length; i++) {
			produto[i] = new Produto( nomes[i], codigoProduto[i],100, precos[i]);
			itens[i] = new ItemVenda(codigoProduto[i], nomes[i], precos[i], i);
		}
		
		Endereco e1 = new Endereco("Rua A", "Cidade A", "12345-100", "999");
		Endereco e2 = new Endereco("Rua B", "Cidade B", "98765-100", "333");
		Endereco enderecoTeste;
		
		Pessoa p1 = new Pessoa(e1, "fabio", "11111111111");
		Pessoa p2 = new Pessoa(e2, "duda", "99999999999");
		Pessoa pessoaTeste;
		
		Funcionario f1 = new Funcionario(p1,"vendedor",2000,1);
		Funcionario f2 = new Funcionario(p2,"vendedor",2000,2);
		Funcionario funcionarioTeste;
			
		controladorFuncionario.inserir(f1);
		controladorFuncionario.inserir(f2);
		
		int opcao, identificacao;
		boolean parar = false;
		boolean vender = true;
		
		
		while(!parar) {
			System.out.println("\n\n=========================================\n\n\tMercadinho mil grau\n\n(1) - Sistema de Vendas\n"
							 + "(2) - Sistema de Estoque\n(3) - Sistema Financeiro\n(4) - Sistema de Funcionarios\n\n==> ");
			opcao = scanf.nextInt();
			
			switch(opcao) {
			case 1: {
				System.out.println("\n\n=========================================\n\n\tSistema de Vendas\n\n(1) - Registrar Venda"
						         + "\n(2) - Listar Vendas\n(3) - Deletar Histórico\n");
				opcao = scanf.nextInt();
				
				if( opcao == 1 ) {
					// tem um laço e nele um sysout com todos os itens a serem vendidos, o usuario escolhe o item, e depois sua quantidade
					// apos a quantidade do produto, o usuario dirá se quer mais algum produto, se ele nao quiser a venda será encerrada.
					// É emitida uma nota fiscal, que será armazenada no repositorio venda, o dinheiro total é enviado para o repositorio financeiro
					//  e os produtos vendidos sao retirados do estoque.
					// essa informação será enviada para a nota fiscal desta venda, que terá o funcionario que efetuou a venda, indentificacao
					System.out.println("\n\n=========================================\n\n\tInforme a identificação do funcionario: ");
					identificacao = scanf.nextInt();
					if( controladorFuncionario.buscar(identificacao) == null) {
						System.out.println("\n\tErro! Funcionario inexistente\n\n");
						vender  = false;
					}
					System.out.println("\n\n=========================================\n\n\tFuncionario "
					+repositorioFuncionario.retornarFuncionario(identificacao).getPessoa().getNome() + " irá registrar a venda\n");
					System.out.println("\n\n==========================================\n\n\tTabela de produtos\n\n");
					//for (int i = 0; i < ; i++)
					
					vender = true;
				} 
				else if (opcao == 2) {
					// chamar controlador venda, que possui o metodo listar todas as vendas
				} 
				else if(opcao == 3) {
					// chamar o controlador venda, que apaga o historico
				}
				break;
			}
			case 2: {
				
				
				break;
			}
			case 3: {
				
				
				break;
			}
			case 4: {
				
				
				break;
			}			
		}
		} // fechamento do switch externo
	}
}

	  		
	/*
		String rua, cep, cidade, nome, cpf, numero, funcao = "vendedor";
		boolean parar = false;
		int identificacao, opcao;
		
		Endereco e1 = new Endereco("Rua A", "Cidade A", "12345-100", "999");
		Endereco e2 = new Endereco("Rua B", "Cidade B", "98765-100", "333");
		Endereco enderecoTeste;
		
		Pessoa p1 = new Pessoa(e1, "fabio", "11111111111");
		Pessoa p2 = new Pessoa(e2, "duda", "99999999999");
		Pessoa pessoaTeste;
		
		Funcionario f1 = new Funcionario(p1,"vendedor",2000,1);
		Funcionario f2 = new Funcionario(p2,"vendedor",2000,2);
		Funcionario funcionarioTeste;
		
	
		
		ControladorFuncionario controladorFuncionario;
		controladorFuncionario = ControladorFuncionario.instanciarControlFuncionario();
		
		controladorFuncionario.inserir(f1);
		controladorFuncionario.inserir(f2);
		
		do {
			//controladorFuncionario.imprimirTodos();
			
			System.out.println("\n==============================================\n");
			System.out.println("\n\tSistema de Funcionarios\n\n\t(1) inserir");
			System.out.print("\t(2) alterar\n\t(3) buscar\n\t(4) remover\n\n\t==>");
			opcao = scanf.nextInt();
			switch(opcao) {
				case 1: {
					
					System.out.println("\n==============================================\n");
					System.out.println("\n\tinsira a identifica��o do funcionario: ");
					identificacao = scanf.nextInt();
					System.out.println("insira o numero da sua resid�ncia: ");
					scanf.nextLine();
					numero = scanf.nextLine();
					System.out.println("insira sua rua: ");
					rua = scanf.nextLine();
					System.out.println("insira seu cep: ");
					cep = scanf.nextLine();
					System.out.println("insira sua cidade: ");
					cidade = scanf.nextLine();
					System.out.println("insira seu nome: ");
					nome =scanf.nextLine();
					System.out.println("insira seu CPF: ");
					cpf =scanf.nextLine();
					
					enderecoTeste = new Endereco(rua, cidade, cep, numero);
					pessoaTeste = new Pessoa(enderecoTeste, nome, cpf);
					funcionarioTeste = new Funcionario(pessoaTeste, funcao, 2000, identificacao);
					
					controladorFuncionario.inserir(funcionarioTeste);
					
					break;
				}
				case 2: {
					System.out.println("\n==============================================\n");
					System.out.println("\n\tinsira a identifica��o do funcionario: ");
					identificacao = scanf.nextInt();
					System.out.println("\ninsira o numero da sua resid�ncia: ");
					scanf.nextLine();
					numero = scanf.nextLine();
					System.out.println("insira sua rua: ");
					rua = scanf.nextLine();
					System.out.println("insira seu cep: ");
					cep = scanf.nextLine();
					System.out.println("insira sua cidade: ");
					cidade = scanf.nextLine();
					System.out.println("insira seu nome: ");
					nome =scanf.nextLine();
					System.out.println("insira seu CPF: ");
					cpf =scanf.nextLine();
					
					enderecoTeste = new Endereco(rua, cidade, cep, numero);
					pessoaTeste = new Pessoa(enderecoTeste, nome, cpf);
					funcionarioTeste = new Funcionario(pessoaTeste, funcao, 2000, identificacao);
					
					controladorFuncionario.inserir(funcionarioTeste);
					break;
				}
				case 3: {
					System.out.println("\n==============================================\n");
					System.out.println("\n\tinsira a identifica��o do funcionario: ");
					funcionarioTeste = controladorFuncionario.buscar(scanf.nextInt());
					System.out.println("\n\t"+funcionarioTeste+"\n\n");
					break;
				}
				case 4: {
					System.out.println("\n==============================================\n");
					System.out.println("\n\tinsira a identifica��o do funcionario: ");
					controladorFuncionario.remover(scanf.nextInt());
					break;
				}
			}
		}while(!parar);
		
		if(scanf != null)
			scanf.close();
	 */
