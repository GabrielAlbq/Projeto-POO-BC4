package ufrpe.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ufrpe.gui.model.ControladorGerente;
import ufrpe.negocio.ControladorFuncionario;
import ufrpe.negocio.beans.Admin;
import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.Gerente;
import ufrpe.negocio.beans.Login;
import ufrpe.negocio.beans.Vendedor;

public class Principal extends Application {

	// ATRIBUTOS
	private static Principal instance;
	private Stage primaryStage;
	private Funcionario logado;
	private Pane rootScene;
	private ControladorFuncionario control;
	
	private Funcionario funcionariologado;
	
	public Funcionario getFuncionariologado() {
		return funcionariologado;
	}

	public void setFuncionariologado(Funcionario funcionariologado) {
		
		this.funcionariologado = funcionariologado;
	}
	

	// SINGLETON
	public static Principal getInstance() {
		if (instance == null) {
			instance = new Principal();
		}
		return instance;
	}

	@Override
	public void start(Stage primaryStage) {
		instance = this;
		this.funcionariologado = new Admin();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Sistema de Mercado");
		showLogin();

	}


	public void showGerente() {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Principal.class.getResource("views/Gerente.fxml"));
     			AnchorPane funcionario = (AnchorPane) loader.load();

		//		rootScene.setCenter(funcionario);
				ControladorGerente controller = loader.getController();
		//		controller.setMainApp(this);

				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
//	public void showGerente(){
//		
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/Gerente.fxml"));
//			Scene scene = new Scene(root);
//			primaryStage.setScene(scene);
//			primaryStage.show();
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		
//	}

	public void showLogin() {

		try {
					Parent root = FXMLLoader.load(getClass().getResource("views/ShowLogin.fxml"));
					Scene scene = new Scene(root);
			//		scene.getStylesheets().add(getClass().getResource("NOME DO CSS.css").toExternalForm()); PARA O CSS CASO FOR ADICIONAR
					primaryStage.setScene(scene);
					primaryStage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
	}
	
	
	public void changeStage(Stage stage){
		  this.primaryStage = stage;
	}
	public Stage getPrimaryStage(){
		return this.primaryStage;
	}
	
	public Pane getRootScene(){
		return this.rootScene;
	}
	
	public static void main(String[] args) {

//		Fachada fachada = Fachada.getInstancia();
//		Login log1 = new Login("admin", "padrao", "mercado");
//		Funcionario f1 = new Admin(log1, 100);
//		try {
//			fachada.inserirFuncionario(f1);
//		} catch (NegocioException e) {
//			e.printStackTrace();
//		}
//		
		launch(args);
		Login log4 = new Login("fabio", "123", "pokemon");
		Login log2 = new Login("elthon", "123", "trator");
		Login log3 = new Login("admin", "admin", "chefe");

	}
}