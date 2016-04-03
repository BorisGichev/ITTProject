package com.example.controller.newSpring;

import javax.activation.UnsupportedDataTypeException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.DAO.IProjectDAO;
import com.example.model.DAO.IUserDAO;
import com.example.model.POJO.Project;
import com.example.model.POJO.User;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

@Controller

@SessionAttributes({ "project", "user" })

public class SelectProject {
	@RequestMapping(value = "/SelectProject", method = RequestMethod.GET)
	public String selectProjectForProjecBoard(Model model, @ModelAttribute("user") User user,
			@RequestParam("projectId") Integer projectId) {

		Project project = null;
		
		try {
			project = IProjectDAO.getDAO("db").getProjectById(projectId);
		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.setLastProjectId(project.getId());
		
		try {
			System.out.println(IUserDAO.getDAO("db").updateUser(user));
		} catch (UnsupportedDataTypeException | DBException | WorkPlanDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		return "forward:ProjectBoard";

	}
	
	@RequestMapping(value = "/SelectProjectForIssue", method = RequestMethod.GET)
	public String selectProjectForIssue(Model model, @ModelAttribute("user") User user,
			@RequestParam("projectId") Integer projectId) {

		Project project = null;
		
		try {
			project = IProjectDAO.getDAO("db").getProjectById(projectId);
		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.setLastProjectId(project.getId());
		
		try {
			System.out.println(IUserDAO.getDAO("db").updateUser(user));
		} catch (UnsupportedDataTypeException | DBException | WorkPlanDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		return "forward:CreateIssue";

	}

}
