package ufrpe.gui.model;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ufrpe.gui.Principal;

public class ControladorOverview {
	
	private Principal main;
	
	
	@FXML private MenuItem mi_adicionarprod;
	@FXML private MenuItem mi_alterarprod;
	@FXML private MenuItem mi_removerprod;
	@FXML private MenuItem mi_buscarprod;
	@FXML private MenuItem mi_pdv;
	
	@FXML
	private void AdicionarProduto(){
		main = Principal.getInstance();
		Stage stage;
		Parent root;
		Parent old;
		try{
			old = FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/OverviewTemplate.fxml"));
			root = (Parent) FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/CadastrarProduto.fxml")); // NOVO FXML
			((BorderPane) old).setCenter(root);
			Scene scene = new Scene(old);
			stage = main.getPrimaryStage();
			stage.setScene(scene);
			main.changeStage(stage);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setMain(Principal principal) {
		this.main = principal;
	}
}
