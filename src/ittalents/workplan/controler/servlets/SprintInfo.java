package ittalents.workplan.controler.servlets;

import ittalents.workplan.model.DAO.ISprintDAO;
import ittalents.workplan.model.POJO.Sprint;
import ittalents.workplan.model.exception.DBException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sprint
 */
@WebServlet("/SprintInfo")
public class SprintInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SprintInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Sprint sprint;
		try {
			sprint = ISprintDAO.getDAO("db").getSprintById(
					Integer.parseInt(request.getParameter("id")));
			request.getSession().setAttribute("sprint", sprint);
		} catch (NumberFormatException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("./createsprint.jsp").forward(request,
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
