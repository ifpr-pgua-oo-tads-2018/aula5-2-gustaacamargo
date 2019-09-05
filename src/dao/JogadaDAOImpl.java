package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import models.Jogada;

public class JogadaDAOImpl extends DAO implements JogadaDAO{

	 private QueryRunner dbAccess = new QueryRunner();
		
		@Override
		public void cadastraJogada(Jogada a) throws SQLException {
			Connection con = DAO.getConnection();
			PreparedStatement stm = con.prepareStatement("INSERT INTO jogadas (emailj1, emailj2, palpitej1, palpitej2, soma) VALUES (?,?,?,?,?)");
			try {
				stm.setString(1, a.getEmailJ1());
				stm.setString(2, a.getEmailJ2());
				stm.setInt(3, a.getPalpiteJ1());
				stm.setInt(4, a.getPalpiteJ2());
				stm.setInt(5, a.getSoma());

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
		public List<Jogada> buscaJogada(String email) throws SQLException {
			Connection con = DAO.getConnection();
			
	        BeanListHandler<Jogada> handler = new BeanListHandler<Jogada>(Jogada.class);
			String s = "SELECT * FROM jogadas WHERE emailj1 like '"+email+ "' or emailj2 like '"+email+"'";
	        List<Jogada> list = dbAccess.query(con, "SELECT * FROM jogadas WHERE emailj1 like '"+email+ "' or emailj2 like '"+email+"'", handler);
			return list;
		}
}
