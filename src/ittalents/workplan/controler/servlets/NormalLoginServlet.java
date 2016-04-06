package com.example.controller.oldServlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.DAO.IOrganizationDAO;
import com.example.model.DAO.IProjectDAO;
import com.example.model.DAO.IUserDAO;
import com.example.model.POJO.Organization;
import com.example.model.POJO.Project;
import com.example.model.POJO.User;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

/**
 * Servlet implementation class NormalLoginS
 */
@WebServlet("/NormalLoginS")
public class NormalLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NormalLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = null;
		Organization org = null;
		Project project = null;

		try {
			user = IUserDAO.getDAO("db").getUserByEmail(email);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user != null && user.getPassword().equals(password)) {
			try {
				org = IOrganizationDAO.getDAO("db").getOrgByID(user.getOrganizationId());

			} catch (WorkPlanDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				request.getSession().setAttribute("projects", IProjectDAO.getDAO("db").getAllProjectsByOrg(org.getId()));
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("organization", org);
			request.getSession().setAttribute("user", user);
			
			if (user.getLastProjectId()!=0) {
				try {
					project=IProjectDAO.getDAO("db").getProjectById(user.getLastProjectId());
				} catch (WorkPlanDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().setAttribute("project", project);
		
				response.sendRedirect("./ProjectBoard");
				
//				RequestDispatcher dispatcher = request.getRequestDispatcher("./ProjectBoard");
//				dispatcher.forward(request, response);
				return;
				
			}
			
			

			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/homeTrue.jsp");
			dispatcher.forward(request, response);
		}

		else {
			request.setAttribute("errorMessage", "Wrong password or username ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/normalLogin.jsp");
			dispatcher.forward(request, response);
			return;

		}

	}

}
