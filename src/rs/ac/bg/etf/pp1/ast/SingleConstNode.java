// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class SingleConstNode extends ConstDeclSpec {

    private ConstSingleSpec ConstSingleSpec;

    public SingleConstNode (ConstSingleSpec ConstSingleSpec) {
        this.ConstSingleSpec=ConstSingleSpec;
        if(ConstSingleSpec!=null) ConstSingleSpec.setParent(this);
    }

    public ConstSingleSpec getConstSingleSpec() {
        return ConstSingleSpec;
    }

    public void setConstSingleSpec(ConstSingleSpec ConstSingleSpec) {
        this.ConstSingleSpec=ConstSingleSpec;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstSingleSpec!=null) ConstSingleSpec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstSingleSpec!=null) ConstSingleSpec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstSingleSpec!=null) ConstSingleSpec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleConstNode(\n");

        if(ConstSingleSpec!=null)
            buffer.append(ConstSingleSpec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleConstNode]");
        return buffer.toString();
    }
}
