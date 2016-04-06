package com.example.model.DAO;

import javax.activation.UnsupportedDataTypeException;

import com.example.model.POJO.Organization;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

public interface IOrganizationDAO {

	public int addOrganization(Organization organization) throws WorkPlanDAOException, DBException;

	public static IOrganizationDAO getDAO(String storage) throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new OrganizationDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	String getOrgName(int orgId) throws WorkPlanDAOException, DBException;

	Organization getOrgByID(int orgId) throws WorkPlanDAOException, DBException;
}
