package ittalents.workplan.model.DAO;

import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.sql.SQLException;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

public interface IUserDAO {

	public int addUser(User user) throws DBException, WorkPlanDAOException;

	public User getUserById(int userID) throws DBException;

	public static UserDAO getDAO(String storage)
			throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new UserDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public boolean isThereSuchAUser(String username, String email)
			throws DBException;

	boolean isThereSuchAUser(String email) throws DBException;

	public void insertIntoAttachments(int userID, int activityID, String path);

	User loginUser(String username, String password, String organization)
			throws WorkPlanDAOException, DBException;

	int updateUser(User user) throws DBException, WorkPlanDAOException;

	int updateOrgId(User user, Integer orgId) throws DBException,
			WorkPlanDAOException;

	User getUserByEmail(String email) throws DBException;

	List<User> getAllUsersForOrganization(Integer orgId) throws SQLException;

}
