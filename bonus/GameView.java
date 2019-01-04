import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It
 * extends <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual
 * game) and two instances of JButton. The action listener for the buttons is
 * the controller.
 */

public class GameView extends JFrame {

  private DotButton[][] board;
  private GameModel gameModel;
  private JLabel nbreOfStepsLabel;
  private JLabel differenceLabel;

  /**
   * Constructor used for initializing the Frame
   *
   * @param gameModel      the model of the game (already initialized)
   * @param gameController the controller
   */

  public GameView(GameModel gameModel, GameController gameController) {

    // sets the title
    super("MineSweeper");

    // initializing variables to be used
    this.gameModel = gameModel;
    board = new DotButton[gameModel.getHeigth()][gameModel.getWidth()];
    nbreOfStepsLabel = new JLabel("Number of steps: " + gameModel.getNumberOfSteps() + ", ");
    int difference = gameModel.getNumberOfMines() - gameModel.getNumberOfFlags();
    differenceLabel = new JLabel("Difference between Mines and Flag: " + difference);

    // Frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBackground(Color.WHITE);

    // create JPanel for grid (DotButtons)
    JPanel grid = new JPanel();
    grid.setLayout(new GridLayout(gameModel.getHeigth(), gameModel.getWidth()));
    grid.setBackground(Color.WHITE);

    // Creat JPanel for bottom panel (number of steps, reset, quit)
    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottom.setBackground(Color.WHITE);
    bottom.add(nbreOfStepsLabel);
    bottom.add(differenceLabel);

    // Populate the board with DotButton instances and addActionListener for each
    // button
    for (int i = 0; i < gameModel.getHeigth(); i++) {
      for (int j = 0; j < gameModel.getWidth(); j++) {
        board[i][j] = new DotButton(j, i, 11);
        board[i][j].addMouseListener(gameController);
        grid.add(board[i][j]);

      }
    }

    add(grid, BorderLayout.CENTER);

    // Create JButtons for reset and quit and addActionListener for each button
    JButton reset = new JButton("Reset");
    reset.setBackground(Color.WHITE);
    reset.addMouseListener(gameController);
    bottom.add(reset);

    JButton quit = new JButton("Quit");
    quit.setBackground(Color.WHITE);
    quit.addMouseListener(gameController);
    bottom.add(quit);
    add(bottom, BorderLayout.SOUTH);

    setResizable(false);
    setLocationRelativeTo(null);
    pack();
    setVisible(true);

  }

  /**
   * update the status of the board's DotButton instances based on the current
   * game model, then redraws the view
   */

  public void update() {

    for (int i = 0; i < gameModel.getHeigth(); i++) {

      for (int j = 0; j < gameModel.getWidth(); j++) {

        board[i][j].setIconNumber(getIcon(i, j));
        nbreOfStepsLabel.setText("Number of steps: " + gameModel.getNumberOfSteps() + ", ");
        int difference = gameModel.getNumberOfMines() - gameModel.getNumberOfFlags();
        differenceLabel.setText("Difference between Mines and Flag: " + difference);

      }
    }

  }

  /**
   * returns the icon value that must be used for a given dot in the game
   *
   * @param i the x coordinate of the dot
   * @param j the y coordinate of the dot
   * @return the icon to use for the dot at location (i,j)
   */
  private int getIcon(int i, int j) {

    int number;

    if (gameModel.isCovered(i, j) && !(gameModel.isFlagged(i, j))) {

      number = 11;

    } else if (gameModel.isMined(i, j) && gameModel.hasBeenClicked(i, j)) {

      number = 10;

    } else if (gameModel.isMined(i, j) && !(gameModel.isFlagged(i, j))) {

      number = 9;

    } else if (gameModel.isCovered(i, j) && gameModel.isFlagged(i, j)) {

      number = 12;
    }

    else {

      number = gameModel.getNeighbooringMines(i, j);

    }

    return number;

  }

}
