package Store;

public class Admin {
    // 
    String name;
    String password;

    public Admin ()
    {
        password = "dfsdfsdf";
    }

    public boolean login(String password)
    {
        return this.password == password;
    }
}
