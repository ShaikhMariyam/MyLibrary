package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Librarian;

/**
 * Servlet implementation class adminController
 */
@WebServlet("/adminController")
public class adminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        Librarian l = new Librarian();
        HttpSession session=request.getSession();
        try {
        PrintWriter out = response.getWriter();
        String firstname = request.getParameter("fullname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String pass = request.getParameter("password");
		String confirmpass = request.getParameter("check");
		if(!pass.equals(confirmpass)){
		    request.setAttribute("errorMessage", "Please match both the passwords.");
		    request.getRequestDispatcher("StudentRegistration.jsp").forward(request, response);
		}else{
    		try {
                    l = new Librarian(firstname, lastname, email, phone, pass);
                    l.register();
                    request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
                } catch (SQLException e) {
                    System.out.println(e.getMessage()); 
                    e.printStackTrace(); 
		request.getRequestDispatcher("ErrorLogin.jsp").forward(request, response);
                    }
		}
	

        }finally{}
	}

}