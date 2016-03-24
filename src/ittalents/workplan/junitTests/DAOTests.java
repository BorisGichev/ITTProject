package ittalents.workplan.junitTests;

import static org.junit.Assert.*;

import javax.activation.UnsupportedDataTypeException;

import org.junit.Test;

import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.exception.DBException;

public class DAOTests {

	@Test
	public void DBConnectionTest() {
		ittalents.workplan.model.DAO.DBConnection.getInstance();
	}
	
	@Test
	public void test() throws UnsupportedDataTypeException, DBException {
		System.out.println(IUserDAO.getDAO("db").isThereSuchAUser("drago@abv.bg"));
	}

}
