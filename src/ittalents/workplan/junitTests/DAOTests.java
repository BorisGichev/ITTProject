package ittalents.workplan.junitTests;

import static org.junit.Assert.*;

import javax.activation.UnsupportedDataTypeException;

import org.junit.Test;

import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

public class DAOTests {
	User user=new User();
	

	@Test
	public void DBConnectionTest() {
		ittalents.workplan.model.DAO.DBConnection.getInstance();
	}
	
	@Test
	public void CheckEmail() throws UnsupportedDataTypeException, DBException {
		System.out.println(IUserDAO.getDAO("db").isThereSuchAUser("drago@abv.bg"));
	}
	
	@Test
	public void CheckCreateUser() throws UnsupportedDataTypeException, DBException, WorkPlanDAOException {
		user.setEmail("morga@abv.bg");
		user.setPassword("kolon123");
		user.setUsername("klnka1");
		System.out.println(IUserDAO.getDAO("db").addUser(user));
	}

}
