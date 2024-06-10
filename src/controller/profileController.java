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
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jdbc.pool.DataSource;

import model.Student;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class profileController
 */
@WebServlet("/profileController")
public class profileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public static int userID = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		try {

			// Step 1: Load the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Step 2: Establish a connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylibrary", "root", "");
			System.out
					.println("successfully open database connection in profile controller :" + connection.getMetaData());

			HttpSession session = request.getSession();
			// Step 3: Create a statement
			String sql = "SELECT * FROM student WHERE email = "+ "'" +session.getAttribute("email").toString()+ "'";
			// + session.getAttribute("name").toString()
			java.sql.Statement statement = connection.createStatement();

			// Step 4: Execute the SELECT statement
			ResultSet resultSet = statement.executeQuery(sql);

			// Step 5: Iterate through the result set and retrieve the data
		
			Student student = new Student("None", "None", 0000000000, 0);
				// do something with the data
				
				while (resultSet.next())
				{
					String name = resultSet.getString("first_Name");
					String email = resultSet.getString("email");
					int phoneNo = resultSet.getInt("phoneNo");
					int id = resultSet.getInt("studentID");
					userID = id;

					student = new Student(name, email, phoneNo, userID);		
				}
				request.setAttribute("student", student);
			
				 

			// Step 6: Close the connection, statement, and result set
			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// Handle the exception for loading the JDBC driver
			System.out
			.println("CLASS NOT FOUND IN doGet profileController");
		} catch (SQLException e) {
			// Handle the exception for SQL errors
			System.out
			.println("SQL Exception while retriveing profile data");
		}



        RequestDispatcher dispatcher = request.getRequestDispatcher("profilePage.jsp");
        dispatcher.forward(request, response);

	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		HttpSession session = request.getSession();
		session.setAttribute("email", email);

		Connection con = null;
		PreparedStatement ps = null;

		try {
		    con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/mylibrary", "root", "");
		    System.out
			.println("update database connection :" + con.getMetaData());
		    ps = con.prepareStatement("UPDATE student SET first_Name = ?, email = ?, phoneNo = ? WHERE studentID = ?");
		    ps.setString(1, name);
		    ps.setString(2, email);
		    ps.setString(3, phone);
		    ps.setInt(4, userID);
		    ps.executeUpdate();
		} catch (SQLException e) {
		    // handle SQL exception

			    System.out
				.println("UPDATE FAILED");
		} finally {
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException e) {
		            // handle SQL exception

		        }
		    }
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) {
		            // handle SQL exception

		        }
		    }
		}
		
		doGet(request, response);

		}
	}
	


