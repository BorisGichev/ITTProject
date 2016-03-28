package ittalents.workplan.controler.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ittalents.workplan.model.DAO.INFO;
import ittalents.workplan.model.DAO.IOrganizationDAO;
import ittalents.workplan.model.DAO.IUserDAO;
import ittalents.workplan.model.POJO.Organization;
import ittalents.workplan.model.POJO.User;
import ittalents.workplan.model.exception.DBException;
import ittalents.workplan.model.exception.WorkPlanDAOException;

/**
 * Servlet implementation class MoreDetails
 */
@WebServlet("/MoreDetailsS")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class MoreDetailsS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoreDetailsS() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		
		response.getWriter().println(user);

		if (user.getAdmin() == 1) {
			String orgName = (request.getParameter("orgName"));

			try {
				user.setId(IUserDAO.getDAO("db").addUser(user));
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WorkPlanDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File fileSaveDir = new File(INFO.IMAGES_PATH + File.separator + user.getId());

			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			for (Part part : request.getParts()) {
				part.write(fileSaveDir + File.separator + "avatar.jpg");
			}

			user.setAvatarPath(fileSaveDir + File.separator + "avatar.jpg");

			try {
				IUserDAO.getDAO("db").updateUser(user);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WorkPlanDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Organization org = new Organization();
			org.setAdminID(user.getId());
			org.setName(orgName);

			try {
				org.setId(IOrganizationDAO.getDAO("db").addOrganization(org));
			} catch (WorkPlanDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// response.getWriter().println(org);

			user.setOrganizationId(org.getId());

			try {
				IUserDAO.getDAO("db").updateOrgId(user, org.getId());
			} catch (DBException | WorkPlanDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// response.getWriter().println(user);
			getServletContext().getRequestDispatcher("/homeTrue.jsp").forward(request, response);

		}
		if (user.getAdmin() == 0) {

			File fileSaveDir = new File(INFO.IMAGES_PATH + File.separator + user.getId());

			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			for (Part part : request.getParts()) {
				part.write(fileSaveDir + File.separator + "avatar.jpg");
			}

			user.setAvatarPath(fileSaveDir + File.separator + "avatar.jpg");

			try {
				IUserDAO.getDAO("db").updateUser(user);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WorkPlanDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// response.getWriter().println(org);

			// response.getWriter().println(user);
			getServletContext().getRequestDispatcher("/homeTrue.jsp").forward(request, response);

		}

	}

}
