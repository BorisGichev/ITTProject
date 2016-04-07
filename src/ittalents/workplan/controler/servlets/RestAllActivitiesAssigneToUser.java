package com.example.controller.oldServlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

import javax.activation.UnsupportedDataTypeException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.DAO.IActivityDAO;
import com.example.model.POJO.Activity;
import com.example.model.POJO.Project;
import com.example.model.POJO.User;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;
import com.google.gson.Gson;

/**
 * Servlet implementation class RestAllActivitiesAssigneToUser
 */
@WebServlet("/RestAllActivitiesAssigneToUser")
public class RestAllActivitiesAssigneToUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {
			Set<Activity> listWithSerachedActivities = new TreeSet<Activity>((
					a1, a2) -> a1.getId() - a2.getId());
			try {
				listWithSerachedActivities.addAll(IActivityDAO.getDAO("db")
						.getActivitiesByAssigneeIDInProject(
								((User) (request.getSession()
										.getAttribute("user"))).getId(),
								((Project) (request.getSession()
										.getAttribute("project"))).getId()));
			} catch (UnsupportedDataTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().append(
					new Gson().toJson(listWithSerachedActivities));
		} catch (DBException | IOException e) {
			e.printStackTrace();
		}

	}

}
