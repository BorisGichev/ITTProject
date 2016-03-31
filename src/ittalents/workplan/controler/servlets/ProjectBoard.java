package ittalents.workplan.controler.servlets;

import ittalents.workplan.model.DAO.IActivityDAO;
import ittalents.workplan.model.DAO.ISprintDAO;
import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.POJO.Sprint;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProjectBoard
 */
@WebServlet("/ProjectBoard")
public class ProjectBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			List<Sprint> listWithSprintsForThisProject = ISprintDAO
					.getDAO("db").getAllSprintByProjectID(
							(Integer) request.getSession().getAttribute(
									"projectID"));
//			System.out.println(listWithSprintsForThisProject);
			Map<Sprint, ArrayList<Activity>> activitiesBySprint = new TreeMap<Sprint, ArrayList<Activity>>(
					(s1, s2) -> s1.getId() - s2.getId());
			for (Sprint sprint : listWithSprintsForThisProject) {
				if (!activitiesBySprint.containsKey(sprint)) {
					activitiesBySprint.put(sprint, new ArrayList<Activity>());
				}
				activitiesBySprint.get(sprint).addAll(
						IActivityDAO.getDAO("db").getAllActivitiesBySprintID(
								sprint.getId()));

			}
			request.setAttribute("activitiesBySprint", activitiesBySprint);
//			System.out.println("Map: " + activitiesBySprint);
			List<Activity> activitiesNotInSprint = IActivityDAO.getDAO("db")
					.getActivitiesNotInSprint(
							(Integer) request.getSession().getAttribute(
									"projectID"));

			request.getSession().setAttribute("activitiesNotInSprint",
					activitiesNotInSprint);
//			int activeSprint = ISprintDAO.getDAO("db")
//					.isThereAnActiveSprintInThisProject(
//							(Integer) request.getSession().getAttribute(
//									"projectID"));
//			request.getSession().setAttribute("activeSprint", activeSprint);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("./projectboard.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
