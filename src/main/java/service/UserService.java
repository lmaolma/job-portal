package service;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;


    public boolean registerUser(User user) {
        return userDAO.registerUser(user); // ✅ Không còn static
    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username); // ✅ Thêm nếu servlet cần gọi
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
