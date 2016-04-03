package com.example.controller.oldServlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.DAO.IUserDAO;
import com.example.model.POJO.User;

@WebServlet("/ManageUsersS")
public class ManageUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManageUsersServlet() {
		super();

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession(false) == null) {
			response.sendRedirect("./");
			return;
		}
		
		User user = (User) request.getSession().getAttribute("user");
		List<User> usersByOrg=null;
		
		try {
			usersByOrg = IUserDAO.getDAO("db").getAllUsersForOrganization(user.getOrganizationId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getSession().setAttribute("usersForOrg", usersByOrg);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/userManagmentPage.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet (request,response);
		
	
	}

}
