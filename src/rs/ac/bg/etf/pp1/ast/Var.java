// generated with ast extension for cup
// version 0.8
// 25/6/2020 14:34:23


package rs.ac.bg.etf.pp1.ast;

public class Var extends Factor {

    private Designator Designator;
    private IncDec IncDec;

    public Var (Designator Designator, IncDec IncDec) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.IncDec=IncDec;
        if(IncDec!=null) IncDec.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public IncDec getIncDec() {
        return IncDec;
    }

    public void setIncDec(IncDec IncDec) {
        this.IncDec=IncDec;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(IncDec!=null) IncDec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(IncDec!=null) IncDec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(IncDec!=null) IncDec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Var(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IncDec!=null)
            buffer.append(IncDec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Var]");
        return buffer.toString();
    }
}
