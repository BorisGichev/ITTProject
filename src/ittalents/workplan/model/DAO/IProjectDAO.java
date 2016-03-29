package ittalents.workplan.model.DAO;

import ittalents.workplan.model.POJO.Project;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.sql.SQLException;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

public interface IProjectDAO {
	public static ProjectDAO getDAO(String storage)
			throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new ProjectDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public int addProject(Project project) throws WorkPlanDAOException,
			DBException;

	List<User> getAllUsersForProject(Integer projID) throws 
			DBException, UnsupportedDataTypeException;
}
