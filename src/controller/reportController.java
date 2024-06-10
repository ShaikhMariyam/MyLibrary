package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class reportController
 */
@Controller()
@RequestMapping("/report")
public class reportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int totalBooks = 0;
        int totalStudents = 0;
        int totalBorrowedBooks = 0;
        double totalProfit = 0;
        int totalAvalibaleCopies = 0;

        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Step 2: Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylibrary", "root", "");
            System.out.println("Successfully connected to the database.");

            // Step 3: Create a statement
            Statement statement = connection.createStatement();

            // Step 4: Execute the SELECT statement to get total number of books
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM books");
            if (resultSet.next()) {
                totalBooks = resultSet.getInt(1);
            }

            // Step 5: Execute the SELECT statement to get total number of students
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM student");
            if (resultSet.next()) {
                totalStudents = resultSet.getInt(1);
            }

            // Step 6: Execute the SELECT statement to get total number of borrowed books and calculate profit
            resultSet = statement.executeQuery("SELECT book_Copy, book_Total, bookBorrowCost FROM books");
            while (resultSet.next()) {
                int availableCopies = resultSet.getInt("book_Copy");
                int totalCopies = resultSet.getInt("book_Total");
                double borrowCost = resultSet.getDouble("bookBorrowCost");

                // Calculate total borrowed books
                int borrowedBooks = totalCopies - availableCopies;
                totalBorrowedBooks += borrowedBooks;
                
                totalAvalibaleCopies+=availableCopies;

                // Calculate total profit
                double profit = borrowedBooks * borrowCost;
                totalProfit += profit;
            }
            
            // Step 7: Close the connection and statement
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error: Unable to load the JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Unable to execute the SELECT statement.");
            e.printStackTrace();
        }

        // Step 8: Set the values as request attributes to be used in the JSP
        request.setAttribute("totalBooks", totalBooks);
        request.setAttribute("totalStudents", totalStudents);
        request.setAttribute("totalBorrowedBooks", totalBorrowedBooks);
        request.setAttribute("totalProfit", totalProfit);
        request.setAttribute("totalAvalibaleCopies", totalAvalibaleCopies);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminReport2.jsp");
        dispatcher.forward(request, response);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
