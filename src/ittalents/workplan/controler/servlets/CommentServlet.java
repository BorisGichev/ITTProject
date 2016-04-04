package ittalents.workplan.controler.servlets;

import ittalents.workplan.model.DAO.ICommentDAO;
import ittalents.workplan.model.POJO.Activity;
import ittalents.workplan.model.POJO.Comment;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Map<Comment, User> comments = ICommentDAO.getDAO("db")
					.getAllCommentsForActivity(
							((Activity) request.getSession().getAttribute(
									"activity")).getId());
			request.getSession().setAttribute("comments", comments);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./IssueComments.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int userID = ((User) request.getSession().getAttribute("user")).getId();
		int activityID = ((Activity) (request.getSession()
				.getAttribute("activity"))).getId();
		Comment comment = new Comment(request.getParameter("commentContent"),
				activityID, userID);
		try {
			ICommentDAO.getDAO("db").addComment(comment);
		} catch (WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
