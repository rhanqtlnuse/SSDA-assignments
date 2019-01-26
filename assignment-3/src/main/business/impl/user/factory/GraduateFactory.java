package main.business.impl.user.factory;

import main.common.user.Graduate;

public class GraduateFactory extends UserFactory {

    private static final GraduateFactory singleton = new GraduateFactory();

    public static GraduateFactory getInstance() {
        return singleton;
    }

    private GraduateFactory() {

    }

    @Override
    public Graduate create(String username, String password) {
        return new Graduate(username, password);
    }

}
