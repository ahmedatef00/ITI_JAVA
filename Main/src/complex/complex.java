package complex;

import java.util.Scanner;
import Main.Comp;;
public class complex {
	

	public static void main(String[] args) {
		float  real;
	    float img;

	    Comp myComp1=new Comp();
	    Comp myComp2=new Comp();

	    myComp1.setCoplex(-52, -62);
	    myComp2.setCoplex(-5, -6);

	    Comp c=Comp.add(myComp1, myComp2);
	    Comp s=Comp.sub(myComp1, myComp2);

 c.printComplex();
 s.printComplex();
	
	}

}
