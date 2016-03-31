package ittalents.workplan.controler.servlets;

import ittalents.workplan.model.DAO.IActivityDAO;
import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Issue
 */
@WebServlet("/Issue")
public class Issue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Issue() {
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
			Activity activity = IActivityDAO.getDAO("db").getActivityByID(
					Integer.parseInt(request.getParameter("id")));
			request.getSession().setAttribute("activity", activity);
			if(activity.getAssigneeID()>0){
			request.getSession()
					.setAttribute(
							"assignee",
							IUserDAO.getDAO("db").getUserById(
									activity.getAssigneeID()).getFullName());}
			request.getSession()
					.setAttribute(
							"reporter",
							IUserDAO.getDAO("db").getUserById(
									activity.getReportedID()).getFullName());
			System.out.println(IUserDAO.getDAO("db").getUserById(
									activity.getReportedID()).getFullName());
		} catch (WorkPlanDAOException | DBException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("./IssueAll.jsp").forward(request,
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
