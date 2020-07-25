// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class SingleSpecNode extends VarSingleSpec {

    private String varName;
    private WAss WAss;

    public SingleSpecNode (String varName, WAss WAss) {
        this.varName=varName;
        this.WAss=WAss;
        if(WAss!=null) WAss.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public WAss getWAss() {
        return WAss;
    }

    public void setWAss(WAss WAss) {
        this.WAss=WAss;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(WAss!=null) WAss.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WAss!=null) WAss.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WAss!=null) WAss.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleSpecNode(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(WAss!=null)
            buffer.append(WAss.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleSpecNode]");
        return buffer.toString();
    }
}
