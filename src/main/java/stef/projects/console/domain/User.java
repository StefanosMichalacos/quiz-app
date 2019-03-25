package stef.projects.console.domain;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String Password;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    private UserRole userRole;

    public User (long id,String firstName,String lastName, String username, String email, String password, UserRole userRole){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.Password = password;
        this.userRole = userRole;
    }

}
