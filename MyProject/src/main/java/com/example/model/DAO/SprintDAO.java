package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.POJO.Sprint;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

public class SprintDAO extends AbstractDBConnDAO implements ISprintDAO {

	@Override
	public int addSprint(Sprint sprint) throws WorkPlanDAOException, DBException {

		if (sprint == null) {
			throw new WorkPlanDAOException("There is no sprint to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement("INSERT into sprints values (null,?,?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, sprint.getName());
			ps.setBoolean(2, false);
			ps.setInt(3, sprint.getProjectId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("The sprint cannot be add right now!Try again later!", e);
		}

	}

	@Override
	public void updateSprintActivity(boolean isActive, int sprintID) throws WorkPlanDAOException {
		try {
			PreparedStatement ps = getCon().prepareStatement("Update sprints set is_active=? where sprint_id=?;");
			ps.setBoolean(1, isActive);
			ps.setInt(2, sprintID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WorkPlanDAOException("The sprint cannot be updated right now!Try again later!", e);
		}

	}

	@Override
	public int isThereAnActiveSprintInThisProject(int projectID) throws DBException {
		try {
			PreparedStatement ps = getCon()
					.prepareStatement("Select sprint_id,is_active from sprints where project_id=?");
			ps.setInt(1, projectID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean(2) == true) {
					return rs.getInt(1);
				}
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot get info right now!Try again later!", e);
		}

	}

	@Override
	public List<Sprint> getAllSprintByProjectID(int projectID) throws DBException {
		if (projectID == 0) {
			throw new DBException("No Project Id");
		}
		List<Sprint> sprints = new ArrayList<Sprint>();
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement("Select  sprint_id from sprints where project_id =?;");
			ps.setInt(1, projectID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				sprints.add(getSprintById(rs.getInt(1)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sprints;
	}

	@Override
	public Sprint getSprintById(int sprintID) throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement("SELECT * from sprints where sprint_id=?");
			ps.setInt(1, sprintID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new Sprint(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getInt(4));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot get sprint right now!Try again later!", e);
		}

	}

}
