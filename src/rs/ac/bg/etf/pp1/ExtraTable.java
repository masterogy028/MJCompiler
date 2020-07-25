package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

class StructExt {
    public static final int Bool = 2000;
}
public class ExtraTable {
    static Struct boolType = new Struct(StructExt.Bool);
    public static void init() {
        Tab.init();
        Tab.currentScope().addToLocals(new Obj(Obj.Type, "bool", ExtraTable.boolType));
    }
}

