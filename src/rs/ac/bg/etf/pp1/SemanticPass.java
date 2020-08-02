package rs.ac.bg.etf.pp1;
import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.mj.runtime.Code;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticPass extends VisitorAdaptor {

	boolean errorDetected = false;
	int printCallCount = 0;
	Obj currentMethod = null;
	Type currentVarDecl = null;
	boolean returnFound = false;
	int varDeclCount = 0;
	int nVars;
	int arrayCnt = 0;

	Logger log = Logger.getLogger(getClass());
//	private void varInsert(String varName, Struct varType, int varLine) {
//		if (Tab.currentScope().findSymbol(varName) == null) {
//			int kind;
//			kind = Obj.Var;
//
//			Obj temp = Tab.insert(kind, varName, varType);
//
//			if (temp.getKind() == Obj.Var) {
//				if (temp.getType().getKind() == Struct.Array) {
//					if (temp.getLevel() == 0) {
//						arrayCnt++;
//					}
//				} else {
//					varDeclCount++;
//				}
//			}
//		} else {
//			report_error("Greska na " + varLine + "(" + varName + ") vec deklarisano", null);
//		}
//	}
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void visit(Program program) {		
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
		Tab.openScope();     	
	}

	public void visit(VarDecl varDecl) {
		varDeclCount++;
	}
	public void visit(VarTypeNode vtn) {
		currentVarDecl = vtn.getType();
		//report_info(currentVarDecl.getTypeName(), null);
	}
	public void visit(SingleVarNode varDecl) {
		//report_info("Single: ", null);
	}

	public void visit(NoAssNode varDecl) {

		report_info("Deklarisana promenljiva: "+ ((SingleSpecNode)varDecl.getParent()).getVarName(), varDecl.getParent());
		Obj varNode = Tab.insert(Obj.Var,( (SingleSpecNode)varDecl.getParent()).getVarName(),currentVarDecl.obj.getType());
	}
	public void visit(AssNode varDecl) {

		report_info("Deklarisana promenljiva sa dodelom: "+ ((SingleSpecNode)varDecl.getParent()).getVarName(), varDecl.getParent());
		Obj varNode = Tab.insert(Obj.Var,( (SingleSpecNode)varDecl.getParent()).getVarName(),currentVarDecl.obj.getType());
	}
	public void visit(ArrayDeclNode varDecl) {

		report_info("Deklarisana promenljiva tipa niz: "+ ((SingleSpecNode)varDecl.getParent()).getVarName(), varDecl.getParent());
		Obj varNode = Tab.insert(Obj.Var,( (SingleSpecNode)varDecl.getParent()).getVarName(), new Struct(Struct.Array, currentVarDecl.obj.getType()) );
		String s = varNode.getName();
		varNode = Tab.find(s);
		String ss= varNode.getName();
	}
	public void visit(ConstTypeNode vtn) {
		currentVarDecl = vtn.getType();
		//report_info(currentVarDecl.getTypeName(), null);
	}
	public void visit(ConstDecl varDecl) {
		varDeclCount++;
	}
	public void visit(ConstSingleSpecNode varDecl) {

		report_info("Deklarisana konstanta: " + varDecl.getConstName(), varDecl);
		Obj varNode = Tab.insert(Obj.Con, varDecl.getConstName(), currentVarDecl.obj.getType());
		//if(varNode.)
		varNode.setAdr(varDecl.getConstFactor().obj.getAdr());
	}
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
			type.obj = Tab.noObj;
		} 
		else {
			if (Obj.Type == typeNode.getKind()) {
				type.obj = typeNode;
			} 
			else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
				type.obj = Tab.noObj;
			}
		}  
	}

	public void visit(MethodDecl methodDecl) {
		if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funcija " + currentMethod.getName() + " nema return iskaz!", null);
		}
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		returnFound = false;
		currentMethod = null;
	}

	public void visit(MethodTypeNameNode methodTypeName) {
		currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getMethType().obj.getType());
		methodTypeName.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
	}
	public void visit(VoidNode voidNode) {
		voidNode.obj = Tab.noObj;
	}
	public void visit(MethTypeNode node) {
		node.obj = node.getType().obj;
	}

	public void visit(Assignment assignment) {
		if(assignment == null)
		{report_error("assignment je null", null);return;}
		if(assignment.getDesignator() == null)
		{report_error("designator je null", null);return;}
		if(assignment.getExprNeg() == null)
		{report_error("Expr je null", null);return;}
		if(assignment.getDesignator().obj == null)
		{report_error("obj je null", null);return;}
		if(assignment.getExprNeg().obj == null)
		{report_error("obj je null", null);return;}
		if(assignment.getExprNeg().obj.getType() == null)
		{report_error("type je null", null);return;}
		if(assignment.getDesignator().obj.getType() == null)
		{report_error("type je null", null);return;}

		if (assignment.getDesignator().obj.getKind() == Obj.Con) {
			report_error("Greska na liniji " + assignment.getDesignator().getLine()+ " : za promenjivu "+assignment.getDesignator().obj.getName()+" nije moguca operacija promena vrednosti konstante! ", null);
			return;
		}

		if((assignment.getDesignator().obj.getType().getKind()!= Struct.Array) && (assignment.getExprNeg().obj.getType().getKind()!=Struct.Array)) {
			if (!assignment.getExprNeg().obj.getType().assignableTo(assignment.getDesignator().obj.getType()))
				report_error("Greska na liniji " + assignment.getLine() + " : " + " nekompatibilni tipovi u dodeli vrednosti ", null);
		} else if(assignment.getExprNeg().obj.getType().getKind() != Struct.Array && assignment.getDesignator().obj.getType().getKind() == Struct.Array ){
			//report_info(assignment.getExpr().obj.getType().getKind()+" = " +assignment.getDesignator().obj.getType().getKind(), null);
			if (!assignment.getExprNeg().obj.getType().assignableTo(assignment.getDesignator().obj.getType().getElemType()))
				report_error("Greska na liniji " + assignment.getLine() + " : " + " nekompatibilni tipovi u dodeli vrednosti ", null);
		} else if (assignment.getExprNeg().obj.getType().getKind() == Struct.Array && assignment.getDesignator().obj.getType().getKind() != Struct.Array) {
			if (!assignment.getExprNeg().obj.getType().getElemType().assignableTo(assignment.getDesignator().obj.getType()))
				report_error("Greska na liniji " + assignment.getLine() + " : " + " nekompatibilni tipovi u dodeli vrednosti ", null);
		} else if (assignment.getExprNeg().obj.getType().getKind() == Struct.Array && assignment.getDesignator().obj.getType().getKind() == Struct.Array) {
			if (!assignment.getExprNeg().obj.getType().getElemType().assignableTo(assignment.getDesignator().obj.getType().getElemType()))
				report_error("Greska na liniji " + assignment.getLine() + " : " + " nekompatibilni tipovi u dodeli vrednosti ", null);
		}
	}

	public void visit(PrintStmt printStmt){
		//report_info("print stmt", printStmt);
		printCallCount++;    	
	}

	public void visit(ReturnExpr returnExpr){
		returnFound = true;
		report_info("return iz funkcije: "+currentMethod.getName(), null);
		Struct currMethType = currentMethod.getType();
		if(currentMethod.getType().equals(Tab.noType)) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "funkcija je tipa void" + currentMethod.getName(), null);
			return;
		}
		if (!currMethType.compatibleWith(returnExpr.getExprNeg().obj.getType())) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
		}			  	     	
	}
	public void visit(ReturnNoExpr returnExpr){
		returnFound = true;
		report_info("return iz funkcije: "+currentMethod.getName(), null);
		Struct currMethType = currentMethod.getType();
		if(currentMethod.getType().equals(Tab.noType)) {

		}
		else  {
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
		}
	}

	public void visit(ProcCall procCall){
		Obj func = procCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) { 
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + procCall.getLine(), null);
			//RESULT = func.getType();
		} 
		else {
			report_error("Greska na liniji " + procCall.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
			//RESULT = Tab.noType;
		}     	
	}
	public void visit(RegularExp regExp) {
		regExp.obj=regExp.getExpr().obj;
	}
	public void visit(NegativeExp regExp) {
		if(regExp.getExpr().obj.getType()!=null && regExp.getExpr().obj.getType().getKind() != Struct.Int ) {
			report_error("Greska na liniji "+ regExp.getLine()+" : nije moguca negacija tipa koji nije INT!", null);
		}
		regExp.obj=regExp.getExpr().obj;
	}
	public void visit(OppExpr oppExpr) {
		Obj te = oppExpr.getExpr().obj;
		Obj t = oppExpr.getTerm().obj;
		//report_info("current ", oppExpr);
		if (te.getType() == t.getType() && te.getType() == Tab.intType)
			oppExpr.obj = te;
		else {
			report_error("Greska na liniji "+ oppExpr.getLine()+" : nekompatibilni tipovi u izrazu za neku od operacija.", null);
			oppExpr.obj = Tab.noObj;
		} 
	}
	public void visit(EqExpr oppExpr) {
		Obj te = oppExpr.getExpr().obj;
		Obj t = oppExpr.getTerm().obj;
		if (te.getType().compatibleWith(t.getType()) && te.getType() == Tab.intType)
			oppExpr.obj = te;
		else {
			report_error("Greska na liniji "+ oppExpr.getLine()+" : nekompatibilni tipovi u izrazu za dodelu i operaciju.", null);
			oppExpr.obj = Tab.noObj;
		}
	}
	public void visit(BoolExpr oppExpr) {
		Obj te = oppExpr.getExpr().obj;
		Obj t = oppExpr.getTerm().obj;
		if (te.equals(t) && te.getType() == ExtraTable.boolType)
			oppExpr.obj = te;
		else {
			report_error("Greska na liniji "+ oppExpr.getLine()+" : nekompatibilni tipovi u izrazu za poredjenje.", null);
			oppExpr.obj = Tab.noObj;
		}
	}

	public void visit(TermExpr termExpr) {
		termExpr.obj = termExpr.getTerm().obj;
	}

	public void visit(TermNode term) {
		term.obj = term.getFactor().obj;
	}
	public void visit(TermMulopNode term) {
		Obj te = term.getFactor().obj;
		Obj t = term.getTerm().obj;
		if (te.getType().compatibleWith(t.getType()) && te.getType() == Tab.intType)
			term.obj = te;
		else {

			report_error("Greska na liniji "+ term.getLine()+" : nekompatibilni tipovi u izrazu za mnozenje/deljenje.", null);
			term.obj = Tab.noObj;
		}
	}
	public void visit(ConstFactorNode cnst) {cnst.obj = cnst.getConstFactor().obj;}
	public void visit(ConstInt cnst){ cnst.obj = new Obj(Obj.Con, "", Tab.intType, cnst.getValue(), Obj.NO_VALUE); }
	public void visit(ConstBool cnst){ cnst.obj = new Obj(Obj.Con, "", ExtraTable.boolType, cnst.getValue() ? 1 : 0, Obj.NO_VALUE);}

	public void visit(ConstChar cnst){ cnst.obj = new Obj(Obj.Con, "", Tab.charType, cnst.getValue(), Obj.NO_VALUE); }

	public void visit(Var var) {
		var.obj = var.getDesignator().obj;
	}
	public void visit(ParenExpFactorNode var) {
		var.obj = var.getExpr().obj;
	}
	public void visit(NewArrayNode var) {
		if (!var.getExpr().obj.getType().equals(Tab.intType)) {
			report_error("Greska na " + var.getLine() + ": tip izraza u new[] izrazu nije int", null);
		}
		var.obj = new Obj(Obj.Var, "", new Struct(Struct.Array, var.getType().obj.getType())) ;
	}
	public void visit(FuncCall funcCall){
		Obj func = funcCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) { 
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
			funcCall.obj = func;
		} 
		else {
			report_error("Greska na liniji " + funcCall.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
			funcCall.obj = Tab.noObj;
		}
	}

	public void visit(DesignatorNode designator){
		Obj obj = Tab.find(designator.getName());
		if (obj == Tab.noObj) { 
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" nije deklarisano u trenutnom opsegu! ", null);
		}
