package ittalents.workplan.model.POJO;

import java.sql.Timestamp;

public class Activity {

	private int id;
	private String summary;
	private String description;
	private String version;
	private double estimate;
	private Timestamp createdOn;
	private Timestamp updatedOn;
	private int reportedID;
	private int assigneeID;
	private String status;
	private String type;
	private int sprintID;
	private int connectedToID;
	private int projectID;

	public Activity(int id, String summary, String description, String version,
			double estimate, Timestamp createdOn, Timestamp updatedOn,
			int reportedID, int assigneeID, String status, String type,
			int sprintID, int connectedToID, int projectID) {
		this.id = id;
		this.summary = summary;
		this.description = description;
		this.version = version;
		this.estimate = estimate;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.reportedID = reportedID;
		this.assigneeID = assigneeID;
		this.status = status;
		this.type = type;
		this.sprintID = sprintID;
		this.connectedToID = connectedToID;
		this.projectID = projectID;
	}

	public Activity(String summary, int reportedID, String status, String type,
			int projectID) {
		this.summary = summary;
		this.reportedID = reportedID;
		this.status = status;
		this.type = type;
		this.projectID = projectID;
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
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
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

}
