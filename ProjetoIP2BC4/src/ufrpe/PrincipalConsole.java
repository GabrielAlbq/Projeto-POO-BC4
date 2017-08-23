package ufrpe;

import java.time.LocalDateTime;
import java.util.Scanner;

import ufrpe.negocio.Fachada;
import ufrpe.negocio.beans.Admin;
import ufrpe.negocio.beans.Endereco;
import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.Gerente;
import ufrpe.negocio.beans.ItemVenda;
import ufrpe.negocio.beans.Login;
import ufrpe.negocio.beans.Produto;
import ufrpe.negocio.beans.Vendedor;
import ufrpe.negocio.exception.NegocioException;

public class PrincipalConsole {
	public static void main(String[] args) {

		// SCANNER
		Scanner scanf = new Scanner(System.in);

		// FACHADA
		Fachada fachada = Fachada.getInstancia();

		// OBJETOS DE TESTE - Armazena-los em arquivo depois!

//		String[] nomes = { "Salgadinho", "Biscoito", "Sorvete", "Arroz", "Coca-Cola", "Feijao", "Macarrao", "Acucar",
//				"Agua", "Farinha" };
//		double[] precos = { 1.50, 1.50, 13.00, 4.00, 8.00, 7.00, 2.50, 3.00, 1.00, 4.00 };
//		int codigoProduto[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
//
//		Produto[] produto = new Produto[10];
//		ItemVenda[] itens = new ItemVenda[10];
//
//		
//		for (int i = 0; i < produto.length; i++) {
//			produto[i] = new Produto(nomes[i], codigoProduto[i], 1000, precos[i]);
//			itens[i] = new ItemVenda(codigoProduto[i], nomes[i], precos[i], i);
//			try {
//				fachada.inserirProduto(produto[i]);
//			} catch(NegocioException exception) {
//				exception.printStackTrace();
//			}
//		}
//	
//		
		
		// TODO fazer a parte de arquivo funcionar para inserirFuncionario
		Login log1 = new Login("fabio", "123", "pokemon");
		Login log2 = new Login("elthon", "123", "trator");
		Login log3 = new Login("admin", "admin", "chefe");
		Endereco end1 = new Endereco("rua a", "cidade a", "cep a", "numero a");
		Endereco end2 = new Endereco("rua b", "cidade b", "cep b", "numero b");
	//	Vendedor vendedor = new Vendedor(2000, 1, false, log1, "fabio", "11122233344", end1);
		//Gerente gerente = new Gerente(3000, 2, false, log2, "elthon", "66655588844", end2);
		Admin adm = new Admin(log3, 100);
		
		try {
		//	fachada.inserirFuncionario(vendedor);
		//	fachada.inserirFuncionario(gerente);
		fachada.inserirFuncionario(adm);
		} catch(NegocioException exception) {
			exception.printStackTrace();
		}
		
		
		boolean parar = false, terminarVenda = false, auxiliarVenda = false, quantidadenula = true;
		String nome, cep, cidade, logradouro, numeroCasa, cpf, user, pass;
		int opcao, identificacao, codigo, quantidade;
		double preco, salario;
		Funcionario f;

		while(!parar) {
			System.out.println("\n=========================================\n\tUsername: ");
			user = scanf.nextLine();
			System.out.println("\n\tPassword: ");
			pass = scanf.nextLine();
			try {
				f = fachada.validarLogin(new Login(user, pass, ""));
			}catch(NegocioException exception) {
				exception.printStackTrace();
			}
			// TODO
		}
		
		//TODO terminar o sistema rodando pelo main
		while (!parar) {
			System.out.println(
					"\n\n=========================================\n\n\tMercadinho\n\n(1) - Sistema de Vendas\n"
							+ "(2) - Sistema de Estoque\n(3) - Sistema de Funcionario\n(4) - Sistema Financeiro\n(5) - Fechar programa");
			opcao = scanf.nextInt();

			switch (opcao) {
			case 1: {
				System.out.println(
						"\n\n=========================================\n\n\tSistema de Vendas\n\n(1) - Registrar Venda"
								+ "\n(2) - Listar Vendas\n(3) - Deletar Historico\n(4) - Menu principal");
				opcao = scanf.nextInt();
				if (opcao == 1) {

					double totalparcial = 0;
					while (opcao != 0) { // while para voltar a pedir a
											// identificacao caso seja nula.
									
						System.out.println("Digite a identificacao do funcionario");
						identificacao = scanf.nextInt();
						f = fachada.buscarFuncionario(identificacao);
						if (f == null) {
							opcao = 1;
						} else {
							terminarVenda = false; // Recebem o valor aqui pois
													// se for registrar nova
													// venda, para eles entrarem
													// no while precisam recebe
													// false de novo.
							auxiliarVenda = false;
							System.out.println("Funcionario: " + f.getNome());
							try {
								System.out.println(fachada.listarProdutos());
							} catch(NegocioException exception) {
								exception.printStackTrace();
							}
							while (!terminarVenda) {

								System.out.println("\n\n\t(0) - Encerrar a venda\n\t(-1) - Cancelar venda\n\n");
								System.out.println("Digite o codigo do produto");
								codigo = scanf.nextInt();
								if (codigo == 0) {
									try {
										System.out.println(fachada.listarItensVenda());
									} catch(NegocioException exception) {
										exception.printStackTrace();
									}
									System.out.println("\nTotal parcial: " + totalparcial);
									System.out.println(
											"\n\n\tTem certeza de que quer ENCERRAR a compra?\n\t(1) - SIM \n\t(2) - NAO\n\n");
									codigo = scanf.nextInt();
									if (codigo == 1) {
										if (quantidadenula == false) {
											try {
												fachada.encerrarPedido();
											} catch(NegocioException exception) {
												exception.printStackTrace();
											}
											//fachada.gerarNotaFiscal(f);
										}
										auxiliarVenda = true;
										terminarVenda = true;
										opcao = 0;
									} else if (codigo == 2) {
										break;
									}
								}
								if (codigo == -1) {
									System.out.println(
											"\n\n\tTem certeza de que quer CANCELAR a compra?\n\t(1) - SIM \n\t(2) - NAO\n\n");
									codigo = scanf.nextInt();
									if (codigo == 1) {
										fachada.cancelarPedido();

										terminarVenda = true;
										auxiliarVenda = true;
										opcao = 0;
										System.out.println("\n\tVenda cancelada!\n\n");
									} else if (codigo == 2) {
										break;
									}
								}
								if (!auxiliarVenda) {
									if (fachada.buscarProduto(codigo) != null) {
										Produto prod = fachada.buscarProduto(codigo);
										System.out.println("Digite a quantidade");
										quantidade = scanf.nextInt();

										if (quantidade > 0 && quantidade < prod.getQuantidade()) {
											ItemVenda ArrayItem = new ItemVenda(prod, quantidade);
											try {
												fachada.inserirItem(ArrayItem);
											} catch(NegocioException exception) {
												exception.printStackTrace();
											}
											totalparcial = totalparcial + ArrayItem.valorTotal();
											System.out.print("\n\tProduto: " + ArrayItem.getNome() + "\n\tQtd: "
													+ ArrayItem.getQtd() + "\n\tPreco: " + ArrayItem.getPreco()
													+ "\n\tSubTotal: " + (ArrayItem.getPreco() * ArrayItem.getQtd())
													+ "\n");
											System.out.println("\nTotal parcial: " + totalparcial);
											quantidadenula = false;
											
										} else {
											System.out.println("Quantidade tem que ser maior que zero!");
										}
									} 
								}

							}
						}
					}
				} else if (opcao == 2) {
					System.out.println(fachada.listarVendas());
				} else if (opcao == 3) {
					fachada.limparHistorico();
					 System.out.println("\n\n\tHistorico de vendas apagado,\n\tnotas fiscais deletadas!\n\n");
				} else if (opcao == 4) {
				}
				break;
			}
			case 2: {
				System.out.println(
						"\n\n=========================================\n\n\tSistema de estoque\n\n(1) - Listar Produtos"
								+ "\n(2) - Adicionar produto\n(3) - Atualizar produto\n(4) - Remover produto\n(5) - Buscar produto\n(6) - Menu principal\n\n");

				opcao = scanf.nextInt();

				if (opcao == 1) {
					try {
						System.out.println(fachada.listarProdutos());
					} catch(NegocioException exception) {
						exception.printStackTrace();
					}
				} else if (opcao == 2) {

					System.out.println("Digite o codigo do produto");
					codigo = scanf.nextInt();
					System.out.println("Digite o nome do produto");
					scanf.nextLine();
					nome = scanf.nextLine();
					System.out.println("Digite o preco do produto");
					preco = scanf.nextDouble();
					System.out.println("Digite a sua quantidade");
					quantidade = scanf.nextInt();
					Produto prod = new Produto(codigo, nome, preco, quantidade);
					try {
						fachada.inserirProduto(prod);
					} catch(NegocioException exception) {
						exception.printStackTrace();
					}
				} else if (opcao == 3) {
					opcao = 0;
					System.out.println("Digite o codigo do produto que sera atualizado");
					codigo = scanf.nextInt();
					System.out.println("O que voce deseja alterar?\n(1)Quantidade\n(2)Preco\n(3)Nome");
					opcao = scanf.nextInt();
					if (opcao == 1) {
						Produto prod = fachada.buscarProduto(codigo);
						System.out.println("Digite a nova quantidade");
						quantidade = scanf.nextInt();
						if (quantidade > 0) {
							prod.setQuantidade(quantidade);
							try {
								fachada.atualizarProduto(prod);
							} catch(NegocioException exception) {
								exception.printStackTrace();
							}
						} else {
							System.out.println("Erro! quantidade invalida!");
						}
					} else if (opcao == 2) {
						Produto prod = fachada.buscarProduto(codigo);
						System.out.println("Digite o novo preco");
						preco = scanf.nextDouble();
						if (preco > 0) {
							prod.setPreco(preco);
							try {
								fachada.atualizarProduto(prod);
							} catch(NegocioException exception) {
								exception.printStackTrace();
							}
						} else {
							System.out.println("Erro! quantidade invalida!");
						}
					} else if (opcao == 3) {
						Produto prod = fachada.buscarProduto(codigo);
						System.out.println("Digite o novo nome");
						scanf.nextLine();
						nome = scanf.nextLine();
						prod.setNome(nome);
						try {
							fachada.atualizarProduto(prod);
						} catch(NegocioException exception) {
							exception.printStackTrace();
						}
					}
				} else if (opcao == 4) {
					System.out.println("Digite o codigo do produto a ser removido");
					codigo = scanf.nextInt();
					try {
						fachada.removerProduto(codigo);
					} catch(NegocioException exception) {
						exception.printStackTrace();
					}
				}

				else if (opcao == 5) {
					System.out.println("Digite o codigo do produto a ser buscado");
					codigo = scanf.nextInt();
					System.out.println(fachada.buscarProduto(codigo));
				} else if (opcao == 6) {
				}
				break;
			}
			case 3: {
				System.out.println(
						"\n\n=========================================\n\n\tSistema de Funcionario\n\n(1) - Listar todos os funcionarios\n"
								+ "(2) - Adicionar funcionario" + "\n(3) - Atualizar funcionario\n"
								+ "(4) - Remover funcionario\n(5) - Buscar funcionario\n(6) - Menu principal\n\n");

				opcao = scanf.nextInt();

				if (opcao == 1) {
					try {
						System.out.println(fachada.listarFuncionarios());
					} catch(NegocioException exception) {
						exception.printStackTrace();
					}
				} else if (opcao == 2) {
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
					scanf.nextLine();
					// TODO
//					try {
//						
//						fachada.inserirFuncionario();
//					}catch(NegocioException exception) {
//						exception.printStackTrace();
//					}
				} else if (opcao == 3) {
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
					// enderecoTeste = new Endereco(logradouro, cidade, cep,
					// numeroCasa);
					// pessoaTeste = new Pessoa(enderecoTeste, nome, cpf);
					// fachada.atualizarFuncionario(new Funcionario(pessoaTeste,
					// identificacao));
				} else if (opcao == 4) {
					System.out.println("\n\n\tDigite a identificacao do funcionario: \n");
					try {
						fachada.removerFuncionario(scanf.nextInt());
					} catch(NegocioException exception) {
						exception.printStackTrace();
					}
				}

				else if (opcao == 5) {
					System.out.println("\n\n\tDigite a identificacao do funcionario: \n");
					f = fachada.buscarFuncionario(scanf.nextInt());
					System.out.println(f);
				} else if (opcao == 6) {
					// ja volta automaticamente com qualquer numero exceto os
					// que estao nos if's!
				}
				break;
			}
			case 4: {
				System.out.println(
						"\n\n=========================================\n\n\tSistema Financeiro\n\n(1) - Pagar funcionario"
								+ "\n(2) - Informacoes das financas\n(3) - Menu principal\n\n");

				opcao = scanf.nextInt();

				if (opcao == 1) {
					System.out.println("\n\n\tDigite a identificacao do funcionario: \n");
					try {
						fachada.pagarFuncionario(scanf.nextInt());//fachada.buscarFuncionario(scanf.nextInt()));
					} catch(NegocioException exception) {
						exception.printStackTrace();
					}
				} else if (opcao == 2) {
					System.out.println(fachada.exibirFinancas());
				} else if (opcao == 3) {
					// ja volta automaticamente com qualquer numero exceto os
					// que estao nos if's!
				}
				break;
			}
			case 5: {
				parar = true;
				break;
			}
			}
		} // fechamento do switch
		if (scanf != null)
			scanf.close();
	}
}