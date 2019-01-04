import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. It
 * stores the following information: - the state of all the ``dots'' on the
 * board (mined or not, clicked or not, number of neighbooring mines...) - the
 * size of the board - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough
 * appropriate Getters. The controller can also update the model through
 * Setters. Finally, the model is also in charge of initializing the game
 */
public class GameModel {

  private int heigthOfGame;
  private DotInfo[][] model;
  private int numberOfMines;
  private int numberOfSteps;
  private int numberUncovered;
  private int widthOfGame;

  /**
   * Constructor to initialize the model to a given size of board.
   *
   * @param width         the width of the board
   *
   * @param heigth        the heigth of the board
   *
   * @param numberOfMines the number of mines to hide in the board
   */
  public GameModel(int width, int heigth, int numberOfMines) {

    // initializing all needed variables
    this.widthOfGame = width;
    this.heigthOfGame = heigth;
    this.numberOfMines = numberOfMines;

    numberOfSteps = 0;
    numberUncovered = 0;

    // creating an instance of the DotInfo class and populating it with mines at
    // random locations

    model = new DotInfo[heigthOfGame][widthOfGame];

    Random generator = new Random();

    int randomWidth, randomHeight;

    for (int i = 0; i < heigthOfGame; i++) {

      for (int j = 0; j < widthOfGame; j++) {

        model[i][j] = new DotInfo(i, j);
      }
    }

    // Algorithm to calculate the number of Neighbooring Mines efficiently (O(n))
    // Algorithm: Set of if statements for every possibility

    for (int i = 0; i < numberOfMines; i++) {

      randomWidth = generator.nextInt(widthOfGame);
      randomHeight = generator.nextInt(heigthOfGame);

      if (!(model[randomHeight][randomWidth].isMined())) {

        model[randomHeight][randomWidth].setMined();

        if (randomWidth - 1 >= 0) {
          model[randomHeight][randomWidth - 1]
              .setNeighbooringMines(model[randomHeight][randomWidth - 1].getNeighbooringMines() + 1);
        }
        if (randomWidth + 1 < widthOfGame) {
          model[randomHeight][randomWidth + 1]
              .setNeighbooringMines(model[randomHeight][randomWidth + 1].getNeighbooringMines() + 1);
        }
        if (randomHeight - 1 >= 0) {
          model[randomHeight - 1][randomWidth]
              .setNeighbooringMines(model[randomHeight - 1][randomWidth].getNeighbooringMines() + 1);
        }
        if (randomHeight + 1 < heigthOfGame) {
          model[randomHeight + 1][randomWidth]
              .setNeighbooringMines(model[randomHeight + 1][randomWidth].getNeighbooringMines() + 1);
        }
        if (randomWidth - 1 >= 0 && randomHeight - 1 >= 0) {
          model[randomHeight - 1][randomWidth - 1]
              .setNeighbooringMines(model[randomHeight - 1][randomWidth - 1].getNeighbooringMines() + 1);
        }
        if (randomWidth + 1 < widthOfGame && randomHeight - 1 >= 0) {
          model[randomHeight - 1][randomWidth + 1]
              .setNeighbooringMines(model[randomHeight - 1][randomWidth + 1].getNeighbooringMines() + 1);
        }
        if (randomWidth - 1 >= 0 && randomHeight + 1 < heigthOfGame) {
          model[randomHeight + 1][randomWidth - 1]
              .setNeighbooringMines(model[randomHeight + 1][randomWidth - 1].getNeighbooringMines() + 1);
        }
        if (randomWidth + 1 < widthOfGame && randomHeight + 1 < heigthOfGame) {
          model[randomHeight + 1][randomWidth + 1]
              .setNeighbooringMines(model[randomHeight + 1][randomWidth + 1].getNeighbooringMines() + 1);
        }

      }

    }

  }

  /**
   * Resets the model to (re)start a game. The previous game (if there is one) is
   * cleared up .
   */
  public void reset() {

    // ADD YOU CODE HERE

    // Same thing as in the constructor
    model = new DotInfo[heigthOfGame][widthOfGame];
    numberOfSteps = 0;
    numberUncovered = 0;

    Random generator = new Random();

    int randomWidth, randomHeight;

    for (int i = 0; i < heigthOfGame; i++) {

      for (int j = 0; j < widthOfGame; j++) {

        model[i][j] = new DotInfo(i, j);
      }
    }

    for (int i = 0; i < numberOfMines; i++) {

      randomWidth = generator.nextInt(widthOfGame);
      randomHeight = generator.nextInt(heigthOfGame);

      if (!(model[randomHeight][randomWidth].isMined())) {

        model[randomHeight][randomWidth].setMined();

        if (randomWidth - 1 >= 0) {
          model[randomHeight][randomWidth - 1]
              .setNeighbooringMines(model[randomHeight][randomWidth - 1].getNeighbooringMines() + 1);
        }
        if (randomWidth + 1 < widthOfGame) {
          model[randomHeight][randomWidth + 1]
              .setNeighbooringMines(model[randomHeight][randomWidth + 1].getNeighbooringMines() + 1);
        }
        if (randomHeight - 1 >= 0) {
          model[randomHeight - 1][randomWidth]
              .setNeighbooringMines(model[randomHeight - 1][randomWidth].getNeighbooringMines() + 1);
        }
        if (randomHeight + 1 < heigthOfGame) {
          model[randomHeight + 1][randomWidth]
              .setNeighbooringMines(model[randomHeight + 1][randomWidth].getNeighbooringMines() + 1);
        }
        if (randomWidth - 1 >= 0 && randomHeight - 1 >= 0) {
          model[randomHeight - 1][randomWidth - 1]
              .setNeighbooringMines(model[randomHeight - 1][randomWidth - 1].getNeighbooringMines() + 1);
        }
        if (randomWidth + 1 < widthOfGame && randomHeight - 1 >= 0) {
          model[randomHeight - 1][randomWidth + 1]
              .setNeighbooringMines(model[randomHeight - 1][randomWidth + 1].getNeighbooringMines() + 1);
        }
        if (randomWidth - 1 >= 0 && randomHeight + 1 < heigthOfGame) {
          model[randomHeight + 1][randomWidth - 1]
              .setNeighbooringMines(model[randomHeight + 1][randomWidth - 1].getNeighbooringMines() + 1);
        }
        if (randomWidth + 1 < widthOfGame && randomHeight + 1 < heigthOfGame) {
          model[randomHeight + 1][randomWidth + 1]
              .setNeighbooringMines(model[randomHeight + 1][randomWidth + 1].getNeighbooringMines() + 1);
        }

      }

    }

  }

