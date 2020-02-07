package de.unidue.inf.is.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.app.Application;
import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.domain.Project;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/view_comment")
public class CommentGUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentGUI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
	
		String id =  request.getParameter("id");
		String sessionEmail = request.getParameter("sessionEmail");
		
		// ToDo
		// instead of project make a query to return the project 
		// with id from Integer.parseInt(request.getParameter("id"))
		
		Project project = Application.getInstance().queryParentProject(id);

		System.out.println(">CommentGUI(get)> id: " + id);
		System.out.println(">CommentGUI(get)> sessionEmail: " + sessionEmail);
		
		request.setAttribute("id", id);
		request.setAttribute("sessionEmail", sessionEmail);
		request.setAttribute("project", project);
		request.setAttribute("pagetitle", "Comment");
		request.getRequestDispatcher("/new_comment.ftl").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Add in the database 
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		if(action.equals("newComment")) {
			String sessionEmail = request.getParameter("sessionEmail");
			String id = request.getParameter("id");
			
			//String email = request.getParameter("projectCreator");
			String comment = request.getParameter("comment");
			String anonymous = request.getParameter("anonymous");
			String status;
			System.out.println(">CommentGUI>(post) anonymous: " + anonymous);
			
			if(anonymous == null)
				status = "public";
			else
				status = "privat";
			System.out.println(">CommentGUI(post)> Comment: " + comment);
			System.out.println(">CommentGUI(post)> anonymous: " + anonymous);
			//System.out.println(">CommentGUI(post)> email: " + email);
			
			Application.getInstance().addComment(comment, status, id, sessionEmail);
			
			request.setAttribute("pagetitle", "Main");
			request.setAttribute("sessionEmail", sessionEmail);
			//request.setAttribute("email", email);
			request.setAttribute("id", id);
			request.getRequestDispatcher("/view_project").forward(request, response);
		}else {
			doGet(request,response);
		}
	}

}
