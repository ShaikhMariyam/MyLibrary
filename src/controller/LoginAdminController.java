package controller;

import model.Librarian;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import DB.DBconnect;

@WebServlet("/LoginAdminController")
public class LoginAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginAdminController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Librarian l = new Librarian();
        HttpSession session=request.getSession();
        try {
        	PrintWriter out = response.getWriter();
            String email=request.getParameter("email");
            String pass=request.getParameter("pass");

            l.setEmail(email);;
            l.setPass(pass);
                out.println("crossed login");
                int result = 0;
                try{
                    result = l.Alogin();
                }catch(Exception ex){
                        response.sendRedirect("ErrorLoginA.jsp");
                }
                
                switch (result) {
                case 1:
        	            Connection conn=DBconnect.getConn();
        	            String sql="SELECT * FROM librarian WHERE email='"+email+"'";
        	
					PreparedStatement ST;
					try {
						ST = conn.prepareStatement(sql);
        	        	ResultSet RS= ST.executeQuery();
        	        	while(RS.next()){
        	                    String id =RS.getString("librarian_ID");
                                session.setAttribute("AID", id);
        	                }	
					} catch (SQLException e) {
						e.printStackTrace();
					}


         	       RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard.jsp");
        	       dispatcher.forward(request, response);
                    session.setAttribute("email", email);
             
                    break;
                case 2:
                    response.sendRedirect("ErrorLoginA.jsp");
                    break;
}}finally {}
}


}