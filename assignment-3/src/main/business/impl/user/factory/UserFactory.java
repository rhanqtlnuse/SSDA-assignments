package main.business.impl.user.factory;

import main.common.user.User;

public abstract class UserFactory {

    public abstract User create(String username, String password);

}
