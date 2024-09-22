import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Register {
    private List<Member> membersList = new ArrayList<>();
    private static int memberCount = 0;  

    
    public Member createMember(String email, String password, String name, String phoneNo) {
        for (Member member : membersList) {
            if (member.getEmail().equals(email)) {
                System.out.println("A member with this email already exists.");
                return null;
            }
        }

        String memberID = generateMemberID();  
        Member newMember = new Member(memberID, email, password, name, phoneNo);
        membersList.add(newMember);
        saveMembersToFile(); 
        System.out.println("Member registered successfully.");
        return newMember;
    }

   
    private String generateMemberID() {
        memberCount++;
        return String.format("MO%03d", memberCount);
    }

    private void saveMembersToFile() {
        try (FileWriter writer = new FileWriter("members.txt")) {
            for (Member member : membersList) {
                writer.write(member.toString() + "\n");
            }
            System.out.println("All members saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving members to file: " + e.getMessage());
        }
    }

   
    public Member findMemberByEmail(String email) {
        for (Member member : membersList) {
            if (member.getEmail().equals(email)) {
                return member;
            }
        }
        return null;
    }

   
    public void viewAllMembers() {
        if (membersList.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            for (Member member : membersList) {
                System.out.println(member.toString());
            }
        }
    }

 
    public void updateMember(String email, String name, String phoneNo) {
        Member member = findMemberByEmail(email);
        if (member != null) {
            member.updateProfile(name, phoneNo);
            saveMembersToFile(); 
            System.out.println("Member updated successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

  
    public void deleteMember(String email) {
        Member memberToDelete = findMemberByEmail(email);
        if (memberToDelete != null) {
            membersList.remove(memberToDelete);
            saveMembersToFile();
            System.out.println("Member deleted successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }
}
