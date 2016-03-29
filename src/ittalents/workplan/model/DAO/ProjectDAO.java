package ittalents.workplan.model.DAO;

import ittalents.workplan.model.POJO.Project;
import ittalents.workplan.model.POJO.Sprint;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

public class ProjectDAO extends AbstractDBConnDAO implements IProjectDAO {

	@Override
	public List<User> getAllUsersForProject(Integer projID) throws DBException,
			UnsupportedDataTypeException {
		List<User> users = new ArrayList<User>();

		PreparedStatement ps;
		try {
			ps = getCon()
					.prepareStatement(
							"Select  id_user from users_projects where id_user_project =?;");
			ps.setInt(1, projID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				users.add(IUserDAO.getDAO("db").getUserById(rs.getInt(1)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	public int addProject(Project project) throws WorkPlanDAOException,
			DBException {
		if (project == null) {
			throw new WorkPlanDAOException("There is no project to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(
					"INSERT into projects values(null,?,?,?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, project.getName());
			ps.setInt(2, project.getOrganizationID());
			ps.setString(3, project.getKey());
			ps.setInt(4, project.getProjectLeaderID());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"The project cannot be add right now!Try again later!", e);
		}

	}

}
