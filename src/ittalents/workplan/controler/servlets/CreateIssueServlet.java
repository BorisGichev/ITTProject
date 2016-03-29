package ittalents.workplan.controler.servlets;

import ittalents.workplan.model.DAO.IActivityDAO;
import ittalents.workplan.model.DAO.IProjectDAO;
import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.POJO.Project;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

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
			response.sendRedirect("./normalLogin.jsp");
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
		request.getRequestDispatcher("./createissue.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		// to repair that "To do" in costructor
		Activity activity = new Activity(req.getParameter("summary"),
				user.getId(), req.getParameter("issueType"), "To do", (int) req
						.getSession().getAttribute("projectID"));
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
