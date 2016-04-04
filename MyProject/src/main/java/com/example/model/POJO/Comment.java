package com.example.model.POJO;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Comment {
	private int id;
	private String createdOn;
	private String text;
	private int activityID;
	private int userID;

	public Comment(String text, int activityID, int userID) {
		this.text = text;
		this.activityID = activityID;
		this.userID = userID;
	}

	public Comment(int id, Timestamp time, String text, int activityID,
			int userID) {
		this.id = id;
		this.createdOn = time.toLocalDateTime().format(
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
		this.text = text;
		this.activityID = activityID;
		this.userID = userID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedOn() {
		return createdOn;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getActivityID() {
		return activityID;
	}

	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
