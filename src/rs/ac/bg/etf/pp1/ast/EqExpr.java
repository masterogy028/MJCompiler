// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class EqExpr extends Expr {

    private Expr Expr;
    private EqualOp EqualOp;
    private Term Term;

    public EqExpr (Expr Expr, EqualOp EqualOp, Term Term) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.EqualOp=EqualOp;
        if(EqualOp!=null) EqualOp.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public EqualOp getEqualOp() {
        return EqualOp;
    }

    public void setEqualOp(EqualOp EqualOp) {
        this.EqualOp=EqualOp;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(EqualOp!=null) EqualOp.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(EqualOp!=null) EqualOp.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(EqualOp!=null) EqualOp.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EqExpr(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EqualOp!=null)
            buffer.append(EqualOp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EqExpr]");
        return buffer.toString();
    }
}
