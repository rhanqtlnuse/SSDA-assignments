package main.data.service.user;

import main.common.user.*;

import java.util.List;

public interface UserDataService {

    void add(Teacher u);
    void add(Graduate u);
    void add(Undergraduate u);
    void add(Administrator u);
    void add(User u);

    void remove(User u);

    void update(User u);

    User findByUsername(String username);
    List<User> findAll();

}
