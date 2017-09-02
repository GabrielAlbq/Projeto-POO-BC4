package ufrpe.gui.model;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ufrpe.gui.Principal;
import ufrpe.negocio.Fachada;
import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.ItemVenda;
import ufrpe.negocio.beans.NotaFiscal;
import ufrpe.negocio.beans.Produto;
import ufrpe.negocio.exception.NegocioException;

public class ControladorVendedor {

	Fachada fachada = Fachada.getInstancia();
	private Principal main;

	// PRINCIPAL
	@FXML
	Button btnSair;
	@FXML
	Label lbAdmNome;

	// LISTAR
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
	// @FXML
	// TableColumn<ItemVenda, Integer> tbcVenProd;
	// @FXML
	// TableColumn<ItemVenda, String> tbcVenQtd;
	// @FXML
	// TableColumn<ItemVenda, String> tbcVenPre;
	// @FXML
	// TableColumn<ItemVenda, Double> tbcVenTot;

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

	Button btnFuncBuscarAlt;
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

	@FXML
	TextField tfBuscFunVenda;
	@FXML
	Button btnConfirmarFunc;
	@FXML
	TextField tfBuscProdCodV;
	@FXML
	TextField tfBuscProdQtdV;
	@FXML
	Button btnProdInserirItem;
	@FXML
	Button btnProdRemoverItem;
	@FXML
	TableColumn<ItemVenda, String> tbcItemVNome;
	@FXML
	TableColumn<ItemVenda, Integer> tbcItemVQtd;
	@FXML
	TableColumn<ItemVenda, Double> tbcItemVPreco;
	@FXML
	TableView<ItemVenda> tbvListaItemV;
	@FXML
	TableColumn<ItemVenda, Double> tbcItemVTotal;
	@FXML
	Button btnCancelarVenda;
	@FXML
	Button btnFinalizarVenda;
	@FXML
	TextField tfTotalPagar;

	// NOTAS FISCAIS
	@FXML
	TableView<NotaFiscal> tbvNF;
	@FXML
	TableColumn<NotaFiscal, Integer> tbcNF;
	@FXML
	TableColumn<NotaFiscal, Double> tbcTotalNF;
	@FXML
	TableColumn<NotaFiscal, String> tbcFuncNF;
	@FXML
	TableColumn<ItemVenda, String> tbcNFItemNome; 
	@FXML
	TableColumn<ItemVenda, Integer> tbcNFItemQtd;
	@FXML
	TableColumn<ItemVenda, Double> tbcNFItemPreco;
	@FXML
	TableColumn<ItemVenda, Double> tbcNFItemTotal;
	@FXML
	TitledPane tpListaNF;
	@FXML
	TableView<ItemVenda> tbvIVNF;

