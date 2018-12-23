package connection;

import java.sql.*;


import entity.User;

public class RegisterConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://db4free.net:3306/ollehhh";

	static final String USER = "ollehhh";
	static final String PASS = "lhgfei111";

	public void RegCon(User user) {
		Connection conn = null;
		Statement stmt = null;
		String sql = "INSERT INTO `USERCOMPANY` (`ID`,`LOGIN`, `PASS`, `FIRSTNAME`, `SURNAME`, `MAIL`, `PHONE`, `COMPANYNAME`, `SPECIALIZATION`) VALUES (NULL, ";
		sql = sql + "'" + user.getLogin() + "', '" + String.valueOf(user.getPassword()) + "', '"
				+ user.getFirstName() + "', '" + user.getSurName() + "', '" + user.getEmail() + "', " + user.getPhone()
				+ ", '" + user.getCompanyName() + "', '" + user.getSpecialization() + "');";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
