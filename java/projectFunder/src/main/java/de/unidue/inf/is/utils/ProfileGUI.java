package de.unidue.inf.is.utils;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.app.Application;
import de.unidue.inf.is.domain.Account;
import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.domain.Support;
import de.unidue.inf.is.domain.User;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/view_profile")
public class ProfileGUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileGUI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String sessionEmail;
			String email;
			
			String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
			
			if(action.equals("myProfile")){
				sessionEmail= request.getParameter("sessionEmail");
				email = sessionEmail;
			}
			else {
				sessionEmail = request.getParameter("sessionEmail");
				email = request.getParameter("email");
			}
			System.out.println(">ProfileGUI(get)> sessionEmail: " + sessionEmail);
			System.out.println(">ProfileGUI(get)> email: " + email);
			
			// ToDo 
			// instead of user, make a query to return the users data
			User user = Application.getInstance().queryUserInformation(email);
			Account acc = user.getAccount();
			
			//Account acc = new Account("email@gmail.com",23.32,"2134");
			//User user = new User("email@gmail.com","yosry","nothing",acc);
			
			// ToDo
			// instead of createdProjects, make a query to return all projects
			// created by the email request.setAttribute("email", request.getParameter("email"));
			ArrayList<Project> createdProjects = Application.getInstance().queryAllUserProjects(email);
			
//			Project project = new Project(1, "title", "des", "status", 3.1, "email@gmail.com", 1, 3, 2.1,"yosry","icons/art.png"); 
//			Project project2 = new Project(2, "title2", "des2", "status2", 3.1, "email@gmail.com", 2, 3, 2.1,"yosry","icons/education.png"); 
//			Project project3 = new Project(3, "title3", "des3", "status3", 3.1, "email@gmail.com", 3, 3, 2.1,"yosry","icons/tech.png"); 
//			createdProjects.add(project);
//			createdProjects.add(project2);
//			createdProjects.add(project3);
			
			int createdCount = createdProjects.size();
			
			// ToDo
			// instead of supportedProjects make a query to return all projects
			// created by the email request.setAttribute("email", request.getParameter("email"));
			ArrayList<Project> supportedProjects = Application.getInstance().queryAllSupportedProjects(email);
			ArrayList<Support> supports = Application.getInstance().queryAllUserSupports(email);
			
			System.out.println("ProfileGUI(get)> supportedProjects: " + supportedProjects.size());
			System.out.println("ProfileGUI(get)> supports: " + supports.size());
			
//			Project project11 = new Project(1, "title", "des", "status", 3.1, "email@gmail.com", 1, 3, 2.1,"yosry","icons/art.png"); 
//			Project project22 = new Project(2, "title2", "des2", "status2", 3.1, "email@gmail.com", 2, 3, 2.1,"yosry","icons/education.png"); 
//			Project project33 = new Project(3, "title3", "des3", "status3", 3.1, "email@gmail.com", 3, 3, 2.1,"yosry","icons/tech.png"); 
//			supportedProjects.add(project);
//			supportedProjects.add(project2);
//			supportedProjects.add(project3);
//			
			ArrayList<Integer> counter = new ArrayList<Integer>();
			for(int i = 0 ;i < supportedProjects.size();i++)
				counter.add(i);
			
			int supportedCount = supportedProjects.size();
			request.setAttribute("sessionEmail", sessionEmail);
			request.setAttribute("email", email);
			request.setAttribute("supports", supports);
			request.setAttribute("user", user);
			request.setAttribute("createdCount", createdCount);
			request.setAttribute("supportedCount", supportedCount);
			request.setAttribute("counter", counter);
			request.setAttribute("createdProjects", createdProjects);
			request.setAttribute("supportedProjects", supportedProjects);
			request.setAttribute("pagetitle", "Profile View");
			request.getRequestDispatcher("/view_profile.ftl").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


