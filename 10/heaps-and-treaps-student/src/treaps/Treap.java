package treaps;

import java.util.ArrayList;
import java.util.List;

public class Treap<E extends Comparable<E>> {
    Node<E> root;
    int size;

    public int size() {
        return size;
    }

    public boolean contains(E e) {
        return find(e) != null;
    }

    private Node<E> find(E e, Node<E> n) {
        if (n == null) {
            return null;
        } else if (e.equals(n.data)) {
            return n;
        } else if (e.compareTo(n.data) < 0) {
            return find(e, n.left);
        } else {
            return find(e, n.right);
        }
    }
    
    private Node<E> find(E e) {
        return find(e, root);
    }

    static <E extends Comparable<E>> List<E> inOrder(Node<E> node) {
        List<E> result = new ArrayList<>();
        if (node != null) {
            result.addAll(inOrder(node.left));
            result.add(node.data);
            result.addAll(inOrder(node.right));
        }
        return result;
    }

    static <E extends Comparable<E>> boolean isBST(Node<E> n) {
        if (n == null) return true;
        if (n.left != null && n.data.compareTo(n.left.data) <= 0) return false;
        if (n.right != null && n.data.compareTo(n.right.data) >= 0) return false;
        return isBST(n.left) && isBST(n.right);
    }

    static <E extends Comparable<E>> boolean isHeap(Node<E> n) {
        if (n == null) return true;
        if (n.left != null && n.priority < n.left.priority) return false;
        if (n.right != null && n.priority < n.right.priority) return false;
        return isHeap(n.left) && isHeap(n.right);
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node<E> add(Node<E> node, E e) {
        if (node == null) {
            node = new Node<>(e);
            size++;
        } else if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
            if (node.left.priority > node.priority) {
                node = rotateRight(node);
            }
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(node.right, e);
            if (node.right.priority > node.priority) {
                node = rotateLeft(node);
            }
        }
        return node;
    }

    private Node<E> rotateRight(Node<E> n) {
        Node<E> leftChild = n.left;
        n.left = leftChild.right;
        leftChild.right = n;
        return leftChild;
    }

    private Node<E> rotateLeft(Node<E> n) {
        Node<E> rightChild = n.right;
        n.right = rightChild.left;
        rightChild.left = n;
        return rightChild;
    }

    public static void main(String[] args) {
        Treap<Integer> t = new Treap<>();
        for (int i = 0; i < 15; i++) {
            t.add(i);
        }
        System.out.println(isBST(t.root) + "/" + isHeap(t.root));
        List<Integer> inorder = inOrder(t.root);
        System.out.println("Inorder traversal: " + inorder);
    }
}
