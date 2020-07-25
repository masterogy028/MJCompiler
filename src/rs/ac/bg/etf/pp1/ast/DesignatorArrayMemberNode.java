// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArrayMemberNode extends Designator {

    private DesignatorLbrack DesignatorLbrack;
    private Expr Expr;

    public DesignatorArrayMemberNode (DesignatorLbrack DesignatorLbrack, Expr Expr) {
        this.DesignatorLbrack=DesignatorLbrack;
        if(DesignatorLbrack!=null) DesignatorLbrack.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorLbrack getDesignatorLbrack() {
        return DesignatorLbrack;
    }

    public void setDesignatorLbrack(DesignatorLbrack DesignatorLbrack) {
        this.DesignatorLbrack=DesignatorLbrack;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorLbrack!=null) DesignatorLbrack.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorLbrack!=null) DesignatorLbrack.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorLbrack!=null) DesignatorLbrack.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArrayMemberNode(\n");

        if(DesignatorLbrack!=null)
            buffer.append(DesignatorLbrack.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArrayMemberNode]");
        return buffer.toString();
    }
}
