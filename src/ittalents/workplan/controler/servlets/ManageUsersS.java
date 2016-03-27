package ittalents.workplan.controler.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.POJO.User;

/**
 * Servlet implementation class ManageUsersS
 */
@WebServlet("/ManageUsersS")
public class ManageUsersS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageUsersS() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		List<User> usersByOrg=null;
		
		try {
			usersByOrg = IUserDAO.getDAO("db").getAllUsersForOrganization(user.getOrganizationId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getSession().setAttribute("usersForOrg", usersByOrg);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./userManagmentPage.jsp");
		dispatcher.forward(request, response);
	
	}

}
