package recursion;

public class RecursionExercises {

    /**
     * sumOfDigits (both implementations) should return the sum of the
     * digits of its input, x.
     * 
     * If `x` is 1234, the function should return 1 + 2 + 3 + 4, that is, 10.
     * 
     * If `x` is 345, the function should return 3 + 4 + 5 = 12.
     * 
     * If `x` is 3, the function should return 3.
     * 
     * If `x` is negative, *ignore the minus sign*. For example, -12 and 12
     * should both return 3.
     */

    /**
     * Return the sum of the digits of `x`; your algorithm must be implemented
     * iteratively.
     * 
     * @param x
     * @return
     */
    static int sumOfDigitsIterative(int x) {
        x = Math.abs(x); // Handle negative numbers by converting x to its absolute value
        int sum = 0;
        while (x > 0) {
            sum += x % 10; // Add the last digit of x to sum
            x /= 10; // Remove the last digit from x
        }
        return sum;
    }

    /**
     * Return the sum of the digits of `x`; your algorithm must be implemented
     * recursively. No explicit loops (`for`, `while`) or use of Streams are
     * allowed!
     * 
     * @param x
     * @return
     */
    static int sumOfDigitsRecursive(int x) {
        x = Math.abs(x); // Handle negative numbers by converting x to its absolute value
        if (x == 0) {
            return 0; // Base case: if x is 0, return 0
        } else {
            return x % 10 + sumOfDigitsRecursive(x / 10); // Recursive call with the remaining number
        }
    }

    /**
     * Returns the length of the linked list, starting from node.
     * Empty lists have length 0.
     * 
     * (Must traverse the list using recursion.)
     * 
     * @param node - the head of a list
     * @return the size (length) of the linked list, starting from node
     */
    static <E> int size(Node<E> node) {
        if (node == null) {
            return 0; // Base case: if the node is null, the list is empty, so return 0
        } else {
            return 1 + size(node.next); // Recursive step: move to the next node and add 1 to the count
        }
    }

    /**
     * Returns the i-th Node in the linked list which starts at node,
     * or null if the list is empty.
     * 
     * (Must be implemente recursively.)
     * 
     * @param node - the head of a list
     * @param i
     * @return the i-th node of the list
     */
    static <E> Node<E> getNode(Node<E> node, int i) {
        if (node == null) {
            return null; // Base case: if the node is null, return null (list is too short)
        } else if (i == 0) {
            return node; // Base case: if i is 0, the current node is the i-th node
        } else {
            return getNode(node.next, i - 1); // Recursive step: move to the next node and decrement i
        }
    }
}
