package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Book;

/**
 * Servlet implementation class SearchController
 */
@Controller()
@RequestMapping("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> books = new ArrayList<Book>();
		String bookName = request.getParameter("search");
		try {
			
		    // Step 1: Load the JDBC driver
		    Class.forName("com.mysql.jdbc.Driver");
		    
		    // Step 2: Establish a connection
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylibrary", "root", "");
		    System.out.println("successfully open database connection in search controller :" +connection.getMetaData());
		    // Step 3: Create a statement
		    java.sql.Statement statement = connection.createStatement();
		    
		    // Step 4: Execute the SELECT statement
		    ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE book_Name LIKE '%"+ bookName + "%' or book_Name LIKE '"+ bookName + "_%' or book_Name LIKE '_"+ bookName + "%'");
		    
		    // Step 5: Iterate through the result set and retrieve the data
		    while (resultSet.next()) {
		        String title = resultSet.getString("book_Name");
		        InputStream img = resultSet.getBinaryStream("book_Image");
		        int days = resultSet.getInt("duration_Days");
		        int total = resultSet.getInt("book_Total");
			    System.out.println("Book Name :" +title);
		        // do something with the data
			    Book book = new Book(title, img, days,total);
			    if(book != null)
		        books.add(book);

		    }
			request.setAttribute("book", books);

		    // Step 6: Close the connection, statement, and result set
		    resultSet.close();
		    statement.close();
		    connection.close();
		} catch (ClassNotFoundException e) {
		    // Handle the exception for loading the JDBC driver
		} catch (SQLException e) {
		    // Handle the exception for SQL errors
		}

        RequestDispatcher dispatcher = request.getRequestDispatcher("BooksPage.jsp");
        dispatcher.forward(request, response);
	}

}
