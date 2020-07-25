// generated with ast extension for cup
// version 0.8
// 22/5/2020 17:49:18


package rs.ac.bg.etf.pp1.ast;

public class MethodTypeNameNode extends MethodTypeName {

    private MethType MethType;
    private String methName;

    public MethodTypeNameNode (MethType MethType, String methName) {
        this.MethType=MethType;
        if(MethType!=null) MethType.setParent(this);
        this.methName=methName;
    }

    public MethType getMethType() {
        return MethType;
    }

    public void setMethType(MethType MethType) {
        this.MethType=MethType;
    }

    public String getMethName() {
        return methName;
    }

    public void setMethName(String methName) {
        this.methName=methName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethType!=null) MethType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethType!=null) MethType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethType!=null) MethType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodTypeNameNode(\n");

        if(MethType!=null)
            buffer.append(MethType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+methName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodTypeNameNode]");
        return buffer.toString();
    }
}
