package main.data.impl.user;

import main.business.impl.user.PersonalInfoMediator;
import main.common.user.*;
import main.data.service.user.UserDataService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDataServiceImpl implements UserDataService {

    private static final Set<Teacher> TEACHERS = new HashSet<>();
    private static final Set<Graduate> GRADUATES = new HashSet<>();
    private static final Set<Undergraduate> UNDERGRADUATES = new HashSet<>();
    private static final Set<Administrator> ADMINISTRATORS = new HashSet<>();

    static {
        TEACHERS.add(new Teacher("IN968720", "123456"));
        TEACHERS.add(new Teacher("IN173947", "123456"));

        GRADUATES.add(new Graduate("MF832042", "987654"));
        GRADUATES.add(new Graduate("MF731325", "987654321"));

        UNDERGRADUATES.add(new Undergraduate("BH734204", "543167"));
        UNDERGRADUATES.add(new Undergraduate("BH453652", "12346123456"));

        Administrator a = new Administrator("AD428201", "admin");
        ADMINISTRATORS.add(a);
        PersonalInfoMediator.getInstance().addAdministrator(a);
    }

    private static UserDataServiceImpl singleton = new UserDataServiceImpl();

    public static UserDataServiceImpl getInstance() {
        return singleton;
    }

    private UserDataServiceImpl() { }

    @Override
    public void add(Teacher u) {
        TEACHERS.add(u);
    }

    @Override
    public void add(Graduate u) {
        GRADUATES.add(u);
    }

    @Override
    public void add(Undergraduate u) {
        UNDERGRADUATES.add(u);
    }

    @Override
    public void add(Administrator u) {
        ADMINISTRATORS.add(u);
        PersonalInfoMediator.getInstance().addAdministrator(u);
    }

    @Override
    public void add(User u) {
        Class cl = u.getClass();
        if (cl == Teacher.class) {
            add((Teacher) u);
        } else if (cl == Graduate.class) {
            add((Graduate) u);
        } else if (cl == Undergraduate.class) {
            add((Undergraduate) u);
        } else if (cl == Administrator.class) {
            add((Administrator) u);
        }
    }

    @Override
    public void remove(User u) {
        Class cl = u.getClass();
        if (cl == Teacher.class) {
            TEACHERS.remove(u);
        } else if (cl == Graduate.class) {
            GRADUATES.remove(u);
        } else if (cl == Undergraduate.class) {
            UNDERGRADUATES.remove(u);
        } else if (cl == Administrator.class) {
            ADMINISTRATORS.remove(u);
            PersonalInfoMediator.getInstance().removeAdministrator((Administrator) u);
        }
    }

    @Override
    public void update(User u) {
        Class cl = u.getClass();
        if (cl == Teacher.class) {
            if (TEACHERS.remove(u)) {
                TEACHERS.add((Teacher) u);
            }
        } else if (cl == Graduate.class) {
            if (GRADUATES.remove(u)) {
                GRADUATES.add((Graduate) u);
            }
        } else if (cl == Undergraduate.class) {
            if (UNDERGRADUATES.remove(u)) {
                UNDERGRADUATES.add((Undergraduate) u);
            }
        } else if (cl == Administrator.class) {
            if (ADMINISTRATORS.remove(u)) {
                ADMINISTRATORS.add((Administrator) u);
                PersonalInfoMediator.getInstance().removeAdministrator((Administrator) u);
                PersonalInfoMediator.getInstance().addAdministrator((Administrator) u);
            }
        }
    }

    @Override
    public User findByUsername(String username) {
        for (Teacher u : TEACHERS) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        for (Graduate u : GRADUATES) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        for (Undergraduate u : UNDERGRADUATES) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        for (Administrator u : ADMINISTRATORS) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> res = new ArrayList<>();
        res.addAll(TEACHERS);
        res.addAll(GRADUATES);
        res.addAll(UNDERGRADUATES);
        res.addAll(ADMINISTRATORS);
        return res;
    }

    public static void main(String[] args) {
        Set<User> s = new HashSet<>();
        s.add(new Teacher("IN968720", "123456"));
        s.add(new Teacher("IN173947", "123456"));
        s.add(new Graduate("MF832042", "987654"));
        s.add(new Graduate("MF731325", "987654321"));
        System.out.println(s);
        System.out.println(s.remove(new Graduate("MF731325", "")));
        System.out.println(s.remove(new Graduate("MF000000", "")));
        s.add(new Graduate("MF731325", "000000"));
        System.out.println(s);
    }

}