  /**
   * Getter method for the heigth of the game
   *
   * @return the value of the attribute heigthOfGame
   */
  public int getHeigth() {
    return heigthOfGame;

  }

  /**
   * Getter method for the width of the game
   *
   * @return the value of the attribute widthOfGame
   */
  public int getWidth() {
    return widthOfGame;
  }

  /**
   * returns true if the dot at location (i,j) is mined, false otherwise
   *
   * @param i the x coordinate of the dot
   * @param j the y coordinate of the dot
   * @return the status of the dot at location (i,j)
   */
  public boolean isMined(int i, int j) {
    return model[i][j].isMined();
  }

  /**
   * returns true if the dot at location (i,j) has been clicked, false otherwise
   *
   * @param i the x coordinate of the dot
   * @param j the y coordinate of the dot
   * @return the status of the dot at location (i,j)
   */
  public boolean hasBeenClicked(int i, int j) {
    return model[i][j].hasBeenClicked();
  }

  /**
   * returns true if the dot at location (i,j) has zero mined neighboor, false
   * otherwise
   *
   * @param i the x coordinate of the dot
   * @param j the y coordinate of the dot
   * @return the status of the dot at location (i,j)
   */
  public boolean isBlank(int i, int j) {
    return model[i][j].getNeighbooringMines() == 0;

  }

  /**
   * returns true if the dot is covered, false otherwise
   *
   * @param i the x coordinate of the dot
   * @param j the y coordinate of the dot
   * @return the status of the dot at location (i,j)
   */
  public boolean isCovered(int i, int j) {
    return model[i][j].isCovered();
  }

  /**
   * returns the number of neighbooring mines os the dot at location (i,j)
   *
   * @param i the x coordinate of the dot
   * @param j the y coordinate of the dot
   * @return the number of neighbooring mines at location (i,j)
   */
  public int getNeighbooringMines(int i, int j) {
    return model[i][j].getNeighbooringMines();

  }

  /**
   * Sets the status of the dot at location (i,j) to uncovered
   *
   * @param i the x coordinate of the dot
   * @param j the y coordinate of the dot
   */
  public void uncover(int i, int j) {
    model[i][j].uncover();
    numberUncovered++;

  }

  /**
   * Sets the status of the dot at location (i,j) to clicked
   *
   * @param i the x coordinate of the dot
   * @param j the y coordinate of the dot
   */
  public void click(int i, int j) {
    model[i][j].click();

  }

  /**
   * Uncover all remaining covered dot
   */
  public void uncoverAll() {

    for (int i = 0; i < heigthOfGame; i++) {

      for (int j = 0; j < widthOfGame; j++) {

        if (model[i][j].isCovered()) {

          model[i][j].uncover();
        }

      }
    }

  }

  /**
   * Getter method for the current number of steps
   *
   * @return the current number of steps
   */
  public int getNumberOfSteps() {
    return numberOfSteps;
  }

  /**
   * Getter method for the model's dotInfo reference at location (i,j)
   *
   * @param i the x coordinate of the dot
   * @param j the y coordinate of the dot
   *
   * @return model[i][j]
   */
  public DotInfo get(int i, int j) {
    return model[i][j];
  }

  /**
   * The metod <b>step</b> updates the number of steps. It must be called once the
   * model has been updated after the payer selected a new square.
   */
  public void step() {
    numberOfSteps++;
  }

  /**
   * The metod <b>isFinished</b> returns true iff the game is finished, that is,
   * all the nonmined dots are uncovered.
   *
   * @return true if the game is finished, false otherwise
   */

  public boolean isFinished() {

    for (int i = 0; i < heigthOfGame; i++) {

      for (int j = 0; j < widthOfGame; j++) {

        if (!(model[i][j].isMined()) && model[i][j].isCovered()) {

          return false;

        }

      }
    }

    return true;

  }

  /**
   * Builds a String representation of the model
   *
   * @return String representation of the model
   */
  public String toString() {

    String format = "Width = " + widthOfGame + ", Height = " + heigthOfGame + ", Number of Mines = " + numberOfMines
        + "\n";

    for (int i = 0; i < heigthOfGame; i++) {

      format += "\n";

      for (int j = 0; j < widthOfGame; j++) {

        if (!(model[i][j].isCovered())) {

          format += "* ";

        } else if (model[i][j].isMined()) {

          format += "m ";

        } else {

          format += getNeighbooringMines(i, j) + " ";

        }

      }

    }

    format += "\n";

    return format;
  }

}
