// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class MultiConstNode extends ConstDeclSpec {

    private ConstDeclSpec ConstDeclSpec;
    private ConstSingleSpec ConstSingleSpec;

    public MultiConstNode (ConstDeclSpec ConstDeclSpec, ConstSingleSpec ConstSingleSpec) {
        this.ConstDeclSpec=ConstDeclSpec;
        if(ConstDeclSpec!=null) ConstDeclSpec.setParent(this);
        this.ConstSingleSpec=ConstSingleSpec;
        if(ConstSingleSpec!=null) ConstSingleSpec.setParent(this);
    }

    public ConstDeclSpec getConstDeclSpec() {
        return ConstDeclSpec;
    }

    public void setConstDeclSpec(ConstDeclSpec ConstDeclSpec) {
        this.ConstDeclSpec=ConstDeclSpec;
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
        if(ConstDeclSpec!=null) ConstDeclSpec.accept(visitor);
        if(ConstSingleSpec!=null) ConstSingleSpec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclSpec!=null) ConstDeclSpec.traverseTopDown(visitor);
        if(ConstSingleSpec!=null) ConstSingleSpec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclSpec!=null) ConstDeclSpec.traverseBottomUp(visitor);
        if(ConstSingleSpec!=null) ConstSingleSpec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiConstNode(\n");

        if(ConstDeclSpec!=null)
            buffer.append(ConstDeclSpec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstSingleSpec!=null)
            buffer.append(ConstSingleSpec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiConstNode]");
        return buffer.toString();
    }
}
