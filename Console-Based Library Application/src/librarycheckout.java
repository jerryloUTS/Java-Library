//20261346 Jerry Lo
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.Duration;


public class librarycheckout {
  public  ArrayList<book> booksAvailable = new ArrayList<book>();
  public ArrayList<member> members = new ArrayList<member>(); 
  public ArrayList<loan> loan = new ArrayList<loan>();
  public ArrayList<book> books = new ArrayList<book>();
  public ArrayList<book> booksBorrowed = new ArrayList<book>();
  public ArrayList<book> tempBook = new ArrayList<book>();
  
  private static Scanner keyboard; 
  public static void main(String[] args) throws IOException {
    new librarycheckout().libraryCheckoutUse();
  }
  public boolean isMember(int id){
    for (member thisMember : members){
      if(thisMember.getMemberID() == id){
        return true;
      }
    }
    return false;
  }
  public book booksAvailable(String title){
    for (book bookAvailable : booksAvailable){
      if (bookAvailable.hasBook(title)){
        return bookAvailable;
      }
    }
    return null;  
  }

  public book findBook(String bookID) {
    for (book thisBook : books) {
      if (thisBook.getBookID().equals(bookID)) {
      return thisBook;
      }
    }
    return null;
  }
  
  public boolean hasBook(String bookID) {
    for (book thisBook : books) {
      if (thisBook.getBookID().equals(bookID)) {
      return true;
      }
    }
    return false;
  }
  
  public boolean hasMember(int ID) {
    for (member thisMember : members) {
      if (thisMember.getMemberID() == ID) {
      return true;
      }
    }
    return false;
  }
  
  
  public book findAvailableBook(String bookID) {
    for (book thisBook : booksAvailable) {
      if (thisBook.getBookID().equals(bookID)) {
      return thisBook;
      }
    }
    return null;
  }
  
  public book findBorrowedBook(String bookID) {
    for (book thisBook : booksBorrowed) {
      if (thisBook.getBookID().equals(bookID)) {
      return thisBook;
      }
    }
    return null;
  }

  public librarycheckout(){
    try { 
        Scanner sc = new Scanner(new File("books.txt"));
        //initialises books from txt file
        while (sc.hasNextLine()) {
          String input = sc.nextLine();
          String[] splittedInput = input.split(",");
          books.add(new book(splittedInput[0], splittedInput[1], splittedInput[2], Integer.parseInt(splittedInput[3]), splittedInput[4]));
        }
        sc.close();
      
      
        //initialises members from txt file
        sc = new Scanner (new File("members.txt"));
        while (sc.hasNextLine()) {
          String input = sc.nextLine();
          String [] splittedInput = input.split(",");
          members.add(new member(Integer.parseInt(splittedInput[0]), splittedInput[1], splittedInput[2], splittedInput[3], splittedInput[4]));
        }
        sc.close();

        //initialises loans from txt file
        sc = new Scanner (new File("loans.txt"));
        while (sc.hasNextLine()) {
          String input = sc.nextLine();
          String [] splittedInput = input.split(",");              
          try {
            loan.add(new loan(splittedInput[0], Integer.parseInt(splittedInput[1]), splittedInput[2], splittedInput[3], splittedInput[4], splittedInput[5])); //In the event that the line has 5 elements, it inputs those 5.
          }         
          catch (Exception e) {
            loan.add(new loan(splittedInput[0], Integer.parseInt(splittedInput[1]), splittedInput[2], splittedInput[3], splittedInput[4])); //In the event that the line only has 4 elements, it inputs only those 4.    
          }      
        }
        sc.close();
      
        for (loan thisLoan : loan){
          for (book thisBook : books){
            if (thisBook.getBookID().equals(thisLoan.getLoanBookID()) && thisLoan.getLoanReturnedStatus().equals("no")){ //Self-explanatory. Adds lines to booksBorrowed if the LoanReturnedStatus is "no".
              booksBorrowed.add(thisBook);
            }
          }
        }     
        try{
          for(book thisBook : books){
            booksAvailable.add(thisBook);
          }
          for (book borrowedBook : booksBorrowed){
            booksAvailable.remove(borrowedBook);
          }
        //}
      }
        catch(Exception e){
        }     
      sc = new Scanner(new File("loans.txt"));
      sc.close();
    }
    catch(FileNotFoundException e){
      System.out.println("Error; file not found."); //Displays when file is not found/non-existent.
    }
  }

