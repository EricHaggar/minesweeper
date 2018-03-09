//Student name: Eric Haggar
//Student number: 7674509
//Course code: ITI1521
//Lab section: A-2

/**
* The class <b>DotInfo</b> is a simple helper class to store
* the state (e.g. clicked, mined, number of neighbooring mines...)
* at the dot position (x,y)
*
* @author Guy-Vincent Jourdan, University of Ottawa
*/

public class DotInfo {

  // ADD YOUR INSTANCE VARIABLES HERE

  private boolean covered;
  private boolean mined;
  public int neighbooringMines;
  private boolean wasClicked;
  private int x;
  private int y;


  /**
  * Constructor, used to initialize the instance variables
  *
  * @param x
  *            the x coordinate
  * @param y
  *            the y coordinate
  */
  public DotInfo(int x, int y){

    // ADD YOU CODE HERE

    //initializing all instance variables
    this.x = x;
    this.y = y;
    covered = true;
    mined = false;
    neighbooringMines = 0;
    wasClicked = false;

  }

  /**
  * Getter method for the attribute x.
  *
  * @return the value of the attribute x
  */
  public int getX(){

    // ADD YOU CODE HERE

    return this.x;

  }

  /**
  * Getter method for the attribute y.
  *
  * @return the value of the attribute y
  */
  public int getY(){

    // ADD YOU CODE HERE

    return this.y;

  }


  /**
  * Setter for mined
  */
  public void setMined() {

    // ADD YOU CODE HERE

    mined = true;

  }

  /**
  * Getter for mined
  *
  * @return mined
  */
  public boolean isMined() {

    // ADD YOU CODE HERE

    return mined;

  }


  /**
  * Setter for covered
  */
  public void uncover() {

    // ADD YOU CODE HERE

    covered = false;

  }

  /**
  * Getter for covered
  *
  * @return covered
  */
  public boolean isCovered(){

    // ADD YOU CODE HERE
    return covered;

  }


  /**
  * Setter for wasClicked
  */
  public void click() {

    // ADD YOU CODE HERE

    wasClicked = true;

  }


  /**
  * Getter for wasClicked
  *
  * @return wasClicked
  */
  public boolean hasBeenClicked() {

    // ADD YOU CODE HERE

    return wasClicked;

  }


  /**
  * Setter for neighbooringMines
  *
  * @param neighbooringMines
  *          number of neighbooring mines
  */
  public void setNeighbooringMines(int neighbooringMines) {

    // ADD YOU CODE HERE

    this.neighbooringMines = neighbooringMines;

  }

  /**
  * Get for neighbooringMines
  *
  * @return neighbooringMines
  */
  public int getNeighbooringMines() {

    // ADD YOU CODE HERE

    return neighbooringMines;

  }

}
