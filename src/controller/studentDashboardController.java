package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DBconnect;
import model.borrowersList;

/**
 * Servlet implementation class studentDashboardController
 */
@WebServlet("/studentDashboardController")
public class studentDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static int studentID = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentDashboardController() {
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
	}
	
    public static List<borrowersList> allbooks(String email) {
        Connection conn = DBconnect.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
	 	   String sql2="SELECT studentID FROM student WHERE email = ?";
	       PreparedStatement st2;
		try {
			st2 = conn.prepareStatement(sql2);
		       st2.setString(1, email);
		       rs = st2.executeQuery();
		       while (rs.next()) {
		    	   int id = rs.getInt("studentID");
		           studentID = id; 
		           }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        List<borrowersList> borrowers = new ArrayList<borrowersList>();
        try {
            stmt = conn.prepareStatement("SELECT studentID, book_ID, borrow_Date, borrow_Return_Date, book_Status, fine, total_Pending_Payment, total_Borrowed_Books, book_Location FROM borrowerslist where studentID = "+studentID);
            rs = stmt.executeQuery();
            
            int bookID = 0;
     
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
}
