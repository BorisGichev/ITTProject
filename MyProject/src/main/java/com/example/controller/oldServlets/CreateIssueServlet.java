package com.example.controller.oldServlets;

import com.example.model.DAO.IActivityDAO;
import com.example.model.DAO.IUserDAO;
import com.example.model.POJO.Activity;
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
 * Servlet implementation class CreateIssueServlet
 */
@WebServlet("/CreateIssueServlet")
public class CreateIssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateIssueServlet() {
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
			response.sendRedirect("./normalLogin");
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
		request.getRequestDispatcher("./WEB-INF/views/jsp/createissue.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getSession(false) == null) {
			resp.sendRedirect("./normalLogin");
			return;
		}
		
		Project project = (Project) (req.getSession().getAttribute("project"));
		User user = (User) req.getSession().getAttribute("user");
		
		// to repair that "To do" in costructor
		Activity activity = new Activity(req.getParameter("summary"),
				user.getId(), "To do", req.getParameter("issueType"),
				project.getId());
		try {
			Integer activityID = IActivityDAO.getDAO("db")
					.addActivity(activity);
			// req.getSession().setAttribute("projectID", projectID);
		} catch (WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.sendRedirect("./ProjectBoard");
	}
}
