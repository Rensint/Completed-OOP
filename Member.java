public class Member extends User {
    private String memberID;
    private String phoneNo;

   
    public Member(String email, String password, String name) {
        super(email, password, name);
        this.memberID = generateMemberID();
    }

    
    public Member(String memberID, String email, String password, String name, String phoneNo) {
        super(email, password, name);
        this.memberID = memberID;
        this.phoneNo = phoneNo;
    }

   
    private String generateMemberID() {
        return "MO" + System.currentTimeMillis();
    }

    public String getMemberID() {
        return memberID;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "MemberID: " + memberID + "\nName: " + getName() + "\nEmail: " + getEmail() + "\nPhone: " + phoneNo;
    }

   
    public void updateProfile(String name, String phoneNo) {
        setName(name);
        this.phoneNo = phoneNo;
    }
}
