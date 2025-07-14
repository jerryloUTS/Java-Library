public class member {
  private int memberID;
  private String memberPhone;
  private String memberFullName;
  private String memberAddress;
  private String memberEmail;
  
  public member() {
  }
  public member(int memberID, String memberPhone, String memberFullName, String memberAddress, String memberEmail) {
    this.memberID = memberID;
    this.memberPhone = memberPhone;
    this.memberFullName = memberFullName;
    this.memberAddress = memberAddress;
    this.memberEmail = memberEmail;
    

  }

  public void setMemberID (int mID) {
    mID = memberID;
  }
  
  public void setMemberPhone (String mPhone) {
    mPhone = memberPhone;
  }
  
  public void setMemberName (String mName) {
    mName = memberFullName;
  }
  
  public void setMemberAddress (String mAddress) {
    mAddress = memberAddress;
  }
  
  public void setMemberEmail (String mEmail) {
    mEmail = memberEmail;
  }
  
  public int getMemberID() {
    return memberID;
  }
  
  public String getMemberPhone() {
    return memberPhone;
  }
  
  public String getMemberName(){
    return memberFullName;
  }
  public String getMemberAddress() {
    return memberAddress;
  }
  
  public String getMemberEmail() {
    return memberEmail;
  }
  
  public String toString() {
    return memberID + "," + memberPhone +
           "," + memberFullName + "," + memberAddress + "," + memberEmail;
  }
}
 