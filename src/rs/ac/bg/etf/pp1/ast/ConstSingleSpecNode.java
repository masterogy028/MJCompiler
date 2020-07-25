// generated with ast extension for cup
// version 0.8
// 25/6/2020 14:34:23


package rs.ac.bg.etf.pp1.ast;

public class ConstSingleSpecNode extends ConstSingleSpec {

    private String constName;
    private ConstFactor ConstFactor;

    public ConstSingleSpecNode (String constName, ConstFactor ConstFactor) {
        this.constName=constName;
        this.ConstFactor=ConstFactor;
        if(ConstFactor!=null) ConstFactor.setParent(this);
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public ConstFactor getConstFactor() {
        return ConstFactor;
    }

    public void setConstFactor(ConstFactor ConstFactor) {
        this.ConstFactor=ConstFactor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstFactor!=null) ConstFactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstFactor!=null) ConstFactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstFactor!=null) ConstFactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstSingleSpecNode(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(ConstFactor!=null)
            buffer.append(ConstFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstSingleSpecNode]");
        return buffer.toString();
    }
}
