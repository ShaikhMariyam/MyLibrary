package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import DB.DBconnect;
import model.Book;
import model.Student;
import model.borrowersList;

/**
 * Servlet implementation class SecurityQuestions
 */
@WebServlet("/SecurityQuestions")
public class SecurityQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SecurityQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Connection conn = DBconnect.getConn();
		 HttpSession session = request.getSession(false);

		String Q1 = request.getParameter("check1");
		String Q2 = request.getParameter("check2");
		String Q3 = request.getParameter("check3");
		String email = (String)session.getAttribute("email");

		System.out.println(email+Q1+Q2+Q3);
		session.setAttribute("email", email);

		PreparedStatement stmt = null;
        String sql = "UPDATE student SET SecQ1 = ?, SecQ2 = ?, SecQ3 = ? WHERE email = ?";

        
        try {
	
        	stmt = conn.prepareStatement(sql);
		    
        	stmt.setString(1, Q1);
        	stmt.setString(2, Q2);
        	stmt.setString(3, Q3);
        	stmt.setString(4, email);
        	stmt.executeUpdate();
        	response.sendRedirect("LoginStudent.jsp");
		} catch (SQLException e) {
		    // handle SQL exception

			    System.out.println("UPDATE FAILED");
		} finally {
		    if (stmt != null) {
		        try {
		        	stmt.close();
		        } catch (SQLException e) {
		            // handle SQL exception

		        }
		    }
		    if (conn != null) {
		        try {
		            conn.close();
		        } catch (SQLException e) {
		            // handle SQL exception

		        }
		    }
		}
	}

}
