package ittalents.workplan.model.DAO;

import ittalents.workplan.model.POJO.Organization;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizationDAO extends AbstractDBConnDAO implements IOrganizationDAO {

	private static final String SELECT_NAME_FROM_ID = "SELECT `name`  from organizations where organization_id=?;";
	private static final String INSERT_INTO_ORGANIZATIONS = "INSERT into organizations values (null,?,?);";

	@Override
	public int addOrganization(Organization organization) throws WorkPlanDAOException, DBException {
		if (organization == null) {
			throw new WorkPlanDAOException("There is no organization to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(INSERT_INTO_ORGANIZATIONS,
					PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, organization.getName());
			ps.setInt(2, organization.getAdminID());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("The organization cannot be add right now!Try again later!");
		}

	}

	@Override
	public String getOrgName(int orgId) throws WorkPlanDAOException, DBException {
		if (orgId == 0) {
			throw new WorkPlanDAOException("There is no organization to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(
					SELECT_NAME_FROM_ID);

			ps.setInt(1, orgId);
			ResultSet rs = ps.executeQuery();
			rs.next();

			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("No name for this organization!Try again later!");
		}

	}

}
