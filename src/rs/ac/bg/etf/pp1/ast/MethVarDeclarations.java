// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class MethVarDeclarations extends MethVarDeclList {

    private MethVarDeclList MethVarDeclList;
    private VarDecl VarDecl;

    public MethVarDeclarations (MethVarDeclList MethVarDeclList, VarDecl VarDecl) {
        this.MethVarDeclList=MethVarDeclList;
        if(MethVarDeclList!=null) MethVarDeclList.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public MethVarDeclList getMethVarDeclList() {
        return MethVarDeclList;
    }

    public void setMethVarDeclList(MethVarDeclList MethVarDeclList) {
        this.MethVarDeclList=MethVarDeclList;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethVarDeclList!=null) MethVarDeclList.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethVarDeclList!=null) MethVarDeclList.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethVarDeclList!=null) MethVarDeclList.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethVarDeclarations(\n");

        if(MethVarDeclList!=null)
            buffer.append(MethVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethVarDeclarations]");
        return buffer.toString();
    }
}
