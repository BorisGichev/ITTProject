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
import com.example.model.exception.WorkPlanDAOException;

@SessionAttributes({ "project", "user", "projects" })
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
	
	@RequestMapping(value = "/UpdateIssue", method = RequestMethod.GET)
	public String goToUpdateIssue(Model model,@ModelAttribute("issueId") Integer issueId,@ModelAttribute("user") User user,@ModelAttribute("project") Project project) {
		
		
		Activity activity=null;
		try {
			activity = IActivityDAO.getDAO("db").getActivityByID(issueId);
		} catch (NumberFormatException | UnsupportedDataTypeException | WorkPlanDAOException | DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(activity);
		
		
		
		
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
		
		
		model.addAttribute("oldIssue",activity);
		model.addAttribute("issue",new Activity());
		return "updateIssue";
	}
	
	@RequestMapping(value = "/CreateIssue", method = RequestMethod.POST)
	public String createIssueInDb(Model model,
			@ModelAttribute("user") User user,@ModelAttribute("project") Project project,	
			@ModelAttribute ("issue") Activity issue) {
		
		try {
			project=IProjectDAO.getDAO("db").incrementIssuecount(project);
		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(project);
		
		issue.setIssueKey(project.getKey()+"-"+project.getIssueCount());
		issue.setProjectID(project.getId());
		
		try {
			IActivityDAO.getDAO("db").addActivity(issue);
		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	
		return "redirect:ProjectBoard";
	}

}
