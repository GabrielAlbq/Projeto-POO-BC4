package ufrpe.gui.model;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import ufrpe.gui.Principal;
import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.ItemVenda;
import ufrpe.negocio.beans.Produto;

public class ControladorGerente {
	
	
 
	private Principal main;

	// PRINCIPAL
	@FXML Button btnSair;
	
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
	
	//@FXML TableView <Produto> tbvListaProd;
	@FXML TitledPane tbvListaProd;
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

	@FXML TextField tfAltProdCod;
	@FXML TextField tfAltProdNome;
	@FXML TextField tfAltProdPrec;
	@FXML TextField tfAltProdQtd;
	@FXML Button btnProdBuscarAlt;
	@FXML Button btnProdAtualizar;

	// VENDAS 
	// TODO
	public void sair(ActionEvent event){
		main = Principal.getInstance();
		Stage stage;
		Parent root;
		Parent old;
		try{
			//old = FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/Gerente.fxml"));
			root = (Parent) FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/ShowLogin.fxml")); // NOVO FXML
			Scene scene = new Scene(root);
			stage = main.getPrimaryStage();
			stage.setScene(scene);
			main.changeStage(stage);
			main.setAtual(null);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setMain(Principal principal) {
		this.main = principal;
	}
	


}
