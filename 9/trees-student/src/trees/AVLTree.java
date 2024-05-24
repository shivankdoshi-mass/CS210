/*
 * Copyright 2023 Marc Liberatore.
 */
package trees;


public class AVLTree<E extends Comparable<E>> {
    Node<E> root;
    int size;

    public int size() {
        return size;
    }

    public void add(E e) {
        if (root == null) {
            root = new Node<>(e);
            size = 1;
            return;
        }
        add(e, root);
    }

    private void add(E e, Node<E> node) {
        if (e.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new Node<>(e, node);
                size++;
                insertionCheck(node.left);
            } else {
                add(e, node.left);
            }
        } else if (e.compareTo(node.data) > 0) {
            if (node.right == null) {
                node.right = new Node<>(e, node);
                size++;
                insertionCheck(node.right);
            } else {
                add(e, node.right);
            }
        }
    }

    private void insertionCheck(Node<E> node) {
        Node<E> n = node;
        while (n != null) {
            if (!isAVL(n)) {
                performRotation(n);
                break;
            }
            n = n.parent;
        }
    }

    private void performRotation(Node<E> node) {
        int balance = getBalance(node);
        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                rotateLR(node);
            } else {
                rotateRR(node);
            }
        } else if (balance < -1) {
            if (getBalance(node.right) > 0) {
                rotateRL(node);
            } else {
                rotateLL(node);
            }
        }
    }

    private int height(Node<E> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private boolean isAVL(Node<E> node) {
        return Math.abs(height(node.left) - height(node.right)) <= 1;
    }

    private int getBalance(Node<E> node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private void rotateRR(Node<E> n) {
        Node<E> A = n.left;
        Node<E> T2 = A.right;
        A.right = n;
        n.left = T2;
        if (T2 != null) T2.parent = n;
        A.parent = n.parent;
        if (n.parent == null) root = A;
        else if (n == n.parent.left) n.parent.left = A;
        else n.parent.right = A;
        n.parent = A;
    }

    private void rotateLL(Node<E> n) {
        Node<E> B = n.right;
        Node<E> T2 = B.left;
        B.left = n;
        n.right = T2;
        if (T2 != null) T2.parent = n;
        B.parent = n.parent;
        if (n.parent == null) root = B;
        else if (n == n.parent.right) n.parent.right = B;
        else n.parent.left = B;
        n.parent = B;
    }

    private void rotateLR(Node<E> n) {
        rotateLL(n.left);
        rotateRR(n);
    }

    private void rotateRL(Node<E> n) {
        rotateRR(n.right);
        rotateLL(n);
    }

    public boolean contains(E e) {
        Node<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.data);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    private Node<E> find(E e, Node<E> n) {
        while (n != null) {
            int cmp = e.compareTo(n.data);
            if (cmp < 0) {
                n = n.left;
            } else if (cmp > 0) {
                n = n.right;
            } else {
                return n;
            }
        }
        return null;
    }

    public E findData(E e) {
        Node<E> node = find(e, root);
        if (node == null) return null;
        return node.data;
    }
}
