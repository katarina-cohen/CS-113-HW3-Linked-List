/**
*Term.java
*
*Stores the coefficient and exponent values for a given term. Coefficient and exponent can be entered as individual terms, or a string of the form 4x^2 can be parsed for these values. Class also implemements Comparable interface to compare exponents to one another (whether they are greater, lesser, or equal in value).
*
*@author Katarina Cohen
*@version 2.0
*
*/

package edu.miracosta.cs113;

public class Term implements Comparable<Term>, Cloneable {

  //CONSTANT VARIABLES
  public static final int DEFAULT_COEFFICIENT = 1;
  public static final int DEFAULT_EXPONENT = 1;
  
  //INSTANCE VARIABLES
  private int coefficient = 0;
  private int exponent = 0;
 
  /**
  *Full constructor, initializes coefficient and exponent for new Term object.
  *
  *@param c   the integer set as the term's coefficient
  *@param e   the integer set as the term's exponent
  */
  public Term(int coefficient, int exponent) {
    this.coefficient = coefficient;
    this.exponent = exponent;
  }

  /**
  *Full String constructor, initializes coefficient and exponent for new Term object.
  *Reformats the entered string into the form cx^e for easier parsing.  
  *
  *@param term   the term from which the coefficient and exponent will be extracted and set
  */
  public Term (String term) { 
	  
	  term = term.replace(" ", "");
	  
	  if (term.equals("")) {
		  term = "0x^0";
	  }
	  else {
		  //Reformat coefficients +1 and -1
		  term = term.replace("-x", "-1x");
		  if (term.startsWith("x")) {
			  term = "1x";
		  }
		  else if (term.startsWith("+x")) {
			  term = term.replace("+", "+1");
		  }
		  
		//Reformat exponents 0 and 1
		  if (!term.contains("x")) {
			  term += "x^0";
		  }
		  else if (!term.contains("^")) {
			  term += "^1";
		  }
	  }
	  		  
	  String [] parts = term.split("x\\^");
	  this.coefficient = Integer.parseInt(parts[0]);
	  this.exponent = Integer.parseInt(parts[1]);
	}

  /**
  *Default constructor, initializes Term to coefficient and exponent constants if nothing provided.
  */
  public Term() {
	  this.coefficient = DEFAULT_COEFFICIENT;
      this.exponent = DEFAULT_EXPONENT;
  }

  /**
  *Copy constructor creates an object of type Term by initializing it with a Term object that has been created previously, if this Term is not null.
  *
  *@param original  the Term object you want to copy from
  */
  public Term(Term original) {
    if(original == null) {
      System.out.println("ERROR: Null data given to copy Term constructor. Exiting...");
      System.exit(0);
    }
    else{
      this.setAll(original.coefficient, original.exponent);
    }
  }

  /**
  *Sets given integer as term's coefficient.
  *
  *@param coefficient   the integer entered for the coefficient
  */
  public void setCoefficient(int coefficient) {
	  this.coefficient = coefficient;
  }

  /**
  *Sets given integer as term's exponent.
  *
  *@param exponent   the integer entered for the exponent
  */
  public void setExponent(int exponent) {
	  this.exponent = exponent;
  }

  /**
  *Sets given integer as term's coefficient and exponent.
  *
  *@param coefficient   the integer entered for the coefficient
  *@param exponent   the integer entered for the exponent
  */
  public void setAll(int coefficient, int exponent) {
	  this.coefficient = coefficient;
	  this.exponent = exponent;
  }

  /**
  *Returns term's coefficient as an integer.
  *
  *@return  Returns term's coefficient
  */
  public int getCoefficient() {
	  return this.coefficient;
  }

  /**
  *Returns term's exponent as an integer.
  *
  *@return  Returns term's exponent
  */
  public int getExponent() {
	  return this.exponent;
  }

  /**
  *toString() method that outputs the coefficient and exponent of the provided term. Accounts
  *for the following cases: Case 1 -- c is 0, Case 2 -- e is 0 and c is positive, Case 3 -- e 
  *is 0 and c is negative, Case 4 -- c is -1 and |e| > 1, Case 5 -- c is 1 and |e| > 1, Case 
  *6 -- c is positive and |e| > 1, Case 7 -- c is negative and and |e| > 1.
  *
  *@return  the string containing the term's coefficient and exponent
  */
  @Override
  public String toString() {
	String finalTerm = "";
	
	//Case 1: Coefficient is 0
	if (this.coefficient == 0) {
		return finalTerm;
	}
	else if (this.exponent == 0) {
		if (this.coefficient > 0) {
			return "+" + this.coefficient;
		}
		else {
			return String.valueOf(this.coefficient);
		}
	}
	
	if (this.coefficient == -1) {
		finalTerm += "-x";
	}
	else if (this.coefficient == 1) {
		finalTerm += "+x";
	}
	else {
		if (this.coefficient > 0) {
			finalTerm += "+" + this.coefficient + "x";
		}
		else {
			finalTerm = String.valueOf(this.coefficient) + "x";
		}
	}
	
	if (this.exponent != 1) {
		finalTerm += "^" + this.exponent;
	}
	
    return finalTerm;
  }
  
  /**
  *equals() method that compares one term's coefficient and exponent to another's to assess equality.
  *
  *@param object  reference object to which the current object needs to compare
  *
  *@return  returns false if not equal and true if components are equal  
  */
  @Override
  public boolean equals(Object other) {
    if(other == null || getClass() != other.getClass()) {
      return false;
    }

    Term otherTerm = (Term)other;

    return this.coefficient == otherTerm.coefficient && this.exponent == otherTerm.exponent;
  }

  /**
  *Compares values of exponents.
  *
  *@param t   the Term object whose exponent will be a part of the comparison
  */
  @Override
  public int compareTo(Term t) {
    if (this.exponent > t.exponent) {
      return 1;
    } 
    else if (this.exponent == t.exponent) {
      return 0;
    } 
    else {
      return -1;
    }
  }
  
  /**
  *Clone method.
  */
  public Object clone()
  {
      try
      {
          return super.clone();
      } catch (CloneNotSupportedException e)
      {
          return null;
      }
  }
}