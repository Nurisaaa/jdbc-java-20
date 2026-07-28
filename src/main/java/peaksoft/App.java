package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoImpl;
import peaksoft.models.User;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        UserDao userDao = new UserDaoImpl();
        userDao.createUserTable();
//        System.out.println(userDao.saveUser(new User("Sapargul", "s@gmail.com", "123456")));
//        System.out.println(userDao.getUserById(2L));
        System.out.println(userDao.getAllUsers());
        System.out.println(userDao.deleteUserById(2L));
        System.out.println(userDao.getAllUsers());
    }
}
