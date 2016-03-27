package ittalents.workplan.controler.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ittalents.workplan.model.DAO.IOrganizationDAO;
import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

/**
 * Servlet implementation class NormalLoginS
 */
@WebServlet("/NormalLoginS")
public class NormalLoginS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NormalLoginS() {
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

		User user=null;
		try {
			user = IUserDAO.getDAO("db").getUserByEmail(email);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user != null && user.getPassword().equals(password)) {
			try {
				user.setOrganizationName(IOrganizationDAO.getDAO("db").getOrgName(user.getOrganizationId()));
			} catch (WorkPlanDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.getSession().setAttribute("user", user);		
			response.sendRedirect("./homeTrue.jsp");
		}

		else {
			request.getSession().setAttribute("errorMessage", "Wrong password or username ");
			response.sendRedirect("./normalLogin.jsp");
			return;

		}

	}

}
