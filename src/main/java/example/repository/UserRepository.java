package example.repository;

import example.model.UsersAston;

import java.util.List;

public interface UserRepository {
    void addUser(UsersAston user);

    boolean updateUser(UsersAston user);

    boolean deleteUser(int id);

    UsersAston getUserById(int id);

    List<UsersAston> getAllUsers();
}
