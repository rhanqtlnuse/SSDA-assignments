package main.business.impl.user.factory;

import main.common.user.Teacher;
import main.common.user.Undergraduate;
import main.common.user.User;

public class TeacherFactory extends UserFactory {

    private static final TeacherFactory singleton = new TeacherFactory();

    public static TeacherFactory getInstance() {
        return singleton;
    }

    private TeacherFactory() {

    }

    @Override
    public Teacher create(String username, String password) {
        return new Teacher(username, password);
    }

}
