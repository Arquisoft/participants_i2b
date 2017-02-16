package domain;

import java.io.Serializable;

/**
 * Created by Nicolás on 15/02/2017.
 */
public class UserInfo implements Serializable{

    private String firstName;
    private String lastName;
    private int age;
    private String id;
    private String email;

    UserInfo(){

    }

    public UserInfo(String firstName, String lastName, int age, String id, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
