// generated with ast extension for cup
// version 0.8
// 25/6/2020 14:34:23


package rs.ac.bg.etf.pp1.ast;

public class SingleVarNode extends VarDeclSpec {

    private VarSingleSpec VarSingleSpec;

    public SingleVarNode (VarSingleSpec VarSingleSpec) {
        this.VarSingleSpec=VarSingleSpec;
        if(VarSingleSpec!=null) VarSingleSpec.setParent(this);
    }

    public VarSingleSpec getVarSingleSpec() {
        return VarSingleSpec;
    }

    public void setVarSingleSpec(VarSingleSpec VarSingleSpec) {
        this.VarSingleSpec=VarSingleSpec;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarSingleSpec!=null) VarSingleSpec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarSingleSpec!=null) VarSingleSpec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarSingleSpec!=null) VarSingleSpec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleVarNode(\n");

        if(VarSingleSpec!=null)
            buffer.append(VarSingleSpec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleVarNode]");
        return buffer.toString();
    }
}
