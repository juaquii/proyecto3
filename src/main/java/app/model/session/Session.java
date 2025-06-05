package app.model.session;

import app.model.entity.User;

public class Session {
    private static Session _instance;
    private static User userLogged;

    private Session() {

    }

    public void logIn(User user) {
        userLogged = user;
    }

    public static Session getInstance() {
        if (_instance == null) {
            _instance = new Session();
            _instance.logIn(userLogged);
        }
        return _instance;
    }

    public User getUserLogged() {
        return userLogged;
    }

    public static void LogOut() {
        userLogged = null;
    }
}
