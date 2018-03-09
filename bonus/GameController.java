//Student name: Eric Haggar
//Student number: 7674509
//Course code: ITI1521
//Lab section: A-2

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.*;


/**
* The class <b>GameController</b> is the controller of the game. It is a listener
* of the view, and has a method <b>play</b> which computes the next
* step of the game, and  updates model and view.
*
* @author Guy-Vincent Jourdan, University of Ottawa
*/


public class GameController implements MouseListener {

  // ADD YOUR INSTANCE VARIABLES HERE

  private int width;
  private int height;
  private int numberOfMines;
  private GameModel gameModel;
  private GameView gameView;


  /**
  * Constructor used for initializing the controller. It creates the game's view
  * and the game's model instances
  *
  * @param width
  *            the width of the board on which the game will be played
  * @param height
  *            the height of the board on which the game will be played
  * @param numberOfMines
  *            the number of mines hidden in the board
  */
  public GameController(int width, int height, int numberOfMines) {

    // ADD YOU CODE HERE
    this.width = width;
    this.height = height;
    this.numberOfMines = numberOfMines;

    gameModel = new GameModel(width, height, numberOfMines);

    gameView = new GameView(gameModel, this);

  }

  /**
  * Callback used when the user right clicks (flag or unflag)
  *
  * @param e
  *            the MouseEvent
  */

