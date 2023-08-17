package Store.Database;

public class Admin {
    // 
    String username;
    String password;

    public Admin ()
    {
        this.username = "admin";
        this.password = "admin";
    }

    public boolean login(String username, String password)
    {
        return (this.password.equals(password) && this.username.equals(username));
    }
}
