package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.*;
import javax.servlet.jsp.PageContext;
import javax.servlet.*;
import javax.sql.DataSource;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import DB.DBconnect;
import model.Book;
import model.Student;
import model.borrowersList;


@WebServlet("/BorrowBookController")
public class BorrowBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static int studentID = 0;


    public BorrowBookController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Connection conn = DBconnect.getConn();
		 ResultSet rs = null;
    	 String email = (String) request.getSession().getAttribute("email");
         
    	 Date Brdate = null;
    	 try {
    		 Brdate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("Bdate"));
    		 } catch (ParseException e) {
    		 // Handle the exception
    		 }    	 
    	 String BLoc = request.getParameter("Campus");
    	 int bid = 1;
    	 bid = Integer.parseInt(request.getParameter("bookId"));

         try {
        	   String sql="INSERT INTO borrowerslist(studentID, book_ID, borrow_Date, fine, book_Location, book_Status) VALUES (?,?,?,?,?,?)";
        	   String sql2="SELECT studentID FROM student WHERE email = ?";
               PreparedStatement st=conn.prepareStatement(sql);
               PreparedStatement st2=conn.prepareStatement(sql2);
               System.out.println(studentID);
               System.out.println(email);
               st2.setString(1, email);
               rs = st2.executeQuery();
               while (rs.next()) {
            	   int id = rs.getInt("studentID");
                   studentID = id; 
                   }
               System.out.println(studentID);
                	st.setInt(1, studentID);
                    st.setInt(2, bid);
                    st.setDate(3, new java.sql.Date(Brdate.getTime()));
                    st.setDouble(4, 0);
                    st.setString(5, BLoc);
                    st.setString(6, "requested");
                    st.executeUpdate();
                    st.close();
                    conn.close();
                  
                    try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


                      JOptionPane.showMessageDialog(null, "Successfully added to borrowerslist!");                  
                      response.sendRedirect("/MyLibrary/books");
        } catch (Exception e) {
            e.printStackTrace();
        } }
		         public static Book BookDB(int id) {
				 Connection conn = DBconnect.getConn();
				 ResultSet rs = null;
		         PreparedStatement stmt = null;
	        	 Book books = null;
		         try {
		        	 
		                stmt = conn.prepareStatement("SELECT * FROM books WHERE book_ID =" +id);
		                rs = stmt.executeQuery();             

		                while (rs.next()) {
		                    String serialNo = rs.getString("book_SerialNo");
		                    int bookID = Integer.parseInt(rs.getString("book_ID"));
		                    int BCopy = Integer.parseInt(rs.getString("book_Copy"));
		                    String name = rs.getString("book_Name");
		                    String location = rs.getString("book_Location");
		                    InputStream image = rs.getBinaryStream("book_Image");
		                    books= new Book(serialNo, name, location, image, bookID,BCopy);
		                    
		                }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {  }
					return books;

		        //request.setAttribute("book", books);
		      //  request.getRequestDispatcher("BorrowBook.jsp").forward(request, response);
		    }
		         
		         
		         public static Student BorrowDB(int id) {
					 Connection conn = DBconnect.getConn();
			         ResultSet rs2= null;
			         PreparedStatement stmt2 = null;
			         Student s = null;
			         try {
			                    stmt2 = conn.prepareStatement("SELECT * FROM student WHERE studentID =" + id);
				                rs2 = stmt2.executeQuery();
				                while (rs2.next()) {
				                	double fine = Double.parseDouble(rs2.getString("fine"));
				                	s = new Student(fine, id);
				                	}
			        } catch (SQLException e) {
			            e.printStackTrace();
			        } finally {  }
						return s;
			    }
		}