package ua.edunung.se.dao;

import ua.edunung.se.entity.User;

public class UserDAO extends BaseDAO<User> {
    public UserDAO() {
        super(User.class);
    }
}
