package com.example.controller.newSpring;

import javax.activation.UnsupportedDataTypeException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.DAO.IOrganizationDAO;
import com.example.model.DAO.IProjectDAO;
import com.example.model.DAO.IUserDAO;
import com.example.model.POJO.Organization;
import com.example.model.POJO.User;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

@SessionAttributes({ "user", "organization", "projects" })
@Controller
public class WorkerLoginController {
	@RequestMapping(value = "/WorkerLogin", method = RequestMethod.GET)
	public String goToIndex(Model model, @RequestParam("username") String username,
			@RequestParam("email") String email) {

		System.out.println(username);
		System.out.println(email);

		User user = new User();

		user.setEmail(email);
		user.setUsername(username);
		user.setAdmin(0);

		try {
			user = IUserDAO.getDAO("db").getUserByEmail(email);
		} catch (DBException | UnsupportedDataTypeException e) {
			e.printStackTrace();
		}

		if (user.getPassword() != null && user.getUsername() != null) {

		}

		Organization org = null;

		try {
			org = IOrganizationDAO.getDAO("db").getOrgByID(user.getOrganizationId());
		} catch (WorkPlanDAOException | DBException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			model.addAttribute("projects", IProjectDAO.getDAO("db").getAllProjectsByOrg(org.getId()));
		} catch (DBException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("user", user);
		model.addAttribute("organization", org);
		if (user.getPassword() != null && user.getUsername() != null) {
			return "forward:./normalLogin";
		}
		return "index";

	}
}
