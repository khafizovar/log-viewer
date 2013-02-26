package org.tatasu.model;

import java.sql.Date;

public class Logs {

	private String	user_id;
	private String	dated;
	private String	logger;
	private String	level;
	private String	message;
	private String	id;

	public Logs() {

	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDated() {
		return dated;
	}

	public void setDated(String dated) {
		this.dated = dated;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
