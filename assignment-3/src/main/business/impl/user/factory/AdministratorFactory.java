package main.business.impl.user.factory;

import main.business.impl.user.PersonalInfoMediator;
import main.common.user.Administrator;

public class AdministratorFactory extends UserFactory {

    private static final AdministratorFactory singleton = new AdministratorFactory();

    public static AdministratorFactory getInstance() {
        return singleton;
    }

    private AdministratorFactory() {

    }

    @Override
    public Administrator create(String username, String password) {
        Administrator a = new Administrator(username, password);
        PersonalInfoMediator.getInstance().addAdministrator(a);
        return a;
    }

}
