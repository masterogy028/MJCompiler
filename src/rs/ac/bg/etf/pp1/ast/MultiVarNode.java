// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class MultiVarNode extends VarDeclSpec {

    private VarDeclSpec VarDeclSpec;
    private VarSingleSpec VarSingleSpec;

    public MultiVarNode (VarDeclSpec VarDeclSpec, VarSingleSpec VarSingleSpec) {
        this.VarDeclSpec=VarDeclSpec;
        if(VarDeclSpec!=null) VarDeclSpec.setParent(this);
        this.VarSingleSpec=VarSingleSpec;
        if(VarSingleSpec!=null) VarSingleSpec.setParent(this);
    }

    public VarDeclSpec getVarDeclSpec() {
        return VarDeclSpec;
    }

    public void setVarDeclSpec(VarDeclSpec VarDeclSpec) {
        this.VarDeclSpec=VarDeclSpec;
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
        if(VarDeclSpec!=null) VarDeclSpec.accept(visitor);
        if(VarSingleSpec!=null) VarSingleSpec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclSpec!=null) VarDeclSpec.traverseTopDown(visitor);
        if(VarSingleSpec!=null) VarSingleSpec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclSpec!=null) VarDeclSpec.traverseBottomUp(visitor);
        if(VarSingleSpec!=null) VarSingleSpec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiVarNode(\n");

        if(VarDeclSpec!=null)
            buffer.append(VarDeclSpec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarSingleSpec!=null)
            buffer.append(VarSingleSpec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiVarNode]");
        return buffer.toString();
    }
}
