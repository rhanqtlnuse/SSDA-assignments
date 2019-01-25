package main.data.impl.user;

import main.common.user.*;
import main.data.service.user.UserDataService;

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
    public void remove(User u) {

    }

    @Override
    public void update(Teacher u) {

    }

    @Override
    public void update(Graduate u) {

    }

    @Override
    public void update(Undergraduate u) {

    }

    @Override
    public void update(Administrator u) {

    }

    @Override
    public void findByUsername(String username) {

    }

}