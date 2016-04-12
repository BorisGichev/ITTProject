package bg.ittalents.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	private static String url = "jdbc:mysql://127.0.0.1:3306/workplandb?useSSL=false";
	private static String passwordDB = "Istinataboli1@";
	private static String userDB = "borisgichev";
	

	private static DBConnection instance = null;

	private Connection conn;

	DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, userDB, passwordDB);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static DBConnection getInstance() {
		synchronized (DBConnection.class) {
			if (instance == null) {
				instance = new DBConnection();
			}
			return instance;
		}
	}

	public Connection getCon() {
		return conn;
	}
}
