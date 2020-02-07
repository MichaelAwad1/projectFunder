package de.unidue.inf.is.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.app.Application;
import de.unidue.inf.is.domain.Account;
import de.unidue.inf.is.domain.User;

/**
 * Servlet implementation class LoginGUI
 */
@WebServlet("/Login")
public class LoginGUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginGUI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("pagetitle", "Login");
		try {
//			Account acc = new Account("email1", 5, "1234");
//			User user= new User("email1","name1","des1",acc);
//			User user2= new User("email2","name2","des2",acc);
//			User user3= new User("email3","name3","des3",acc);
//			User user4= new User("email4","name4","des4",acc);
//			User user5= new User("email5","name5","des5",acc);
//			User user6= new User("email6","name6","des6",acc);
//			
//			ArrayList <User> allUsers = new ArrayList<User>();
//			allUsers.add(user);
//			allUsers.add(user2);
//			allUsers.add(user3);
//			allUsers.add(user4);
//			allUsers.add(user5);
//			allUsers.add(user6);
			ArrayList <User> allUsers = Application.getInstance().queryAllUsers();
			request.setAttribute("allUsers", allUsers);
			request.setAttribute("sessionEmail", " ");
			request.getRequestDispatcher("/login.ftl").forward(
					request, response);
		} catch (ServletException | IOException e) {
			request.setAttribute("errormessage",
					"Template error: please contact the administrator");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		if(request.getParameter("action").equals("tryLogin"))
		{
			String email=request.getParameter("loginEmail");  
		    String name=request.getParameter("loginName"); 
		    
			//System.out.println("LOginGUI email is:" + email);
		    // TODO instead of status, make a query to check if email and login are the same
		    boolean status = true;
		    if(status) {
		    	request.setAttribute("sessionEmail", email);
		    	request.setAttribute("name", name);
		    //	System.out.println(">LoginGui: " +"email :"+ email);
		    //	System.out.println(">LoginGui: " +"name :"+ name);
		    	request.setAttribute("pagetitle", "Main");
		    	
		    	request.getRequestDispatcher("/view_main").forward(request, response);
		    }
		    else {
		    	RequestDispatcher rd=request.getRequestDispatcher("/login.ftl");  
		        rd.include(request,response);
		    }
		}else
		doGet(request, response);
		}
}

