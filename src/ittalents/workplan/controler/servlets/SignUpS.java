package ittalents.workplan.controler.servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignUpS")
public class SignUpS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpS() {
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
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String reppassword = request.getParameter("repPassword");
		
		
		response.getWriter().println(username + ":" + email + ":" + password + ":" + reppassword);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./index.jsp");
		
		if (username.trim().length()<5) {
			request.setAttribute("errorMessage", "Username must me at least 5 symbols");
			dispatcher.forward(request, response);
		}
		
		if (username.contains(" ")) {
			request.setAttribute("errorMessage", "No spaces in username allowed");
			dispatcher.forward(request, response);
			return;
		}
		

		if (!isMailValid(email)) {
			request.setAttribute("errorMessage", "Invalid e-mail! Try Again");
			dispatcher.forward(request, response);
		}

		if (!password.equals(reppassword)) {
			request.setAttribute("errorMessage", "Passwords do no match please use the button!");
			dispatcher.forward(request, response);
		}
		if (!isPaswordStrong(password)) {
			request.setAttribute("errorMessage", "Password must contain 5 symbols and at least one number and leter");
			dispatcher.forward(request, response);
		}
		
		try {
			if (IUserDAO.getDAO("db").isThereSuchAUser(email)) {
				request.setAttribute("errorMessage", "User with such mail exists !!!");
				dispatcher.forward(request, response);
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (username.contains(" ")) {
			request.setAttribute("errorMessage", "No spaces in username allowed");
			dispatcher.forward(request, response);
			return;
		}
		
		User user = new User();
		
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);
		user.setAdmin(1);
		
		request.getSession().setAttribute("user", user);
		response.sendRedirect("./moreDetails.jsp");
		
		
		
		

	}
	
	
	public static boolean isPaswordStrong(String pass){
		boolean letter=false;
		boolean number=false;
		
		for (int i = 0; i < pass.length(); i++) {
			if (letter==false&&pass.charAt(i)>='A'&&pass.charAt(i)<='z') {
				letter=true;
			}
			if (number==false&&pass.charAt(i)>='0'&&pass.charAt(i)<='9') {
				number=true;
			}
			
			if (number&&letter&&pass.length()>4) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isMailValid(String mail){
		
			final Pattern pat = Pattern.compile(
					"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
		
		return pat.matcher(mail).matches();
	}

}
