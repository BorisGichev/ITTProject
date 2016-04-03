package com.example.model.POJO;

import java.sql.Timestamp;

public class Activity {

	private int id;
	private String summary;
	private String description;
	private String issueKey;
	private double estimate;
	private Timestamp createdOn;
	private Timestamp finishedOn;
	private int reportedID;
	private int assigneeID;
	private String status;
	private String type;
	private int sprintID;
	private int connectedToID;
	private int projectID;
	private int prioriy;
	private String connectedType;

	public Activity() {
		
	}
	
	public Activity(int id, String summary, String description, String version,
			double estimate, Timestamp createdOn, Timestamp updatedOn,
			int reporterID, int assigneeID, String status, String type,
			int sprintID, int connectedToID, int projectID) {
		this.id = id;
		this.summary = summary;
		this.description = description;
		this.issueKey = version;
		this.estimate = estimate;
		this.createdOn = createdOn;
		this.finishedOn = updatedOn;
		this.reportedID = reporterID;
		this.assigneeID = assigneeID;
		this.status = status;
		this.type = type;
		this.sprintID = sprintID;
		this.connectedToID = connectedToID;
		this.projectID = projectID;
	}

	public Activity(String summary, int reporterID, String status, String type,
			int projectID) {
		this.summary = summary;
		this.reportedID = reporterID;
		this.status = status;
		this.type = type;
		this.projectID = projectID;
	}

	public Activity(int id, String summary, String description, String issueKey, double estimate, Timestamp createdOn,
			Timestamp finishedOn, int reportedID, int assigneeID, String status, String type, int sprintID,
			int connectedToID, int projectID, int prioriy, String connectedType) {
		super();
		this.id = id;
		this.summary = summary;
		this.description = description;
		this.issueKey = issueKey;
		this.estimate = estimate;
		this.createdOn = createdOn;
		this.finishedOn = finishedOn;
		this.reportedID = reportedID;
		this.assigneeID = assigneeID;
		this.status = status;
		this.type = type;
		this.sprintID = sprintID;
		this.connectedToID = connectedToID;
		this.projectID = projectID;
		this.prioriy = prioriy;
		this.connectedType = connectedType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return issueKey;
	}

	public void setVersion(String version) {
		this.issueKey = version;
	}

	public double getEstimate() {
		return estimate;
	}

	public void setEstimate(double estimate) {
		this.estimate = estimate;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getUpdatedOn() {
		return finishedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.finishedOn = updatedOn;
	}

	public int getReportedID() {
		return reportedID;
	}

	public void setReportedID(int reportedID) {
		this.reportedID = reportedID;
	}

	public int getAssigneeID() {
		return assigneeID;
	}

	public void setAssigneeID(int assigneeID) {
		this.assigneeID = assigneeID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSprintID() {
		return sprintID;
	}

	public void setSprintID(int sprintID) {
		this.sprintID = sprintID;
	}

	public int getConnectedToID() {
		return connectedToID;
	}

	public void setConnectedToID(int connectedToID) {
		this.connectedToID = connectedToID;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public int getPrioriy() {
		return prioriy;
	}

	public void setPrioriy(int prioriy) {
		this.prioriy = prioriy;
	}

	public String getConnectedType() {
		return connectedType;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", summary=" + summary + ", description=" + description + ", issueKey=" + issueKey
				+ ", estimate=" + estimate + ", createdOn=" + createdOn + ", finishedOn=" + finishedOn + ", reportedID="
				+ reportedID + ", assigneeID=" + assigneeID + ", status=" + status + ", type=" + type + ", sprintID="
				+ sprintID + ", connectedToID=" + connectedToID + ", projectID=" + projectID + ", prioriy=" + prioriy
				+ ", connectedType=" + connectedType + "]";
	}

	public void setConnectedType(String connectedType) {
		this.connectedType = connectedType;
	}

}
