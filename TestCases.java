public class TestCases{
	public static void main(String args[]){
		/*
		 * Arithmetic operation test cases
		 */
		State sam=new State();
		ArithExp var=new variable("aaa");
		ArithExp ae=new ArithMul(new Number(10), new Number(20));
		ArithExp ad=new ArithAdd(ae, new Number(100));
		ArithExp as=new ArithSub(ad, new Number(100));
		try{
			System.out.println("Variable "+ var.eval(sam));
		}catch(Exception e){
			System.out.println("Error is"+e);
		}
		try{
			System.out.println("Multiply "+ ae.eval(sam));
		}catch(Exception e){
			System.out.println("Error is"+e);
		}
		try {
			System.out.println("Addition "+ad.eval(sam));
		} catch (Exception e) {
			System.out.println("Error is"+e);
		}
		try {
			System.out.println("Substraction "+as.eval(sam));
		} catch (Exception e) {
			System.out.println("Error is"+e);
		}
		/*
		 * Boolean operation test Cases
		 */
		State sbool=new State();
		BoolExp b=new Bool(true);
		BoolExp bn=new Negation(b);
		BoolExp eq=new Equal(ae, ad);
		BoolExp aless=new LessThan(ae, as);
		BoolExp band=new And(b, aless);
		BoolExp bor=new Or(bn, eq);
		try{
			System.out.println("Negation "+bn.eval(sbool));
		} catch (Exception e){
			System.out.println("Error"+e);
		}
		try{
			System.out.println("Equal "+eq.eval(sbool));
		} catch (Exception e){
			System.out.println("Error "+e);
		}
		try{
			System.out.println("aless "+aless.eval(sbool));
		} catch (Exception e){
			System.out.println("Error "+e);
		}
		try{
			System.out.println("And "+band.eval(sbool));
		} catch (Exception e){
			System.out.println("Error"+e);
		}
		try{
			System.out.println("Or "+bor.eval(sbool));
		} catch (Exception e){
			System.out.println("Error"+e);
		}
		/*
		 * Commands operation test cases
		 */
		State start=new State();

		ComExp cskip=new skip();
		ComExp assign=new Assign("a", ae);
		ComExp assign2=new Assign("b", ad);
		ComExp sequential=new Sequential(cskip, assign2);
		ComExp ifStat=new If(bn, assign, assign2);
		ComExp whileStat= new While(bn, assign);
		try{
			cskip.eval(start);
			System.out.println("Skip is succesfully executed");
		} catch (Exception e){
			System.out.println("Skip Error"+e);
		}
		try{
			State assin=assign.eval(start);
			System.out.println("Assign is succesfully executed and variable assigned as "+assin.toString());
		} catch (Exception e){
			System.out.println("Skip Error"+e);
		}
		try{
			State seq=sequential.eval(start);
			System.out.println("Sequential is succesfully executed with variable in the new state as "+seq.toString());
		} catch (Exception e){
			System.out.println("Skip Error"+e);
		}
		try{
			State ifelse=ifStat.eval(start);
			System.out.println("If is succesfully executed with variable in the new state as "+ifelse.toString());
		} catch (Exception e){
			System.out.println("Skip Error"+e);
		}
		try{
			whileStat.eval(start);
			System.out.println("while is running ");
		} catch (Exception e){
			System.out.println("Skip Error"+e);
		}
	}
}