package de.unidue.inf.is.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.app.Application;
import de.unidue.inf.is.domain.Project;

/**
 * Servlet implementation class Donate
 */
@WebServlet("/new_project_fund")
public class DonateGUI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonateGUI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		
		try {
			String id = request.getParameter("id");
			String sessionEmail = request.getParameter("sessionEmail");
			
			System.out.println(">DonateGUI(get)> id: " + id);
			System.out.println(">DonateGUI(get)> sessionEmail: " + sessionEmail);
			
			Project project = Application.getInstance().queryParentProject(id);
			Double balance =Application.getInstance().queryUserInformation(sessionEmail).getAccount().getBalance();
			request.setAttribute("id", id);
			request.setAttribute("project", project);
			request.setAttribute("balance", balance);
			request.setAttribute("sessionEmail", sessionEmail);
			request.setAttribute("pagetitle", "Donation");
			request.getRequestDispatcher("/new_project_fund.ftl").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		
		if(action.equals("newDonation")) 
		{
			String amount = request.getParameter("amount");
			String anonymous = request.getParameter("anonymous");
			String id = request.getParameter("id");
			String sessionEmail = request.getParameter("sessionEmail");
			
			String status;
			if(anonymous == null)
				status = "public";
			else
				status = "privat";
			
			if(!Application.getInstance().addSupport(sessionEmail, id, amount, status))
				
			
			System.out.println(">DonateGUI(post)> amount: " + amount);
			System.out.println(">DonateGUI(post)> anonymous: " + anonymous);
			System.out.println(">DonateGUI(post)> id: " + id);
			System.out.println(">DonateGUI(post)> sessionEmail: " + sessionEmail);
			
			//TODO check whether the amount is less than or equal to his credit in the Account.
			// if not redirect him again
			// if yes add the donation and redirect to view_project
			
			request.setAttribute("id", id);
			request.setAttribute("sessionEmail", sessionEmail);
			request.setAttribute("pagetitle", "Main");
			request.getRequestDispatcher("/view_project").forward(request, response);
	
		}else {
			doGet(request,response);
		}
	}
}

