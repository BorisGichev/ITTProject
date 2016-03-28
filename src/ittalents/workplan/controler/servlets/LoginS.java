package ittalents.workplan.controler.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;

/**
 * Servlet implementation class LoginS
 */
@WebServlet("/LoginS")
public class LoginS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginS() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");

		response.getWriter().println(username);
		response.getWriter().println(email);

		User user = new User();

		user.setEmail(email);
		user.setUsername(username);
		user.setAdmin(0);
		
		
		try {
			user=IUserDAO.getDAO("db").getUserByEmail(email);
		} catch (DBException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("user", user);
//		response.getWriter().println(user);
		response.sendRedirect("./login.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
