package ufrpe.gui.model;

import java.io.IOException;

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
import ufrpe.negocio.beans.Produto;
import ufrpe.negocio.exception.NegocioException;

public class ControladorGerente {
	
	
	Fachada fachada = Fachada.getInstancia();
	private Principal main;

	// PRINCIPAL
	@FXML Button btnSair;
	@FXML Label lbAdmNome;
	// CADASTRAR 
	@FXML TextField tfCadFuncID;
	@FXML TextField tfCadFuncNome;
	@FXML TextField tfCadFuncCPF;
	@FXML TextField tfCadFuncLog;
	@FXML TextField tfCadFuncCid;
	@FXML TextField tfCadFuncCEP;
	@FXML TextField tfCadFuncCasa;
	@FXML TextField tfCadFuncSal;
	@FXML TextField tfCadFuncFun;
	@FXML Button btnFuncCadastrar;

	@FXML TextField tfCadProdCodigo;
	@FXML TextField tfCadProdNome;
	@FXML TextField tfCadProdPreco;
	@FXML TextField tfCadProdQtd;
	@FXML Button btnProdCadastrar;

	// REMOVER
	@FXML TextField tfRemoFuncID;
	@FXML Button btnFuncRemover;

	@FXML TextField tfRemoProdID;
	@FXML Button btnProdRemover;

	// LISTAR
	@FXML TableView <Funcionario> tbvListaFunc;
	//@FXML TitledPane tbvListaFunc;
	@FXML TableColumn <Funcionario, Integer> tbcFuncID;
	@FXML TableColumn <Funcionario, String> tbcFuncNome;
	@FXML TableColumn <Funcionario, String> tbcFuncFun;
	@FXML TableColumn <Funcionario, Double> tbcFuncSal;
	
	@FXML TitledPane tpListProd;
	@FXML TableView <Produto> tbvListaProd;
	@FXML TableColumn <Produto, Integer>tbcProdCod;
	@FXML TableColumn <Produto, String>tbcProdNome;
	@FXML TableColumn <Produto, Double>tbcProdPrec;
	@FXML TableColumn <Produto, Integer>tbcProdQtd;
	
	//@FXML TableView <ItemVenda> tbvListaVenda;
	@FXML TitledPane tbvListaVenda;
	@FXML TableColumn <ItemVenda, Integer> tbcVenProd;
	@FXML TableColumn <ItemVenda, String> tbcVenQtd;
	@FXML TableColumn <ItemVenda, String> tbcVenPre;
	@FXML TableColumn <ItemVenda, Double> tbcVenTot;

	// BUSCAR
	@FXML TextField tfBuscFuncID;
	@FXML TextField tfBuscFuncNome;
	@FXML TextField tfBuscFuncCPF;
	@FXML TextField tfBuscFuncLog;
	@FXML TextField tfBuscFuncCid;
	@FXML TextField tfBuscFuncCEP;
	@FXML TextField tfBuscFuncCasa;
	@FXML TextField tfBuscFuncSal;
	@FXML TextField tfBuscFuncFun;
	@FXML Button btnFuncBuscar;

	@FXML TextField tfBuscProdCod;
	@FXML TextField tfBuscProdNome;
	@FXML TextField tfBuscProdPrec;
	@FXML TextField tfBuscProdQtd;
	@FXML Button btnProdBuscar;

	// ATUALIZAR
	@FXML TextField tfAltFuncID;
	@FXML TextField tfAltFuncNome;
	@FXML TextField tfAltFuncCPF;
	@FXML TextField tfAltFuncLog;
	@FXML TextField tfAltFuncCidade;
	@FXML TextField tfAltFuncCEP;
	@FXML TextField tfAltFuncCasa;
	@FXML TextField tfAltFuncSal;
	@FXML TextField tfAltFuncFun;
	@FXML Button btnFuncBuscarAlt;
	@FXML Button btnFuncAtualizar;
	
	@FXML TextField	tfBuscProdCod1;
	@FXML TextField tfAltProdCod;
	@FXML TextField tfAltProdNome;
	@FXML TextField tfAltProdPrec;
	@FXML TextField tfAltProdQtd;
	@FXML Button btnProdBuscarAlt;
	@FXML Button btnProdAtualizar;
	
