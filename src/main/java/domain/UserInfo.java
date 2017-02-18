package domain;

import java.io.Serializable;

/**
 * Created by Nicolás on 15/02/2017.
 */
public class UserInfo implements Serializable{

    private String firstName;
    private String lastName;
    private int age;
    private String userId;
    private String email;

    UserInfo(){

    }

    public UserInfo(String firstName, String lastName, int age, String userId, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
