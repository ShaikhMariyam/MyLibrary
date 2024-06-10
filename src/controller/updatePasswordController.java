package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import DB.DBconnect;

@WebServlet("/updatePasswordController")
public class updatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] Answers = null;

    public updatePasswordController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Q1 = request.getParameter("check1");
		String Q2 = request.getParameter("check2");
		String Q3 = request.getParameter("check3");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String confirmpass = request.getParameter("cpass");
		getAccount(email);
		if (Q1.equals(Answers[0]) && Q2.equals(Answers[1]) & Q3.equals(Answers[2])) {
			System.out.println(email+Q1+Q2+Q3);
			if (pass.equals(confirmpass)) {
	        Connection conn=DBconnect.getConn();
	        String sql="UPDATE student SET pass = ? where SecQ1 = '" + Answers[0] +"'";
			try {
				    PreparedStatement stmt = conn.prepareStatement(sql);
				    stmt.setString(1, pass);  // Replace 'password' with the actual password value
				    int rowsUpdated = stmt.executeUpdate();
				    if (rowsUpdated > 0) {
				        System.out.println("Password updated successfully.");
				    }
                    JOptionPane.showMessageDialog(null, "Password Updated Successfully!");                  
				    response.sendRedirect("LoginStudent.jsp");
			}
		     catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "An error occurred. Please try again!");  
				    response.sendRedirect("LoginStudent.jsp");

		        e.printStackTrace();
		    }
			} else {
                JOptionPane.showMessageDialog(null, "Your passwords don't match!");  
				response.sendRedirect("LoginStudent.jsp");
			}
		} else {
            JOptionPane.showMessageDialog(null, "Security Questions Incorrect!");  
			response.sendRedirect("LoginStudent.jsp");
		}
	}
	
	public static void getAccount(String id) {
        Connection conn=DBconnect.getConn();
        String sql="SELECT * FROM student WHERE email='"+id+"'";
        String[] answers = new String[3];
		PreparedStatement ST;
		try {
			
	        ST = conn.prepareStatement(sql);
	        ResultSet RS = ST.executeQuery();
	        while (RS.next()) {
	            answers[0] = RS.getString("SecQ1");
	            answers[1] = RS.getString("SecQ2");
	            answers[2] = RS.getString("SecQ3");
	        }
	        System.out.println(answers[0]+answers[1]+answers[2]);
	    } catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, "Security Questions Incorrect!");  
	        e.printStackTrace();
	        
	    }
        Answers = answers;
	}

	public static boolean getemail(String email) throws SQLException {
        Connection conn=DBconnect.getConn();
        String sql="SELECT * FROM student WHERE email='"+email+"'";
		PreparedStatement ST;
		boolean x = true;			
	        ST = conn.prepareStatement(sql);
	        ResultSet rs = ST.executeQuery();
	        	if (rs.next()) {
	                x =  false;
	            } else {
	                x =  true;
	            }
	    return x;
	}
}
