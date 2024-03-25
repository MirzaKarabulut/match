// stack.java
// demonstrates stacks
// to run this program: C>java StackApp
////////////////////////////////////////////////////////////////
class StackX {
   private int maxSize; // size of stack array
   private long[] stackArray;
   private int top; // top of stack
   // --------------------------------------------------------------

   public StackX(int s) // constructor
   {
      maxSize = s; // set array size
      stackArray = new long[maxSize]; // create array
      top = -1; // no items yet
   }

   // --------------------------------------------------------------
   public void push(long j) // put item on top of stack
   {
      stackArray[++top] = j; // increment top, insert item
   }

   // --------------------------------------------------------------
   public long pop() // take item from top of stack
   {
      return stackArray[top--]; // access item, decrement top
   }

   // --------------------------------------------------------------
   public long peek() // peek at top of stack
   {
      return stackArray[top];
   }

   // --------------------------------------------------------------
   public boolean isEmpty() // true if stack is empty
   {
      return (top == -1);
   }

   // --------------------------------------------------------------
   public boolean isFull() // true if stack is full
   {
      return (top == maxSize - 1);
   }
   // --------------------------------------------------------------
} // end class StackX
  ////////////////////////////////////////////////////////////////

class Main {
   public static void main(String[] args) {
      StackX theStack = new StackX(10); // make new stack
      theStack.push(20); // push items onto stack
      theStack.push(40);
      theStack.push(60);
      theStack.push(80);

      while (!theStack.isEmpty()) // until it's empty,
      { // delete item from stack
         long value = theStack.pop();
         System.out.print(value); // display it
         System.out.print(" ");
      } // end while
      System.out.println("");
      String[] array = { "if", "do", "if", "for", "if", "do", "if", "if", "while", "else", "for", "else", "if", "while",
            "while", "if", "do", "while", "else", "if", "for", "while" };
      match(array);
   } // end main()

   public static void match(String[] arr) {
      StackX ifStack = new StackX(arr.length); // Stack to store indices of "if" statements
      StackX doStack = new StackX(arr.length); // Stack to store indices of "do" statements
      int[] elseCount = new int[arr.length]; // Array to count occurrences of "else"
      int[] whileCount = new int[arr.length]; // Array to count occurrences of "while"
      StackX nestedPairs = new StackX(arr.length); // Stack to track nested "if-else" and "do-while" pairs
      int lastIfIndex = -1;
      int lastDoIndex = -1;

      for (int i = 0; i < arr.length; i++) {
         String current = arr[i];
         if (current.equals("if")) {
            ifStack.push(i);
            lastIfIndex = i;
            if (!doStack.isEmpty()) {
               nestedPairs.push(i); // Push the index of "if" after a "do"
            }
         } else if (current.equals("else")) {
            if (ifStack.isEmpty()) {
               System.out.println("Error: else at index " + i + " cannot be matched with any if.");
            } else {
               int ifIndex = (int) ifStack.pop();
               if (nestedPairs.isEmpty() || (int) nestedPairs.peek() != ifIndex) {
                  System.out.println("Error: else at index " + i + " cannot be matched with any if.");
               } else {
                  nestedPairs.pop(); // Pop the index of "if"
                  elseCount[i]++; // Increment else count at current index
                  elseCount[ifIndex]--; // Decrement else count at matched if index
                  System.out.println("if at index " + ifIndex + " matches with else at index " + i);
               }
            }
         } else if (current.equals("do")) {
            doStack.push(i);
            lastDoIndex = i;
            nestedPairs.push(i); // Push the index of "do" to track nested pairs
         } else if (current.equals("while")) {
            if (doStack.isEmpty()) {
               System.out.println("Error: while at index " + i + " cannot be matched with any do.");
            } else {
               int doIndex = (int) doStack.pop();
               int doWhilePairIndex = (int) nestedPairs.pop(); // Get the index of "do" for the current "while"
               if (doIndex != doWhilePairIndex) {
                  System.out.println("Error: do at index " + doIndex + " cannot be matched with any while.");
               }
               nestedPairs.push(doWhilePairIndex); // Restore the index of "do" to track nested pairs
               nestedPairs.push(i); // Push the index of "while" to track nested pairs
               whileCount[i]++; // Increment while count at current index
               whileCount[doIndex]--; // Decrement while count at matched do index
               System.out.println("do at index " + doIndex + " matches with while at index " + i);
            }
         }
      }

      // Check for unmatched "else" statements
      for (int i = 0; i < elseCount.length; i++) {
         if (elseCount[i] > 0) {
            System.out.println("Error: else at index " + i + " cannot be matched with any if.");
         }
      }

      // Check for unmatched "do" statements
      while (!doStack.isEmpty()) {
         int unmatchedDoIndex = (int) doStack.pop();
         System.out.println("Error: do at index " + unmatchedDoIndex + " cannot be matched with any while.");
      }

      // Check for unmatched "if" statements
      if (!ifStack.isEmpty()) {
         int unmatchedIfIndex = (int) ifStack.pop();
         System.out.println("Error: if at index " + unmatchedIfIndex + " does not have a matching else.");
      }

      // Check for unmatched "while" statements
      if (lastIfIndex > lastDoIndex) {
         System.out.println("Error: while at index " + lastIfIndex + " does not have a matching do.");
      }
   }

}
// end class StackApp
////////////////////////////////////////////////////////////////
