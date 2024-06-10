package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import model.Book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Statement;


/**
 * Servlet implementation class booksController
 */
@WebServlet("/booksController")
public class booksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public booksController() {
        super();
        // TODO Auto-generated constructor stub

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int i = 0;
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			
		    // Step 1: Load the JDBC driver
		    Class.forName("com.mysql.jdbc.Driver");
		    
		    // Step 2: Establish a connection
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylibrary", "root", "");
		    System.out.println("successfully open database connection in books controller :" +connection.getMetaData());
		    // Step 3: Create a statement
		    java.sql.Statement statement = connection.createStatement();
		    
		    // Step 4: Execute the SELECT statement
		    ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
		    
		    // Step 5: Iterate through the result set and retrieve the data
		    while (resultSet.next()) {
		        String title = resultSet.getString("book_Name");
		        InputStream img = resultSet.getBinaryStream("book_Image");
		        int days = resultSet.getInt("duration_Days");
		        int total = resultSet.getInt("book_Total");
		        int id	 = resultSet.getInt("book_ID");
			    System.out.println("Book Name :" +title);
		        // do something with the data
			    Book book = new Book(title, img, days,total,id);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
