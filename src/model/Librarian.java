package model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import DB.DBconnect;

@WebServlet("/librarian")
public class Librarian{
	int librarian_ID, phoneNo;
	String first_Name, last_Name, email, pass;
	
	
	public Librarian() {}
	
	public Librarian(int librarian_ID, String first_Name, String last_Name, String email, int phoneNo,String pass) {
		this.librarian_ID=librarian_ID;
		this.first_Name=first_Name;
		this.last_Name=last_Name;
		this.email=email;
		this.phoneNo=phoneNo;
		this.pass=pass;
	}
	
	public Librarian(String first_Name, String last_Name, String email, int phoneNo,String pass) {
		this.first_Name=first_Name;
		this.last_Name=last_Name;
		this.email=email;
		this.phoneNo=phoneNo;
		this.pass=pass;
	}
	
	public int getLibrarian_ID() {
	    return librarian_ID;
	}

	public void setLibrarian_ID(int librarian_ID) {
	    this.librarian_ID = librarian_ID;
	}

	public String getFirst_Name() {
	    return first_Name;
	}

	public void setFirst_Name(String first_Name) {
	    this.first_Name = first_Name;
	}

	public String getLast_Name() {
	    return last_Name;
	}

	public void setLast_Name(String last_Name) {
	    this.last_Name = last_Name;
	}

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public int getPhoneNo() {
	    return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
	    this.phoneNo = phoneNo;
	}

	public String getPass() {
	    return pass;
	}

	public void setPass(String pass) {
	    this.pass = pass;
	}
	
	 public void register() throws SQLException {
			Connection conn=DBconnect.getConn();
			String sql="INSERT INTO librarian (first_Name, last_Name, email, phoneNo, pass) VALUES (?,?,?,?,?)";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, first_Name);
			st.setString(2, last_Name);
	    	st.setString(3, email);
	    	st.setInt(4, phoneNo);
	    	st.setString(5, pass);
	    	st.executeUpdate();
         st.close();
         conn.close();
	    }

    

	public int Alogin() throws SQLException{
        Connection conn=DBconnect.getConn();
        String sql="SELECT * FROM librarian WHERE email='"+email+"' AND pass='"+pass+"'";

        PreparedStatement ST=conn.prepareStatement(sql);
    	ResultSet RS= ST.executeQuery();

    	while(RS.next()){
                String emaildb =RS.getString("email");
                String passdb=RS.getString("pass");
                if(email.equals(emaildb) && pass.equals(passdb))
                	return 1;
            }	
    	return 2;	
	}
}
