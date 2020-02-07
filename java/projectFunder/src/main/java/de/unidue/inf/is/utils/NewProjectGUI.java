package de.unidue.inf.is.utils;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.app.Application;
import de.unidue.inf.is.domain.Project;

/**
 * Servlet implementation class NewProject
 */
@WebServlet("/new_project")
public class NewProjectGUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewProjectGUI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String sessionEmail = request.getParameter("sessionEmail");
		System.out.println(">NewProjectGUI(get): sessionEmail:" + sessionEmail);
		request.setAttribute("sessionEmail", sessionEmail);
		request.setAttribute("pagetitle", "New Project");

		
		
		
		//ToDo
		// instead of createdProjects, make a query to return all the projects
		// made by this user(project.creator)
		ArrayList<Project> createdProjects = Application.getInstance().queryAllUserProjects(sessionEmail);
//		Project project2 = new Project(2, "title2", "des2", "status2", 3.1, "email@gmail.com", 1, 3, 2.1,"name2","/projectFunder/src/main/icons/tech.png"); 
//		Project project3 = new Project(3, "title3", "des3", "status3", 3.1, "email@gmail.com", 2, 3, 2.1,"name3","/projectFunder/src/main/icons/art.png"); 
//		createdProjects.add(project2);
//		createdProjects.add(project3);
		
		
		
		request.setAttribute("createdProjects", createdProjects);
		request.getRequestDispatcher("/new_project.ftl").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// ToDo
		// instead of project make a query to return the project 
		// with id from Integer.parseInt(request.getParameter("id"))
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		if(action.equals("newProject")) {
			String sessionEmail = request.getParameter("sessionEmail");
			
			
			String title = (String) request.getParameter("title");
			String description = (String) request.getParameter("description");
			String fundingLimit = (String) request.getParameter("fundingLimit");
			String parent = (String) request.getParameter("parent");
			String category = "";
			String icon="";
			
			System.out.println(request.getParameter("category"));
			
			if(request.getParameter("category").equals("Tech & Innovation")) {
				icon = "icons/tech.png";
				category = "4";
			}
					
			else if(request.getParameter("category").equals("Health & Wellness")) {
				icon = "icons/health.png";
				category = "1";
			}
			
			else if(request.getParameter("category").equals("Education")) {
				icon = "icons/education.png"; 
				category = "3";
			}
			
			else if(request.getParameter("category").equals("Art & Creative Works")){
				icon = "icons/art.png";
				category = "2";
			}
			
			Application.getInstance().addProject(title, description, "opened", fundingLimit, sessionEmail, parent, category);
			
			System.out.println(">NewProjectGUI(post)> sessionEmail: " + request.getParameter("sessionEmail"));
			System.out.println(">NewProjectGUI(post)> Title: " + title + " Description: "+ description+ " fundingLimit: "
			+ fundingLimit+ " parent: " + parent + " Category: " + category + " Icon: " + icon);
			
			request.setAttribute("sessionEmail", (String) request.getParameter("email"));
			request.setAttribute("pagetitle", "View Main");
			request.getRequestDispatcher("/view_main").forward(request, response);
		}
		else{
			doGet(request, response);
		}
	}

}
