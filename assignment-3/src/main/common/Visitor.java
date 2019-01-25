package main.common;

import main.common.user.Administrator;
import main.common.user.Graduate;
import main.common.user.Teacher;
import main.common.user.Undergraduate;

public interface Visitor {

    void visit(Teacher e);
    void visit(Graduate e);
    void visit(Undergraduate e);
    void visit(Administrator e);

}
