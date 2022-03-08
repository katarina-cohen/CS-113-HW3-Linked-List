/**
*PolynomialDriver.java
*
*The PolynomialDriver class allows the user to store, display, and manipulate two polynomials. The user can add or remove terms to each polynomial, clear a polynomial, or find the sum of the two polynomials.
*
*@author Katarina Cohen
*@version 1.0
*
*/

import java.util.Scanner;

public class PolynomialDriver {
  private static final String POLYNOMIAL_A = "Polynomial A: ";
  private static final String POLYNOMIAL_B = "Polynomial B: ";
  
  private static Polynomial polynomialA, polynomialB;
  private static Scanner scan = new Scanner(System.in);
  
  public static void main(String[] args) {
    
    polynomialA = new Polynomial();
    polynomialB = new Polynomial();

    menu();
    
  }

  /**
  *Displays the main menu for the driver program. User can select one of four options (1-4), and the menu responds accordingly by displaying the editPolynomial menu, displaying the sum, or exiting the program.
  */
  private static void menu() {
    int choice;

    do {
      System.out.println("Main Menu");
      System.out.println();
      System.out.println(POLYNOMIAL_A + polynomialA.toString());
      System.out.println(POLYNOMIAL_B + polynomialB.toString());
      System.out.println();
      System.out.println("1) Edit Polynomial A\n2) Edit Polynomial B\n3) Display Sum\n4) Exit");
      System.out.println();
      System.out.println("Select an option (1-4): ");

      choice = scan.nextInt();

      switch(choice) {
        case 1:
          editPolynomial(POLYNOMIAL_A, polynomialA);
          break;
        
        case 2:
          editPolynomial(POLYNOMIAL_B, polynomialB);
          break;
        
        case 3:
          Polynomial copyPolyA = new Polynomial(polynomialA);
          copyPolyA.add(polynomialB);
          System.out.println("\nThe sum of Polynomials A and B is " + copyPolyA.toString() + "\n");
          break;
        
        case 4:
          scan.close();
          System.exit(0);
          break;
        
        default:
          System.out.println("Unknown option. Please try again.");
          break;
      }
    } while(choice != 0);  
  }

  /**
  *Displays the edit menu for each polynomial. User can select one of four options (1-4). User can add or remove a term from the polynomial, clear a polynomial, or return to the main menu.
  */
  private static void editPolynomial(String poly, Polynomial polynomial) {
    int choice;
    do {
      System.out.println("\nEdit Polynomial");
      System.out.println();
      System.out.println(poly + polynomial.toString());
      System.out.println();
      System.out.println("1) Add Term\n2) Remove Term\n3) Clear\n4) Exit");
      System.out.println();
      System.out.println("Select an option (1-4): ");

      choice = scan.nextInt();
    
      switch(choice) {
        case 1:
          Term newTerm = new Term();
          System.out.println("\nEnter term Coefficient: ");
          newTerm.setCoefficient(scan.nextInt());
          System.out.println("\nEnter term Exponent: ");
          newTerm.setExponent(scan.nextInt());
          polynomial.addTerm(newTerm);
          break;
        
        case 2:
          System.out.println("\nEnter the index of the term you'd like to remove: ");
          int index = scan.nextInt();
          polynomial.remove(index);
          break;
        
        case 3:
          polynomial.clear();
          break;
        
        case 4:
          menu();
          break;
        
        default:
          System.out.println("Unknown option. Please try again.");
          break;
      }
    } while (choice != 4);
  }
}