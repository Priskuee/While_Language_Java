import java.util.HashMap;
import java.util.Map.Entry;
/*
 * State Class
 */
class State {

	HashMap<String,Integer> variableValues = new HashMap<String,Integer>();

	public State() {}

	public void setVariable(String name, int value) {
		variableValues.put(name, value);
	}

	public int getVariable(String name) throws NotDefinedException {
		Integer value = variableValues.get(name); 
		if (value == null) throw new NotDefinedException(name);
		return value.intValue();
	}
	public String toString() {
		String table = "";
		for (Entry<String,Integer> entry : variableValues.entrySet()) {
			table += entry.getKey() + "  " + entry.getValue() + "\n";
		}
		return table;
	}
}
/*
 * Exception handling class
 */
class NotDefinedException extends Exception {

	static final long serialVersionUID = 1L;

	String variableName;

	NotDefinedException(String name) {
		this.variableName = name;
	}

	public String toString() {
		return "The variable " + variableName + " is not defined.";
	}

}
/*
 * Arithmetic Class for all the Arithmetic Operations
 */
abstract class ArithExp{
	abstract int eval(State S) throws NotDefinedException;
}
abstract class BoolExp{
	abstract boolean eval(State S) throws NotDefinedException;
}
abstract class ComExp{
	abstract State eval(State S) throws NotDefinedException;
}
/*
 * Number type
 */
class Number extends ArithExp{
	int n;
	Number(int n){
		this.n=n;
	}
	int eval(State S) throws NotDefinedException{
		return n;
	}
}

class Bool extends BoolExp{
	boolean b;
	Bool(boolean b){
		this.b=b;
	}
	boolean eval(State S) throws NotDefinedException{
		return b;
	}
}
/*
 * Arithmetic Operations
 */
class variable extends ArithExp{
	String x;
	variable(String x){
		this.x=x;
	}
	public int eval(State s) throws NotDefinedException{
		s.setVariable(x, 0);
		return 0;
	}
}
class ArithMul extends ArithExp{
	ArithExp e1,e2;
	ArithMul(ArithExp e1,ArithExp e2){
		this.e1=e1;
		this.e2=e2;
	}
	public int eval(State s) throws NotDefinedException{
		return e1.eval(s)*e2.eval(s);
	}
}

class ArithAdd extends ArithExp{
	ArithExp e1,e2;
	ArithAdd(ArithExp e1,ArithExp e2){
		this.e1=e1;
		this.e2=e2;
	}
	int eval(State s) throws NotDefinedException{
		return e1.eval(s)+e2.eval(s);
	}
}
class ArithSub extends ArithExp{
	ArithExp e1,e2;
	ArithSub(ArithExp e1,ArithExp e2){
		this.e1=e1;
		this.e2=e2;
	}
	int eval(State s) throws NotDefinedException{
		return e1.eval(s)-e2.eval(s);
	}
}

/*
 * Boolean Operations
 */
class Negation extends BoolExp{
	BoolExp b;
	Negation(BoolExp b){
		this.b=b;
	}
	boolean eval(State s) throws NotDefinedException{
		return !b.eval(s);
	}
}
class Equal extends BoolExp{
	ArithExp e1,e2;
	Equal(ArithExp e1,ArithExp e2){
		this.e1=e1;
		this.e2=e2;
	}
	boolean eval(State s) throws NotDefinedException{
		return (e1.eval(s)==e2.eval(s));
	}
}
class LessThan extends BoolExp{
	ArithExp e1,e2;
	LessThan(ArithExp e1,ArithExp e2){
		this.e1=e1;
		this.e2=e2;
	}
	boolean eval(State s) throws NotDefinedException{
		return e1.eval(s)<e2.eval(s);
	}
}
class And extends BoolExp{
	BoolExp b1,b2;
	And(BoolExp b1,BoolExp b2){
		this.b1=b1;
		this.b2=b2;
	}
	boolean eval(State s) throws NotDefinedException{
		return b1.eval(s)&&b2.eval(s);
	}
}
class Or extends BoolExp{
	BoolExp b1,b2;
	Or(BoolExp b1,BoolExp b2){
		this.b1=b1;
		this.b2=b2;
	}
	boolean eval(State s) throws NotDefinedException{
		return b1.eval(s)||b2.eval(s);
	}
}
/*
 * Commands Operations
 */
class skip extends ComExp{
	State eval(State s) throws NotDefinedException{
		return s;
	}
}
class Assign extends ComExp{
	String x;
	ArithExp e;
	Assign(String x,ArithExp e){
		this.x=x;
		this.e=e;
	}
	State eval(State s) throws NotDefinedException{
		int value=e.eval(s);
		State s1=new State();
		s1.setVariable(x, value);
		return s1;
	}
}
class Sequential extends ComExp{
	ComExp c1,c2;
	Sequential(ComExp c1,ComExp c2){
		this.c1=c1;
		this.c2=c2;
	}
	State eval(State s) throws NotDefinedException{
		State s1= c1.eval(s);
		return c2.eval(s1);
	}
}
class If extends ComExp{
	BoolExp b;
	ComExp c1,c2;
	If(BoolExp b,ComExp c1,ComExp c2){
		this.b=b;
		this.c1=c1;
		this.c2=c2;
	}
	public State eval(State s) throws NotDefinedException{
		if(b.eval(s)) return c1.eval(s);
		else  return c2.eval(s);
	}
}
class While extends ComExp{
	BoolExp b;
	ComExp c;
	While(BoolExp b,ComExp c){
		this.b=b;
		this.c=c;
	}
	State eval(State s) throws NotDefinedException{
		if(b.eval(s)){
			do {
				return c.eval(s);
			} while (b.eval(s));
		}
		else {
			return s;
		}
	}
}