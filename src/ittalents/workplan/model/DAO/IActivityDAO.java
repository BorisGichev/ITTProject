package ittalents.workplan.model.DAO;

import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

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

	public List<Activity> getActivitiesNotInSprint(Integer projectID)
			throws DBException;

	public List<Activity> getAllActivitiesWithStatus(
			ActivityStatus activityType, int sprintID) throws DBException,
			WorkPlanDAOException;

	public List<Activity> getAllActivitiesWithStatusInWholeProject(
			ActivityStatus activityStatus, int projectID) throws DBException,
			WorkPlanDAOException;
}