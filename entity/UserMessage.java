package entity;

import java.time.LocalDateTime;

public class UserMessage {
	int id, user1Id, user2Id;
	String message;
	LocalDateTime date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser1Id() {
		return user1Id;
	}

	public void setUser1Id(int user1Id) {
		this.user1Id = user1Id;
	}

	public int getUser2Id() {
		return user2Id;
	}

	public void setUser2Id(int user2Id) {
		this.user2Id = user2Id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
