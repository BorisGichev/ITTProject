package com.example.controller.oldServlets;

import com.example.model.DAO.IProjectDAO;
import com.example.model.DAO.IUserDAO;
import com.example.model.POJO.Project;
import com.example.model.POJO.User;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CreateProjectServlet
 */
@WebServlet("/CreateProjectServlet")
public class CreateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateProjectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession(false) == null) {
			response.sendRedirect("./");
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
		try {
			request.setAttribute("employeesInOrg", IUserDAO.getDAO("db")
					.getAllUsersForOrganization(user.getOrganizationId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("./WEB-INF/views/jsp/createproject.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		
		
		Project project = new Project(req.getParameter("name"),
				user.getOrganizationId(), req.getParameter("key"),
				Integer.parseInt(req.getParameter("leader")));
		try {
			Integer projectID = IProjectDAO.getDAO("db").addProject(project);
			project.setId(projectID);
			req.getSession().setAttribute("project", project);
		} catch (WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("./ProjectBoard");
	}
}
