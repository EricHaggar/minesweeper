/**
 * The class <b>DotInfo</b> is a simple helper class to store the state (e.g.
 * clicked, mined, number of neighbooring mines...) at the dot position (x,y)
 */

public class DotInfo {

  private boolean covered;
  private boolean mined;
  public int neighbooringMines;
  private boolean wasClicked;
  private int x;
  private int y;
  private boolean flagged;

  /**
   * Constructor, used to initialize the instance variables
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public DotInfo(int x, int y) {

    this.x = x;
    this.y = y;
    covered = true;
    mined = false;
    neighbooringMines = 0;
    wasClicked = false;
    flagged = false;

  }

  /**
   * Getter method for the attribute x.
   *
   * @return the value of the attribute x
   */
  public int getX() {
    return this.x;
  }

  /**
   * Getter method for the attribute y.
   *
   * @return the value of the attribute y
   */
  public int getY() {
    return this.y;
  }

  /**
   * Setter for mined
   */
  public void setMined() {
    mined = true;
  }

  /**
   * Getter for mined
   *
   * @return mined
   */
  public boolean isMined() {
    return mined;
  }

  /**
   * Setter for covered
   */
  public void uncover() {
    covered = false;
  }

  /**
   * Getter for covered
   *
   * @return covered
   */
  public boolean isCovered() {
    return covered;
  }

  /**
   * Setter for wasClicked
   */
  public void click() {
    wasClicked = true;
  }

  /**
   * Getter for wasClicked
   *
   * @return wasClicked
   */
  public boolean hasBeenClicked() {
    return wasClicked;
  }

  /**
   * Setter for neighbooringMines
   *
   * @param neighbooringMines number of neighbooring mines
   */
  public void setNeighbooringMines(int neighbooringMines) {
    this.neighbooringMines = neighbooringMines;
  }

  /**
   * Get for neighbooringMines
   *
   * @return neighbooringMines
   */
  public int getNeighbooringMines() {
    return neighbooringMines;
  }

  /**
   * Setter for flagged to set it to true
   */
  public void flag() {

    flagged = true;

  }

  /**
   * Setter for flagged to set it to false
   */
  public void unflag() {

    flagged = false;

  }

  /**
   * Getter for flagged
   *
   * @return flagged
   */
  public boolean isFlagged() {

    return flagged;

  }

}
