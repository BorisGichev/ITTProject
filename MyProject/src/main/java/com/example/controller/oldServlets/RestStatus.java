package com.example.controller.oldServlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.DAO.IActivityDAO;
import com.example.model.POJO.Activity;
import com.example.model.POJO.ActivityStatus;
import com.example.model.POJO.Project;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;
import com.google.gson.Gson;

/**
 * Servlet implementation class RestStatus
 */
@WebServlet("/RestStatus")
public class RestStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {

			String json = new BufferedReader(new InputStreamReader(
					request.getInputStream())).readLine();
			// System.out.println(json);
			Set<Activity> listWithSerachedActivities = new TreeSet<Activity>((
					a1, a2) -> a1.getId() - a2.getId());
			if (json != null) {
				listWithSerachedActivities.addAll(IActivityDAO.getDAO("db")
						.getAllActivitiesWithStatusInProject(
								ActivityStatus.valueOf(json), ((Project)(request.getSession().getAttribute("project"))).getId()));
			}
			response.getWriter().append(
					new Gson().toJson(listWithSerachedActivities));
		} catch (DBException | WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
