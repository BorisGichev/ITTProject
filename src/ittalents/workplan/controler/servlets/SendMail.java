package ittalents.workplan.controler.servlets;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

/**
 * Servlet implementation class SendMail
 */
@WebServlet("/SendMail")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendMail() {
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

		User adminUser= (User) request.getSession().getAttribute("user");
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");

		response.getWriter().println(username);
		response.getWriter().println(email);

		RequestDispatcher dispatcher = request.getRequestDispatcher("./userManagmentPage.jsp");

		if (username.trim().length() < 5) {
			request.setAttribute("errorMessage", "Username must me at least 5 symbols");
			dispatcher.forward(request, response);
			return;
		}
		
		if (username.contains(" ")) {
			request.setAttribute("errorMessage", "No spaces in username allowed");
			dispatcher.forward(request, response);
			return;
		}

		if (!isMailValid(email)) {
			request.setAttribute("errorMessage", "Invalid e-mail! Try Again");
			dispatcher.forward(request, response);
			return;
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
		
		
		
		User user = new User();

		user.setEmail(email);
		user.setUsername(username);
		user.setAdmin(0);
		user.setOrganizationId(adminUser.getOrganizationId());
		
		
		try {
			user.setId(IUserDAO.getDAO("db").addUser(user));
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			IUserDAO.getDAO("db").updateOrgId(user, adminUser.getOrganizationId());
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		// Sending mail
		Properties props = new Properties();

		props.setProperty("mail.host", "mail.qkoa.info");
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.auth", "true");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("office@qkoa.info", "Istinataboli1@");
			}

		};

		Session session = Session.getDefaultInstance(props, auth);

		Message msg = new MimeMessage(session);
		try {
			msg.setSubject("validate account");
			// ?name=ferret
			msg.setText("please click this to validate your reg link http://localhost:8080/WorkPlan/LoginS?username="
					+ username + "&email=" + email);
			msg.setFrom(new InternetAddress("office@qkoa.info", "Boris"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("errorMessage", "E-mail send succesfully to your worker");
		dispatcher.forward(request, response);

		// End sending mail
	}

	public static boolean isMailValid(String mail) {

		final Pattern pat = Pattern.compile(
				"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");

		return pat.matcher(mail).matches();
	}

}
