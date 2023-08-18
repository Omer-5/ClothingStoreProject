package Store.Database;

public class Admin {
    // 
    String password;

    public Admin ()
    {
        this.password = "admin";
    }

    public boolean checkCred(String password)
    {
        return this.password.equals(password);
    }
}
