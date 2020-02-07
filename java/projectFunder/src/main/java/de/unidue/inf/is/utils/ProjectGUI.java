package de.unidue.inf.is.utils;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.app.Application;
import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.domain.Support;

/**
 * Servlet implementation class test
 */
@WebServlet("/view_project")
public class ProjectGUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectGUI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
			String sessionEmail = request.getParameter("sessionEmail");
			String id = request.getParameter("id");
			
			if(action.equals("deleteProject")) {
				
				System.out.println(">ProjectGUI(post)> sessionEmail: " + sessionEmail);
				
				
				// TODO call the function to delete the projects
		    	request.setAttribute("sessionEmail", sessionEmail);
		    	
		    	// TODO return the user with email = sessionEmail
		    	String name=Application.getInstance().queryUserInformation(sessionEmail).getName();
				
		    	Application.getInstance().removeProject(id);
		    	
		    	
		    	request.setAttribute("sessionEmail", sessionEmail);
		    	request.setAttribute("name", name);
		    	request.setAttribute("pagetitle", "Main");
		    	request.getRequestDispatcher("/view_main").forward(request, response);
			}
			
			
			
			// ToDo
			// instead of project make a query to return the project 
			// with id from Integer.parseInt(request.getParameter("id"))
			
			Project project = Application.getInstance().queryParentProject(id);
			String donated = "false";
			// TODO
			// check if the user with seesionEmail has already donated.
			// and set it with the following String;
			ArrayList<Support> supports = Application.getInstance().queryAllUserSupports(sessionEmail);
			
			for(Support s : supports)
				System.out.println(s.toString());
			System.out.println("change");
			
			
			for(int i =0; i<supports.size(); i++) {
				if(supports.get(i).getProject() == Integer.parseInt(id)) {
					donated = "true";
				}
				if(supports.get(i).getDonationStatus().equalsIgnoreCase("privat")){
					supports.remove(i);
				}
			}
			for(Support s : supports)
				System.out.println(s.toString());
			
			System.out.println(">ProjectGUI(get)> donated: " + donated);
			String related;
			if(!sessionEmail.equalsIgnoreCase(project.getCreator()))
				related = "false";
			else
				related = "true";
			System.out.println(">ProjectGUI(get)> related: " + related);
			
			System.out.println(">ProjectGUI(get)> id: " + id);
			System.out.println(">ProjectGUI(get)> sessionEmail: " + sessionEmail);
			System.out.println(">ProjectGUI(get)> title: " + project.getTitle());
			
			// ToDo
			// instead of name of the creator, make a query to return the name of the creator
			String creatorName= Application.getInstance().queryUserInformation(project.getCreator()).getName();
			
			
			// ToDo
			// instead of list donators, make a query to return list of support
//			ArrayList<Support> donators = new ArrayList();
//			Support sup = new Support("email1",1, 123, "public", "Michael");
//			Support sup2 = new Support("email2",2, 321, "privat", "yosry");
//			Support sup3 = new Support("email3",3,1234,"privat","Omar");
//			donators.add(sup);
//			donators.add(sup2);
//			donators.add(sup3);
			
			ArrayList<Support> donators = Application.getInstance().queryAllProjectDonations(id);
			
			
			//ToDo
			// instead of list supporter, make a query to return list of comments
//			ArrayList<Comment> comments = new ArrayList();
//			Timestamp t1 = new Timestamp(2020,01,18,01,01,01,01);
//			Comment com1 = new Comment(4001,"Fuck DB",t1,"privat",1,"Michael");
//			Comment com2 = new Comment(4002,"Fuck SWT",t1,"public",2,"Yosry");
//			Comment com3 = new Comment(4003,"Fuck anything",t1,"privat",3,"Omar");
//			
//			comments.add(com1);
//			comments.add(com2);
//			comments.add(com3);
			ArrayList<Comment> comments = Application.getInstance().queryAllProjectComments(id);
			
			Project parentProject = Application.getInstance().queryParentProject(String.valueOf(project.getPre()));
			
			
			request.setAttribute("id", id);
			request.setAttribute("sessionEmail", sessionEmail);
			request.setAttribute("project", project);
			request.setAttribute("parentProject", parentProject);
			request.setAttribute("creatorName", creatorName);
			request.setAttribute("donators", donators);
			request.setAttribute("comments", comments);
			request.setAttribute("related", related);
			request.setAttribute("donated", donated);
			request.setAttribute("pagetitle", "Project View");
			request.getRequestDispatcher("/view_project.ftl").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		
		/*
		 * if(action.equals("deleteProject")) {
		 * 
		 * System.out.println(">ProjectGUI(post)> sessionEmail: " + sessionEmail);
		 * String sessionEmail = request.getParameter("sessionEmail"); String id =
		 * request.getParameter("id");
		 * 
		 * // TODO call the function to delete the projects
		 * request.setAttribute("sessionEmail", sessionEmail);
		 * 
		 * // TODO return the user with email = sessionEmail String
		 * name=Application.getInstance().queryUserInformation(sessionEmail).getName();
		 * 
		 * Application.getInstance().removeProject(id);
		 * 
		 * 
		 * request.setAttribute("sessionEmail", sessionEmail);
		 * request.setAttribute("name", name); request.setAttribute("pagetitle",
		 * "Main"); request.getRequestDispatcher("/view_main").forward(request,
		 * response); }
		 */		doGet(request, response);
	}

}