  public void mouseClicked(MouseEvent e) {


    if (SwingUtilities.isLeftMouseButton(e)) {

      JButton source = (JButton) e.getSource();

      if (source.getActionCommand().equals("Reset")) {


        reset();

      } else if (source.getActionCommand().equals("Quit")) {

        System.exit(0);

      } else {

        DotButton tileSource = (DotButton) e.getSource();

        int row = tileSource.getRow();
        int column = tileSource.getColumn();

        play(column,row);

      }
    }

    if (SwingUtilities.isRightMouseButton(e)) {

      DotButton source = (DotButton) e.getSource();

      int row = source.getRow();
      int column = source.getColumn();

      if (gameModel.isFlagged(row,column) && gameModel.isCovered(row,column)) {

        gameModel.unflag(row,column);

      } else if (!(gameModel.isFlagged(row,column)) && gameModel.isCovered(row,column)) {


        gameModel.flag(row,column);

      }








    }

    gameView.update();

  }


  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }



  /**
  * resets the game
  */
  private void reset(){

    // ADD YOU CODE HERE

    gameModel.reset();
    gameView.update();


  }

  /**
  * <b>play</b> is the method called when the user clicks on a square.
  * If that square is not already clicked, then it applies the logic
  * of the game to uncover that square, and possibly end the game if
  * that square was mined, or possibly uncover some other squares.
  * It then checks if the game
  * is finished, and if so, congratulates the player, showing the number of
  * moves, and gives to options: start a new game, or exit
  * @param width
  *            the selected column
  * @param heigth
  *            the selected line
  */
  private void play(int width, int heigth){

    // ADD YOU CODE HERE
    Object[] options = { "Quit", "Play Again" };

    if (!(gameModel.hasBeenClicked(heigth, width)) && !(gameModel.isFlagged(heigth,width))) {

      gameModel.click(heigth,width);
      gameModel.uncover(heigth, width);
      gameModel.step();

      if (gameModel.isMined(heigth, width)) {

        gameModel.uncoverAll();
        gameView.update();

        //JOptionPane if the game was lost
        int n = JOptionPane.showOptionDialog(gameView, "Aouch, you lost in " + gameModel.getNumberOfSteps() + " steps! \n" + "Would you like to play again?","Boom!", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, null);
        if (n == 0) {

          System.exit(0);

        } else {

          reset();
        }


      } else if (gameModel.isBlank(heigth, width)) {

        clearZone(gameModel.get(heigth,width));
        gameView.update();

        if (gameModel.isFinished()) {

          gameModel.uncoverAll();
          gameView.update();

          int n = JOptionPane.showOptionDialog(gameView, "Congratulations, you won in " + gameModel.getNumberOfSteps() + " steps! \n" + "Would you like to play again?","Won", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, null);
          if (n == 0) {

            System.exit(0);

          } else {

            reset();
          }



        }


      } else {

        gameView.update();

        if (gameModel.isFinished()) {

          gameModel.uncoverAll();
          gameView.update();

          int n = JOptionPane.showOptionDialog(gameView, "Congratulations, you won in " + gameModel.getNumberOfSteps() + " steps! \n" + "Would you like to play again?","Won", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, null);

          if (n == 0) {

            System.exit(0);

          } else {

            reset();
          }

        }


      }


    }


  }

  /**
  * <b>clearZone</b> is the method that computes which new dots should be ``uncovered''
  * when a new square with no mine in its neighborood has been selected
  * @param initialDot
  *      the DotInfo object corresponding to the selected DotButton that
  * had zero neighbouring mines
  */
  private void clearZone(DotInfo initialDot) {

    // ADD YOU CODE HERE

    Stack<DotInfo> stack = new GenericArrayStack<DotInfo>(gameModel.getHeigth()*gameModel.getWidth());

    stack.push(initialDot);

    while (!(stack.isEmpty())) {

      DotInfo index = stack.pop();

      if (index.getX()-1 >=0) {

        if (gameModel.get(index.getX()-1, index.getY()).isCovered() && !(gameModel.get(index.getX()-1, index.getY()).isFlagged())) {

          gameModel.get(index.getX()-1, index.getY()).uncover();

          if (gameModel.getNeighbooringMines(index.getX()-1, index.getY()) == 0) {

            stack.push(gameModel.get(index.getX()-1, index.getY()));
          }

        }
      }

      if (index.getX()+1 <gameModel.getHeigth()) {

        if (gameModel.get(index.getX()+1, index.getY()).isCovered() && !(gameModel.get(index.getX()+1, index.getY()).isFlagged())) {

          gameModel.get(index.getX()+1, index.getY()).uncover();

          if (gameModel.getNeighbooringMines(index.getX()+1, index.getY()) == 0) {

            stack.push(gameModel.get(index.getX()+1, index.getY()));
          }

        }
      }

      if (index.getY()-1 >= 0) {

        if (gameModel.get(index.getX(), index.getY()-1).isCovered() && !(gameModel.get(index.getX(), index.getY()-1).isFlagged())) {

          gameModel.get(index.getX(), index.getY()-1).uncover();

          if (gameModel.getNeighbooringMines(index.getX(), index.getY()-1) == 0) {

            stack.push(gameModel.get(index.getX(), index.getY()-1));
          }

        }
      }

      if (index.getY()+1 <gameModel.getWidth()) {

        if (gameModel.get(index.getX(), index.getY()+1).isCovered() && !(gameModel.get(index.getX(), index.getY()+1).isFlagged())) {

          gameModel.get(index.getX(), index.getY()+1).uncover();

          if (gameModel.getNeighbooringMines(index.getX(), index.getY()+1) == 0) {

            stack.push(gameModel.get(index.getX(), index.getY()+1));
          }

        }
      }

      if (index.getX()-1 >=0 && index.getY()-1 >=0) {

        if (gameModel.get(index.getX()-1, index.getY()-1).isCovered() && !(gameModel.get(index.getX()-1, index.getY()-1).isFlagged())) {

          gameModel.get(index.getX()-1, index.getY()-1).uncover();

          if (gameModel.getNeighbooringMines(index.getX()-1, index.getY()-1) == 0) {

            stack.push(gameModel.get(index.getX()-1, index.getY()-1));
          }

        }
      }

      if (index.getX()+1 < gameModel.getHeigth() && index.getY()-1 >=0) {

        if (gameModel.get(index.getX()+1, index.getY()-1).isCovered() && !(gameModel.get(index.getX()+1, index.getY()-1).isFlagged())) {

          gameModel.get(index.getX()+1, index.getY()-1).uncover();

          if (gameModel.getNeighbooringMines(index.getX()+1, index.getY()-1) == 0) {

            stack.push(gameModel.get(index.getX()+1, index.getY()-1));
          }

        }
      }

      if (index.getX()-1 >=0 && index.getY()+1 < gameModel.getWidth()) {

        if (gameModel.get(index.getX()-1, index.getY()+1).isCovered() && !(gameModel.get(index.getX()-1, index.getY()+1).isFlagged())) {

          gameModel.get(index.getX()-1, index.getY()+1).uncover();

          if (gameModel.getNeighbooringMines(index.getX()-1, index.getY()+1) == 0) {

            stack.push(gameModel.get(index.getX()-1, index.getY()+1));
          }

        }
      }

      if (index.getX()+1 < gameModel.getHeigth() && index.getY()+1 < gameModel.getWidth()) {

        if (gameModel.get(index.getX()+1, index.getY()+1).isCovered() && !(gameModel.get(index.getX()+1, index.getY()+1).isFlagged())) {

          gameModel.get(index.getX()+1, index.getY()+1).uncover();

          if (gameModel.getNeighbooringMines(index.getX()+1, index.getY()+1) == 0) {

            stack.push(gameModel.get(index.getX()+1, index.getY()+1));
          }

        }
      }
    }
  }

  /**
  * Getter for numberOfMines
  *
  * @return numberOfMines
  */
  public int getNumberOfMines(){

    return numberOfMines;

  }
}
