package ufrpe.gui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ufrpe.negocio.Fachada;
import ufrpe.negocio.beans.Admin;
import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.Gerente;
import ufrpe.negocio.beans.ItemVenda;
import ufrpe.negocio.beans.Login;
import ufrpe.negocio.beans.Produto;
import ufrpe.negocio.beans.Vendedor;
import ufrpe.negocio.exception.NegocioException;

public class Principal extends Application{
	
	private Stage primaryStage;
	private Funcionario logado;
	private BorderPane rootLayout;
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Mercad�o mil grau");
		
		initRootLayout();
		if(logado == null){
			showLogin();
		}
	}
	 public void showLogin() {
	        try {

	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Principal.class.getResource("view/Login.fxml"));
	            AnchorPane Login = (AnchorPane) loader.load();

	            rootLayout.setCenter(Login);
	          //  LoginController controller = loader.getController();
	 //           controller.setMainApp(this);
	            
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	 public void initRootLayout() {
	        try {

	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Principal.class.getResource("view/RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 
	public static void main(String[] args) {
		
		launch(args);

		// SCANNER
		Scanner scanf = new Scanner(System.in);

		// FACHADA
		Fachada fachada = Fachada.getInstancia();
		
		// TODO fazer a parte de arquivo funcionar para inserirFuncionario
		Login log1 = new Login("fabio", "123", "pokemon");
		Login log2 = new Login("elthon", "123", "trator");
		Login log3 = new Login("admin", "admin", "chefe");

	}
}