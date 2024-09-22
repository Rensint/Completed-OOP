public class Signup {
    private String name;
    private String email;
    private String password;

   
    public Signup(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nEmail: " + email;
    }
}
