package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import entity.User;

public class EditConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://db4free.net:3306/ollehhh";

	static final String USER = "ollehhh";
	static final String PASS = "lhgfei111";

	public void editCon(User user) {
		Connection conn = null;
		Statement stmt = null;
		String sql = "UPDATE `USERCOMPANY` SET `LOGIN` = '" + user.getLogin() + "', `PASS` = '" + user.getPassword()
				+ "', `FIRSTNAME` = '" + user.getFirstName() + "', `SURNAME` = '" + user.getSurName() + "', `MAIL` = '"
				+ user.getEmail() + "', `PHONE` = " + user.getPhone() + ", `COMPANYNAME` = '" + user.getCompanyName()
				+ "', `SPECIALIZATION` = '" + user.getSpecialization() + "' WHERE `ID`= " + user.getId() + ";";

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
