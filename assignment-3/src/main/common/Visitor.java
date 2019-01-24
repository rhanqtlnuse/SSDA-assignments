package main.common;

public interface Visitor {

    void visit(Teacher e);
    void visit(Graduate e);
    void visit(Undergraduate e);
    void visit(Administrator e);

}
