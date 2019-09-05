package dao;

import java.sql.SQLException;
import java.util.List;

import models.Jogada;

public interface JogadaDAO {
	void cadastraJogada(Jogada a) throws SQLException;
	List<Jogada> buscaJogada(String email) throws SQLException;
}
