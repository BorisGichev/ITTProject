package ittalents.workplan.controler.servlets;

import ittalents.workplan.model.DAO.ActivityStatus;
import ittalents.workplan.model.DAO.IActivityDAO;
import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		Activity activity = ((Activity) request.getSession().getAttribute(
				"activity"));
		try {
			IActivityDAO.getDAO("db").updateStatus(
					ActivityStatus.valueOf(status), activity.getId());
			Activity updatedActivity = IActivityDAO.getDAO("db")
					.getActivityByID(activity.getId());
			request.getSession().setAttribute("activity", updatedActivity);

		} catch (DBException | WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./IssueAll.jsp");
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
