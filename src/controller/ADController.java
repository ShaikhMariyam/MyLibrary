package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DBconnect;
import model.Book;

@WebServlet("/ADController")
public class ADController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADController() {
        super();
    }


    public static void deleteBook(int book_ID) {
        Connection connection = DBconnect.getConn();
        String sql = "DELETE FROM books WHERE book_ID = ?";
        try {
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, book_ID);
          statement.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
        }
    }
	    public static List<Book> BookfromDB() {
	        Connection conn = DBconnect.getConn();
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        List<Book> books = new ArrayList<Book>();
	        try {
	            stmt = conn.prepareStatement("SELECT book_ID, book_SerialNo, book_Name, book_Location, book_Image FROM books");
	            rs = stmt.executeQuery();

	            while (rs.next()) {
	                String serialNo = rs.getString("book_SerialNo");
	                int bookID = Integer.parseInt(rs.getString("book_ID"));
	                String name = rs.getString("book_Name");
	                String location = rs.getString("book_Location");
	                InputStream image = rs.getBinaryStream("book_Image");
	                books.add(new Book(serialNo, name, location, image, bookID));
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
	        return books;
		       }
	}


