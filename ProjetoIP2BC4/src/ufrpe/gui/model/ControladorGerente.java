package ufrpe.gui.model;

import java.io.IOException;
import java.util.Observable;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import ufrpe.gui.Principal;
import ufrpe.negocio.Fachada;
import ufrpe.negocio.beans.Endereco;
import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.Gerente;
import ufrpe.negocio.beans.ItemVenda;
import ufrpe.negocio.beans.Login;
import ufrpe.negocio.beans.NotaFiscal;
import ufrpe.negocio.beans.Produto;
import ufrpe.negocio.beans.Vendedor;
import ufrpe.negocio.exception.NegocioException;

public class ControladorGerente {

	Fachada fachada = Fachada.getInstancia();
	private Principal main;

	// PRINCIPAL
	@FXML
	Button btnSair;
	@FXML
	Label lbAdmNome;
	// CADASTRAR
	@FXML
	TextField tfCadFuncID;
	@FXML
	TextField tfCadFuncNome;
	@FXML
	TextField tfCadFuncCPF;
	@FXML
	TextField tfCadFuncLog;
	@FXML
	TextField tfCadFuncCid;
	@FXML
	TextField tfCadFuncCEP;
	@FXML
	TextField tfCadFuncCasa;
	@FXML
	TextField tfCadFuncSal;
	@FXML
	TextField tfCadFuncFun;
	@FXML
	TextField tfCadFuncLogin;
	@FXML
	TextField tfCadFuncSenha;
	@FXML
	TextField tfCadFuncPdS;
	@FXML
	Button btnFuncCadastrar;
	@FXML
	ChoiceBox<String> cbCadFuncFun;

	@FXML
	TextField tfCadProdCodigo;
	@FXML
	TextField tfCadProdNome;
	@FXML
	TextField tfCadProdPreco;
	@FXML
	TextField tfCadProdQtd;
	@FXML
	Button btnProdCadastrar;

	// REMOVER
	@FXML
	TextField tfRemoFuncID;
	@FXML
	Button btnFuncRemover;

	@FXML
	TextField tfRemoProdID;
	@FXML
	Button btnProdRemover;

	// LISTAR
	@FXML
	TableView<Funcionario> tbvListaFunc;
	// @FXML TitledPane tbvListaFunc;
	@FXML
	TitledPane tpListFunc;
	@FXML
	TableColumn<Funcionario, Integer> tbcFuncID;
	@FXML
	TableColumn<Funcionario, String> tbcFuncNome;
	@FXML
	TableColumn<Funcionario, String> tbcFuncFun;
	@FXML
	TableColumn<Funcionario, Double> tbcFuncSal;

	@FXML
	TitledPane tpListProd;
	@FXML
	TableView<Produto> tbvListaProd;
	@FXML
	TableColumn<Produto, Integer> tbcProdCod;
	@FXML
	TableColumn<Produto, String> tbcProdNome;
	@FXML
	TableColumn<Produto, Double> tbcProdPrec;
	@FXML
	TableColumn<Produto, Integer> tbcProdQtd;

	// @FXML TableView <ItemVenda> tbvListaVenda;
	@FXML
	TitledPane tbvListaVenda;
//	@FXML
//	TableColumn<ItemVenda, Integer> tbcVenProd;
//	@FXML
//	TableColumn<ItemVenda, String> tbcVenQtd;
//	@FXML
//	TableColumn<ItemVenda, String> tbcVenPre;
//	@FXML
//	TableColumn<ItemVenda, Double> tbcVenTot;

	// BUSCAR
	@FXML
	TextField tfBuscFuncID;
	@FXML
	TextField tfBuscFuncNome;
	@FXML
	TextField tfBuscFuncCPF;
	@FXML
	TextField tfBuscFuncLog;
	@FXML
	TextField tfBuscFuncCid;
	@FXML
	TextField tfBuscFuncCEP;
	@FXML
	TextField tfBuscFuncCasa;
	@FXML
	TextField tfBuscFuncSal;
	@FXML
	TextField tfBuscFuncFun;
	@FXML
	Button btnFuncBuscar;

