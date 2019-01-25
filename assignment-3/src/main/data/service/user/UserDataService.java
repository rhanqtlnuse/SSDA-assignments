package main.data.service.user;

import main.common.user.*;

public interface UserDataService {

    void add(Teacher u);
    void add(Graduate u);
    void add(Undergraduate u);
    void add(Administrator u);

    void remove(User u);

    void update(Teacher u);
    void update(Graduate u);
    void update(Undergraduate u);
    void update(Administrator u);

    User findByUsername(String username);

}