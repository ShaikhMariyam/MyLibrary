package model;

import javax.servlet.annotation.WebServlet;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DB.DBconnect;



@WebServlet("/student")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;


		    String firstname, lastname, password, email, matricNo, borrowedBooks,SeQ1, SeQ2, SeQ3;
		    double fine;
		    int studentID, phone;
		    public Student() {
		    }
		    public Student(String firstname,  String email, int phone, int studentID) {
		        this.firstname = firstname;
		        
		        this.phone = phone;
		        
		        this.email = email;
		        
		        this.studentID = studentID;
		     
		    }
		    public Student(String SeQ1,String SeQ2,String SeQ3, int studentID) {
		    	this.SeQ1 = SeQ1;
		    	this.SeQ2 = SeQ2;
		    	this.SeQ3 = SeQ3;
		    	this.studentID = studentID;
		    }
		    public Student(double fine,int studentID) {
				this.fine = fine;
				this.studentID = studentID;
			}
		    public Student(String firstname, String lastname, String matricNo, String email, int phone, String password, double fine, String borrowedBooks) {
		        this.firstname = firstname;
		        this.password = password;
		        this.lastname = lastname;
		        this.phone = phone;
		        this.matricNo = matricNo;
		        this.borrowedBooks = borrowedBooks;
		        this.email = email;
		        this.fine = fine;
		    }
            public Student(String name, int ID) {
            	this.firstname=name;
            	this.studentID=ID;
            }
		    
		    public String getFirstName() {
		        return firstname;
		    }

		    public void setFirstName(String firstname) {
		        this.firstname = firstname;
		    }

		    public String getLastName() {
		        return lastname;
		    }

		    public void setLastName(String lastname) {
		        this.lastname = lastname;
		    }

		    public String getPassword() {
		        return password;
		    }

		    public void setPassword(String password) {
		        this.password = password;
		    }

		    public int getPhone() {
		        return phone;
		    }

		    public void setPhone (int phone) {
		        this.phone = phone;
		    }

		    public String getMatricNo() {
		        return matricNo;
		    }

		    public void setMatricNo(String matric) {
		        this.matricNo = matric;
		    }

		    public double getFine() {
		        return fine;
		    }

		    public void setFine(double fine) {
		        this.fine = fine;
		    }

		    public String getborrowedBooks() {
		        return borrowedBooks;
		    }

		    public void setAborrowedBooks(String borrowedBooks) {
		        this.borrowedBooks = borrowedBooks;
		    }

		    public String getEmail() {
		        return email;
		    }

		    public void setEmail(String email) {
		        this.email = email;
		    }

		    public int getstudentID() {
		        return studentID;
		    }

		    public void setstudentID(int studentID) {
		        this.studentID = studentID;
		    }
		    		    
		    public void register() throws SQLException {
				Connection conn=DBconnect.getConn();
				String sql="INSERT INTO student (first_Name, last_Name, email, phoneNo, pass, matricNo, fine, borrowed_books) VALUES (?,?,?,?,?,?,?,?)";
				PreparedStatement st=conn.prepareStatement(sql);
				st.setString(1, firstname);
				st.setString(2, lastname);
		    	st.setString(3, email);
		    	st.setInt(4, phone);
		    	st.setString(5, password);
		    	st.setString(6, matricNo);
		    	st.setDouble(7, fine);
		    	st.setString(8, borrowedBooks);
		    	st.executeUpdate();
	            st.close();
	            conn.close();
		    }

	        public int login() throws SQLException{
	            Connection conn=DBconnect.getConn();
	            String sql="SELECT * FROM student WHERE email='"+email+"' AND pass='"+password+"'";
	
	            PreparedStatement ST=conn.prepareStatement(sql);
	        	ResultSet RS= ST.executeQuery();
	
	        	while(RS.next()){
	                    String emaildb =RS.getString("email");
	                    String passdb=RS.getString("pass");
	                    if(email.equals(emaildb) && password.equals(passdb))
	                    	return 1;
	                }	
	        	return 2;	
	    	}
}