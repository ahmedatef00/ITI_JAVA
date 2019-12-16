package Main;

import Main.Comp;

	public class Comp
	{
		float real;
		float img;
	    public Comp()
	    {
	        real= 0;
	        img = 0;
	    }
	    public Comp(float r,float i){
	    	real=r;
	    	img=i;
	    };
	    
	    public float getImage() {
	        return img;
	    }
	    float getReal() {
	        return real;
	    }
	    public   void setCoplex(float r , float i ) {real=r;img=i;}

	    public static Comp add(Comp mycomp1,Comp mycomp2) {
	    	Comp mycomp3=new Comp() ;
	    	    mycomp3.setCoplex( mycomp1.getReal()+mycomp2.getReal(),mycomp1.getImage()+mycomp2.getImage());
	    	    return mycomp3;    
	    
	    }
	    
	    public static Comp sub(Comp mycomp1,Comp mycomp2) {
	    	Comp mycomp3 =new Comp();
	    	    mycomp3.setCoplex( mycomp1.getReal()-mycomp2.getReal(),mycomp1.getImage()-mycomp2.getImage());
	    	    return mycomp3;    
	    	    }
	    
	   public void printComplex() {
		   if (img<0)
	        System.out.println(real+"-"+Math.abs(img)+"i");
	        else
	        	System.out.println(real+"+"+Math.abs(img)+"i");


	    }
	    
	    
	    
	    


	}




