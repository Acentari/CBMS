package model;

import javax.persistence.*;
import java.io.Serializable;
@NamedQuery(name="User.getuser", query="select case when count (u.username) > 0 then true else false end FROM User u where u.username =:username AND u.pass =:password")

@Table(name = "users")
@Entity
public class User implements Serializable {
    @Column(name = "email")
    private String email;
    @Column(name = "fname")
    private String fName;
    @Column(name = "lname")
    private String lName;
//    Select * from users

    @Column(name = "password")
    private String pass;

    @Id
    private String username;

    public User(){}

    public void setLname(String lName) {
        this.lName = lName;
    }

    public void setFname(String fName) {
        this.fName = fName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getlName() {
        return lName;
    }

    public String getfName() {
        return fName;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String id) {
        this.username = id;
    }
}
