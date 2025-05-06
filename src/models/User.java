package models;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email; }

    public String getPassword(){
        return password;
    }

    public void setName(String name ){
        this.name = name;
    }

    public void setEmail(String email ){
        this.email = email;
    }

    public void setPasswordHash(String password ){
        this.password = password;
    }

}
