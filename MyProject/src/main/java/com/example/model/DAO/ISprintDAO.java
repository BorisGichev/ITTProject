package com.example.model.DAO;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import com.example.model.POJO.Sprint;

import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

public interface ISprintDAO {

	public static SprintDAO getDAO(String storage) throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new SprintDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public int addSprint(Sprint sprint) throws WorkPlanDAOException, DBException;

	public int isThereAnActiveSprintInThisProject(int projectID) throws DBException;

	public Sprint getSprintById(int sprintID) throws DBException;

	public List<Sprint> getAllSprintByProjectID(int projectID) throws DBException;

	void updateSprintActivity(boolean isActive, int sprintID) throws WorkPlanDAOException;

}
