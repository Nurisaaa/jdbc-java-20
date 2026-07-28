package peaksoft.dao;

import peaksoft.models.User;

import java.util.List;

public interface UserDao {
    void createUserTable();
    String saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    String deleteUserById(Long id);
}
