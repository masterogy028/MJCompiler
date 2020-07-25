package rs.ac.bg.etf.pp1;

import javafx.fxml.Initializable;
import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;

import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.net.URL;
import java.util.ResourceBundle;

public class CodeGenerator extends VisitorAdaptor {

	private int varCount;

	private int paramCnt;

	private int mainPc;

	public int getMainPc() {
		return mainPc;
	}

	@Override
	public void visit(MethodTypeNameNode methodTypeName) {
		if ("main".equalsIgnoreCase(methodTypeName.getMethName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);

		// Collect arguments and local variables.
		SyntaxNode methodNode = methodTypeName.getParent();
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);

		// Generate the entry.
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(varCnt.getCount() + fpCnt.getCount());
	}

	@Override
	public void visit(VarDecl VarDecl) {
		varCount++;
	}

	@Override
	public void visit(FormalParamDecl FormalParam) {
		paramCnt++;
	}

	@Override
	public void visit(MethodDecl MethodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(ReturnExpr ReturnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(ReturnNoExpr ReturnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(ConstInt cn) {
		if(cn.getParent().getClass() == ConstSingleSpecNode.class) return;
		Obj con = Tab.insert(Obj.Con, "$", cn.obj.getType());
		con.setLevel(0);
		con.setAdr(cn.getValue());
		Code.load(cn.obj);
	}
	@Override
	public void visit(ConstChar cn) {
		if(cn.getParent().getClass() == ConstSingleSpecNode.class) return;
		Obj con = Tab.insert(Obj.Con, "$", cn.obj.getType());
		con.setLevel(0);
		con.setAdr(cn.getValue());
		Code.load(cn.obj);
	}
	@Override
	public void visit(ConstBool cn) {
		if(cn.getParent().getClass() == ConstSingleSpecNode.class) return;
		Obj con = Tab.insert(Obj.Con, "$", cn.obj.getType());
		con.setLevel(0);
		con.setAdr(cn.getValue()?1:0);
		Code.load(cn.obj);
	}

	@Override
	public void visit(Assignment assignment) {

		SyntaxNode parent = assignment.getDesignator().getParent();
		Code.store(assignment.getDesignator().obj);

//		if(assignment.getDesignator().obj.getType() == new Struct(Struct.Array, assignment.getDesignator().obj.getType().getElemType()));
//			Code.load(assignment.getDesignator().obj);
//		if(assignment.getDesignator().obj.getKind() == Obj.Elem) {
//
//			String s = ((DesignatorArrayMemberNode)assignment.getDesignator()).getName();
//			Obj o = Tab.find(s);
//			Code.load(o);
//		}
	}

	public void visit(NewArrayNode node)
	{
		Expr arraySize = node.getExpr();

		//Code.put(node.getExpr().obj.);
		Code.put(Code.newarray);

		if (node.obj.getType().getElemType() == Tab.charType)
		{
			Code.put(0);
		}
		else Code.put(1);

	}

	@Override
	public void visit(DesignatorNode designator) {
		SyntaxNode parent = designator.getParent();
		if (parent.getClass()!=Assignment.class && parent.getClass()!= FuncCall.class && parent.getClass()!=ProcCall.class)
			Code.load(designator.obj);

	}
	@Override
	public void visit(DesignatorArrayMemberNode designator) {
		SyntaxNode parent = designator.getParent();
		if (parent.getClass()!=Assignment.class&&parent.getClass()!=IncrementOnlyNode.class&&
				parent.getClass()!= DecrementOnlyNode.class && parent.getClass()!=ProcCall.class)
			Code.load(designator.obj);
	}
	@Override
	public void visit(DesignatorLbrackNode designator) {
		SyntaxNode parent = designator.getParent();
		Code.load(designator.obj);
	}
	@Override
	public void visit(FuncCall FuncCall) {
		Obj functionObj = FuncCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}

	public void visit(PrintStmt printStmt){
		if(printStmt.getExpr().obj.getType() == Tab.intType){
			Code.loadConst(5);
			Code.put(Code.print);
		}else{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}

	@Override
	public void visit(OppExpr AddExpr) {
		if(AddExpr.getAddOperations() instanceof Addop)
			Code.put(Code.add);
		if(AddExpr.getAddOperations() instanceof Subop)
			Code.put(Code.sub);


	}
	@Override
	public void visit(TermMulopNode mulOperations) {

		if(mulOperations.getMulOperations() instanceof Mulop)
			Code.put(Code.mul);
		if(mulOperations.getMulOperations() instanceof Divop)
			Code.put(Code.div);
		if(mulOperations.getMulOperations() instanceof Modop)
			Code.put(Code.rem);

	}

	@Override
	public void visit(IncrementOnlyNode node)
	{

		if (node.getDesignator() instanceof DesignatorArrayMemberNode) {Code.put(Code.dup2);   }
		else Code.put(Code.dup);

		Code.load(node.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(node.getDesignator().obj);

	}

	@Override
	public void visit(IncNode node)
	{

		if (((Var)node.getParent()).getDesignator() instanceof DesignatorArrayMemberNode)
		{Code.put(Code.dup2); }
		else Code.put(Code.dup);

		Code.load(((Var)node.getParent()).getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(((Var)node.getParent()).getDesignator().obj);

	}
	@Override
	public void visit(DecNode node)
	{

		if (((Var)node.getParent()).getDesignator() instanceof DesignatorArrayMemberNode) Code.put(Code.dup2);
		else Code.put(Code.dup);

		Code.load(((Var)node.getParent()).getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(((Var)node.getParent()).getDesignator().obj);

	}

	@Override
	public void visit(DecrementOnlyNode node)
	{

		if (node.getDesignator() instanceof DesignatorArrayMemberNode) {Code.put(Code.dup2);   }
		else Code.put(Code.dup);

		Code.load(node.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(node.getDesignator().obj);

	}

}
