package com.example.controller.oldServlets;

import com.example.model.DAO.ICommentDAO;
import com.example.model.POJO.Activity;
import com.example.model.POJO.Comment;
import com.example.model.POJO.User;
import com.example.model.exception.DBException;
import com.example.model.exception.WorkPlanDAOException;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/IssueCommentsPage.jsp");
		dispatcher.forward(request, response);
//		response.sendRedirect("./WEB-INF/views/jsp/IssueCommentsPage.jsp");

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
