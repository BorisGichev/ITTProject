package ittalents.workplan.controler.servlets;

import ittalents.workplan.model.DAO.IActivityDAO;
import ittalents.workplan.model.DAO.ISprintDAO;
import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.POJO.Sprint;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateSprint
 */
@WebServlet("/CreateSprint")
public class CreateSprint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateSprint() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Sprint sprint = new Sprint();
		User user = (User) request.getSession().getAttribute("user");
		sprint.setName(request.getParameter("name"));
		sprint.setProjectId((Integer) request.getSession().getAttribute(
				"projectID"));
		try {
			Sprint sprintWithId = ISprintDAO.getDAO("db").getSprintById(
					ISprintDAO.getDAO("db").addSprint(sprint));
			request.getSession().setAttribute("sprint", sprintWithId);
		} catch (WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("./createsprint.jsp").forward(request,
				response);
		;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			IActivityDAO.getDAO("db").setSprint(
					Integer.parseInt(request.getParameter("activityID")),
					((Sprint) request.getSession().getAttribute("sprint"))
							.getId());

			// ot tuk povtarqm kod
			List<Activity> activitiesNotInSprint = IActivityDAO.getDAO("db")
					.getActivitiesNotInSprint(
							(Integer) request.getSession().getAttribute(
									"projectID"));

			request.getSession().setAttribute("activitiesNotInSprint",
					activitiesNotInSprint);

		} catch (WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		request.getRequestDispatcher("./createsprint.jsp").forward(request,
				response);
	}
}
