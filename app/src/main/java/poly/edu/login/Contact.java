package poly.edu.login;

public class Contact {

    int id;
    String name, email ,sdt , pass ;
////
    public void setId(int id){ this.id = id ;}
    public int getId (){return this.id ;}


    public  void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
////

    public  void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }
////

    public  void setSdt(String sdt) { this.sdt = sdt; }
    public String getSdt()
    {
        return this.sdt;
    }
////

    public  void setPass(String pass)
    {
        this.pass = pass;
    }
    public String getPass()
    {
        return this.pass;
    }
}
