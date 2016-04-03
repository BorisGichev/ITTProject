package com.example.controller.oldServlets;

import com.example.model.DAO.ISprintDAO;
import com.example.model.POJO.Activity;
import com.example.model.POJO.Project;
import com.example.model.POJO.Sprint;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

import java.io.IOException;
import java.util.ArrayList;

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
		Project project = (Project) request.getSession().getAttribute("project");
		Sprint sprint = new Sprint();
		sprint.setName(request.getParameter("sprintName"));
		sprint.setProjectId(project.getId());
		try {
			// adding the sprint and getting getting it back from the DB with ID
			Sprint sprintWithId = ISprintDAO.getDAO("db").getSprintById(
					ISprintDAO.getDAO("db").addSprint(sprint));
			request.getSession().setAttribute("listWithActivitiesInThisSprint",
					null);
			// jstl doesnt throw nullpointer in case of iterating over null
			request.getSession().setAttribute("sprint", sprintWithId);
		} catch (WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("./WEB-INF/views/jsp/createsprint.jsp").forward(request,
				response);
		;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	// protected void doPost(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// try {
	// IActivityDAO.getDAO("db").setSprint(
	// Integer.parseInt(request.getParameter("activityID")),
	// ((Sprint) request.getSession().getAttribute("sprint"))
	// .getId());
	//
	// List<Activity> activitiesNotInSprint = IActivityDAO.getDAO("db")
	// .getActivitiesNotInSprint(
	// (Integer) request.getSession().getAttribute(
	// "projectID"));
	//
	// request.getSession().setAttribute("activitiesNotInSprint",
	// activitiesNotInSprint);
	//
	// } catch (WorkPlanDAOException | DBException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	//
	// }
	//
	// request.getRequestDispatcher("./createsprint.jsp").forward(request,
	// response);
	// }
}
