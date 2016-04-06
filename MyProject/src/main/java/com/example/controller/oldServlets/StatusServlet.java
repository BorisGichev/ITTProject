package com.example.controller.oldServlets;

import com.example.model.POJO.ActivityStatus;
import com.example.model.DAO.IActivityDAO;
import com.example.model.POJO.Activity;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet("/StatusServlet")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatusServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImageServletFromId.isLogged(request, response);
		String status = request.getParameter("status");
		Activity activity = ((Activity) request.getSession().getAttribute("activity"));
		try {
			IActivityDAO.getDAO("db").updateStatus(ActivityStatus.valueOf(status), activity.getId());
			Activity updatedActivity = IActivityDAO.getDAO("db").getActivityByID(activity.getId());
			request.getSession().setAttribute("activity", updatedActivity);

		} catch (DBException | WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./Issue?id=" + activity.getId());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
