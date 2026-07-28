package peaksoft.dao;

import peaksoft.config.DbConnection;
import peaksoft.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final Connection connection = DbConnection.getConnection();
    @Override
    public void createUserTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("" +
                    "create table if not exists users(id serial primary key," +
                    "name varchar(50)," +
                    "email varchar unique," +
                    "password varchar);");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String saveUser(User user) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("insert into users(name,email,password) values(?,?,?)");
            prepareStatement.setString(1, user.getName());
            prepareStatement.setString(2, user.getEmail());
            prepareStatement.setString(3, user.getPassword());
            prepareStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return "Saved";
    }

    @Override
    public User getUserById(Long id) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                users.add(user);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public String deleteUserById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return "Deleted";
    }
}
