package ittalents.workplan.model.DAO;

import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ActivityDAO extends AbstractDBConnDAO implements IActivityDAO {
	private static final String SELECT_ACTIVITY_BY_ASSIGNEE_ID = "SELECT * from activities where assignee_id=?";
	private static final String SELECT_ACTIVITY_BY_ID = "SELECT * from activities where activity_id=?";

	@Override
	public int addActivity(Activity activity) throws WorkPlanDAOException,
			DBException {
		if (activity == null) {
			throw new WorkPlanDAOException("There is no activity to add!");
		}
		try {
			PreparedStatement ps = getCon()
					.prepareStatement(
							"INSERT into activities (activity_id,summary,reporter_id,status,type,project_id) values (null,?,?,?,?,?);",
							PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, activity.getSummary());
			ps.setInt(2, activity.getReportedID());
			ps.setString(3, activity.getStatus());
			ps.setString(4, activity.getType());
			ps.setInt(5, activity.getProjectID());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(
					"The activity cannot be add right now!Try again later!");
		}

	}

	@Override
	public List<Activity> getActivitiesByProject(int projectID)
			throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					"SELECT * from activities where project_id=?");
			ps.setInt(1, projectID);
			ResultSet rs = ps.executeQuery();
			List<Activity> listWithActivities = new LinkedList<Activity>();
			while (rs.next()) {

				listWithActivities.add(new Activity(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getString(4), rs
						.getDouble(5), rs.getTimestamp(6), rs.getTimestamp(7),
						rs.getInt(8), rs.getInt(9), rs.getString(10), rs
								.getString(11), rs.getInt(12), rs.getInt(13),
						rs.getInt(14)));
			}
			return listWithActivities;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"Cannot get Activities right now!Try again later!");
		}
	}

	@Override
	public List<Activity> getActivitiesByAssigneeID(int assigneeID)
			throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					SELECT_ACTIVITY_BY_ASSIGNEE_ID);
			ps.setInt(1, assigneeID);
			ResultSet rs = ps.executeQuery();
			List<Activity> listWithActivitiesOfUser = new LinkedList<Activity>();
			while (rs.next()) {

				listWithActivitiesOfUser.add(new Activity(rs.getInt(1), rs
						.getString(2), rs.getString(3), rs.getString(4), rs
						.getDouble(5), rs.getTimestamp(6), rs.getTimestamp(7),
						rs.getInt(8), rs.getInt(9), rs.getString(10), rs
								.getString(11), rs.getInt(12), rs.getInt(13),
						rs.getInt(14)));
			}
			return listWithActivitiesOfUser;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"Cannot get Activities right now!Try again later!");
		}
	}

	@Override
	public Activity getActivityByID(int activityID)
			throws WorkPlanDAOException, DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					SELECT_ACTIVITY_BY_ID);
			ps.setInt(1, activityID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				return new Activity(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getDouble(5),
						rs.getTimestamp(6), rs.getTimestamp(7), rs.getInt(8),
						rs.getInt(9), rs.getString(10), rs.getString(11),
						rs.getInt(12), rs.getInt(13), rs.getInt(14));
			}
			throw new WorkPlanDAOException("No such an activity!");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"Cannot get Activity right now!Try again later!");
		}
	}

}