  public void addBook(){
    String bookTitle = keyboard.nextLine();    
    System.out.println("Enter the book title: ");      
    bookTitle = keyboard.nextLine();    
    while (bookTitle == "") {
      System.out.println("Empty input. Please enter a book title: ");
      bookTitle = keyboard.nextLine();
    }   
    System.out.println("Enter the author's name: ");
    String bookAuthor = keyboard.nextLine(); 
    while (bookAuthor == "") {
      System.out.println("Empty input. Please enter an author name: ");
      bookAuthor = keyboard.nextLine();
    }   
    System.out.println("Enter the ISBN: ");
    String bookID = keyboard.next();
    boolean dupe = false;
      while(dupe == false){
        if (hasBook(bookID)){
          System.out.println("Duplicate ID. Please re-enter: ");
          bookID = keyboard.next();
        }
        else{
          dupe = true;
        }
      }
    while (bookID.length() > 6 || bookID.length() < 6) {
      System.out.println("Invalid input. ISBN MUST be 6 digits.");
      System.out.println("Enter the ISBN: ");
      bookID = keyboard.next();  
    } 
    
    System.out.println("Enter the book aisle: ");          
    int bookAisle = keyboard.nextInt();
    while (bookAisle > 999 || bookAisle < 0) {
      System.out.println("Invalid input. Aisle MUST be between 1 - 999.");
      System.out.println("Enter the book aisle: ");
      bookAisle = keyboard.nextInt();
    }
    String bookShelf = keyboard.nextLine();    
    System.out.println("Enter the book location/shelf: ");
    bookShelf = keyboard.nextLine();      
    while (bookShelf == "") {
      System.out.println("Empty input. Please enter the location or shelf: ");
      bookShelf = keyboard.nextLine();
    }
    books.add(new book (bookID, bookTitle, bookAuthor, bookAisle, bookShelf));
  }

  public void addMember(){
    System.out.println("Enter your ID: ");   
     int memberID = keyboard.nextInt(); 
     int lengthOfInt = String.valueOf(memberID).length();
     while (lengthOfInt < 6 || lengthOfInt > 6){
       System.out.println("ID MUST be 6 digits. Please re-enter: ");
       memberID = keyboard.nextInt();
       lengthOfInt = String.valueOf(memberID).length();
     }
     boolean dupe = false;
     while(dupe == false){
       if (hasMember(memberID)){
         System.out.println("Duplicate ID. Please re-enter: ");
         memberID = keyboard.nextInt();
       }
       else {
         dupe = true;
       }
    }
    String memberPhone = keyboard.nextLine();
    System.out.println("Enter your phone number ");
    memberPhone = keyboard.nextLine(); 
    while (memberPhone == "") {
      System.out.println("");
      memberPhone = keyboard.nextLine();
    }   
    System.out.println("Enter your full name: ");      
    String memberFullName = keyboard.nextLine();    
    while (memberFullName == "") {
      System.out.println("");
      memberFullName = keyboard.nextLine();
    }   
    System.out.println("Enter your address: ");
    String memberAddress = keyboard.nextLine(); 
    while (memberAddress == "") {
      System.out.println("");
      memberAddress = keyboard.nextLine();
    }   
    System.out.println("Enter your email: ");
    String memberEmail = keyboard.next();
    //int atLocation = memberEmail.indexOf('@');
    //String domain = memberEmail.substring(atLocation+1); 
    //String userName = memberEmail.substring(0, atLocation);         
    while (!memberEmail.contains("@")) {
      System.out.println("Invalid input. Please re-enter your email with an @.");
      memberEmail = keyboard.next();          
    }
    members.add(new member (memberID, memberPhone, memberFullName, memberAddress, memberEmail));
    System.out.println(members);
  }

