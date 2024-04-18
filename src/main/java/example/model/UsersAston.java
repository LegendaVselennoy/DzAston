package example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersAston {

    private int id;
    private String username;
    private String surname;

    public UsersAston() {
    }

    public UsersAston(int id, String username, String surname) {
        this.id = id;
        this.username = username;
        this.surname = surname;
    }
}
