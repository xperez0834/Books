package Equations;

import java.util.ArrayList;

import javax.swing.JFrame;

import Equations.Term;

public class Solver{


	public static void main(String[] args){
		System.out.println("...on the left, and on the right:");
		
		
		//Once your methods are all working, you can use the following to initiate the GUI
		SolverGUI s = new SolverGUI();
		s.setVisible(true);
		s.setSize(900, 500);
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static Equation interpretInput(String s){
		//Gets rid of spaces
		String str = s.replaceAll("\\s","");

		String[] leftAndRight = str.split("=");

		ArrayList<Term> leftSide = createAndAddTerms(leftAndRight[0]);
		ArrayList<Term> rightSide = createAndAddTerms(leftAndRight[1]);

		return new Equation(leftSide,rightSide);
	}

	private static ArrayList<Term> createAndAddTerms(String termString){
		ArrayList<Term> terms = new ArrayList<Term>();

		int i = 0;//Index of the first digit (in case the first term is negative)
		boolean positiveTerm= true;//Assumes term is positive
		if(termString.startsWith("-")){
			positiveTerm=false;
			i++;//start at index 1 if character at 0 is "-"
		}
		while(termString.length()>0){
			int endOfTerm=termString.length();
			int indexOfPlus = termString.indexOf("+");
			//If there is a "+", above value is -1
			if(indexOfPlus<0)indexOfPlus=endOfTerm;

			int indexOfMinus = termString.indexOf("-");
			//If there is a "+", above value is -1
			if(indexOfMinus<0)indexOfMinus=endOfTerm;

			if(indexOfMinus<indexOfPlus) {endOfTerm=indexOfMinus;}
			else{endOfTerm=indexOfPlus;}

			try{
				Term a = parseTerm(termString.substring(i,endOfTerm));

				if(!positiveTerm)	
					a=a.getAddInverse();//HomeWork

				if(a!= null) terms.add(a);

				//Check if next term is + or -
				if (indexOfMinus<indexOfPlus)positiveTerm = false;
				else positiveTerm=true;

				//cut out the term that was added. including the NEXT +/- symbol

				termString = termString.substring(endOfTerm+1,termString.length());
			}catch(Exception e){
				//If the user is trying to do something that throws an error
				//Skip this term
				termString = termString.substring(endOfTerm,termString.length());
				//Cuts out the term
			}
		}
		return null;
	}
	/**

	 * @param sample string from user: 34a^3

	 * @return new Term representing input from user

	 * Use this method for writing interpretInput

	 */

	private static Term parseTerm(String s) {

		if(s.matches("\\d*\\w\\^\\d+" +"|" + "\\d*\\w" + "|" + "\\d+")){
//regex identifies anything like 23x^2 or y^11 or 12b or z or 13

			double coef;

			String variable;

			Term t;

			if(s.matches("\\d*")){

				coef = Double.parseDouble(s);

				t = new Term(coef);

			}

			else if(s.matches("\\d*\\w+(\\^\\d+)?")){

				String noDigits = s.replaceAll("\\d", "");

				int indOfVar = s.indexOf(noDigits);

				if(s.substring(0,indOfVar).length()==0){

					coef = 1.0;

				}

				else coef = Double.parseDouble(s.substring(0, indOfVar));

				variable=s.substring(indOfVar, indOfVar+1);

				if(s.indexOf("^")>-1){

					int exponent = Integer.parseInt(s.substring(s.indexOf("^")+1));

					t =new Term(coef,variable,exponent); 

				}else{

					t =new Term(coef,variable,1); 

				}

			}else{

				t=null;

			}

			return t;

		}

		else return null;

	}

	public static String getConfirm(Equation eq) {
		return null;
	}

	public static String getNoTricks(String usersInput) {
		return usersInput;
	}

	public static void solve(Equation eq){
		ArrayList<Term> side = eq.getLeftSide();
		if(eq.cancelRight())side = eq.getRightSide();
		
	}

}