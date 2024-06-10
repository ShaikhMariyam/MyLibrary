package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class updatefine
 */
@Controller()
@RequestMapping("/updateFine")
public class updatefine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatefine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String studentId = request.getParameter("studentId");
		int studentIdInt = Integer.parseInt(studentId);

		String bookId = request.getParameter("bookId");
		int bookIdInt = Integer.parseInt(bookId);
	        double paidAmount = Double.parseDouble(request.getParameter("paidAmount"));
	        String sql = "SELECT fine FROM borrowerslist WHERE studentID = ? And book_ID=?";
	        
	        try
	        {
	         Class.forName("com.mysql.jdbc.Driver");	
	         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylibrary", "root", "");
			    System.out.println("successfully open database connection in books controller :" +connection.getMetaData());
			    
			    PreparedStatement statement = connection.prepareStatement(sql); 
			        statement.setInt(1, studentIdInt);
			        statement.setInt(2, bookIdInt);
			        ResultSet result = statement.executeQuery();
			        if (result.next()) {
			            double fine = result.getDouble("fine");
			            double updatedFine = fine - paidAmount;
			            String updateSql = "UPDATE borrowerslist SET book_Status = ?, fine = ? WHERE studentID = ? and book_ID=?";

			            
			            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
			                updateStatement.setString(1, "Paid");
			                updateStatement.setDouble(2, updatedFine);
			                updateStatement.setInt(3, studentIdInt);
			                updateStatement.setInt(4, bookIdInt);
			                int rowsAffected = updateStatement.executeUpdate();
			                if (rowsAffected > 0) {
			                    System.out.println("Fine updated successfully for student ID: " + studentId);
			                } else {
			                    System.out.println("Error updating fine for student ID: " + studentId);
			                }
			        }
			    
	        }
			        
			     catch (SQLException e) {
			        System.out.println("Error connecting to the database: " + e.getMessage());
			    }
	        
	        
	        catch (ClassNotFoundException e) {
				System.out.print(" 1 no Successful prepeare");
			    // Handle the exception for loading the JDBC driver
			} 
	        response.sendRedirect("AdminDashboard.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
