package main.business.user;

import main.common.Administrator;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUserInfoBroker {

    private List<Administrator> admins;

    public AbstractUserInfoBroker() {
        this.admins = new ArrayList<>();
    }

    public final void addAdmin(Administrator admin) {
        admins.add(admin);
    }

    public final void removeAdmin(Administrator admin) {
        admins.remove(admin);
    }

    private void informAll() {
        for (Administrator admin : admins) {

        }
    }

    private void inform(/* TODO */) {

    }

}
