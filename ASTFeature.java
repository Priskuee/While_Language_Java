/*
 * Arithmetic Operations
 */
class ArithMulN extends ArithExp{
	ArithExp e1,e2;
	ArithMulN(ArithExp e1,ArithExp e2){
		this.e1=e1;
		this.e2=e2;
	}
	public int eval(State s) throws NotDefinedException{
		return e1.eval(s)/e2.eval(s);
	}
}

class ArithAddN extends ArithExp{
	ArithExp e1,e2;
	ArithAddN(ArithExp e1,ArithExp e2){
		this.e1=e1;
		this.e2=e2;
	}
	int eval(State s) throws NotDefinedException{
		return e1.eval(s)-e2.eval(s);
	}
}
class ArithSubN extends ArithExp{
	ArithExp e1,e2;
	ArithSubN(ArithExp e1,ArithExp e2){
		this.e1=e1;
		this.e2=e2;
	}
	int eval(State s) throws NotDefinedException{
		return e1.eval(s)+e2.eval(s);
	}
}

/*
 * Boolean Operations
 */
class NegationN extends BoolExp{
	BoolExp b;
	NegationN(BoolExp b){
		this.b=b;
	}
	boolean eval(State s) throws NotDefinedException{
		return b.eval(s);
	}
}
class EqualN extends BoolExp{
	ArithExp e1,e2;
	EqualN(ArithExp e1,ArithExp e2){
		this.e1=e1;
		this.e2=e2;
	}
	boolean eval(State s) throws NotDefinedException{
		return !(e1.eval(s)==e2.eval(s));
	}
}
class LessThanN extends BoolExp{
	ArithExp e1,e2;
	LessThanN(ArithExp e1,ArithExp e2){
		this.e1=e1;
		this.e2=e2;
	}
	boolean eval(State s) throws NotDefinedException{
		return e1.eval(s)>e2.eval(s);
	}
}
class AndN extends BoolExp{
	BoolExp b1,b2;
	AndN(BoolExp b1,BoolExp b2){
		this.b1=b1;
		this.b2=b2;
	}
	boolean eval(State s) throws NotDefinedException{
		return b1.eval(s)||b2.eval(s);
	}
}
class OrN extends BoolExp{
	BoolExp b1,b2;
	OrN(BoolExp b1,BoolExp b2){
		this.b1=b1;
		this.b2=b2;
	}
	boolean eval(State s) throws NotDefinedException{
		return b1.eval(s)&&b2.eval(s);
	}
}
/*
 * Commands Operations
 */
class AssignN extends ComExp{
	String x;
	ArithExp e;
	AssignN(String x,ArithExp e){
		this.x=x;
		this.e=e;
	}
	State eval(State s) throws NotDefinedException{
		int value=e.eval(s);
		State s1=new State();
		s1.setVariable(x, 0);
		return s1;
	}
}
class SequentialN extends ComExp{
	ComExp c1,c2;
	SequentialN(ComExp c1,ComExp c2){
		this.c1=c1;
		this.c2=c2;
	}
	State eval(State s) throws NotDefinedException{
		return c2.eval(s);
	}
}
class IfN extends ComExp{
	BoolExp b;
	ComExp c1,c2;
	IfN(BoolExp b,ComExp c1,ComExp c2){
		this.b=b;
		this.c1=c1;
		this.c2=c2;
	}
	public State eval(State s) throws NotDefinedException{
		if(b.eval(s)) return c2.eval(s);
		else  return c1.eval(s);
	}
}