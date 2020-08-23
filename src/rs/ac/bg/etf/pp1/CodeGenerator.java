package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;

import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class CodeGenerator extends VisitorAdaptor {

	private boolean designatorOnEnd = false;
	private int varCount;
	private int depth=0;
	private Stack<SyntaxNode> designatorStack = new Stack<>();
	private Stack<SyntaxNode> operationStack = new Stack<>();
	private int paramCnt;
	private boolean EqOpp = false;
	private int mainPc;
	private Stack<Integer> inIndex=new Stack<>();
	private SyntaxNode designatorToProcess = null;
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
		if(inIndex.size()==0)
			depth++;
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
		//designatorStack.add(assignment.getDesignator());
		//designatorStack.add(assignment.getEqualOp());


		if (!EqOpp)
			Code.store(assignment.getDesignator().obj);
		else {
			if(depth==designatorStack.size())
				designatorStack.pop();
			designatorToProcess=designatorStack.pop();
			if(assignment.getEqualOp() instanceof EqualNode)
				Code.store(((Designator)designatorToProcess).obj); else
			if(assignment.getEqualOp() instanceof AddEq) {

				if(((Designator)designatorToProcess).obj.getKind()!= Obj.Elem){
					Code.put(Code.add);
					Code.put(Code.dup);
					Code.store(((Designator)designatorToProcess).obj);
				}else {
					Code.put(Code.add);
					Code.put(Code.dup_x2);
					Code.store(((Designator)designatorToProcess).obj);
				}
			} else
			if(assignment.getEqualOp() instanceof SubEq) {
				if(((Designator)designatorToProcess).obj.getKind()!= Obj.Elem){
					Code.put(Code.sub);
					Code.put(Code.dup);
					Code.store(((Designator)designatorToProcess).obj);
				}else {
					Code.put(Code.sub);
					Code.put(Code.dup_x2);
					Code.store(((Designator)designatorToProcess).obj);
				}
			} else
			if(assignment.getEqualOp() instanceof MulEq) {
				if(((Designator)designatorToProcess).obj.getKind()!= Obj.Elem){
					Code.put(Code.mul);
					Code.put(Code.dup);
					Code.store(((Designator)designatorToProcess).obj);
				}else {
					Code.put(Code.mul);
					Code.put(Code.dup_x2);
					Code.store(((Designator)designatorToProcess).obj);
				}
			} else
			if(assignment.getEqualOp() instanceof DivEq) {
				if(((Designator)designatorToProcess).obj.getKind()!= Obj.Elem){
					Code.put(Code.div);
					Code.put(Code.dup);
					Code.store(((Designator)designatorToProcess).obj);
				}else {
					Code.put(Code.div);
					Code.put(Code.dup_x2);
					Code.store(((Designator)designatorToProcess).obj);
				}
			}
		}
		designatorOnEnd = false;
		if(EqOpp) Code.put(Code.pop);
		EqOpp=false;
		designatorToProcess=null;
		designatorStack=new Stack<>();
		operationStack=new Stack<>();
		depth=0;
	}




	@Override
	public void visit(EqExpr EqExpr) {
		if(depth==designatorStack.size())designatorStack.pop();
		designatorToProcess=designatorStack.pop();
		if(EqExpr.getEqualOp() instanceof EqualNode) {
		 	Code.store(((Designator)designatorToProcess).obj);
			Code.load(((Designator)designatorToProcess).obj);
		 } else if(EqExpr.getEqualOp() instanceof AddEq) {
			if(((Designator)designatorToProcess).obj.getKind()!= Obj.Elem){
				Code.put(Code.add);
				Code.put(Code.dup);
				Code.store(((Designator)designatorToProcess).obj);
			}else {
				Code.put(Code.add);
				Code.put(Code.dup_x2);
				Code.store(((Designator)designatorToProcess).obj);
			}
		 }
		 else if(EqExpr.getEqualOp() instanceof MulEq) {
			if(((Designator)designatorToProcess).obj.getKind()!= Obj.Elem){
				 Code.put(Code.mul);
				 Code.put(Code.dup);
				 Code.store(((Designator)designatorToProcess).obj);
			}
			 else {
				Code.put(Code.mul);
				Code.put(Code.dup_x2);
				Code.store(((Designator)designatorToProcess).obj);
			}
		 }
		 else if(EqExpr.getEqualOp() instanceof DivEq) {
			if(((Designator)designatorToProcess).obj.getKind()!= Obj.Elem){
				Code.put(Code.div);
				Code.put(Code.dup);
				Code.store(((Designator)designatorToProcess).obj);
			}
			else {
				Code.put(Code.div);
				Code.put(Code.dup_x2);
				Code.store(((Designator)designatorToProcess).obj);
			}
		 }
		 else if(EqExpr.getEqualOp() instanceof SubEq) {
			if(((Designator)designatorToProcess).obj.getKind()!= Obj.Elem){
				Code.put(Code.sub);
				Code.put(Code.dup);
				Code.store(((Designator)designatorToProcess).obj);
			}
			else {
				Code.put(Code.sub);
				Code.put(Code.dup_x2);
				Code.store(((Designator)designatorToProcess).obj);
			}
		 }
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
		checkIfEqOpp(designator, parent);
		if (parent.getClass()!=Assignment.class && parent.getClass()!= FuncCall.class &&
			parent.getClass()!=ProcCall.class && parent.getClass()!= ReadStmt.class
			&& !EqOpp)
			Code.load(designator.obj);
		else if(EqOpp) {
			depth++;
			Code.load(designator.obj);
			if(!(designator.getParent().getParent().getParent() instanceof  OppExpr)&&!(designator.getParent().getParent() instanceof TermMulopNode)) {
				designatorStack.add(designator);
			}

			//designatorToProcess = designator;
		}

	}

	private void checkIfEqOpp(Designator designator, SyntaxNode parent) {
		if((parent instanceof Assignment && !(((Assignment) parent).getEqualOp() instanceof EqualNode)))
			EqOpp=true;
	}

	@Override
	public void visit(DesignatorArrayMemberNode designator) {
		SyntaxNode parent = designator.getParent();
		checkIfEqOpp(designator, parent);

		if (parent.getClass()!=Assignment.class&&parent.getClass()!=IncrementOnlyNode.class&&
				parent.getClass()!= DecrementOnlyNode.class && parent.getClass()!=ProcCall.class && parent.getClass()!= ReadStmt.class && !EqOpp)
			Code.load(designator.obj);
		else if(EqOpp) {
			depth++;
			if((parent.getParent().getParent() instanceof EqExpr)||(parent.getParent().getParent().getParent() instanceof EqExpr)||parent instanceof Assignment)
			Code.put(Code.dup2);
			Code.load(designator.obj);
			try{
				if(EqOpp)
					inIndex.pop();
			}
			catch (Exception e) {
				int i;
				i=1;
				i++;
				int y=i;
			}


			if(inIndex.size()==0 && !(designator.getParent().getParent().getParent() instanceof  OppExpr)&&!(designator.getParent().getParent() instanceof TermMulopNode))
			designatorStack.add(designator);
		}
	}
	@Override
	public void visit(DesignatorLbrackNode designator) {
		SyntaxNode parent = designator.getParent();
		checkIfEqOpp((Designator) designator.getParent(), parent);
		Code.load(designator.obj);
		if(EqOpp)
			inIndex.add(1);
		//if(EqOpp) designatorToProcess = designator;
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
	public void visit(NegativeTerm ExprNeg) {
		Code.put(Code.neg);
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
    public void visit(ReadStmt ReadStmt) {
        if (ReadStmt.getDesignator().obj.getType() == Tab.intType || ReadStmt.getDesignator().obj.getType() == ExtraTable.boolType)
        {
            Code.put(Code.read);
        }
        else
        {
            Code.put(Code.bread);
        }

        Code.store(ReadStmt.getDesignator().obj);
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
