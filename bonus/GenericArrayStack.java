//Student name: Eric Haggar
//Student number: 7674509
//Course code: ITI1521
//Lab section: A-2

public class GenericArrayStack<E> implements Stack<E> {

  // ADD YOUR INSTANCE VARIABLES HERE
  private E[] elems;
  private int top;

  // Constructor
  @SuppressWarnings("unchecked")
  public GenericArrayStack( int capacity ) {

    // ADD YOU CODE HERE

    elems = (E[]) new Object [capacity];
    top = 0;

  }

  // Returns true if this ArrayStack is empty
  public boolean isEmpty() {

    // ADD YOU CODE HERE

    return (top == 0) ;

  }

  public void push( E elem ) {

    // ADD YOU CODE HERE

    if (top == elems.length) {

      E[] biggerArray;
      biggerArray = (E[]) new Object [elems.length*2];
      for (int i = 0; i < top; i++) {

        biggerArray[i] = elems[i];

      }

      elems = biggerArray;
    }

    elems[top++] = elem;

  }
  public E pop() {

    // ADD YOU CODE HERE
    E saved = elems[--top];
    elems[top] = null;
    return saved;

  }

  public E peek() {

    // ADD YOU CODE HERE

    return elems[top - 1];


  }
}
