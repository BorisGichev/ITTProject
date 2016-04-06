package com.example.model.DAO;

import com.example.model.POJO.Activity;
import com.example.model.POJO.ActivityStatus;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

public class ActivityDAO extends AbstractDBConnDAO implements IActivityDAO {
	private static final String UPDATE_ACTIVITIES_STATUS = "UPDATE activities set status=? where activity_id=?;";
	private static final String SELECT_ACTIVITIES_BY_STATUS_AND_SPRINT_ID = "Select  activity_id from activities where status=? AND sprint_id=?;";
	private static final String UPDATE_ACTIVITIES_SET_SPRINT = "Update activities set sprint_id=? where activity_id=?;";
	private static final String SELECT_ACTIVITIES_BYSPRINT = "Select  activity_id from activities where sprint_id =?;";
	private static final String SELECT_ACTIVITIES_BY_PROJECT_ID = "SELECT * from activities where project_id=?";
	private static final String INSERT_INTO_ACTIVITIES = "INSERT into activities (summary,description,issue_key,estimate,reporter_id,assignee_id,`status`,`type`,sprint_id,connected_to_id,project_id,priority,connected_type) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SELECT_ACTIVITY_BY_ID = "SELECT * from activities where activity_id=?";
	private static final String SELECT_ACTIVITY_BY_ASSIGNEE_ID = "SELECT * from activities where assignee_id=?";
	@Override
	public int addActivity(Activity activity) throws WorkPlanDAOException, DBException {
		if (activity == null) {
			throw new WorkPlanDAOException("There is no activity to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(INSERT_INTO_ACTIVITIES,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, activity.getSummary());
			ps.setString(2, activity.getDescription());
			ps.setString(3, activity.getIssueKey());
			ps.setDouble(4, activity.getEstimate());
			ps.setInt(5, activity.getReportedID());
			ps.setInt(6, activity.getAssigneeID());
			ps.setString(7, activity.getStatus());
			ps.setString(8, activity.getType());
			if (activity.getSprintID()!=0) {
				ps.setInt(9, activity.getSprintID());
			}
			if (activity.getSprintID()==0) {
				ps.setNull(9, Types.INTEGER);
			}
			if (activity.getConnectedToID()!=0) {
				ps.setInt(10, activity.getConnectedToID());
			}
			if (activity.getConnectedToID()==0) {
				ps.setNull(10, Types.INTEGER);
			}
			ps.setInt(11, activity.getProjectID());
			ps.setInt(12, activity.getPrioriy());
			ps.setString(13, activity.getConnectedType());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("The activity cannot be add right now!Try again later!");
		}

	}

	@Override
	public Activity getActivityByID(int activityID) throws WorkPlanDAOException, DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_ACTIVITY_BY_ID);
			ps.setInt(1, activityID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				return new Activity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5),
						rs.getTimestamp(6), rs.getTimestamp(7), rs.getInt(8), rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getString(16));

			}
			throw new WorkPlanDAOException("No such an activity!");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot get Activity right now!Try again later!");
		}
	}

	@Override
	public List<Activity> getActivitiesByProject(int projectID) throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_ACTIVITIES_BY_PROJECT_ID);
			ps.setInt(1, projectID);
			ResultSet rs = ps.executeQuery();
			List<Activity> listWithActivities = new LinkedList<Activity>();
			while (rs.next()) {

				listWithActivities.add(IActivityDAO.getDAO("db").getActivityByID(rs.getInt(1)));
			}
			return listWithActivities;
		} catch (SQLException | UnsupportedDataTypeException | WorkPlanDAOException e) {
			e.printStackTrace();
			throw new DBException("Cannot get Activities right now!Try again later!");
		}
	}

	@Override
	public List<Activity> getActivitiesByAssigneeID(int assigneeID) throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_ACTIVITY_BY_ASSIGNEE_ID);
			ps.setInt(1, assigneeID);
			ResultSet rs = ps.executeQuery();
			List<Activity> listWithActivitiesOfUser = new LinkedList<Activity>();
			while (rs.next()) {

				listWithActivitiesOfUser.add(IActivityDAO.getDAO("db").getActivityByID(rs.getInt(1)));
			}
			return listWithActivitiesOfUser;
		} catch (SQLException | UnsupportedDataTypeException | WorkPlanDAOException e) {
			e.printStackTrace();
			throw new DBException("Cannot get Activities right now!Try again later!");
		}
	}

	public List<Activity> getAllActivitiesBySprintID(Integer sprintID) throws DBException, WorkPlanDAOException {
		List<Activity> activitiesInSprint = new ArrayList<Activity>();

		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(SELECT_ACTIVITIES_BYSPRINT);
			ps.setInt(1, sprintID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				activitiesInSprint.add(IActivityDAO.getDAO("db").getActivityByID(rs.getInt(1)));
			}
		} catch (SQLException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return activitiesInSprint;
	}

	public void setSprint(Integer activityID, Integer sprintID) throws WorkPlanDAOException, DBException {
		if (sprintID == null) {
			throw new WorkPlanDAOException("There is no sprint to add to activity!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(UPDATE_ACTIVITIES_SET_SPRINT);
			ps.setInt(1, sprintID);
			ps.setInt(2, activityID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("The sprint cannot be add right now to the activity!Try again later!");
		}
	}

	@Override
	public List<Activity> getActivitiesNotInSprint(Integer projectID) {
		List<Activity> activitiesNotInSprint = new ArrayList<Activity>();
		try {
			for (Activity activity : IActivityDAO.getDAO("db").getActivitiesByProject(projectID)) {
				if (activity.getSprintID() == 0) {
					activitiesNotInSprint.add(activity);
				}
			}
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activitiesNotInSprint;
	}

	public List<Activity> getAllActivitiesWithStatus(ActivityStatus activityStatus, int sprintID)
			throws DBException, WorkPlanDAOException {
		List<Activity> getAllActivitiesWithStatusInSprint = new ArrayList<Activity>();

		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(SELECT_ACTIVITIES_BY_STATUS_AND_SPRINT_ID);
			if (activityStatus == ActivityStatus.ToDo) {
				ps.setString(1, "Todo");
			}
			if (activityStatus == ActivityStatus.InProgress) {
				ps.setString(1, "InProgress");
			}
			if (activityStatus == ActivityStatus.Done) {
				ps.setString(1, "Done");
			}
			ps.setInt(2, sprintID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				getAllActivitiesWithStatusInSprint.add(getActivityByID(rs.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getAllActivitiesWithStatusInSprint;
	}
	@Override
	public void updateStatus(ActivityStatus status, int activityID)
			throws DBException {
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(
					UPDATE_ACTIVITIES_STATUS);
			ps.setString(1, status.toString());
			ps.setInt(2, activityID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(
					"cannot update activity right now !Try again later!");
		}

	}
	
	@Override
	public List<Activity> getAllActivitiesContainingComment(String comment,
			int projectID) throws DBException, WorkPlanDAOException {
		List<Activity> getAllActivitiesContainingComment = new ArrayList<Activity>();

		PreparedStatement ps;
		try {
			ps = getCon()
					.prepareStatement(
							"SELECT c.activity_id FROM comments c join activities a on(c.activity_id=a.activity_id) where text like ? AND a.project_id=?;");

			ps.setString(1, "%" + comment + "%");
			ps.setInt(2, projectID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				getAllActivitiesContainingComment.add(getActivityByID(rs
						.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getAllActivitiesContainingComment;
	}

	@Override
	public List<Activity> getActivitiesByAssigneeName(String assignee,
			int projectID) throws DBException {
		try {
			PreparedStatement ps = getCon()
					.prepareStatement(
							"SELECT activity_id  from activities a join  users u  on(a.assignee_id=u.user_id)  where full_name like ? AND project_id=?;");
			ps.setString(1, "%" + assignee + "%");
			ps.setInt(2, projectID);
			ResultSet rs = ps.executeQuery();
			List<Activity> listWithActivitiesOfUser = new LinkedList<Activity>();
			while (rs.next()) {

				listWithActivitiesOfUser.add(IActivityDAO.getDAO("db")
						.getActivityByID(rs.getInt(1)));
			}
			return listWithActivitiesOfUser;
		} catch (SQLException | UnsupportedDataTypeException
				| WorkPlanDAOException e) {
			e.printStackTrace();
			throw new DBException(
					"Cannot get Activities right now!Try again later!");
		}
	}

	@Override
	public List<Activity> getAllActivitiesWithStatusInProject(
			ActivityStatus activityStatus, int projectID) throws DBException,
			WorkPlanDAOException {
		List<Activity> getAllActivitiesWithStatusInProject = new ArrayList<Activity>();

		PreparedStatement ps;
		try {
			ps = getCon()
					.prepareStatement(
							"Select  activity_id from activities where status=? AND project_id=?;");
			ps.setString(1, activityStatus.toString());
			ps.setInt(2, projectID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				getAllActivitiesWithStatusInProject.add(getActivityByID(rs
						.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getAllActivitiesWithStatusInProject;
	}

	@Override
	public List<Activity> getAllActivitiesWithTypeInWholeProject(String type,
			int projectID) throws DBException, WorkPlanDAOException {
		List<Activity> getAllActivitiesWithTypeInProject = new ArrayList<Activity>();

		PreparedStatement ps;
		try {
			ps = getCon()
					.prepareStatement(
							"Select  activity_id from activities where type=? AND project_id=?;");
			ps.setString(1, type);
			ps.setInt(2, projectID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				getAllActivitiesWithTypeInProject.add(getActivityByID(rs
						.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(
					" cannot get all activities with status right now!Try again later!");
		}

		return getAllActivitiesWithTypeInProject;
	}

}
