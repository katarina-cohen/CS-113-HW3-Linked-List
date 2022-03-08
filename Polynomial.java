/**
*Polynomial.java
*
*The Polynomial class creates and stores a linked list of Terms for a given Polynomial object. 
*Methods allow the user to add or remove terms from the linked list and clear the polynomial. 
*Terms are ordered from highest exponent to lowest, and exponents are compared so that terms can 
*be combined or properly added to the list.
*
*@author Katarina Cohen
*@version 2.0
*
*/

import java.util.LinkedList;

public class Polynomial extends Term {

  protected LinkedList<Term> terms = null;

  /**
  *Full constructor, initializes terms to a new linked list.
  */
  public Polynomial() {
    this.terms = new LinkedList();
  }

  /**
  *Copy constructor creates an object of type Polynomial by initializing it with a Polynomial object 
  *that has been created previously, if this Polynomial is not null. Initializes terms and copies every 
  *Term from the Polynomial original.
  *
  *@param original  the Polynomial object you want to copy from
  */
  public Polynomial(Polynomial original) {
    this.terms = new LinkedList();
    if(original == null) {
      System.out.println("ERROR: Null data given to copy Polynomial constructor. Exiting...");
      System.exit(0);
    }
    else{
      for (int i = 0; i < original.getNumTerms(); i++) {
        terms.add(new Term(original.getTerm(i)));
      }
    }
  }

  /**
  *Adds the entered term to a given polnomial. A new coefficient is calculated if the exponent matches 
  *that of a preexisting term. Method also makes sure terms are added in the correct order with highest 
  *exponents first.
  *
  *@param newTerm  the Term you want to add to the polynomial 
  */
  public void addTerm(Term newTerm) {
    Term sumOfTerms = new Term(); 
    Term firstTerm, term, nextTerm = new Term();
    int newCoefficient = 0;

    //If there are no terms in the list (size is 0), then we add the new term to the list.
    if (this.terms.size() == 0) {
      this.terms.add(newTerm);
    }
    //Else if there is one term
    else if (this.terms.size() == 1) {
      firstTerm = terms.get(0);

      //If the newTerm's exponent is greater than the firstTerm's, add newTerm before 
      if (newTerm.compareTo(firstTerm) == 1) {
        terms.add(0, newTerm);
      }
      //If exponents are the same, add coefficients together. If coefficient = 0, remove from the list.
      else if (newTerm.compareTo(firstTerm) == 0) {
        newCoefficient = newTerm.getCoefficient() + firstTerm.getCoefficient();
        sumOfTerms.setAll(newCoefficient, newTerm.getExponent());
        if (newCoefficient !=0) {
          terms.set(0, sumOfTerms);
        }
        else{
          terms.remove(0);
        }
      }
      //If newTerm's exponent is less than firstTerm's, add to end of list
      else {
        terms.add(newTerm);
      }
    }
    //If there is more than one term in the polynomial
    else {
      //Go through and compare to all terms except for the last term becuase there isn't a "nextTerm" for comparison for the final term
      for (int i = 0; i < this.getNumTerms() - 1; i++) {
        term = this.getTerm(i);
        nextTerm = this.getTerm(i+1);

        //If newTerm exponent is greater than the current term, add new Term at that index
        if (newTerm.compareTo(term) == 1) {
          terms.add(i, newTerm);
        }
        //Else if newTerm exponent is equal to current term, add coefficients together
        else if (newTerm.compareTo(term) == 0) {
          newCoefficient = newTerm.getCoefficient() + term.getCoefficient();
        sumOfTerms.setAll(newCoefficient, newTerm.getExponent());

          if (newCoefficient !=0) {
            terms.set(i, sumOfTerms);
          }
          else {
            terms.remove(i);
          }
        }
        //If newTerm exponent is less than the current term exponent but greater than the nextTerm exponent, add at index of the nextTerm
        if ((newTerm.compareTo(term) == -1) && (newTerm.compareTo(nextTerm) == 1)) {
          terms.add(i+1, newTerm);
        }
      }

      //Calculate final index by subtracting 1 from size of the list, use this index to find the lastTerm in the list
      int finalIndex = this.getNumTerms() - 1;
      Term lastTerm = this.getTerm(finalIndex);

      //If the new term has the same exponent as the last term, add coefficients
      if (newTerm.compareTo(lastTerm) == 0) {
        newCoefficient = newTerm.getCoefficient() + lastTerm.getCoefficient();
        sumOfTerms.setAll(newCoefficient, newTerm.getExponent());

          if (newCoefficient !=0) {
            terms.set(finalIndex, sumOfTerms);
          }
          else {
            terms.remove(finalIndex);
          }
      }
      //If exponent of newTerm is still less than the final term's, add to the end of the list.
      else {
        terms.add(newTerm);
      }
    }  
  }

  /**
  *Locates the selected term at the entered index and removes it from the poylnomial.
  *
  *@param i  the integer index of the term you want to remove from the polynomial
  */
  public void remove(int i) {
    this.terms.remove(i);
  }

  /**
  *Clears every term from the selected polynomial's term list. 
  */
  public void clear() {
    this.terms.clear();
  }

  /**
  *Returns the term located at the provided index. 
  *
  *@param i  the integer index of the term accessed
  *
  *@return  the Term located at the entered index
  */
  public Term getTerm(int i) {
    return new Term(this.terms.get(i));
  }

  /**
  *Returns the size of the terms list.
  *
  *@return  the integer size of the terms list (the number of terms in the list)
  */
  public int getNumTerms() {
    return this.terms.size();
  }

  /**
  *Uses addTerm() method to add the terms of one polynomial to another.
  *
  *@param polynomial  the Polynomial object whose terms will be added to another polynomial
  */
  public void add(Polynomial polynomial) {
    for (int i = 0; i < polynomial.getNumTerms(); i++) {
      this.addTerm(polynomial.getTerm(i));
    }
  }


  /**
  *toString() method that outputs a properly formatted polynomial.
  *
  *@return  the string containing each term's coefficient and exponent
  */
  @Override
  public String toString() {
    String finalPolynomial = "";

    if(this.terms == null || this.terms.size() == 0) {
      finalPolynomial = "  -  Empty  -  ";
    }
    else {
      for (Term term : terms) {
        finalPolynomial += (" + " + term.toString());
      }
      if (finalPolynomial.charAt(1) == '+') {
        finalPolynomial = finalPolynomial.substring(2);
      }
    }
    
    return finalPolynomial;
  }
  
  /**
  *equals() method that compares one polynomial's term list to another to assess equality.
  *
  *@param object  reference object to which the current object needs to compare
  *
  *@return  returns false if not equal and true if polynomials are equal  
  */
  @Override
  public boolean equals(Object other) {
    if(other == null || other instanceof Polynomial) {
      return false;
    }

    Polynomial otherPolynomial = (Polynomial)other;

    return this.terms.equals(otherPolynomial.terms);
  }
}
