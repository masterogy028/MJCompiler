// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class ConstFactorNode extends Factor {

    private ConstFactor ConstFactor;

    public ConstFactorNode (ConstFactor ConstFactor) {
        this.ConstFactor=ConstFactor;
        if(ConstFactor!=null) ConstFactor.setParent(this);
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
        buffer.append("ConstFactorNode(\n");

        if(ConstFactor!=null)
            buffer.append(ConstFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstFactorNode]");
        return buffer.toString();
    }
}
