package controllers;

import java.io.IOException;
import java.sql.SQLException;

import dao.JogadorDAO;
import dao.JogadorDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.Jogador;

public class CadastrarJogadorController {

	@FXML
	MenuBar myMenuBar;
	
	@FXML
	TextField tfEmailJogador;
	
	@FXML
	TextField tfNomeJogador;
	
	@FXML
	PasswordField tfSenhaJogador;
	
	@FXML
	public void cadastrar() {
		JogadorDAO conexaoJogador = new JogadorDAOImpl();
		Jogador jogador = new Jogador();
		jogador.setEmail(tfEmailJogador.getText());
		jogador.setNome(tfNomeJogador.getText());
		jogador.setSenha(tfSenhaJogador.getText());
		
		try {
			conexaoJogador.cadastraJogador(jogador);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucesso");
			alert.setHeaderText("Cadastro realizado");
			alert.setContentText("O cadastro foi realizado com sucesso");

			alert.showAndWait();
			
			jogador = new Jogador();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Cadastro não realizado");
			alert.setContentText("Ocorreu um erro ao realizar o cadastro.\n"+e.getStackTrace());

			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	@FXML
	public void trocaTela(ActionEvent event) {

		Stage stage = (Stage) myMenuBar.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/principal.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException ex) {

		}

	}
}
