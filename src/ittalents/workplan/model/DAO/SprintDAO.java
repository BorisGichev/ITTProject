package ittalents.workplan.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ittalents.workplan.model.POJO.Sprint;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

public class SprintDAO extends AbstractDBConnDAO implements ISprintDAO {

	public int addSprint(Sprint sprint) throws WorkPlanDAOException,
			DBException {

		if (sprint == null) {
			throw new WorkPlanDAOException("There is no sprint to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(
					"INSERT into sprints values (null,?,?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, sprint.getName());
			ps.setBoolean(2, sprint.isActive());
			ps.setInt(3, sprint.getProjectId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(
					"The sprint cannot be add right now!Try again later!", e);
		}

	}

	public void updateSprintActivity(boolean isActive, int sprintID)
			throws WorkPlanDAOException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					"Update sprints set is_active=? where sprint_id=?;");
			ps.setBoolean(1, isActive);
			ps.setInt(2, sprintID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WorkPlanDAOException(
					"The sprint cannot be updated right now!Try again later!",
					e);
		}

	}

	public boolean isThereAnActiveSprintInThisProject(int projectID)
			throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					"Select is_active from sprints where project_id=?");
			ps.setInt(1, projectID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean(1) == true) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot get info right now!Try again later!",
					e);
		}

	}

}
