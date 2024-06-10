package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class returnbook
 */
@Controller()
@RequestMapping("/return")
public class returnbook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public returnbook() {
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
	        
	        Connection connection = null;
	        try {
	            // Step 1: Create a connection
	        	Class.forName("com.mysql.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylibrary", "root", "");
	            System.out.println("successfully open database connection in books controller :" +connection.getMetaData());
	            // Step 2: Create a statement
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM borrowerslist WHERE book_Id=? and studentID=? and fine = 0");
	            statement.setInt(1, bookIdInt);
	            statement.setInt(2, studentIdInt);
	            
	            
	            // Step 3: Execute the DELETE statement
	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Book with id: " + bookId + " was successfully deleted from the database.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            try {
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
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
