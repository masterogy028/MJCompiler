// generated with ast extension for cup
// version 0.8
// 25/6/2020 14:34:23


package rs.ac.bg.etf.pp1.ast;

public class NoAssNode extends WAss {

    public NoAssNode () {
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
        buffer.append("NoAssNode(\n");

        buffer.append(tab);
        buffer.append(") [NoAssNode]");
        return buffer.toString();
    }
}