	@FXML
	TextField tfBuscProdCod;
	@FXML
	TextField tfBuscProdNome;
	@FXML
	TextField tfBuscProdPrec;
	@FXML
	TextField tfBuscProdQtd;
	@FXML
	Button btnProdBuscar;

	// ATUALIZAR
	@FXML
	TextField tfAltFuncID;
	@FXML
	TextField tfAltFuncNome;
	@FXML
	TextField tfAltFuncCPF;
	@FXML
	TextField tfAltFuncLog;
	@FXML
	TextField tfAltFuncCidade;
	@FXML
	TextField tfAltFuncCEP;
	@FXML
	TextField tfAltFuncCasa;
	@FXML
	TextField tfAltFuncSal;
	@FXML
	TextField tfAltFuncFun;
	@FXML
	Button btnFuncBuscarAlt;
	@FXML
	Button btnFuncAtualizar;
	@FXML
	TextField tfBuscFuncNome1;
	@FXML
	TextField tfBuscFuncID1;
	@FXML
	TextField tfBuscFuncCPF1;
	@FXML
	TextField tfBuscFuncLog1;
	@FXML
	TextField tfBuscFuncCid1;
	@FXML
	TextField tfBuscFuncCEP1;
	@FXML
	TextField tfBuscFuncCasa1;
	@FXML
	TextField tfBuscFuncSal1;
	@FXML
	TextField tfBuscFuncFun1;
	@FXML
	TextField tfAltFuncID1;
	
	@FXML
	TextField tfBuscProdCod1;
	@FXML
	TextField tfAltProdCod;
	@FXML
	TextField tfAltProdNome;
	@FXML
	TextField tfAltProdPrec;
	@FXML
	TextField tfAltProdQtd;
	@FXML
	Button btnProdBuscarAlt;
	@FXML
	Button btnProdAtualizar;

	@FXML
	TextField tfBuscProdNome1;
	@FXML
	TextField tfBuscProdPrec1;
	@FXML
	TextField tfBuscProdQtd1;

	Produto p;
	Funcionario f;

	// VENDAS
	
	@FXML TextField tfBuscFunVenda;
	@FXML Button btnConfirmarFunc;
	@FXML TextField tfBuscProdCodV;
	@FXML TextField tfBuscProdQtdV;
	@FXML Button btnProdInserirItem;
	@FXML TableColumn<ItemVenda, String> tbcItemVNome;
	@FXML TableColumn<ItemVenda, Integer> tbcItemVQtd;
	@FXML TableColumn<ItemVenda, Double> tbcItemVPreco;
	@FXML TableView<ItemVenda> tbvListaItemV;
	@FXML TableColumn<ItemVenda, Double> tbcItemVTotal;
	@FXML Button btnCancelarVenda;
	@FXML Button btnFinalizarVenda;
	@FXML TextField tfTotalPagar;
	
