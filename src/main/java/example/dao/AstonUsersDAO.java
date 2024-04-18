package example.dao;

import example.db.DBConnector;
import example.model.UsersAston;
import example.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AstonUsersDAO implements UserRepository {

    private static final String INSERT_STUDENT_SQL = "INSERT INTO library.students (name, surname) VALUES (?, ?);";
    private static final String SELECT_ALL_STUDENTS = "select * from library.students";
    private static final String DELETE_STUDENT_SQL = "delete from library.students where id = ?;";
    private static final String UPDATE_STUDENT_SQL = "update library.students set name = ?,surname= ? where id = ?;";


    public void addUser(UsersAston user) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateUser(UsersAston user) {
        boolean rowUpdated;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getSurname());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }

    public boolean deleteUser(int id) {
        boolean rowDeleted;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public UsersAston getUserById(int id) {

        UsersAston user = new UsersAston();

        Connection connect = DBConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connect.prepareStatement("select * from USER where id=?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet
                    = preparedStatement.executeQuery();


            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
            }

            // close the database connection
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return user;
    }

    public List<UsersAston> getAllUsers() {
        List<UsersAston> students = new ArrayList<>();
        try (Connection connection = DBConnector.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                students.add(new UsersAston(id, name, surname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

}
