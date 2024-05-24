package trees;

import java.util.ArrayList;
import java.util.List;

public class TreeUtilities {

    static <E> List<E> inOrder(Node<E> node) {
        List<E> elements = new ArrayList<>();
        if (node != null) {
            elements.addAll(inOrder(node.left));
            elements.add(node.data);
            elements.addAll(inOrder(node.right));
        }
        return elements;
    }

    static <E> int height(Node<E> n) {
        if (n == null) {
            return -1;
        }
        return 1 + Math.max(height(n.left), height(n.right));
    }

    static <E extends Comparable<E>> BinarySearchTree<E> intoBalanced(BinarySearchTree<E> bst) {
        List<E> sortedValues = inOrder(bst.root);
        BinarySearchTree<E> newTree = new BinarySearchTree<>();
        intoBalancedHelper(sortedValues, 0, sortedValues.size() - 1, newTree);
        return newTree;
    }

    static <E extends Comparable<E>> void intoBalancedHelper(List<E> values, int start, int end, BinarySearchTree<E> tree) {
        if (start <= end) {
            int mid = start + (end - start) / 2;
            tree.add(values.get(mid));
            intoBalancedHelper(values, start, mid - 1, tree);
            intoBalancedHelper(values, mid + 1, end, tree);
        }
    }

    static <E extends Comparable<E>> boolean isBST(Node<E> n) {
        return isBST(n, null, null);
    }

    static <E extends Comparable<E>> boolean isBST(Node<E> n, E min, E max) {
        if (n == null) {
            return true;
        }
        if ((min != null && n.data.compareTo(min) <= 0) || (max != null && n.data.compareTo(max) >= 0)) {
            return false;
        }
        return isBST(n.left, min, n.data) && isBST(n.right, n.data, max);
    }

    static <E extends Comparable<E>> boolean isAVLTree(Node<E> n) {
        if (n == null) {
            return true;
        }
        int leftHeight = height(n.left);
        int rightHeight = height(n.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        return isAVLTree(n.left) && isAVLTree(n.right);
    }

    static <E> boolean equalSubtrees(Node<E> n, Node<E> m) {
        if (n == null && m == null) {
            return true;
        } else if (n == null || m == null) {
            return false;
        } else {
            return n.data.equals(m.data) && equalSubtrees(n.left, m.left) && equalSubtrees(n.right, m.right);
        }
    }
}
