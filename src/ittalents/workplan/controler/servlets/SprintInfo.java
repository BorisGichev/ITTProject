package ittalents.workplan.controler.servlets;

import ittalents.workplan.model.DAO.ActivityStatus;
import ittalents.workplan.model.DAO.IActivityDAO;
import ittalents.workplan.model.DAO.ISprintDAO;
import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.POJO.Sprint;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sprint
 */
@WebServlet("/SprintInfo")
public class SprintInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SprintInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Sprint sprint;
		try {
			System.out.println(Integer.parseInt(request.getParameter("id")));
			sprint = ISprintDAO.getDAO("db").getSprintById(
					Integer.parseInt(request.getParameter("id")));
			request.getSession().setAttribute("sprint", sprint);

			if (request.getParameter("activityID") != null) {
				// when we add activity to sprint(pressing add to sprint button)
				IActivityDAO.getDAO("db").setSprint(
						Integer.parseInt(request.getParameter("activityID")),
						sprint.getId());
			}
			List<Activity> activitiesNotInSprint = IActivityDAO.getDAO("db")
					.getActivitiesNotInSprint(
							(Integer) request.getSession().getAttribute(
									"projectID"));

			request.getSession().setAttribute("activitiesNotInSprint",
					activitiesNotInSprint);

//			List<Activity> listWithActivitiesInThisSprint = IActivityDAO
//					.getDAO("db").getAllActivitiesBySprintID(sprint.getId());
//			request.getSession().setAttribute("listWithActivitiesInThisSprint",
//					listWithActivitiesInThisSprint);

			List<Activity> listWithActivitiesToDoInSprint = IActivityDAO
					.getDAO("db").getAllActivitiesWithStatus(
							ActivityStatus.ToDo, sprint.getId());
			request.getSession().setAttribute("listWithActivitiesToDoInSprint",
					listWithActivitiesToDoInSprint);

			List<Activity> listWithActivitiesInProgressInSprint = IActivityDAO
					.getDAO("db").getAllActivitiesWithStatus(
							ActivityStatus.InProgress, sprint.getId());
			request.getSession().setAttribute(
					"listWithActivitiesInProgressInSprint",
					listWithActivitiesInProgressInSprint);

			List<Activity> listWithActivitiesDoneInSprint = IActivityDAO
					.getDAO("db").getAllActivitiesWithStatus(
							ActivityStatus.Done, sprint.getId());
			request.getSession().setAttribute("listWithActivitiesDoneInSprint",
					listWithActivitiesDoneInSprint);

			String isActive = request.getParameter("active");
			if (isActive != null && isActive.equalsIgnoreCase("true")) {
				// when we press Start Sprint
				ISprintDAO.getDAO("db").updateSprintActivity(true,
						sprint.getId());
				request.getSession().setAttribute("activeSprint",
						sprint.getId());
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("./createsprint.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			ISprintDAO.getDAO("db").updateSprintActivity(
					false,
					((Sprint) request.getSession().getAttribute("sprint"))
							.getId());
			request.getSession().setAttribute("activeSprint", null);
		} catch (WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("./createsprint.jsp").forward(request,
				response);
	}
}
