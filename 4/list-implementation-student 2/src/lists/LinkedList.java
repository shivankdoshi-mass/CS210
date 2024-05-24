package lists;

public class LinkedList<E> implements List<E> {
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    Node<E> head;
    Node<E> tail;
    int size;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Node<E> n = head;
        while (n != null) {
            result = prime * result + (n.data == null ? 0 : n.data.hashCode());
            n = n.next;
        }
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof List))
            return false;
        List<?> other = (List<?>) obj;
        if (size != other.size())
            return false;
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            E elem = current.data;
            Object otherElem = other.get(i);
            if (elem == null ? otherElem != null : !elem.equals(otherElem))
                return false;
            current = current.next;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> newNode = new Node<>(e);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) {
                tail = newNode;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E removedData;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
            if (size == 1) {
                tail = null;
            }
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedData = current.next.data;
            current.next = current.next.next;
            if (current.next == null) {
                tail = current;
            }
        }
        size--;
        return removedData;
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldData = current.data;
        current.data = e;
        return oldData;
    }

    @Override
    public int indexOf(E e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (e == null && current.data == null) {
                return i;
            } else if (e != null && e.equals(current.data)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }
}
