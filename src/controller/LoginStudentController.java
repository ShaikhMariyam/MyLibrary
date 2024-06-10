package controller;

import model.Student;

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

@WebServlet("/LoginStudentController")
public class LoginStudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginStudentController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student s = new Student();
        HttpSession session=request.getSession();
        try {
        	PrintWriter out = response.getWriter();
            String email=request.getParameter("email");
            String pass=request.getParameter("pass");

            s.setEmail(email);;
            s.setPassword(pass);
                out.println("crossed login");
                int result = 0;
                try{
                    result = s.login();
                }catch(Exception ex){
                        response.sendRedirect("ErrorLogin.jsp");
                }
                

                
                
                switch (result) {
                    case 1:
        	            Connection conn=DBconnect.getConn();
        	            String sql="SELECT * FROM student WHERE email='"+email+"'";
        	
					PreparedStatement ST;
					try {
						ST = conn.prepareStatement(sql);
        	        	ResultSet RS= ST.executeQuery();
        	        	while(RS.next()){
        	                    String id =RS.getString("studentID");
                                session.setAttribute("SID", id);
        	                }	
					} catch (SQLException e) {
						e.printStackTrace();
					}

             	       RequestDispatcher dispatcher = request.getRequestDispatcher("/books");
            	       dispatcher.forward(request, response);
                        session.setAttribute("email", email);
                 
                        break;
                    case 2:
                        response.sendRedirect("ErrorLogin.jsp");
                        break;
    }}finally {}
    }

}