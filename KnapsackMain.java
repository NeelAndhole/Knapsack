import java.util.Arrays;
import java.util.Scanner;

/**
 * This program simulates the 1-0 knapsack problem
 */
public class KnapsackMain {
  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);
    int numOfInstances = Integer.parseInt(kb.nextLine());
    for (int m = 0; m < numOfInstances; m++) {
      Integer[] numItemsAndCapacity = Arrays.stream(kb.nextLine().split(" "))
          .map(e -> Integer.parseInt(e)).toArray(size -> new Integer[size]);
      Item[] items = new Item[numItemsAndCapacity[0] + 1];
      for (int i = 1; i < numItemsAndCapacity[0] + 1; i++) {
        String[] line = kb.nextLine().split(" ");
        items[i] = new Item(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
      }
      int[][] dpReturns = new int[numItemsAndCapacity[0] + 1][numItemsAndCapacity[1] + 1];
      // fills in the base cases
      /*
       * for (int i = 0; i < dpReturns.length; i++) { dpReturns[i][0] = 0; } for (int i = 0; i <
       * dpReturns[0].length; i++) { dpReturns[0][i] = 0; }
       */
      for (int w = 1; w < numItemsAndCapacity[1] + 1; w++) {
        for (int i = 1; i < numItemsAndCapacity[0] + 1; i++) {
          int firstPossible = dpReturns[i - 1][w];
          int secondPossible = 0;
          if (items[i].weight <= w) {
            secondPossible = dpReturns[i - 1][w - items[i].weight] + items[i].value;
          }
          dpReturns[i][w] = Math.max(firstPossible, secondPossible);
        }
      }
      System.out.println(dpReturns[numItemsAndCapacity[0]][numItemsAndCapacity[1]]);
    }
    kb.close();
  }


}
