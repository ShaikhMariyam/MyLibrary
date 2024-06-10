package model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import DB.DBconnect;

public class Book {
    int book_ID;
    String book_SerialNo;
    String book_Name;
    String book_Description;
    int book_Copy;
    InputStream book_Image;
    //InputStream imageContent;
    int book_total;
    int duration_days;
    String book_Location;
    
    
    public Book(int book_ID,String book_SerialNo,String book_Name,String book_Description,int book_Copy, InputStream book_Image,int duration_days , String book_Location) {
    	this.book_Name = book_Name;
    	this.book_SerialNo=book_SerialNo;
    	this.book_ID=book_ID;
    	this.book_Description=book_Description;
    	this.book_Copy=book_Copy;
    	this.book_Image=book_Image;
    	this.duration_days=duration_days;
    	this.book_Location=book_Location;
    }
    
    public Book() {
    	
    	
    }
    public Book(String name, InputStream img, int days, int total) {
        this.book_Name = name;
        this.book_Image = img;
        this.book_total = total;
        this.duration_days = days;
      }
    public Book(String name, InputStream img, int days, int total, int id) {
        this.book_Name = name;
        this.book_Image = img;
        this.book_total = total;
        this.duration_days = days;
        this.book_ID = id;
      }
    public Book(String name,String description, int days, int total,String book_SerialNo, int book_ID) {
      this.book_ID=book_ID;  
      this.book_Name = name;
        //this.book_Image =  img;
      this.book_Description=description;
        this.book_total = total;
        this.duration_days = days;
        this.book_SerialNo=book_SerialNo;
        this.book_Copy=total;
        
      }
    
    
    public Book(String book_SerialNo,String book_Name)
    {
      this.book_Name = book_Name;
      this.book_SerialNo=book_SerialNo;
    }
    
    public Book(String book_SerialNo, String book_Name, String book_Location,InputStream img, int bookID) {
	  	this.book_SerialNo = book_SerialNo;
	  	this.book_Name = book_Name;
	  	this.book_Image =  img;
	  	this.book_Location = book_Location;
	  	this.book_ID = bookID;
}
    public Book(String book_SerialNo, String book_Name, String book_Location,InputStream img, int bookID, int bookCopy) {
  	this.book_SerialNo = book_SerialNo;
  	this.book_Name = book_Name;
  	this.book_Image =  img;
  	this.book_Location = book_Location;
  	this.book_ID = bookID;
  	this.book_Copy = bookCopy;
}

    public Book(String bookName,int total, String description, int durationDays, int availabeCopys,String serialNum,int id) {
    	  this.book_ID=id;  
          this.book_Name = bookName;
          this.book_Description=description;
            this.book_total = total;
            this.duration_days = durationDays;
            this.book_SerialNo=serialNum;
            this.book_Copy=availabeCopys;
    }
    
    public Book(String book_SerialNo, String book_Name, String book_Location,String book_Description, int bookID) {
	  	this.book_SerialNo = book_SerialNo;
	  	this.book_Name = book_Name;
	  	this.book_Description =  book_Description;
	  	this.book_Location = book_Location;
	  	this.book_ID = bookID;
}
    
    public String getBook_Name () {
      return book_Name;
    }
    
    public int getBook_ID() {
      return book_ID;
    }
    
    public String getBook_Description()
    {
      return book_Description;
    }
    
    public String getBook_Location() {
    	return book_Location;
    }
    
    public String getBook_SerialNo() {
      return book_SerialNo;
    }
    
    public InputStream getBook_Image () {
      return book_Image;
    }
    
    public int getBook_Copy()
    {
      return book_Copy;
    }
    
    public int getBook_total()
    {
      return book_total;
    }
    
    public int getDuration_days()
    {
      return duration_days;
    }
    
    public void setBook_SerialNo(String book_SerialNo) {
        this.book_SerialNo = book_SerialNo;
    }



    public void setBook_Name(String book_Name) {
        this.book_Name = book_Name;
    }

 

    public void setBook_Location(String book_Location) {
        this.book_Location = book_Location;
    }


    public void setBook_Description(String book_Description) {
        this.book_Description = book_Description;
    }



    public void setBook_ID(int book_ID) {
        this.book_ID = book_ID;
    }

  }