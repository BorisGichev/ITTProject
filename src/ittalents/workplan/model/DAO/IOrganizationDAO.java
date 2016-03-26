package ittalents.workplan.model.DAO;

import javax.activation.UnsupportedDataTypeException;

import ittalents.workplan.model.POJO.Organization;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

public interface IOrganizationDAO {
	
	
	public int addOrganization(Organization organization) throws WorkPlanDAOException, DBException;

	public static IOrganizationDAO getDAO(String storage) throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new OrganizationDAO();
		}
		throw new UnsupportedDataTypeException();
	}
}
