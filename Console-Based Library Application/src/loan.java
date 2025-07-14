import java.util.Date;

public class loan {
  private String loanBookID;
  private int loanMemberID;
  private String loanDateBorrowed;
  private String loanDueDate;
  private String loanReturnedStatus;
  private String loanDateReturned;
  
  public loan() {
    loanBookID = null;
    loanMemberID = 0;
    loanDateBorrowed = null;
    loanDueDate = null;
    loanReturnedStatus = null;
    loanDateReturned = "";
  }
  
  public loan(String loanBookID, int loanMemberID, String loanDateBorrowed, String loanDueDate, String loanReturnedStatus, String loanDateReturned) {
    this.loanBookID = loanBookID;
    this.loanMemberID = loanMemberID;
    this.loanDateBorrowed = loanDateBorrowed;
    this.loanDueDate = loanDueDate;
    this.loanReturnedStatus = loanReturnedStatus;
    this.loanDateReturned = loanDateReturned;
  }
  
  public loan(String loanBookID, int loanMemberID, String loanDateBorrowed, String loanDueDate, String loanReturnedStatus) {
    this.loanBookID = loanBookID;
    this.loanMemberID = loanMemberID;
    this.loanDateBorrowed = loanDateBorrowed;
    this.loanDueDate = loanDueDate;
    this.loanReturnedStatus = loanReturnedStatus;
    this.loanDateReturned = "";
  }

  public void setLoanBookID (String lbID) {
   lbID = loanBookID;
  }
  
  public void setLoanMemberID (int lmID) {
    lmID = loanMemberID; 
  }
  
  public void setLoanDateBorrowed (String ldBorrowed) {
    ldBorrowed = loanDateBorrowed;
  }
  
  public void setLoanDueDate (String lDueDate) {
    lDueDate = loanDueDate;
  }
  
  public void setLoanMemberEmail (String lrStatus) {
    lrStatus = loanReturnedStatus;
  }
  
  public void setLoanDateReturned (String ldReturned) {
    ldReturned = loanDateReturned;
  }
  
  public String getLoanBookID() {
    return loanBookID;
  }
  
  public int getLoanMemberID() {
    return loanMemberID;
  }
  
  public String getLoanDateBorrowed(){
    return loanDateBorrowed;
  }
  public String getLoanDueDate() {
    return loanDueDate;
  }
  
  public String getLoanReturnedStatus() {
    return loanReturnedStatus;
  }
  
   public String getLoanDateReturned() {
    return loanDateReturned;
  }
  public String toString() {
    return loanBookID + "," + loanMemberID + "," +  loanDateBorrowed + "," + loanDueDate + "," + loanReturnedStatus + "," + loanDateReturned;
  }
}
 