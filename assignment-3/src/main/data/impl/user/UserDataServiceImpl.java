package main.data.impl.user;

import main.common.user.*;
import main.data.service.user.UserDataService;

import java.util.List;

public class UserDataServiceImpl implements UserDataService {

    private static UserDataServiceImpl singleton = new UserDataServiceImpl();

    public static UserDataServiceImpl getInstance() {
        return singleton;
    }

    private UserDataServiceImpl() { }

    @Override
    public void add(Teacher u) {

    }

    @Override
    public void add(Graduate u) {

    }

    @Override
    public void add(Undergraduate u) {

    }

    @Override
    public void add(Administrator u) {

    }

    @Override
    public void add(User u) {

    }

    @Override
    public void remove(User u) {

    }

    @Override
    public void update(User u) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

}