  public void findBook(){
    System.out.println("Enter book title: ");
    String input = keyboard.nextLine();
    input = keyboard.nextLine();
    System.out.printf("%-10s %-23s %-22s %-10s %-10s %-15s %-5s%n", "ISBN", "Title", "Author", "Aisle", "Shelf", "Availability", "Due Date" );
    System.out.println("----------------------------------------------------------------------------------------------------------");                  
    for(book thisBook : booksAvailable) {
      if (thisBook.hasBook(input)) {               
        System.out.printf("%-10s %-23s %-22s %-10s %-10s %-15s %-5s%n", thisBook.getBookID(), thisBook.getBookTitle(), thisBook.getBookAuthor(), thisBook.getBookAisle(), thisBook.getBookShelf(), "Available", "N/A");                         
      }
    }
    for (loan thisLoan : loan) {
      for(book thisBook : booksBorrowed) {     
        if (thisBook.hasBook(input) && thisBook.getBookID().equals(thisLoan.getLoanBookID())) {               
          System.out.printf("%-10s %-23s %-22s %-10s %-10s %-15s %-5s%n", thisBook.getBookID(), thisBook.getBookTitle(), thisBook.getBookAuthor(), thisBook.getBookAisle(), thisBook.getBookShelf(), "Not Available", thisLoan.getLoanDueDate());                         
        }      
      }
    }
  }

  private void overdueBook(){
    //Scanner sc = new Scanner(System.in);
    System.out.println("Overdue books ");
    System.out.printf("%-25s %-20s %-15s %-15s %-13s %-5s%n", "Book Title", "Book Author", "Member Name", "Member Phone", "Due Date", "Days Overdue" );
    System.out.println("------------------------------------------------------------------------------------------------------------");                  
    for(loan thisLoan : loan){     
      for (member thisMember : members) {
        for(book thisBook : books){
          try {
            Date c = new Date();
            Date ourDateObject = new Date();
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd"); // Formats dates into the specified format.
            ourDateObject = formatter1.parse(thisLoan.getLoanDueDate()); // Parses (or formats) the dates in the text file into ourDateObject.
            long diff = c.getTime() - ourDateObject.getTime(); // ourDateObject is the date the book is due, whilst c is the current date. Thus, the time between the two is calculated.
            long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // The calculated time is in milliseconds by default. This converts it into days.
            if(thisLoan.getLoanBookID().equals(thisBook.getBookID()) && thisLoan.getLoanMemberID() == thisMember.getMemberID() && thisLoan.getLoanReturnedStatus().equals("no")){ //Matches the book ID, memberID AND checks if the returned status is "no".
              System.out.printf("%-25s %-20s %-15s %-15s %-13s %-5s%n", thisBook.getBookTitle(), thisBook.getBookAuthor(), thisMember.getMemberName(), thisMember.getMemberPhone(), thisLoan.getLoanDueDate(), days, "\n"); // If the current date has not surpassed the due date, the number of days will return as negative. Not too sure how to fix this, or if possible.        
              
            }
          }
          catch (Exception e) {         
          }          
        }
      }
    }
  }

