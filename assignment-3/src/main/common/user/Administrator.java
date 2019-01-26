package main.common.user;

public class Administrator extends User {

    private static final int ADMIN_CHECK_OUT_LIMIT = Integer.MAX_VALUE;

    public Administrator(String username, String password) {
        super(username, password);
        setLimit(ADMIN_CHECK_OUT_LIMIT);
    }

}
