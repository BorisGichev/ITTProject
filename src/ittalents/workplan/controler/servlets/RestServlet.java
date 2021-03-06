package com.example.controller.oldServlets;

import io.undertow.attribute.QueryStringAttribute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.DAO.IActivityDAO;
import com.example.model.POJO.Activity;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class RestServlet
 */
@WebServlet("/RestServlet")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {

			String json = new BufferedReader(new InputStreamReader(
					request.getInputStream())).readLine();
			System.out.println(json);
			Set<Activity> listWithSerachedActivities = new TreeSet<Activity>((
					a1, a2) -> a1.getId() - a2.getId());
			listWithSerachedActivities.addAll(IActivityDAO.getDAO("db")
					.getAllActivitiesContainingComment(json, 23));
			response.getWriter().append(
					new Gson().toJson(listWithSerachedActivities));
			// System.out.println(new Gson().toJson(((IActivityDAO.getDAO("db")
			// .getAllActivitiesContainingComment(json, 23)))));
			// System.out.println(new Gson().toJson(((IActivityDAO.getDAO("db")
			// .getActivitiesByAssigneeName(json, 23)))));
		} catch (DBException | WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
