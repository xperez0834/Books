package Equations;

import java.util.ArrayList;
public class Equation{
	private ArrayList<Term> leftSide;
	private ArrayList<Term> rightSide;
	private boolean cancelRight;
	private ArrayList<Double> solution;
	double a=0;
	double b=0;
	double c=0;
	double d=0;
	
	public ArrayList<Term> getLeftSide() {
		return leftSide;
	}
	public void setLeftSide(ArrayList<Term> leftSide) {
		this.leftSide = leftSide;
	}
	public ArrayList<Term> getRightSide() {
		return rightSide;
	}
	public void setRightSide(ArrayList<Term> rightSide) {
		this.rightSide = rightSide;
	}
	public boolean isCancelRight() {
		return cancelRight;
	}
	public void setCancelRight(boolean cancelRight) {
		this.cancelRight = cancelRight;
	}
	public ArrayList<Double> getSolution() {
		return solution;
	}
	public void setSolution(ArrayList<Double> solution) {
		this.solution = solution;
	}
	public Equation(ArrayList<Term> leftSide, ArrayList<Term> rightSide){
		//sets values of leftSide and rightSide only
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}
	public boolean isLinear(){

		return isOfDegree(1);
	}

	private boolean isOfDegree(int degree){
		int maxDegree = 0;
		int minDegree = 0;
		String variable = "";
		for (Term t: leftSide){
			if(t.getDegree()>maxDegree)maxDegree=t.getDegree();
			if(t.getDegree()<minDegree)minDegree=t.getDegree();
			if(!t.isConstant())variable=t.getVariable();
		}
		for (Term t: rightSide){
			if(t.getDegree()>maxDegree)maxDegree=t.getDegree();
			if(t.getDegree()<minDegree)minDegree=t.getDegree();
			if(!t.isConstant())variable=t.getVariable();
		}
		if(maxDegree==degree && minDegree >=0 && variablesMatch(variable))return true;
		else return false;
	}

	private boolean variablesMatch(String s){
		boolean match = true;
		for(Term t: leftSide){
			if(!t.isConstant() && !t.getVariable().equals(s)) return false;
		}
		for(Term t: rightSide){
			if(!t.isConstant() && !t.getVariable().equals(s)) return false;
		}
		return match;
	}
	public boolean isQuadratic(){
		return isOfDegree(2);
	}
	public boolean isSolveable(){
		if(isLinear() || isQuadratic()){
			return true;
		}
		return false;
	}
	public boolean cancelRight(){
		return true;
	}
	public String getSideString(ArrayList<Term> side){
		String s = "";
		try{
			s=side.get(0).toString();
		}
		catch(Exception e){
			s = "0";
		}
		for(int i =1;i<side.size();i++){
			if(side.get(i).isPositive())s+=" + "+side.get(i);
			else s+=" - "+side.get(i).toString().replaceFirst("-", "");
		}
		return s;
	}

	public String toString(){
		String returnString = "";
		for(Term x: this.leftSide){
			if(x.isPositive()){
				if(this.leftSide.indexOf(x)==0){
					returnString += x.toString();	
				}
				else{
					returnString += " + " + x.toString();	
				}
			}
			else{
				returnString += " - " + x.toString();
			}
		}
		returnString += " = ";
		for(Term x: this.rightSide){
			if(x.isPositive()){
				if(this.rightSide.indexOf(x)==0){
					returnString += x.toString();	
				}
				else{
					returnString += " + " + x.toString();	
				}
			}
			else{
				returnString += "-" + x.toString();
			}
		}
		return returnString;
	}
	public static Term getHighestDegreeTerm(ArrayList<Term> side){
		int high = 0;
		Term highTerm = null;
		for(Term x: side){
			if(x.getDegree()>high){
				high = x.getDegree();
				highTerm = x;
			}
		}
		return highTerm;
	}
	public void toZeroOnOneSide(ArrayList<Term> sideBeingCanceled){
		ArrayList<Term> addIn = new ArrayList<Term>();
		for(int i = 0; i < sideBeingCanceled.size(); i++){
			sideBeingCanceled.get(i).getAddInverse();
		}
		for(Term t:addIn){
			leftSide.add(t);
			rightSide.add(new Term(t.getCoefficient(),t.getVariable(),t.getExponent()));
		}
	}
	
