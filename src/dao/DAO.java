package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

	private static String CON_STRING = "jdbc:sqlite:bd.sqlite";
	private static int MAX_CONNECTIONS=5;
	private static Connection[] pool;
	
	private static DAO instance = new DAO();
	
	public DAO() {
		pool = new Connection[MAX_CONNECTIONS];
	}
	
	@SuppressWarnings("static-access")
	public static Connection getConnection() throws SQLException{
		
		for(int i = 0; i < MAX_CONNECTIONS; i++) {
			if(instance.pool[i] == null || instance.pool[i].isClosed()) {
				instance.pool[i] = DriverManager.getConnection(CON_STRING);
				return instance.pool[i];
			}
		}
		
		throw new SQLException("Máximo de conexões");
	}
}
