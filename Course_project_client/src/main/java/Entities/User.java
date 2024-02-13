package Entities;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 104413990234785730L;
    protected Long user_id;
    protected String login;
    protected String password;
    protected String role;

    public User(){ }

    public User(String login, String password){
        this.password = password;
        this.login = login;
    }
    public User(String login, String password, String r){
        this.password = password;
        this.login = login;
        this.role = r;

    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return user_id;
    }

    public String getRole(){return role;}

}
