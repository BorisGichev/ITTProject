package ittalents.workplan.model.DAO;

import ittalents.workplan.model.POJO.Organization;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

public interface IOrganizationDAO {
	public int addActivity(Organization organization, int adminID)
			throws WorkPlanDAOException, DBException;
}
