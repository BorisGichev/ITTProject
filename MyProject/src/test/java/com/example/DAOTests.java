package com.example;

import static org.junit.Assert.*;

import javax.activation.UnsupportedDataTypeException;

import org.junit.Test;

import com.example.model.DAO.IActivityDAO;
import com.example.model.DAO.IProjectDAO;
import com.example.model.DAO.ISprintDAO;
import com.example.model.DAO.IUserDAO;
import com.example.model.POJO.Activity;
import com.example.model.POJO.Project;
import com.example.model.POJO.Sprint;
import com.example.model.POJO.User;
import com.example.model.exception.*;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;

public class DAOTests {
	User user=new User();
	

//	@Test
//	public void dBConnectionTest() {
//		com.example.model.DAO.DBConnection.getInstance();
//	}
//	
//	@Test
//	public void checkEmail() throws UnsupportedDataTypeException, DBException {
//		System.out.println(IUserDAO.getDAO("db").isThereSuchAUser("drago@abv.bg"));
//	}
//	
//	@Test
//	public void CheckCreateUser() throws UnsupportedDataTypeException, DBException, WorkPlanDAOException {
//		user.setEmail("morga@abv.bg");
//		user.setPassword("kolon123");
//		user.setUsername("klnka1");
//		System.out.println(IUserDAO.getDAO("db").addUser(user));
//	}
	
//	@Test
//	public void CheckAddsprint() throws UnsupportedDataTypeException, DBException, WorkPlanDAOException {
//		
//		System.out.println(ISprintDAO.getDAO("db").addSprint(new Sprint(0, "test", true, 1)));
//	}

//	@Test
//	public void CheckUpdateUSer()  {
//		User user8=null;
//		try {
//			user8 =IUserDAO.getDAO("db").getUserById(8);
//		} catch (UnsupportedDataTypeException | DBException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		user8.setLastProjectId(22);
//		System.out.println(user8);
//		try {
//			IUserDAO.getDAO("db").updateUser(user8);
//		} catch (UnsupportedDataTypeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (WorkPlanDAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void CheckIncrementIssueCount()  {
//		Project project=new Project(15, "blaa", 3, "blaa", 8, 0);
//		
//		try {
//			IProjectDAO.getDAO("db").incrementIssuecount(project);
//		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void CheckIncrementIssueCount()  {
		Activity act=null;
		try {
			act = IActivityDAO.getDAO("db").getActivityByID(37);
		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(act);
	}
}
