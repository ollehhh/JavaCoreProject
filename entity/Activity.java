package entity;

import java.time.LocalDateTime;

public class Activity {
	int id, userId;
	LocalDateTime loginDate, logoutDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(LocalDateTime loginDate) {
		this.loginDate = loginDate;
	}

	public LocalDateTime getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(LocalDateTime logoutDate) {
		this.logoutDate = logoutDate;
	}

}