//		if (obj.getKind() == Obj.Con && !(designator.getParent() instanceof Term)) {
//			report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" nije moguca operacija promena vrednosti konstante! ", null);
//		}
		designator.obj = obj;
		int a=1; int b=1; int c=1;

	}

	public void visit(DesignatorArrayMemberNode designator){
		Obj obj = Tab.find((((DesignatorLbrackNode)(designator.getDesignatorLbrack())).getName()));
		if (obj == Tab.noObj) {
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+((DesignatorLbrackNode)(designator.getDesignatorLbrack())).getName()+" niza nije deklarisano u trenutnom opsegu! ", null);
		}

		designator.obj = new Obj(Obj.Elem, "", obj.getType().getElemType());

		((DesignatorLbrackNode)(designator.getDesignatorLbrack())).obj = obj;
	}
	/*public void visit(DesignatorLbrackNode designator){
		Obj obj = Tab.find(designator.getName());
		if (obj == Tab.noObj) {
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" niza nije deklarisano u trenutnom opsegu! ", null);
		}
		if(!(designator.getParent() instanceof Assignment))
			designator.obj = new Obj(Obj.Elem, "", obj.getType().getElemType());
		else
			designator.obj = obj;
	}*/
	public boolean passed() {
		return !errorDetected;
	}
	
}

