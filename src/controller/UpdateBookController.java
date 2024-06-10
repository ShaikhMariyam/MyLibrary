package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Book;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Servlet implementation class updateBookController
 */
@MultipartConfig
@Controller()
@RequestMapping("/bookUpdate")
public class UpdateBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcTemplate jdbct =  new JdbcTemplate(getDataSource());
	 public Connection connection;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("Successful update book");
        int id = Integer.parseInt(request.getParameter("id"));
        
        int availabeCopys = Integer.parseInt(request.getParameter("AvailabeCopys"));
        
        int total = Integer.parseInt(request.getParameter("total"));
        
        String bookName = request.getParameter("bookName");
        int durationDays = Integer.parseInt(request.getParameter("duration"));
        
        String serialNum = request.getParameter("serialnum");
        
        double borrowCost = Double.parseDouble(request.getParameter("borrow_cost"));
        
        String description = request.getParameter("Description");
        
        //Part filePart = request.getPart("filename");
        
        //InputStream img= filePart.getInputStream();
        Book book = new Book(bookName,total,description, durationDays,availabeCopys,serialNum,id);
    
      
            String sql = "UPDATE books SET book_Name=?,book_Total=? , duration_Days=?, book_Copy=?, book_SerialNo=?, book_Description=?, bookBorrowCost=?  WHERE book_ID =?";
            Object args[] = {book.getBook_Name(), book.getBook_total(),book.getDuration_days(), book.getBook_Copy(), book.getBook_SerialNo(),book.getBook_Description(),borrowCost,book.getBook_ID()};
            int rowAffected = jdbct.update(sql, args);
            System.out.print("Successful prepeare");
            
            if (rowAffected == 1) {
            	
            	System.out.print("Successfullllllll");
            	response.sendRedirect("AdminDashboard.jsp");
            	
            } 
            else {
                
                request.setAttribute("errorMessage", "There was an error updating the book. Please try again.");
                request.getRequestDispatcher("EditBook.jsp").forward(request, response);
                
            }
            
             	
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
