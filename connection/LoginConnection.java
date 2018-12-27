package connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import frames.Login;
import frames.MainFrame;

public class LoginConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://db4free.net:3306/ollehhh";

	static final String USER = "ollehhh";
	static final String PASS = "lhgfei111";
	User user ;
	List<User> userList = new ArrayList<User>();

	public void tryLogin(String login, String pass) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM `USERCOMPANY`;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("ID"));
				user.setLogin(rs.getString("LOGIN"));
				user.setPassword(rs.getString("PASS"));
				user.setCompanyName(rs.getString("COMPANYNAME"));
				user.setEmail(rs.getString("MAIL"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setSurName(rs.getString("SURNAME"));
				user.setPhone(rs.getInt("PHONE"));
				user.setSpecialization(rs.getString("SPECIALIZATION"));
				user.setManager_id(rs.getInt("MANAGER_ID"));
				userList.add(user);
			}
			rs.close();
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
		boolean bol = false;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getLogin().equals(login) && userList.get(i).getPassword().equals(pass)) {
				user = userList.get(i);
				bol = true;
			}
		}
		if (bol == false) {
			Login ld = new Login();
			ld.createLogin();
		} else {
			MainFrame mf = new MainFrame();
			mf.createMainFrame(user);
		}
	}
}
