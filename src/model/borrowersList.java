package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Date;

@WebServlet("/borrowersList")
public class borrowersList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String book_Status, book_Location;
	int studentID, book_ID, total_Borrowed_Books;
	Date borrow_Date, borrow_Return_Date;
	double fine, total_Pending_Payment, paid_Ammount;

	public borrowersList() {
		
	}
	
	public borrowersList(Date borrow_Date,Date borrow_Return_Date,double fine) {
		this.borrow_Return_Date=borrow_Return_Date;
		this.borrow_Date=borrow_Date;
		this.fine=fine;
	}
	public borrowersList(String book_Status, int studentID, int book_ID, int total_Borrowed_Books, Date borrow_Date, 
			Date borrow_Return_Date, double fine, double total_Pending_Payment, String book_Location) {
		this.book_Status = book_Status;
		this.studentID = studentID;
		this.book_ID = book_ID;
		this.total_Borrowed_Books = total_Borrowed_Books;
		this.borrow_Date = borrow_Date;
		this.borrow_Return_Date = borrow_Return_Date;
		this.fine = fine;
		this.total_Pending_Payment = total_Pending_Payment;
		this.book_Location = book_Location;
	}
	
	public borrowersList(String book_Status, int studentID, int book_ID, Date borrow_Date, 
			Date borrow_Return_Date, double fine, String book_Location) {
		this.book_Status = book_Status;
		this.studentID = studentID;
		this.book_ID = book_ID;
		this.borrow_Date = borrow_Date;
		this.borrow_Return_Date = borrow_Return_Date;
		this.fine = fine;
		this.book_Location = book_Location;
	}
	

	public borrowersList(Date borrow_Date, int book_ID, int studentID, double fine, String book_Location) {
		this.borrow_Date = borrow_Date;
		this.book_ID = book_ID;
		this.studentID = studentID;
		this.fine = fine;
		this.book_Location = book_Location;
	}
	
	public void setBookStatus(String book_Status) {
	    this.book_Status = book_Status;
	}

	public String getBookStatus() {
	    return this.book_Status;
	}

	public void setStudentID(int studentID) {
	    this.studentID = studentID;
	}

	public int getStudentID() {
	    return this.studentID;
	}

	public void setBookID(int book_ID) {
	    this.book_ID = book_ID;
	}

	public int getBookID() {
	    return this.book_ID;
	}

	public void setTotalBorrowedBooks(int total_Borrowed_Books) {
	    this.total_Borrowed_Books = total_Borrowed_Books;
	}

	public int getTotalBorrowedBooks() {
	    return this.total_Borrowed_Books;
	}

	public void setBorrowDate(Date borrow_Date) {
	    this.borrow_Date = borrow_Date;
	}

	public Date getBorrowDate() {
	    return this.borrow_Date;
	}

	public void setBorrowReturnDate(Date borrow_Return_Date) {
	    this.borrow_Return_Date = borrow_Return_Date;
	}

	public Date getBorrowReturnDate() {
	    return this.borrow_Return_Date;
	}

	public void setFine(double fine) {
	    this.fine = fine;
	}

	public double getFine() {
	    return this.fine;
	}

	public void setTotalPendingPayment(double total_Pending_Payment) {
	    this.total_Pending_Payment = total_Pending_Payment;
	}

	public double getTotalPendingPayment() {
	    return this.total_Pending_Payment;
	}
	
	public void calculateFine() {
	    Date today = new Date();
	    long diff = today.getTime() - borrow_Return_Date.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	    if(diffDays <= 0) {
	        fine = 0;
	    }
	    else if(diffDays <= 7) {
	        fine = 5;
	    }
	    else if(diffDays <= 14) {
	        fine = 10;
	    }
	    else if(diffDays > 14) {
	        fine = 15;
	    }
	}

	public void updateTotalPendingPayment(){
	    this.total_Pending_Payment += this.fine;
	}
	public int calculateOverdueDays() {
	    Date today = new Date();
	    long diff = today.getTime() - borrow_Return_Date.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	    return diffDays > 0 ? (int) diffDays : 0;
	}
	
	public void setPaidAmmount(double paaid)
	{
		this.paid_Ammount=paaid;
	}
	
	public double getPaidAmmount() {
		return paid_Ammount;
	}

}