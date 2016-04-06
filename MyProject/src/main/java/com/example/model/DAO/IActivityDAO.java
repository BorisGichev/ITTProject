package com.example.model.DAO;

import com.example.model.POJO.Activity;
import com.example.model.POJO.ActivityStatus;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

public interface IActivityDAO {

	public static ActivityDAO getDAO(String storage)
			throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new ActivityDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public abstract int addActivity(Activity activity)
			throws WorkPlanDAOException, DBException;

	public abstract List<Activity> getActivitiesByProject(int projectID)
			throws DBException;

	Activity getActivityByID(int activityID) throws WorkPlanDAOException,
			DBException;

	List<Activity> getActivitiesByAssigneeID(int userID) throws DBException;

	public List<Activity> getAllActivitiesBySprintID(Integer sprintID)
			throws DBException, WorkPlanDAOException;

	public void setSprint(Integer activityID, Integer sprintID)
			throws WorkPlanDAOException, DBException;

	public List<Activity> getActivitiesNotInSprint(Integer projectID);

	public List<Activity> getAllActivitiesWithStatus(ActivityStatus activityType,
			int sprintID) throws DBException, WorkPlanDAOException;

	void updateStatus(ActivityStatus status, int activityID) throws DBException;

	List<Activity> getAllActivitiesWithTypeInWholeProject(String type, int projectID)
			throws DBException, WorkPlanDAOException;

	List<Activity> getAllActivitiesWithStatusInProject(ActivityStatus activityStatus, int projectID)
			throws DBException, WorkPlanDAOException;

	List<Activity> getActivitiesByAssigneeName(String assignee, int projectID) throws DBException;

	List<Activity> getAllActivitiesContainingComment(String comment, int projectID)
			throws DBException, WorkPlanDAOException;
}