package com.example.controller.newSpring;

import java.sql.SQLException;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.DAO.IActivityDAO;
import com.example.model.DAO.IProjectDAO;
import com.example.model.DAO.ISprintDAO;
import com.example.model.DAO.IUserDAO;
import com.example.model.POJO.Activity;
import com.example.model.POJO.Project;
import com.example.model.POJO.User;
import com.example.model.exception.DBException;

@SessionAttributes({ "project", "user" })
@Controller
public class CreateIssueController {

	@RequestMapping(value = "/CreateIssue", method = RequestMethod.GET)
	public String goToCreateIssue(Model model,@ModelAttribute("user") User user,@ModelAttribute("project") Project project) {
	
		try {
			model.addAttribute("projects", IProjectDAO.getDAO("db").getAllProjectsByOrg(user.getOrganizationId()));
		} catch (DBException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<User> usersByOrg = null;
		try {
			usersByOrg=IUserDAO.getDAO("db").getAllUsersForOrganization(user.getOrganizationId());
			
			model.addAttribute("usersByOrg",usersByOrg);
		} catch (SQLException | UnsupportedDataTypeException e) {
			e.printStackTrace();
		}
		
		try {
			model.addAttribute("issuesForProject", IActivityDAO.getDAO("db").getActivitiesByProject(project.getId()));
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			model.addAttribute("sprintsForProject", ISprintDAO.getDAO("db").getAllSprintByProjectID(project.getId()));
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("issue",new Activity());
		return "createIssue";
	}
	
	@RequestMapping(value = "/CreateIssue", method = RequestMethod.POST)
	public String createIssueInDb(Model model,@ModelAttribute("user") User user,@ModelAttribute("project") Project project,
			@ModelAttribute ("issue") Activity issue) {
		System.out.println(project);
		
		System.out.println(issue);
	
		return "redirect:ProjectBoard";
	}

}