	// NOTAS FISCAIS
	@FXML TableView<NotaFiscal> tbvNF;
	@FXML TableColumn<NotaFiscal, Integer> tbcNF;
	@FXML TableColumn<NotaFiscal, Double> tbcTotalNF;
	@FXML TableColumn<NotaFiscal, Funcionario>tbcFuncNF;
	@FXML TableColumn<NotaFiscal, ItemVenda> tbcNFItemNome;
	@FXML TableColumn<NotaFiscal, Integer> tbcNFItemQtd;
	@FXML TableColumn<NotaFiscal, Double> tbcNFItemPreco;
	@FXML TableColumn<NotaFiscal, Double> tbcNFItemTotal;
	@FXML TitledPane tpListaNF;
	@FXML TableView<NotaFiscal> tbvIVNF;
	@FXML
	private void initialize() {
		listarproduto();
		listarFuncionario();
		listarnotasfiscais();
		this.cbCadFuncFun.getItems().addAll("Gerente", "Vendedor");
		
		tpListProd.expandedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				tbvListaProd.refresh();
			}
		});
		 tpListFunc.expandedProperty().addListener(new ChangeListener<Boolean>() {
		 @Override
		 public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			 tbvListaFunc.refresh();
		 }
		 });
		 tpListaNF.expandedProperty().addListener(new ChangeListener<Boolean>() {
			 
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				tbvNF.refresh();
				
			}
		});
		
		 tbvNF.getSelectionModel().selectedItemProperty().addListener(
				 (observable, oldValue, newValue) -> selecionarNotaFiscalTableView(newValue));
		this.btnProdBuscarAlt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int codigo = Integer.parseInt(tfBuscProdCod1.getText().toString());
				Produto p = fachada.buscarProduto(codigo);
				if (p != null) {
					tfBuscProdNome1.setPromptText(p.getNome());
					tfBuscProdPrec1.setPromptText(String.valueOf(p.getPreco()));
					tfBuscProdQtd1.setPromptText(String.valueOf(p.getQuantidade()));
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Aviso importante!");
					alert.setHeaderText(null);
					alert.setContentText("Caso voce nao deseje alterar determinado campo, deixe-o em branco.");
					alert.showAndWait();
				}
				if (p == null) {
					tfBuscProdNome1.setPromptText("");
					tfBuscProdPrec1.setPromptText("");
					tfBuscProdQtd1.setPromptText("");
				}
			}
		});
		this.btnFuncBuscarAlt.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int identificacao = Integer.parseInt(tfBuscFuncID1.getText().toString());
				Funcionario f = fachada.buscarFuncionario(identificacao);
				if (f != null) {
					tfBuscFuncNome1.setPromptText(f.getNome());
					tfBuscFuncCPF1.setPromptText(f.getCpf());
					tfBuscFuncLog1.setPromptText(f.getEndereco().getRua());
					tfBuscFuncCid1.setPromptText(f.getEndereco().getCidade());
					tfBuscFuncCEP1.setPromptText(f.getEndereco().getCep());
					tfBuscFuncCasa1.setPromptText(f.getEndereco().getNumero());
					tfBuscFuncSal1.setPromptText(String.valueOf(f.getSalario()));
					tfBuscFuncFun1.setPromptText(f.getFuncao());
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Aviso importante!");
					alert.setHeaderText(null);
					alert.setContentText("Caso voce nao deseje alterar determinado campo, deixe-o em branco.");
					alert.showAndWait();
				}
				if (f == null) {
					tfBuscFuncNome1.setPromptText("");
					tfBuscFuncCPF1.setPromptText("");
					tfBuscFuncLog1.setPromptText("");
					tfBuscFuncCid1.setPromptText("");
					tfBuscFuncCEP1.setPromptText("");
					tfBuscFuncCasa1.setPromptText("");
					tfBuscFuncSal1.setPromptText("");
					tfBuscFuncFun1.setPromptText("");
				}
			}
		});
		
	}

	// METODOS PARA PRODUTOS
	private ObservableList<Produto> obListProd;

	public void cadastrarproduto(ActionEvent event) {
		try {
			int codigo = Integer.parseInt(tfCadProdCodigo.getText().toString());
			String nome = tfCadProdNome.getText().toString();
			double preco = Double.parseDouble(tfCadProdPreco.getText().toString());
			int qtd = Integer.parseInt(tfCadProdQtd.getText().toString());

			Produto produto = new Produto(codigo, nome, preco, qtd);

			fachada.inserirProduto(produto);
		} catch (NegocioException e) {
			e.printStackTrace();
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
	}

	public void removerproduto(ActionEvent event) {
		try {
			fachada.removerProduto(Integer.parseInt(tfRemoProdID.getText().toString()));
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	public void listarproduto() {
		try {
			tbcProdCod.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("codigo"));
			tbcProdNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
			tbcProdPrec.setCellValueFactory(new PropertyValueFactory<Produto, Double>("preco"));
			tbcProdQtd.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("quantidade"));

			obListProd = FXCollections.observableArrayList(fachada.listarProdutos());
			tbvListaProd.setItems(obListProd);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	public void alterarproduto(ActionEvent event) {

		int codigo = Integer.parseInt(tfBuscProdCod1.getText().toString());
		this.p = fachada.buscarProduto(codigo);
		if (p != null) {
			try {
				if (tfBuscProdNome1.getText().isEmpty() == true) {
					tfBuscProdNome1.setText(tfBuscProdNome1.getPromptText().toString());
					p.setNome(tfBuscProdNome1.getPromptText().toString());
				}
				if (tfBuscProdPrec1.getText().trim().isEmpty() == true) {
					tfBuscProdPrec1.setText(tfBuscProdPrec1.getPromptText().toString());
					p.setPreco(Double.parseDouble(tfBuscProdPrec1.getPromptText().toString()));
				}
				if (tfBuscProdQtd1.getText().trim().isEmpty() == true) {
					tfBuscProdQtd1.setText(tfBuscProdQtd1.getPromptText().toString());
					p.setQuantidade(Integer.parseInt(tfBuscProdQtd1.getPromptText().toString()));
				}
				p.setNome(tfBuscProdNome1.getText().toString());
				p.setPreco(Double.parseDouble(tfBuscProdPrec1.getText().toString()));
				p.setQuantidade(Integer.parseInt(tfBuscProdQtd1.getText().toString()));

				fachada.atualizarProduto(p);
				tfBuscProdNome1.clear();
				tfBuscProdQtd1.clear();
				tfBuscProdPrec1.clear();
			} catch (NegocioException e) {
				e.printStackTrace();
			} catch (NumberFormatException ne) {
				ne.printStackTrace();
			}
		}
	}

	public void buscarproduto(ActionEvent event) {
		int codigo = Integer.parseInt(tfBuscProdCod.getText().toString());
		this.p = fachada.buscarProduto(codigo);
		if (p != null) {
			tfBuscProdNome.setText(p.getNome());
			tfBuscProdPrec.setText(String.valueOf(p.getPreco()));
			tfBuscProdQtd.setText(String.valueOf(p.getQuantidade()));
		}
	}

	// METODOS PARA FUNCIONARIOS

	private ObservableList<Funcionario> obListFunc;

	public void cadastrarFuncionario(ActionEvent event) {
		try {
			Funcionario func;
			int id = Integer.parseInt(tfCadFuncID.getText().toString());
			String nome = tfCadFuncNome.getText().toString();
			String cpf = tfCadFuncCPF.getText().toString();
			String logradouro = tfCadFuncLog.getText().toString();
			String cidade = tfCadFuncCid.getText().toString();
			String cep = tfCadFuncCEP.getText().toString();
			String casa = tfCadFuncCasa.getText().toString();
			double salario = Double.parseDouble(tfCadFuncSal.getText().toString());
			String user = tfCadFuncLogin.getText().toString();
			String senha = tfCadFuncSenha.getText().toString();
			String palavraSeguranca = tfCadFuncPdS.getText().toString();

			if (cbCadFuncFun.getValue().equals("Gerente")) {
				func = new Gerente("Gerente",salario, id, false, new Login(user, senha, palavraSeguranca), nome, cpf,
						new Endereco(logradouro, cidade, cep, casa));
				fachada.inserirFuncionario(func);
			}
			if (cbCadFuncFun.getValue().equals("Vendedor")) {
				func = new Vendedor("Vendedor", salario, id, false, new Login(user, senha, palavraSeguranca), nome, cpf,
						new Endereco(logradouro, cidade, cep, casa));
				fachada.inserirFuncionario(func);
			}
		} catch (NegocioException e) {
			e.printStackTrace();
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
	}

	public void removerFuncionario(ActionEvent event) {
		try {
			fachada.removerFuncionario(Integer.parseInt(tfRemoFuncID.getText().toString()));
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	public void listarFuncionario() {
		tbvListaFunc.refresh();
		try {
			tbcFuncID.setCellValueFactory(new PropertyValueFactory<Funcionario, Integer>("identificacao"));
			tbcFuncNome.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
			tbcFuncFun.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("funcao"));
			tbcFuncSal.setCellValueFactory(new PropertyValueFactory<Funcionario, Double>("salario"));

			obListFunc = FXCollections.observableArrayList(fachada.listarFuncionarios());
			tbvListaFunc.setItems(obListFunc);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	public void alterarFuncionario(ActionEvent event) {

	//	int id = Integer.parseInt(tfAltFuncID1.getText().toString());
		this.f = fachada.buscarFuncionario(Integer.parseInt(tfBuscFuncID1.getText().toString()));
		if (f != null) {
			try {
				if(tfBuscFuncNome1.getText().isEmpty()){
					String nome = tfBuscFuncNome1.getPromptText().toString();
					tfBuscFuncNome1.setText(nome);
					f.setNome(nome);
				}
				if(tfBuscFuncCPF1.getText().isEmpty()){
					String cpf = tfBuscFuncCPF1.getPromptText().toString();
					tfBuscFuncCPF1.setText(cpf);
					f.setCpf(cpf);
				}
				if(tfBuscFuncLog1.getText().isEmpty()){
					String rua = tfBuscFuncLog1.getPromptText().toString();
					tfBuscFuncLog1.setText(rua);
					f.getEndereco().setRua(rua);;
				}
				if(tfBuscFuncCid1.getText().isEmpty()){
					String cidade = tfBuscFuncCid1.getPromptText().toString();
					tfBuscFuncCid1.setText(cidade);
					f.getEndereco().setCidade(cidade);
				}
				if(tfBuscFuncCEP1.getText().isEmpty()){
					String cep = tfBuscFuncCEP1.getPromptText().toString();
					tfBuscFuncCEP1.setText(cep);
					f.getEndereco().setCep(cep);
				}
				if(tfBuscFuncCasa1.getText().isEmpty()){
					String casa = tfBuscFuncCasa1.getPromptText().toString();
					tfBuscFuncCasa1.setText(casa);
					f.getEndereco().setNumero(casa);
				}
				if(tfBuscFuncSal1.getText().isEmpty()){
					double salario = Double.parseDouble(tfBuscFuncSal1.getPromptText().toString());
					tfBuscFuncSal1.setText(String.valueOf(salario));
					f.setSalario(salario);
				}
				if(tfBuscFuncFun1.getText().isEmpty()){
					String funcao = tfBuscFuncFun1.getPromptText().toString();
					tfBuscFuncFun1.setText(funcao);
					f.setFuncao(funcao);
				}
				f.setNome(tfBuscFuncNome1.getText().toString());
				f.setCpf(tfBuscFuncCPF1.getText().toString());
				f.getEndereco().setRua(tfBuscFuncLog1.getText().toString());
				f.getEndereco().setCidade(tfBuscFuncCid1.getText().toString());
				f.getEndereco().setCep(tfBuscFuncCEP1.getText().toString());
				f.getEndereco().setNumero(tfBuscFuncCasa1.getText().toString());
				f.setSalario(Double.parseDouble(tfBuscFuncSal1.getText().toString()));
				f.setFuncao(tfBuscFuncFun1.getText().toString());
				
				fachada.atualizarFuncionario(f);
				tfBuscFuncNome1.clear();
				tfBuscFuncCPF1.clear();
				tfBuscFuncLog1.clear();
				tfBuscFuncCid1.clear();
				tfBuscFuncCEP1.clear();
				tfBuscFuncCasa1.clear();
				tfBuscFuncSal1.clear();
				tfBuscFuncFun1.clear();
			} catch (NegocioException e) {
				e.printStackTrace();
			} catch (NumberFormatException ne) {
				ne.printStackTrace();
			}
		}
	}

	public void buscarFuncionario(ActionEvent event) {
		int id = Integer.parseInt(tfBuscFuncID.getText().toString());
		this.f = fachada.buscarFuncionario(id);
		if (f != null) {
			tfBuscFuncNome.setText(f.getNome());
			tfBuscFuncCPF.setText(f.getCpf());
			tfBuscFuncLog.setText(f.getEndereco().getRua());
			tfBuscFuncCid.setText(f.getEndereco().getCidade());
			tfBuscFuncCEP.setText(f.getEndereco().getCep());
			tfBuscFuncCasa.setText(f.getEndereco().getNumero());
			tfBuscFuncSal.setText(String.valueOf(f.getSalario()));
			tfBuscFuncFun.setText(f.getFuncao());
		}
	}
	
	
	//METODOS PARA VENDAS
	ObservableList<ItemVenda> obListVenda;
	double totalapagar = 0;
	public void confirmacaofuncionario(ActionEvent event){
		try{
			int identificacao;
			if(tfBuscFunVenda.getText().toString().isEmpty() == false){
				identificacao = Integer.parseInt(tfBuscFunVenda.getText().toString());
				this.f = fachada.buscarFuncionario(identificacao);
	
					if(f !=  null){
						tfBuscFunVenda.setEditable(false);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Funcionario encontrado!");
						alert.setHeaderText(null);
						alert.setContentText("Funcionário encontrado");
						alert.showAndWait();
					}
					else{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Funcionario não encontrado!");
				alert.setHeaderText(null);
				alert.setContentText("Funcionário não encontrado, verifique se você digitou a identificação correta");
				alert.showAndWait();
			}
			}
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
	}
	
	public void finalizarvenda(ActionEvent event){
		try {
			tfTotalPagar.setText(String.valueOf(totalapagar));
			fachada.encerrarPedido();
			fachada.gerarNotaFiscal(f);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	public void inseriritem(ActionEvent event){
		
		int codigo = Integer.parseInt(tfBuscProdCodV.getText().toString());
		Produto p = fachada.buscarProduto(codigo);
		if(p != null){
			int quantidade = Integer.parseInt(tfBuscProdQtdV.getText().toString());
				ItemVenda item = new ItemVenda(p, quantidade);
				try {
					fachada.inserirItem(item);
					listaritensvenda();
					tbvListaItemV.refresh();
					totalapagar += item.getValort();
				} catch (NegocioException e) {
					e.printStackTrace();
				}
			}
		}
	
	public void listaritensvenda(){
		try {
			tbcItemVNome.setCellValueFactory(new PropertyValueFactory<ItemVenda, String>("nome"));
			tbcItemVPreco.setCellValueFactory(new PropertyValueFactory<ItemVenda, Double>("preco"));
			tbcItemVQtd.setCellValueFactory(new PropertyValueFactory<ItemVenda, Integer>("qtd"));
			tbcItemVTotal.setCellValueFactory(new PropertyValueFactory<ItemVenda, Double>("valort"));
			
			obListVenda = FXCollections.observableArrayList(fachada.listarItensVenda());
			tbvListaItemV.setItems(obListVenda);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	public void cancelarvenda(){
		fachada.cancelarPedido();
		tbvListaItemV.refresh();
	}
	
	// NOTA FISCAL
	ObservableList<NotaFiscal> obListNF;
	
	public void listarnotasfiscais(){
			tbcNF.setCellValueFactory(new PropertyValueFactory<NotaFiscal, Integer>("codigoDaNota"));
			tbcTotalNF.setCellValueFactory(new PropertyValueFactory<NotaFiscal, Double>("totalPagar"));
			tbcFuncNF.setCellValueFactory(new PropertyValueFactory<NotaFiscal, Funcionario>("funcionario"));
			obListNF = FXCollections.observableArrayList(fachada.listarVendas());
			tbvNF.setItems(obListNF);
	}
	public void selecionarNotaFiscalTableView(NotaFiscal nf){
		
		
		//tbcNFItemNome.setCellValueFactory(new PropertyValueFactory<nf, ItemVenda>("itensVendidos"));
	//	tbcNFItemQtd;
	//	tbcNFItemPreco;
	//	tbcNFItemTotal;
	}
	// DESLOGAR
	public void sair(ActionEvent event) {
		main = Principal.getInstance();
		Stage stage;
		Parent root;
		try {
			root = (Parent) FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/ShowLogin.fxml"));
			Scene scene = new Scene(root);
			stage = main.getPrimaryStage();
			stage.setScene(scene);
			stage.setTitle("Sistema de Mercado");
			main.changeStage(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setMain(Principal principal) {
		this.main = principal;
	}

}
