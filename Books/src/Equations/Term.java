package Equations;

public class Term{
	 double coefficient;
	 String variable;
	 int exponent;
	 boolean constant;//this is true if a Term has no variable expression

	 public Term(double constant){
		 //sets coefficient to constant, variable to "", exponent to "0" and constant to 'true')
		 coefficient = constant;
		 variable = "";
		 exponent =0;
		 this.constant = true;
	 }
	 //NEW CONSTRUCTOR
	 public Term(double coefficent, String var, int exp){
		 
		 this.coefficient=coefficent;
		 variable=var;
		 exponent = exp;
		 if(!var.equals("")){
			 constant = false;
		 }
		 else{
			 constant = true;
			 exponent = 0;
		 }
		 if(coefficent==0){
			 variable="";
			 exponent=0;
		 }
		 //if(var.equals)
	 }

	 public double getCoefficient() {
		return coefficient;
	}
	 
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	public String getVariable() {
		return variable;
	}

	public int getExponent() {
		return exponent;
	}

	public boolean isConstant() {
		return constant;
	}

	/**
	  *a constructor for constant: 
	  */
	 /**
	  *Write getters for each field
	  *Note that the getter for the boolean should be called 'isConstant'
	  */ 

	 public Term getAddInverse(){
	 //returns the additive inverse of this Term
		this.coefficient=-1*(this.coefficient);
		return this;
	 }

	 public int getDegree(){
	 //returns the exponent
		 return exponent;
	 }

	 public boolean isPositive(){
       //returns true if the coefficient is positive (or zero), false otherwise
		 if(this.getCoefficient()>0){
	   		return true;
	   	}
	  	else{
	   		return false;
	   	}
	 }	

	public String toString(){
	 /**
	  *Some tips to consider:
	                *doubles always print with trailing zeros (i.e. 2.0) This is not desireable
	  *If a term has a coefficient of 1 or -1, the 1 should not be shown. 
	  *If a term has an exponent of 1, the 1 should not be shown
	  *For exponents, use '^'. The GUI will change it into superscript.
	  */

		String str = null;
		if(this.getCoefficient()==1.0){
			if(this.getExponent()==1.0){
				str = this.getVariable();
			}
			else{
				str = this.getVariable() + "^" + this.getExponent();
			}
		}
		if(this.getCoefficient()==-1.0){
			if(this.getExponent()==1.0){
				str = "-" +this.getVariable();
			}
			else{
				str = this.getVariable() + "^" + this.getExponent();
			}
		}
		return str;
	}
	
	//4.1 Homework
	
	
	public static boolean areLikeTerms(Term s, Term t){
		//Checks if values are the same exponent and variable (like 3x^2 and -2x^2) 
		if(s.getExponent()==t.getExponent() && s.getVariable() == t.getVariable()){
			
			return true;
			
		}else{
			
			return false;
		}
		
	}
	
	
	public static Term combine(Term s, Term t){
		
		Term holder = new Term(0);
		
		holder.setCoefficient(s.getCoefficient()+t.getCoefficient());
		
		return holder;
		
	}
}