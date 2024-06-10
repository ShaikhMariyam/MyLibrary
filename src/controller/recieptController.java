package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Book;
import model.Student;
import model.borrowersList;

/**
 * Servlet implementation class recieptController
 */
@Controller()
@RequestMapping("/receipt")
public class recieptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public recieptController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print(" Working");
        // Retrieve the student's id from the request
        String[] indicesString = request.getParameterValues("index");
        int[] indicesInt = new int[indicesString.length];
        for (int i = 0; i < indicesString.length; i++) {
            if(indicesString[i] != null && !indicesString[i].isEmpty()){
                indicesInt[i] = Integer.parseInt(indicesString[i]);
            }
        }
        int index = 0;
        for (int i = 0; i < indicesString.length; i++) {
            if (indicesInt[i]!=0) {
                index=indicesInt[i];
            }
        }

        double paid = Double.parseDouble(request.getParameter("paidAmount"));

        // Retrieve the ArrayLists from the request (assuming they have been set as attributes)
        ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("student");
        ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("book");
        ArrayList<borrowersList> borrowersList = (ArrayList<borrowersList>) request.getAttribute("borrowersList");
        // Update the fine amount for the corresponding borrower in the list

        // Use the JSPWriter to write the updated information to the response
        response.setBufferSize(0);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<table id='customers' class='center'>");
        out.println("<tr>");
        out.println("<th>User Name</th>");
        out.println("<th>Book Serial Number</th>");
        out.println("<th>Book Name</th>");
        out.println("<th>Borrow Date</th>");
        out.println("<th>Due Data</th>");
        out.println("<th>Days overdue</th>");
        out.println("<th>Fine (RM)</th>");
        out.println("<th> Paid amount </th>");
        out.println("</tr>");
        System.out.print("index"+index);
        for (int i = 0; i < borrowersList.size(); i++) {
        	System.out.print("Test value name "+students.get(i).getFirstName() );
            if(i==index){
                out.println("<tr>");
                out.println("<td>" + students.get(i).getFirstName() + "</td>");
                out.println("<td>" + books.get(i).getBook_SerialNo() + "</td>");
                out.println("<td>" + books.get(i).getBook_Name ()+ "</td>");
                out.println("<td>" + borrowersList.get(i).getBorrowDate() + "</td>");
                out.println("<td>" + borrowersList.get(i).getBorrowReturnDate() + "</td>");
                out.println("<td>" + borrowersList.get(i).calculateOverdueDays() + "</td>");
                out.println("<td>" + borrowersList.get(i).getFine() + "</td>");
                out.println("<td>"+borrowersList.get(i).getPaidAmmount()+"</td>");
                System.out.print("index"+borrowersList.get(i).getPaidAmmount());
                out.println("</tr>");
            }
        }
        out.println("</table>");
        out.flush();
        out.close();
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