	@FXML
	private void initialize() {
		listarproduto();
		listarnotasfiscais();

		tpListProd.expandedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				tbvListaProd.refresh();
			}
		});
		tpListaNF.expandedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				tbvNF.refresh();

			}
		});

		tbvNF.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarNotaFiscalTableView(newValue));

	}

	// METODOS PARA PRODUTOS
	private ObservableList<Produto> obListProd;

	public void listarproduto() {
		try {
			tbcProdCod.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("codigo"));
			tbcProdNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
			tbcProdPrec.setCellValueFactory(new PropertyValueFactory<Produto, Double>("preco"));
			tbcProdQtd.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("quantidade"));

			obListProd = FXCollections.observableArrayList(fachada.listarProdutos());
			tbvListaProd.setItems(obListProd);
		} catch (NegocioException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro ao listar!");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}

	public void buscarproduto(ActionEvent event) {
		int codigo = Integer.parseInt(tfBuscProdCod.getText().toString());
		this.p = fachada.buscarProduto(codigo);
		if (p != null) {
			tfBuscProdNome.setText(p.getNome());
			tfBuscProdPrec.setText(String.valueOf(p.getPreco()));
			tfBuscProdQtd.setText(String.valueOf(p.getQuantidade()));
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Produto nao encontrado");
			alert.setHeaderText(null);
			alert.setContentText("Produto nao existe em estoque, verifique se o codigo foi digitado corretamente!");
			alert.showAndWait();
		}
	}

	// METODOS PARA VENDAS
	ObservableList<ItemVenda> obListVenda;
	double totalapagar = 0;

	public void confirmacaofuncionario(ActionEvent event) {
		try {
			int identificacao;
			if (tfBuscFunVenda.getText().toString().isEmpty() == false) {
				identificacao = Integer.parseInt(tfBuscFunVenda.getText().toString());
				this.f = fachada.buscarFuncionario(identificacao);

				if (f != null) {
					tfBuscFunVenda.setEditable(false);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Funcionario encontrado!");
					alert.setHeaderText(null);
					alert.setContentText("Funcionário encontrado");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Funcionario não encontrado!");
					alert.setHeaderText(null);
					alert.setContentText(
							"Funcionário não encontrado, verifique se você digitou a identificação correta");
					alert.showAndWait();
				}
			}
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
	}

	public void finalizarvenda(ActionEvent event) {
		try {
			tfTotalPagar.setText(String.valueOf(totalapagar));
			fachada.encerrarPedido();
			fachada.gerarNotaFiscal(f);
			totalapagar = 0;
			tfBuscFunVenda.setEditable(true);
			tfBuscProdCodV.clear();
			tfBuscProdQtdV.clear();
		//	tfTotalPagar.clear();
			tbvListaItemV.refresh();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Nota fiscal gerada");
			alert.setHeaderText(null);
			alert.setContentText("Nota fiscal gerada com sucesso!");
			alert.showAndWait();
		} catch (NegocioException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}

	public void inseriritem(ActionEvent event) {

		int codigo = Integer.parseInt(tfBuscProdCodV.getText().toString());
		Produto p = fachada.buscarProduto(codigo);
		if (p != null) {
			int quantidade = Integer.parseInt(tfBuscProdQtdV.getText().toString());
			ItemVenda item = new ItemVenda(p, quantidade);
			try {
				fachada.inserirItem(item);
				listaritensvenda();
				tbvListaItemV.refresh();
				totalapagar += item.getValort();
				tfTotalPagar.setText(String.valueOf(totalapagar));
			} catch (NegocioException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro!");
				alert.setHeaderText(null);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
			}
		}
	}

	public void removerItem(ActionEvent event) {

		int codigo = Integer.parseInt(tfBuscProdCodV.getText().toString());
		Produto p = fachada.buscarProduto(codigo);
		if (p != null) {
			int quantidade = Integer.parseInt(tfBuscProdQtdV.getText().toString());
			ItemVenda item = new ItemVenda(p, quantidade);
			try {
				fachada.remover(item.getCodigo());
				listaritensvenda();
				tbvListaItemV.refresh();
				totalapagar -= item.getValort();
			} catch (NegocioException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro!");
				alert.setHeaderText(null);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
			}
		}
	}

	public void listaritensvenda() {
		try {
			tbcItemVNome.setCellValueFactory(new PropertyValueFactory<ItemVenda, String>("nome"));
			tbcItemVPreco.setCellValueFactory(new PropertyValueFactory<ItemVenda, Double>("preco"));
			tbcItemVQtd.setCellValueFactory(new PropertyValueFactory<ItemVenda, Integer>("qtd"));
			tbcItemVTotal.setCellValueFactory(new PropertyValueFactory<ItemVenda, Double>("valort"));

			obListVenda = FXCollections.observableArrayList(fachada.listarItensVenda());
			tbvListaItemV.setItems(obListVenda);
		} catch (NegocioException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}

	public void cancelarvenda() {
		fachada.cancelarPedido();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Venda cancelada");
		alert.setHeaderText(null);
		alert.setContentText("Venda cancelada com sucesso!");
		alert.showAndWait();
		tbvListaItemV.refresh();
	}

	// NOTA FISCAL
	ObservableList<NotaFiscal> obListNF;
	ObservableList<ItemVenda> obIVNF;

	public void listarnotasfiscais() {
		tbcNF.setCellValueFactory(new PropertyValueFactory<NotaFiscal, Integer>("codigoDaNota"));
		tbcTotalNF.setCellValueFactory(new PropertyValueFactory<NotaFiscal, Double>("totalPagar"));
		tbcFuncNF.setCellValueFactory(new PropertyValueFactory<NotaFiscal, String>("funcionario"));
		obListNF = FXCollections.observableArrayList(fachada.listarVendas());
		tbvNF.setItems(obListNF);
	}

	public void selecionarNotaFiscalTableView(NotaFiscal nf) {

			tbcNFItemNome.setCellValueFactory(new PropertyValueFactory<ItemVenda, String>("nome"));
			tbcNFItemQtd.setCellValueFactory(new PropertyValueFactory<ItemVenda, Integer>("qtd"));
			tbcNFItemPreco.setCellValueFactory(new PropertyValueFactory<ItemVenda, Double>("preco"));
			tbcNFItemTotal.setCellValueFactory(new PropertyValueFactory<ItemVenda, Double>("valort"));
			obIVNF = FXCollections.observableArrayList(nf.getItensVendidos());
			tbvIVNF.setItems(obIVNF);
	
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
