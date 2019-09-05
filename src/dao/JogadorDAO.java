package dao;

import java.sql.SQLException;
import java.util.List;

import models.Jogador;

public interface JogadorDAO {
	void cadastraJogador(Jogador a) throws SQLException;
	void atualizaJogador(Jogador a) throws SQLException;
	List<Jogador> buscaJogador(Jogador a) throws SQLException;
}
