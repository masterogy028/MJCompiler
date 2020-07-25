// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class OppExpr extends Expr {

    private Expr Expr;
    private AddOperations AddOperations;
    private Term Term;

    public OppExpr (Expr Expr, AddOperations AddOperations, Term Term) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.AddOperations=AddOperations;
        if(AddOperations!=null) AddOperations.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public AddOperations getAddOperations() {
        return AddOperations;
    }

    public void setAddOperations(AddOperations AddOperations) {
        this.AddOperations=AddOperations;
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
        if(AddOperations!=null) AddOperations.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(AddOperations!=null) AddOperations.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(AddOperations!=null) AddOperations.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OppExpr(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddOperations!=null)
            buffer.append(AddOperations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OppExpr]");
        return buffer.toString();
    }
}
