import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class book {
  private String bookID;  
  private int bookAisle;                  
  private String bookTitle;
  private String bookAuthor;  
  private String bookShelf;
  
 public book() {
   bookID = null;
   bookAisle = 0; 
   bookTitle = null;
   bookAuthor = null;
   bookShelf = null;
  }
  public book(String bookID, String bookTitle, String bookAuthor, int bookAisle, String bookShelf) {
    this.bookID = bookID;
    this.bookAisle = bookAisle;
    this.bookTitle = bookTitle;
    this.bookAuthor = bookAuthor;
    this.bookShelf = bookShelf;
  }
  
  public void setBookID (String bID) {
    this.bookID = bID;
  }
  
  public void setBookAuthor (String bAuthor) {
    this.bookAuthor = bAuthor;
  }
  
  public void setBookTitle (String bTitle) {
    bookTitle = bTitle;
  }

  public void setBookAisle (int bAisle) {
    this.bookAisle = bAisle;
  }
  
  public void setbookShelf (String bShelf) {
    this.bookShelf = bShelf;
  }
 
  public String getBookID() {
    return this.bookID;
  }
  
  public int getBookAisle() {
    return this.bookAisle;
  }
  
  public String getBookTitle() {
    return bookTitle;
  }

  public String getBookAuthor() {
    return this.bookAuthor;
  }

  public String getBookShelf() {
    return this.bookShelf;
  }
  public String toString() {
    return bookID + "," + bookTitle + "," + bookAuthor + "," + bookAisle + "," + bookShelf;
  }
 
  public Boolean hasBook(String bookTitle) {
    
    if (this.bookTitle.toLowerCase().contains(bookTitle.toLowerCase())) {
      return true;
    }
    else {
      return false;
    }
  }
  public static void borrowed() {
    // TODO Auto-generated method stub
    
  }
  
  }
