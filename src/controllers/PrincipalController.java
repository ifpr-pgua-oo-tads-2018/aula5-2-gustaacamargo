package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.print.attribute.standard.JobOriginatingUserName;

import dao.JogadaDAO;
import dao.JogadaDAOImpl;
import dao.JogadorDAO;
import dao.JogadorDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Jogada;
import models.Jogador;

public class PrincipalController {

	@FXML
	MenuBar myMenuBar;
	
	@FXML
	TextField tfEmailJ1;
	
	@FXML
	TextField tfEmailJ2;
	
	@FXML
	TextField tfPJ1;

	@FXML
	TextField tfPJ2;
	
	@FXML
	Text txPJ2;
	
	@FXML
	Text txPJ1;
	
	@FXML
	Text txSoma;
	
	@FXML
	PasswordField tfSenhaJ1;
	
	@FXML
	PasswordField tfSenhaJ2;
	
	@FXML
	public void jogada() {
		boolean controle = false;
		boolean j1 = false;
		boolean j2 = false;

		JogadorDAO conexaoJogador = new JogadorDAOImpl();
		Jogador jogador1 = new Jogador();
		Jogador jogador2 = new Jogador();

		jogador1.setEmail(tfEmailJ1.getText());
		jogador1.setSenha(tfSenhaJ1.getText());
		
		jogador2.setEmail(tfEmailJ2.getText());
		jogador2.setSenha(tfSenhaJ2.getText());
		
		List<Jogador> lista1 = new ArrayList<Jogador>();
		List<Jogador> lista2 = new ArrayList<Jogador>();
		try {
			lista1 = conexaoJogador.buscaJogador(jogador1);
			lista2 = conexaoJogador.buscaJogador(jogador2);
		} catch (SQLException e) {
			controle = true;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Usuário não encontrado");
			alert.setContentText(null);

			alert.showAndWait();
		}
		
		if(controle == false) {
			try {
				jogador1 = lista1.get(0);
				jogador2 = lista2.get(0);
				
				JogadaDAO conexaoJogada = new JogadaDAOImpl();
				Random gerador = new Random();
				int soma = 1;
				
				for(int i = 0; i < 2; i++) {
					int dado = gerador.nextInt(6) + 1;
					soma = soma + dado;
				}
				
				if(soma == Integer.parseInt(tfPJ1.getText())) {
					j1 = true;
					txPJ1.setText(tfPJ1.getText());
				}
				
				if(soma == Integer.parseInt(tfPJ2.getText())) {
					j2 = true;
					txPJ2.setText(tfPJ2.getText());
				}
				
				if(j1 == true && j2 != true) {
					txPJ1.setText("3");
					txPJ2.setText("0");
					
				}
				else if(j1 != true && j2 == true) {
					txPJ1.setText("0");
					txPJ2.setText("3");
					
				}
				else if(j1 == true && j2 == true) {
					txPJ1.setText("2");
					txPJ2.setText("2");
					
				}
				else if(j1 != true && j2 != true) {
					txPJ1.setText("0");
					txPJ2.setText("0");
					jogador1.setPontos(jogador1.getPontos());
					jogador2.setPontos(jogador2.getPontos());
				}
				
				txSoma.setText(String.valueOf(soma));
				

				List<Jogada> listaJogadasJ1 = new ArrayList<>();
				List<Jogada> listaJogadasJ2 = new ArrayList<>();

				try {
					listaJogadasJ1 = conexaoJogada.buscaJogada(jogador1.getEmail());
					listaJogadasJ2 = conexaoJogada.buscaJogada(jogador2.getEmail());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				jogador1.setNum_jogadas(listaJogadasJ1.size()+1);
				jogador2.setNum_jogadas(listaJogadasJ2.size()+1);
				Jogada jogada = new Jogada();
				jogada.setEmailJ1(tfEmailJ1.getText());
				jogada.setEmailJ2(tfEmailJ2.getText());
				jogada.setPalpiteJ1(Integer.parseInt(tfPJ1.getText()));
				jogada.setPalpiteJ2(Integer.parseInt(tfPJ2.getText()));
				jogada.setSoma(soma);
				
				try {
					conexaoJogador.atualizaJogador(jogador1);
					conexaoJogador.atualizaJogador(jogador2);
					conexaoJogada.cadastraJogada(jogada);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			catch (IndexOutOfBoundsException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Usuário não encontrado");
				alert.setContentText(null);

				alert.showAndWait();
			}
		}
	}
	
	@FXML
	public void trocaTela(ActionEvent event) {

		Stage stage = (Stage) myMenuBar.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/cadastrarJogador.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException ex) {

		}

	}

}
