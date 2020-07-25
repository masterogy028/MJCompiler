// generated with ast extension for cup
// version 0.8
// 25/6/2020 14:34:23


package rs.ac.bg.etf.pp1.ast;

public class Assignment extends Statement {

    private Designator Designator;
    private EqualOp EqualOp;
    private ExprNeg ExprNeg;

    public Assignment (Designator Designator, EqualOp EqualOp, ExprNeg ExprNeg) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.EqualOp=EqualOp;
        if(EqualOp!=null) EqualOp.setParent(this);
        this.ExprNeg=ExprNeg;
        if(ExprNeg!=null) ExprNeg.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public EqualOp getEqualOp() {
        return EqualOp;
    }

    public void setEqualOp(EqualOp EqualOp) {
        this.EqualOp=EqualOp;
    }

    public ExprNeg getExprNeg() {
        return ExprNeg;
    }

    public void setExprNeg(ExprNeg ExprNeg) {
        this.ExprNeg=ExprNeg;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(EqualOp!=null) EqualOp.accept(visitor);
        if(ExprNeg!=null) ExprNeg.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(EqualOp!=null) EqualOp.traverseTopDown(visitor);
        if(ExprNeg!=null) ExprNeg.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(EqualOp!=null) EqualOp.traverseBottomUp(visitor);
        if(ExprNeg!=null) ExprNeg.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Assignment(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EqualOp!=null)
            buffer.append(EqualOp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprNeg!=null)
            buffer.append(ExprNeg.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Assignment]");
        return buffer.toString();
    }
}