	public void simplify(ArrayList<Term> side){
		ArrayList<Term> removeThese = new ArrayList<Term>();
		for(Term t:side){
			for(Term s:side){
				if(t!=s && t.getCoefficient()!=0){
					if(Term.areLikeTerms(s, t)){
						Term result = Term.combine(s,t);
						t.setCoefficient(result.getCoefficient());
						s.setCoefficient(0);
						removeThese.add(s);
					}
				}
			}
		}
		side.removeAll(removeThese);
	}

	public void solveLinear(ArrayList<Term> sideWithVariable){
		Term tmpConst = null;
		Term tmpVarTerm = null;
		for(Term t : sideWithVariable){
			if(t.isConstant()){
				tmpConst = t;
			}
			if(!t.getVariable().equals("")){
				tmpVarTerm = t;
			}
		}
		rightSide.add(tmpConst.getAddInverse());
		leftSide.add(tmpConst.getAddInverse());
	}

	public void solveQuadratic(Equation eq){
		factor(eq.leftSide);
	}

	@SuppressWarnings("null")
	public void factor(ArrayList<Term> eq){
		a = eq.get(0).getCoefficient();
		b = eq.get(1).getCoefficient();
		c = eq.get(2).getCoefficient();
		double d = (b*b) - 4*(a*c);

		double[] positiveFacts = null;
		double[] negativeFacts = null;
		for(int i = 0; i < a*c; i++){
			if((a*c)%i == 0){
				positiveFacts[i] = i;
				negativeFacts[i] = -1;
			}
		}

		if(d > 0){
			System.out.println("There are two real roots");
		}
		if(d == 0){
			System.out.println("There are is one real root that is repeated");
		}
		if(d < 0){
			System.out.println("There are no real roots");
		}
	}
	public void multiplyScalar(ArrayList<Term> side, double scalar){
		double multiScalar;
		for(int i = 0; i < side.size(); i++){
			multiScalar = side.get(i).getCoefficient()*scalar;
		}
	}
	public double determineDiscriminant(ArrayList<Term> side){
		//Variables used to determine discriminate
    	double a=0.0;
    	double b=0.0;
    	double c=0.0;
    	double discriminant;
    	
    	//Checks all characters on given side
    	for(int i=0;i<side.size();i++){
    		//Finds a
    		if(side.get(i).getExponent()==2)	a = side.get(i).getCoefficient();
    		//Finds b
    		if(side.get(i).getExponent()==1)	b = side.get(i).getCoefficient();
    		//Finds c
    		if(side.get(i).getExponent()==0)	c = side.get(i).getCoefficient();
    	}
    	//Uses discriminate equation
    	//b^2-4ac
    	discriminant = (b*b) - 4*a*c;
    	return discriminant;
    }
	public double[] quadraticFormula(ArrayList<Term> side){
		double root1 = 0;
		double root2 = 0;
		double discriminate=0;
		
		//Returned double array
		double[] roots={root1,root2};
		
		for(int i=0;i<side.size();i++){
    		//Finds a
    		if(side.get(i).getExponent()==2)	a = side.get(i).getCoefficient();
    		//Finds b
    		if(side.get(i).getExponent()==1)	b = side.get(i).getCoefficient();
    		//Finds c
    		if(side.get(i).getExponent()==0)	c = side.get(i).getCoefficient();
    	}
		if(discriminate>=0){
			//Real roots
			root1 = (-b + Math.sqrt((b*b)-(4)*(a)*(c)))	/	((2)*(a));
			root2 = (-b - Math.sqrt((b*b)-(4)*(a)*(c)))	/	((2)*(a));
		}
		SolverGUI.addStep(root1 + root2);
	}
	
	public double[] uselessFormula(ArrayList<Term> side){
		double root1 = 1*10^-99;
		double root2 = 1*10^-99;
		
		//Returned double array
		double[] roots={root1,root2};
		
		for(int i=0;i<side.size();i++){
    		//Finds a
    		if(side.get(i).getExponent()==2)	a = side.get(i).getCoefficient();
    		//Finds b
    		if(side.get(i).getExponent()==1)	b = side.get(i).getCoefficient();
    		//Finds c
    		if(side.get(i).getExponent()==0)	c = side.get(i).getCoefficient();
    	}
		
			if((root1*root2)==c && (root1+root2)==b){
			
			}
			
		return roots;
	}
}