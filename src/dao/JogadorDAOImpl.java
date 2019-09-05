package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import models.Jogador;

public class JogadorDAOImpl extends DAO implements JogadorDAO{

    private QueryRunner dbAccess = new QueryRunner();
	
	@Override
	public void cadastraJogador(Jogador a) throws SQLException {
		Connection con = DAO.getConnection();
		PreparedStatement stm = con.prepareStatement("INSERT INTO jogador (nome, email, senha) VALUES (?,?,?)");
		try {
			stm.setString(1, a.getNome());
			stm.setString(2, a.getEmail());
			stm.setString(3, a.getSenha());
			stm.executeUpdate();
		}catch (SQLException e) {
			if (con != null) {
				System.err.print("Rollback efetuado na transação");
				con.rollback();
			}
		}finally {
			stm.close();
			con.close();
			// TODO Auto-generated catch block
		}

	}
	
	@Override
	public void atualizaJogador(Jogador a) throws SQLException {
		Connection con = DAO.getConnection();
		PreparedStatement stm = con.prepareStatement("UPDATE jogador SET pontos = ?, num_jogadas = ? WHERE email like ?");
		try {
			stm.setInt(1, a.getPontos());
			stm.setInt(2, a.getNum_jogadas());
			stm.setString(3, a.getEmail());
			stm.executeUpdate();
		}catch (SQLException e) {
			if (con != null) {
				System.err.print("Rollback efetuado na transação");
				con.rollback();
			}
		}finally {
			stm.close();
			con.close();
			// TODO Auto-generated catch block
		}

	}

	@Override
	public List<Jogador> buscaJogador(Jogador a) throws SQLException {
		Connection con = DAO.getConnection();
		
        BeanListHandler<Jogador> handler = new BeanListHandler<Jogador>(Jogador.class);
		
        List<Jogador> list = dbAccess.query(con, "SELECT * FROM jogador WHERE email like '"+a.getEmail()+ "' and senha like '"+a.getSenha()+"'", handler);
		return list;
	}
	
	

}
