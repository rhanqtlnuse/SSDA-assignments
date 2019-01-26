package main.business.impl;

import main.business.service.GrantBusinessService;

public class GrantBusinessServiceImpl implements GrantBusinessService {

    private static final GrantBusinessService SINGLETON = new GrantBusinessServiceImpl();

    public static GrantBusinessService getInstance() {
        return SINGLETON;
    }

    private GrantBusinessServiceImpl() { }

}
