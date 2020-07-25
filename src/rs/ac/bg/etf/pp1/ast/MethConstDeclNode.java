// generated with ast extension for cup
// version 0.8
// 25/6/2020 14:34:23


package rs.ac.bg.etf.pp1.ast;

public class MethConstDeclNode extends MethVarDeclList {

    private MethVarDeclList MethVarDeclList;
    private ConstDecl ConstDecl;

    public MethConstDeclNode (MethVarDeclList MethVarDeclList, ConstDecl ConstDecl) {
        this.MethVarDeclList=MethVarDeclList;
        if(MethVarDeclList!=null) MethVarDeclList.setParent(this);
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
    }

    public MethVarDeclList getMethVarDeclList() {
        return MethVarDeclList;
    }

    public void setMethVarDeclList(MethVarDeclList MethVarDeclList) {
        this.MethVarDeclList=MethVarDeclList;
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethVarDeclList!=null) MethVarDeclList.accept(visitor);
        if(ConstDecl!=null) ConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethVarDeclList!=null) MethVarDeclList.traverseTopDown(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethVarDeclList!=null) MethVarDeclList.traverseBottomUp(visitor);
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethConstDeclNode(\n");

        if(MethVarDeclList!=null)
            buffer.append(MethVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethConstDeclNode]");
        return buffer.toString();
    }
}
