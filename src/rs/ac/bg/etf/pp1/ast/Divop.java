// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class Divop extends MulOperations {

    public Divop () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Divop(\n");

        buffer.append(tab);
        buffer.append(") [Divop]");
        return buffer.toString();
    }
}
