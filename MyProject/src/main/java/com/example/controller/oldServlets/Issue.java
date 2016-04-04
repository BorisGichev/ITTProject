package com.example.controller.oldServlets;

import com.example.model.DAO.IActivityDAO;
import com.example.model.DAO.IUserDAO;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Activity activity = IActivityDAO.getDAO("db").getActivityByID(Integer.parseInt(request.getParameter("id")));
			request.getSession().setAttribute("activity", activity);
			if (activity.getAssigneeID() > 0) {
				request.getSession().setAttribute("assignee",
						IUserDAO.getDAO("db").getUserById(activity.getAssigneeID()).getFullname());
			}
			request.getSession().setAttribute("reporter",
					IUserDAO.getDAO("db").getUserById(activity.getReportedID()).getFullname());
			System.out.println(IUserDAO.getDAO("db").getUserById(activity.getReportedID()).getFullname());

		} catch (WorkPlanDAOException | DBException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("./IssueAll").forward(request, response);
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
