package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import DB.DBconnect;
import model.*;

/**
 * Servlet implementation class IssueBookController
 */
@WebServlet("/IssueBookController")
public class IssueBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	    String action = request.getParameter("action");
	    Connection conn = DBconnect.getConn();
        PreparedStatement stmt = null;
        int id = Integer.parseInt(request.getParameter("studentid"));
        int bid = Integer.parseInt(request.getParameter("bookid"));
	    if ("issue".equals(action)) {
	        try {
	        	stmt = conn.prepareStatement("UPDATE borrowersList SET book_Status = 'issued' WHERE studentID = ? AND book_ID = ?");
	        	stmt.setInt(1, id);
	        	stmt.setInt(2, bid);
	        	int rowsAffected = stmt.executeUpdate();
	        	stmt = conn.prepareStatement("UPDATE books SET book_Copy = book_Copy - 1 WHERE book_ID = ?");
	        	stmt.setInt(1, bid);
	        	rowsAffected = stmt.executeUpdate();
	        	stmt = conn.prepareStatement("UPDATE student SET borrowed_books = borrowed_books + 1 WHERE studentID = ?");
	        	stmt.setInt(1, id);
	        	rowsAffected = stmt.executeUpdate();
	        	stmt = conn.prepareStatement("UPDATE borrowersList SET borrow_Return_Date = DATE_ADD(borrow_Date, INTERVAL 14 DAY) WHERE studentID = ? AND book_ID = ?");
	        	stmt.setInt(1, id);
	        	stmt.setInt(2, bid);
	        	rowsAffected = stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Book successfully issued!");                  
	        	response.sendRedirect("IssueBookList.jsp");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } else if ("reject".equals(action)) {
	        try {
	        	stmt = conn.prepareStatement("UPDATE borrowersList SET book_Status = 'rejected' WHERE studentID = ? AND book_ID = ?");
	        	stmt.setInt(1, 1);
	        	stmt.setInt(2, 1);
	        	int rowsAffected = stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Book request rejected!");                  
	        	response.sendRedirect("IssueBookList.jsp");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
    public static List<borrowersList> borrowrequests() {
        Connection conn = DBconnect.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<borrowersList> borrowers = new ArrayList<borrowersList>();
        try {
            stmt = conn.prepareStatement("SELECT studentID, book_ID, borrow_Date, borrow_Return_Date, book_Status, fine, total_Pending_Payment, total_Borrowed_Books, book_Location FROM borrowerslist where book_Status = 'requested' ");
            rs = stmt.executeQuery();
            
            int bookID = 1;
     
            while (rs.next()) {
                String book_Status = rs.getString("book_Status");
                int book_ID = Integer.parseInt(rs.getString("book_ID"));
                int studentID = rs.getInt("studentID");
                double fine = Double.parseDouble(rs.getString("fine"));
                String borrow_Date = rs.getString("borrow_Date");
                String location = rs.getString("book_Location");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date returnDate = null, parsedBorrowDate = null;
                try {
                    parsedBorrowDate = format.parse(borrow_Date);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(parsedBorrowDate);
                    calendar.add(Calendar.DATE, 14);
                    returnDate = calendar.getTime();
                    format.format(returnDate);
                } catch (ParseException e) {
                    System.out.println("Error in parsing date: " + e.getMessage());
                }
                borrowers.add(new borrowersList(book_Status, studentID, book_ID, parsedBorrowDate, returnDate, fine, location));
                bookID = book_ID;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return borrowers;
    }
    public static Book BookfromDB(int id) {
        Connection conn = DBconnect.getConn();
        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;
        Book borrowers = null;
        try {
            stmt2 = conn.prepareStatement("SELECT book_SerialNo, book_Name, book_Location, book_Image FROM books where book_ID = ?");
            stmt2.setInt(1, id);
            rs2 = stmt2.executeQuery();
            while(rs2.next()){
                String book_SerialNo = rs2.getString("book_SerialNo");
                String book_Name = rs2.getString("book_Name");
                String book_Location = rs2.getString("book_Location");
                InputStream book_Image = rs2.getBinaryStream("book_Image");
                borrowers = new Book(book_SerialNo,book_Name,book_Location,book_Image,id);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs2 != null) {
                    rs2.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return borrowers;
    }
    public static Student StudentfromDB(int id) {
        Connection conn = DBconnect.getConn();
        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;
        Student person = null;
        try {
            stmt2 = conn.prepareStatement("SELECT * FROM student where studentID = ?");
            stmt2.setInt(1, id);
            rs2 = stmt2.executeQuery();
            while(rs2.next()){
                String firstname = rs2.getString("first_Name");
                String lastname = rs2.getString("last_Name");
                String matricNo = rs2.getString("matricNo");
                String email = rs2.getString("email");
                int phone = Integer.parseInt(rs2.getString("phoneNo"));
                String password = rs2.getString("pass");
                double fine = Double.parseDouble(rs2.getString("fine"));
                String borrowedBooks = rs2.getString("borrowed_books");
                person = new Student(firstname, lastname, matricNo, email, phone, password, fine, borrowedBooks);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs2 != null) {
                    rs2.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return person;
    }

}