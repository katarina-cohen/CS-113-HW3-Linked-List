/**
*Term.java
*
*Stores the coefficient and exponent values for a given term. Coefficient and exponent can be entered as individual terms, or a string of the form 4x^2 can be parsed for these values. Class also implemements Comparable interface to compare exponents to one another (whether they are greater, lesser, or equal in value).
*
*@author Katarina Cohen
*@version 2.0
*
*/

public class Term implements Comparable<Term> {
  //setter and getter for exponent and coefficient
  //setAll
  //full, default, copy constructors
  //string constructor?

  //CONSTANT VARIABLES
  public static final int DEFAULT_COEFFICIENT = 1;
  public static final int DEFAULT_EXPONENT = 1;
  
  //INSTANCE VARIABLES
  protected int coefficient = 0;
  protected int exponent = 0;
 
  /**
  *Full constructor, initializes coefficient and exponent for new Term object.
  *
  *@param c   the integer set as the term's coefficient
  *@param e   the integer set as the term's exponent
  */
  public Term(int c, int e) {
    this.coefficient = c;
    this.exponent = e;
  }

  /**
  *Full String constructor, initializes coefficient and exponent for new Term object.
  *
  *@param term   the term from which the coefficient and exponent will be extracted and set
  */
  public Term(String term) {
    String c, e = null;
    if (term.contains("x")) {
      String t = term.replace("x", "").replace("^"," ");

      String[] parts = t.split(" ");

      if (parts.length == 1) {
        c = parts[0];
        e = "1";
      }
      else {
        c = parts[0];
        e = parts[1];
      }
      this.coefficient = Integer.parseInt(c);
      this.exponent = Integer.parseInt(e);
    }
    else {
      this.coefficient = Integer.parseInt(term);
      this.exponent = 0;
    }
  }

  /**
  *Default constructor, initializes Term to coefficient and exponent constants if nothing provided.
  */
  public Term() {
    this(DEFAULT_COEFFICIENT, DEFAULT_EXPONENT);
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
    this.setCoefficient(coefficient);
    this.setExponent(exponent);
  }

  /**
  *Returns term's coefficient as an integer.
  *
  *@return  Returns term's coefficient
  */
  public int getCoefficient() {
    return coefficient;
  }

  /**
  *Returns term's exponent as an integer.
  *
  *@return  Returns term's exponent
  */
  public int getExponent() {
    return exponent;
  }

  /**
  *toString() method that outputs the coefficient and exponent of the provided term.
  *
  *@return  the string containing the term's coefficient and exponent
  */
  @Override
  public String toString() {
    return this.coefficient + "x^" + this.exponent;
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
    if(other == null || other instanceof Term) {
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
  
  //clone
}