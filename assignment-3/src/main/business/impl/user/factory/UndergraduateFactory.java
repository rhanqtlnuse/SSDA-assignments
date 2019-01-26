package main.business.impl.user.factory;

import main.common.user.Undergraduate;

public class UndergraduateFactory extends UserFactory {

    private static final UndergraduateFactory singleton = new UndergraduateFactory();

    public static UndergraduateFactory getInstance() {
        return singleton;
    }

    private UndergraduateFactory() {

    }

    @Override
    public Undergraduate create(String username, String password) {
        return new Undergraduate(username, password);
    }

}
