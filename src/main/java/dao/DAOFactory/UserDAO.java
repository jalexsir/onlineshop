package dao.DAOFactory;

import entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    public User getUserByLogin(String login);

    public boolean updateUser(User user);

    public boolean insertUser(User user);

    public User getUserByLoginAndPass(String login, String password);
}
