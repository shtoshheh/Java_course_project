package Entities;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 104413990234785730L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long user_id;
    protected String login;
    protected String password;
    protected String role;

    public User(){ }

    public User(String login, String password){
        this.password = password;
        this.login = login;
    }
    public User(String login, String password, String  r){
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

    public String getRole() {
        return role;
    }

    public Long getId() {
        return user_id;
    }

}
