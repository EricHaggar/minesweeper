public class GenericArrayStack<E> implements Stack<E> {

  private E[] elems;
  private int top;

  // Constructor
  @SuppressWarnings("unchecked")
  public GenericArrayStack(int capacity) {

    elems = (E[]) new Object[capacity];
    top = 0;

  }

  // Returns true if this ArrayStack is empty
  public boolean isEmpty() {
    return (top == 0);
  }

  public void push(E elem) {

    if (top == elems.length) {

      E[] biggerArray;
      biggerArray = (E[]) new Object[elems.length * 2];
      for (int i = 0; i < top; i++) {

        biggerArray[i] = elems[i];

      }

      elems = biggerArray;
    }

    elems[top++] = elem;

  }

  public E pop() {

    E saved = elems[--top];
    elems[top] = null;
    return saved;

  }

  public E peek() {
    return elems[top - 1];
  }
}