  private void borrowBook(){
    Date ourDateObject = new Date();
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DAY_OF_WEEK, 14);
    Date time = c.getTime();
    SimpleDateFormat formatter1 = new   SimpleDateFormat("yyyy-MM-dd");
    String strDate = formatter1.format(ourDateObject);   // Formats "ourDateObject" from a Date to a String
    String returnDate = formatter1.format(time);
    System.out.println("Enter your member ID: ");
    int id = keyboard.nextInt();
    if (isMember(id)) {
      String title = keyboard.nextLine();
      boolean isBorrowed = false;
      System.out.println("Enter the book you want to borrow (Input is both case-sensitive and required to be the exact title): ");
      title = keyboard.nextLine();  
        for(book thisBook : booksAvailable) {
          if(thisBook.getBookTitle().equals(title)) {       
            booksBorrowed.add(thisBook); // Adds to booksBorrowed array.
            booksAvailable.remove(thisBook); // Removes from booksAvailable array.
            System.out.println(title + " has been borrowed.");  
            isBorrowed = true;
            loan.add(new loan(thisBook.getBookID(), id, strDate, returnDate, "no"));
            break;
          }
        } 
        if(isBorrowed == false) {
        System.out.println("Book not available.");
        }
    }
  }
  private void returnBook() throws IOException {
    Date ourDateObject = new Date();
    Date c1 = new Date();
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DAY_OF_WEEK, 14);    
    Date time = c.getTime();
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = formatter1.format(ourDateObject);
    String dueDate = formatter1.format(time);
    String returnDate = formatter1.format(c1);
    System.out.println("Enter your member ID: ");
    int id = keyboard.nextInt();
    if (isMember(id)) {
      String title = keyboard.nextLine();
      boolean isBorrowed = false;
      System.out.println("Enter the book you want to return (Input is both case-sensitive and required to be the exact title): ");
      title = keyboard.nextLine();  
      for(book thisBook : booksBorrowed) {
        if(thisBook.getBookTitle().equals(title)) {       
          booksAvailable.add(thisBook);
          booksBorrowed.remove(thisBook);      
          System.out.println(title + " has been returned.");  
          isBorrowed = true;
          if (title.equals(thisBook.getBookTitle())) {
            //loan.add(new loan(thisBook.getBookID(), id, strDate, dueDate, "yes", returnDate));
            int counter = 0;
            //loan test1 = null;
            for (loan thisLoan: loan) {
              counter++;
              //if (thisLoan.equals(new loan(thisBook.getBookID(), id, strDate, dueDate, "no", null))) {
              if (thisLoan.toString().contains(thisBook.getBookID())) { // Keeps counting until this specific if statement is met. This lets the program know which line to change.
                //test1 = thisLoan;            
                break; 
              }
              //System.out.print(counter); 
            }
            loan testLine = new loan(thisBook.getBookID(), id, strDate, dueDate, "yes", returnDate); //Simply defines testLine as the line specified.
            //test1.setLoanDueDate("asjkldosahj");            
            loan.set(counter - 1, testLine); //Sets the line that matches the specific criterion as "testLine". It gets the line specified and removes such.          
            //System.out.print(booksAvailable);            
            break;
          }  
        }
        
      }if(isBorrowed == false) {
          System.out.println("Book is still available.");
        }
    }
  }
  private void libraryCheckoutUse(){
    int menuChoice = 0;
    keyboard = new Scanner(System.in);
    try{
      while (menuChoice != 8) { //Loops menu as long as the choice is NOT 8.
        System.out.println("1. Add a book");
        System.out.println("2. Add a member");
        System.out.println("3. Find book information"); 
        System.out.println("4. Overdue Book Information");
        System.out.println("5. Borrow a book");
        System.out.println("6. Return a book");
        System.out.println("7. Save");
        System.out.println("8. Exit Program");
        menuChoice = keyboard.nextInt();     
        switch (menuChoice) {
          case 1:
            addBook();
            break;
      
          case 2:
            addMember();
            break;
          
          case 3:
            findBook();
            break;  
            
          case 4:
            overdueBook();
            break;
          
          case 5:       
            borrowBook();
            break;
                 
          case 6:
            returnBook();
            break;
        
          case 7: // Saves the changes made to each array.
            FileWriter writer = new FileWriter("books.txt");
            for (book book: books) {
              writer.write(book + System.lineSeparator()); // Saves from "book" array to "books.txt".
            }
            writer.close();
            writer = new FileWriter("members.txt");
            for (member member: members) {
              writer.write(member + System.lineSeparator()); // Saves from "member" array to "member.txt".
            }
            writer.close();
            writer = new FileWriter("loans.txt");
            for (loan loan: loan) {
              writer.write(loan + System.lineSeparator()); // Saves from "loan" array to "loans.txt".
            }
            writer.close();
            menuChoice = 0;
            break;
        
          case 8: 
            System.out.println("Exiting program...");
            System.exit(menuChoice); // Exits the case.
        
          default:
            System.out.println("Invalid input. Please try again. \n");
            break;    
        }
      }
    }
  catch (Exception e) {
    System.out.println("Error; file not found.");
  }
  }
}