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
 * Servlet implementation class search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			String searchText = request.getParameter("searchText");
			String sessionEmail = request.getParameter("sessionEmail");
			
			// TODO make a query to search and return array of projects
	//		
	//		Project project1 = new Project(1, "title1", "des1", "opened", 200.1, "email1@email.com", 0, 1, 2.1,"yosry","icons/art.png"); 
	//		Project project2 = new Project(2, "title2", "des2", "closed", 300.1, "email2@email.com", 1, 2, 2.1,"Michael","icons/education.png"); 
	//		Project project3 = new Project(3, "title3", "des3", "opened", 300.1, "email3@email.com", 2, 3, 2.1,"yosry","icons/tech.png"); 
	//	
			ArrayList<Project> searchedProjects = Application.getInstance().queryProjectsByTitle(searchText);
			
			for(Project p : searchedProjects)
				System.out.println(p.toString());
			int size;
				size = searchedProjects.size();
	//		searchedProjects.add(project1);
	//		searchedProjects.add(project2);
	//		searchedProjects.add(project3);
	//		
			request.setAttribute("searchedProjects", searchedProjects);
			request.setAttribute("sessionEmail", sessionEmail);
			request.setAttribute("searchText", searchText);
			request.setAttribute("size", size);
			request.setAttribute("pagetitle", "Search Result");
			request.getRequestDispatcher("/search.ftl").forward(request, response);
		}catch(Exception e) {
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
