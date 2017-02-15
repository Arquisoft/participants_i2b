package domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Nicolás on 15/02/2017.
 */
public class UserInfo {

    private String firstName;
    private String lastName;
    private int age;
    private String id;
    private String email;

    public UserInfo(String firstName, String lastName, int age, String id, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
        this.email = email;
    }
}
