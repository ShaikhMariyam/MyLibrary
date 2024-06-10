package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;


import model.Book;

/**
 * Servlet implementation class createBook
 */
@MultipartConfig
@Controller()
@RequestMapping("/create")
public class createBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcTemplate jdbct =  new JdbcTemplate(getDataSource());  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int book_ID = Integer.parseInt(request.getParameter("book_id"));
		String book_Name = request.getParameter("book_name");
        String book_Description = request.getParameter("book_description");
        int book_Copy = Integer.parseInt(request.getParameter("avail_copys"));
        String book_SerialNo = request.getParameter("serial_num");
        int duration_days = Integer.parseInt(request.getParameter("duration"));
        String book_Location = request.getParameter("book_location");
        double borrowCost = Double.parseDouble(request.getParameter("borrow_cost"));
        //Part imagePart = request.getPart("image");
        //InputStream book_Image = imagePart.getInputStream();
        //Part filePart = request.getPart("filename");
        //InputStream img= filePart.getInputStream();
        //Book newBook = new Book( book_ID,book_SerialNo, book_Name, book_Description, book_Copy, duration_days ,  book_Location);
        String sql = "INSERT INTO books (book_Name, book_Description, duration_Days, book_Copy, book_SerialNo,book_ID,book_Location,bookBorrowCost) VALUES (?,?,?,?,?,?,?,?)";
        //Object args[] = {newBook.getBook_Name(), newBook.getBook_Description(), newBook.getDuration_days(), newBook.getBook_Copy(), newBook.getBook_SerialNo()};
        Object args[] = {book_Name, book_Description, duration_days, book_Copy, book_SerialNo,book_ID,book_Location,borrowCost};
        int rowAffected = jdbct.update(sql, args);
        if (rowAffected == 1) {
        	System.out.print("Executed");
            response.sendRedirect("AdminDashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "There was an error creating the book. Please try again.");
            request.getRequestDispatcher("CreateBook.jsp").forward(request, response);
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
