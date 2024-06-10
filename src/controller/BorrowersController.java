package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Book;
import model.Student;
import model.borrowersList;

/**
 * Servlet implementation class BorrowersController
 */

@Controller()
@RequestMapping("/Borrowers")
public class BorrowersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcTemplate jdbct =  new JdbcTemplate(getDataSource());  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("Sucessful mapping to fine");
		ArrayList<Book> books = new ArrayList<Book>();
		ArrayList<Student> student = new ArrayList<Student>();
		ArrayList<borrowersList> borrowersList = new ArrayList<borrowersList>();
		int id = 4;
		String idStr =request.getParameter("id");
		if(idStr != null) {
		    id = Integer.parseInt(idStr);
		}
		
		
		try {
			
		    // Step 1: Load the JDBC driver
		    Class.forName("com.mysql.jdbc.Driver");
		    
		    // Step 2: Establish a connection
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylibrary", "root", "");
		    System.out.println("successfully open database connection in books controller :" +connection.getMetaData());
		    // Step 3: Create a statement
		    java.sql.Statement statement = connection.createStatement();
		    
		    // Step 4: Execute the SELECT statement
		    PreparedStatement ps = connection.prepareStatement("SELECT student.studentID, student.first_Name, student.last_Name, borrowerslist.borrow_Date, borrowerslist.borrow_Return_Date, borrowerslist.book_Status, borrowerslist.fine, books.book_SerialNo, books.book_Name FROM borrowerslist JOIN student ON borrowerslist.studentID = student.studentID JOIN books ON borrowerslist.book_ID = books.book_ID WHERE borrowerslist.fine >= 0  And  books.book_ID = ?");
		    ps.setInt(1, id);
		    ResultSet resultSet = ps.executeQuery();
		    
		    
		     //Step 5: Iterate through the result set and retrieve the data
		    while (resultSet.next()) {
		    	System.out.print("Sucessful start Loop");
		        String name = resultSet.getString("first_Name");
		        int studentid = resultSet.getInt("studentID");
		        Date date = resultSet.getDate("borrow_Date");
		        Date date2 = resultSet.getDate("borrow_Return_Date");
		        double fine  = resultSet.getDouble("fine");
		        String bookName = resultSet.getString("book_Name");
		        String bookSerialNumber = resultSet.getString("book_SerialNo");
		        
		        Book book = new Book(bookSerialNumber,bookName);
			    Student stud = new Student(name,id);
			    borrowersList borrowerL = new  borrowersList(date,date2,fine);
		        
		        borrowerL.calculateFine();
		        PreparedStatement ps1 = connection.prepareStatement("Update borrowerslist SET fine=? where studentID=? and book_ID=?");
		        ps1.setDouble(1, borrowerL.getFine());
		        ps1.setInt(2, borrowerL.getStudentID());
		        ps1.setInt(3, book.getBook_ID());
		        int rowsAffected = ps1.executeUpdate();
		        System.out.println(rowsAffected + " rows were updated.");
		        
			   // String sql = "Update borrowerslist SET fine=? where studentID=?";
			    //Object args[] = {borrowerL.getFine(),borrowerL.getStudentID()};
			    //int rowAffected = jdbct.update(sql, args);
	            //System.out.print("Successful prepeare");
	            
		        // do something with the data
			    
			    books.add(book);
			    student.add(stud);
			    borrowersList.add(borrowerL);
			    
			    
			   
		    }
		    request.setAttribute("student", student);
			request.setAttribute("book", books);
			request.setAttribute("borrowersList", borrowersList);

		    // Step 6: Close the connection, statement, and result set
		    resultSet.close();
		    statement.close();
		    connection.close();
		} catch (ClassNotFoundException e) {
			System.out.print(" 1 no Successful prepeare");
		    // Handle the exception for loading the JDBC driver
		} catch (SQLException e) {
		    System.out.println("Error: " + e.getMessage());
		}

        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleWhoBookedlist .jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public DataSource getDataSource() {
		DataSource ds=null;
		
		String dbURL = "jdbc:mysql://localhost:3306/mylibrary";
		String username = "root";
		String password = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ds = new DriverManagerDataSource(dbURL, username, password);		
		return ds;		
	}

}
