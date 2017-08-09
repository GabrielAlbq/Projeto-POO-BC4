package ufrpe.gui.model;

import java.security.Principal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControladorLogin {
	
	@FXML
	Button bt_logar;
	@FXML
	Label lb_password;
	@FXML
	Label lb_user;
	@FXML
	Label lb_welcome;
	@FXML
	TextField tf_user;
	@FXML
	PasswordField pf_password;
	
	public void usuarioLogado (){
		
		tf_user.getText();
		
		
		
	}

//	public void setMainApp(ufrpe.Principal principal) {
//		// TODO Auto-generated method stub
//		
//	}
//	

}
