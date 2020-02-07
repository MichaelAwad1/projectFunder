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
import de.unidue.inf.is.domain.User;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/edit_project")
public class EditGUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditGUI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
	
		request.setAttribute("id", request.getParameter("id"));
		String sessionEmail = request.getParameter("sessionEmail");
		String id = request.getParameter("id");
		
		System.out.println(">EditGUI(get)> : id: " + request.getParameter("id"));
		System.out.println(">EditGUI(get)> : sessionEmail: " + request.getParameter("sessionEmail"));
		
		// ToDo
		// instead of project make a query to return the project 
		// with id from Integer.parseInt(request.getParameter("id"))
		Project project = Application.getInstance().queryParentProject(id);
			
		System.out.println(">EditGUI(get)> : project.category: " + project.getCategory());
		
		//ToDo
		// instead of createdProjects, make a query to return all the projects
		// made by this user(project.creator)
		ArrayList<Project> createdProjects = Application.getInstance().queryAllUserProjects(sessionEmail);
		
		
		for(int i=0;i<createdProjects.size();i++) {
			if(createdProjects.get(i).getProjectID() == Integer.parseInt(id))
				createdProjects.remove(createdProjects.get(i));
		}
//		if(createdProjects.size() != 0) {
//		for(Project p: createdProjects) {
//			if(p.getProjectID() == Integer.parseInt(id))
//				createdProjects.remove(p);
//		}
//		}
//		Project project2 = new Project(2, "title2", "des2", "status2", 200, "email@gmail.com", 1, 3, 2.1,"name2","/projectFunder/src/main/icons/tech.png"); 
//		Project project3 = new Project(3, "title2", "des2", "status2", 300.50, "email@gmail.com", 1, 3, 2.1,"name2","/projectFunder/src/main/icons/tech.png"); 
//		createdProjects.add(project2);
//		createdProjects.add(project3);
//		
		request.setAttribute("sessionEmail", sessionEmail);
		request.setAttribute("id", id);
		request.setAttribute("project", project);
		request.setAttribute("createdProjects", createdProjects);
		
		request.setAttribute("pagetitle", "Edit Project");
		request.getRequestDispatcher("/edit_project.ftl").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		
		if(action.equals("editProject")){
			
			
			
			String sessionEmail = request.getParameter("sessionEmail");
			String id = request.getParameter("id");
			
			System.out.println(">EditGUI(post)> id: " + id);
			System.out.println(">EditGUI(post)> sessionEmail: " + sessionEmail);
			
			
			String title = (String) request.getParameter("title");
			String description = (String) request.getParameter("description");
			String fundingLimit = (String) request.getParameter("fundingLimit");
			String parent = (String) request.getParameter("parent");
			String category = (String) request.getParameter("category");
			
			System.out.println(">EditGUI(post)> Title: " + title + " Description: "+ description+ " fundingLimit: "
			+ fundingLimit+ " parent: " + parent + " Category: " + category);
			
			//ToDo
			// Add in the database 
			Application.getInstance().updateProject(id, title, description, "opened", fundingLimit, sessionEmail
					, parent , category);
			
			request.setAttribute("sessionEmail", sessionEmail);
			request.setAttribute("id", id);
			request.setAttribute("pagetitle", "Main");
			request.getRequestDispatcher("/view_project").forward(request, response);
		}else {
			doGet(request,response);
		}
	}

}
