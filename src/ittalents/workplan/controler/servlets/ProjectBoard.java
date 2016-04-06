package com.example.controller.oldServlets;

import com.example.model.DAO.IActivityDAO;
import com.example.model.DAO.ISprintDAO;
import com.example.model.POJO.Activity;
import com.example.model.POJO.Project;
import com.example.model.POJO.Sprint;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

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

		Project project = (Project) (request.getSession()
				.getAttribute("project"));

		try {
			List<Sprint> listWithSprintsForThisProject = ISprintDAO
					.getDAO("db").getAllSprintByProjectID(project.getId());
			// System.out.println(listWithSprintsForThisProject);

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
			// System.out.println("Map: " + activitiesBySprint);
			List<Activity> activitiesNotInSprint = IActivityDAO.getDAO("db")
					.getActivitiesNotInSprint(project.getId());

			request.getSession().setAttribute("activitiesNotInSprint",
					activitiesNotInSprint);

			if (project.getId() > 0
					// when user log in new session he gets the active sprint
					// for the project.
					&& ISprintDAO
							.getDAO("db")
							.isThereAnActiveSprintInThisProject(project.getId()) > 0) {
				Sprint sprint = ISprintDAO.getDAO("db").getSprintById(
						ISprintDAO.getDAO("db")
								.isThereAnActiveSprintInThisProject(
										project.getId()));
				request.getSession().setAttribute("activeSprint", sprint.getId());
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("./WEB-INF/views/jsp/projectboard.jsp")
				.forward(request, response);

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
