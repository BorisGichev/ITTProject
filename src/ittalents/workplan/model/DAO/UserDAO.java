package ittalents.workplan.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

public class UserDAO extends AbstractDBConnDAO implements IUserDAO {

	private static final String SELECT_USER_BY_ID = "SELECT * from users where user_id=?);";
	private static final String SELECT_USER_BY_USERNAME_AND_CORRECT_PASSWORD = "SELECT * from users u join organizations_users ou on(u.user_id=ou.user_id) where (u.username = ? AND u.password = ? AND ou.organization_name_id=?";
	private static final String SELECT_FROM_USERS_BY_EMAIL = "Select * from users where email=?;";
	private static final String SELECT_USER_BY_USERNAME = "Select * from users where username=?;";
	private static final String INSERT_USER_INTO_DB = "INSERT into users values(null,?,?,?,?,null,?);";
	private static final String UPDATE_USER_INTO_DB = "UPDATE users SET username = ?, password = ?, avatar_path=?, admin=?  WHERE user_id = ?;";
	private static final String UPDATE_ONLY_ORG_ID = "UPDATE users SET organization_id=? WHERE user_id= ?;";
	@Override
	public int addUser(User user) throws DBException, WorkPlanDAOException {
		if (user == null) {
			throw new WorkPlanDAOException("There is no user to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(
					INSERT_USER_INTO_DB,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getAvatarPath());
			ps.setInt(5, user.getAdmin());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"The user cannot be add right now!Try again later!",e);
		}

	}
//	"UPDATE users SET username = ?, password = ?, avatar_path=?, organization_id=? admin=?  WHERE id = ?;";
	@Override
	public int updateUser(User user) throws DBException, WorkPlanDAOException {
		if (user == null) {
			throw new WorkPlanDAOException("There is no user to update!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(UPDATE_USER_INTO_DB);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getAvatarPath());
			ps.setInt(4, user.getAdmin());
			ps.setInt(5, user.getId());
			
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"The user cannot be Updated  right now!Try again later!",e);
		}

	}
	
	@Override
	public int updateOrgId(User user,Integer orgId) throws DBException, WorkPlanDAOException {
		if (user == null) {
			throw new WorkPlanDAOException("There is no user to update!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(UPDATE_ONLY_ORG_ID);
			ps.setInt(1, orgId);
			ps.setInt(2, user.getId());
			
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"The user cannot be Updated  right now!Try again later!",e);
		}

	}

	@Override
	public User getUserById(int userID) throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_USER_BY_ID);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5));

		} catch (SQLException e) {
			e.printStackTrace();
				throw new DBException(
					"Cannot get User right now!Try again later!",e);
		}
	}

	public boolean isThereSuchAUser(String username, String email) throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					SELECT_USER_BY_USERNAME);
			PreparedStatement ps2 = getCon().prepareStatement(
					SELECT_FROM_USERS_BY_EMAIL);
			ps.setString(1, username);
			ps2.setString(1, email);
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			if (rs.next() || rs2.next()) {
				System.out.println("true");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot check for user right now!Try again later",e);
		}
		System.out.println("false");
		return false;
	}
	@Override
	public boolean isThereSuchAUser(String email) throws DBException {
		try {

			PreparedStatement ps2 = getCon().prepareStatement(
					SELECT_FROM_USERS_BY_EMAIL);
			ps2.setString(1, email);
			
			ResultSet rs2 = ps2.executeQuery();
			if (rs2.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot check for user right now!Try again later",e);
		}
		return false;
	}

//	public void insertIntoAttachments(int userID, int activityID, String ) {
//		try {
//			PreparedStatement ps = getCon().prepareStatement(
//					"INSERT into attachments values(null,?,?,?);");
//			ps.setString(1, );
//			ps.setInt(2, activityID);
//			ps.setInt(3, userID);
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	@Override
	public User loginUser(String username, String password,String organization)
			throws WorkPlanDAOException, DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					SELECT_USER_BY_USERNAME_AND_CORRECT_PASSWORD);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, organization);
			ResultSet result = ps.executeQuery();

			if (result.next() == false) {
				throw new WorkPlanDAOException("No such user");
			}
			return new User(result.getInt(1), result.getString(2),
					result.getString(3), result.getString(4),
					result.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot log right now!",e);
		}

	}

	@Override
	public void insertIntoAttachments(int userID, int activityID, String path) {
		// TODO Auto-generated method stub
		
	}
}
