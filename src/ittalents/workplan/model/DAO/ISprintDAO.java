package ittalents.workplan.model.DAO;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import ittalents.workplan.model.POJO.Sprint;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

public interface ISprintDAO {

	public static SprintDAO getDAO(String storage)
			throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new SprintDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public int addSprint(Sprint sprint) throws WorkPlanDAOException,
			DBException;

	public int isThereAnActiveSprintInThisProject(int projectID)
			throws DBException;

	public Sprint getSprintById(int sprintID) throws DBException;

	public List<Sprint> getAllSprintByProjectID(Integer projectID)
			throws DBException;

}