	@FXML TextField tfBuscProdNome1;
	@FXML TextField tfBuscProdPrec1;
	@FXML TextField tfBuscProdQtd1;
	
	Produto p;
	
	// VENDAS 
	// TODO
	@FXML
	private void initialize() {
		tpListProd.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	listarproduto();
            }
        });
	
		this.btnProdBuscarAlt.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent event) {
				int codigo = Integer.parseInt(tfBuscProdCod1.getText().toString());
				Produto p = fachada.buscarProduto(codigo);
				if(p != null){
				tfBuscProdNome1.setPromptText(p.getNome());
				tfBuscProdPrec1.setPromptText(String.valueOf(p.getPreco()));
				tfBuscProdQtd1.setPromptText(String.valueOf(p.getQuantidade()));
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Aviso importante!");
				alert.setHeaderText(null);
				alert.setContentText("Caso você não deseje alterar determinado campo, deixe-o em branco.");
				alert.showAndWait();
			}
				if(p == null){
					tfBuscProdNome1.setPromptText("");
					tfBuscProdPrec1.setPromptText("");
					tfBuscProdQtd1.setPromptText("");
				}
				}});
		
	}
	
	//METODOS PARA PRODUTOS
	private ObservableList<Produto> obListProd;
	
	public void cadastrarproduto(ActionEvent event){
		try{
		String nome = tfCadProdNome.getText().toString();
		int codigo = Integer.parseInt(tfCadProdCodigo.getText().toString());
		int qtd = Integer.parseInt(tfCadProdQtd.getText().toString());
		double preco = Double.parseDouble(tfCadProdPreco.getText().toString());
		
		Produto produto = new Produto(nome,codigo,qtd,preco);
	
			fachada.inserirProduto(produto);
		} catch (NegocioException e) {
			e.printStackTrace();
		} catch (NumberFormatException ne){
			ne.printStackTrace();
		}
	}
	public void removerproduto(ActionEvent event){
		try{
			fachada.removerProduto(Integer.parseInt(tfRemoProdID.getText().toString()));
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	public void listarproduto(){
		try{
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
	public void alterarproduto(ActionEvent event){
		
		int codigo = Integer.parseInt(tfBuscProdCod1.getText().toString());
		this.p = fachada.buscarProduto(codigo);
		if(p != null){
				try {
						if(tfBuscProdNome1.getText().isEmpty() == true ){
							tfBuscProdNome1.setText(tfBuscProdNome1.getPromptText().toString());
							p.setNome(tfBuscProdNome1.getPromptText().toString());
						}
						if(tfBuscProdPrec1.getText().trim().isEmpty() == true ){
							tfBuscProdPrec1.setText(tfBuscProdPrec1.getPromptText().toString());
							p.setPreco(Double.parseDouble(tfBuscProdPrec1.getPromptText().toString()));
						}
						if(tfBuscProdQtd1.getText().trim().isEmpty() == true){
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
					}
					 catch (NegocioException e) {
						e.printStackTrace();
					} catch (NumberFormatException ne){
						ne.printStackTrace();
					}
		}
		}
	public void buscarproduto(ActionEvent event) {
		int codigo = Integer.parseInt(tfBuscProdCod.getText().toString());
		this.p = fachada.buscarProduto(codigo);
		if(p != null){
			tfBuscProdNome.setText(p.getNome());
			tfBuscProdPrec.setText(String.valueOf(p.getPreco()));
			tfBuscProdQtd.setText(String.valueOf(p.getQuantidade()));
		}
	}
	
	//DESLOGAR
	public void sair(ActionEvent event){
		main = Principal.getInstance();
		Stage stage;
		Parent root;
		try{
			root = (Parent) FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/ShowLogin.fxml"));
			Scene scene = new Scene(root);
			stage = main.getPrimaryStage();
			stage.setScene(scene);
			stage.setTitle("Sistema de Mercado");
			main.changeStage(stage);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setMain(Principal principal) {
		this.main = principal;
	}
	


}
