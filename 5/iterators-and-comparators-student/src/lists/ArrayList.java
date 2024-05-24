package lists;

import java.util.Iterator;
import java.util.Objects;

public class ArrayList<E> implements List<E> {
    private E[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        size = 0;
        array = (E[]) new Object[10];
    }

    private void ensureCapacity() {
        if (size == array.length) {
            @SuppressWarnings("unchecked")
            E[] newArray = (E[]) new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public void add(E e) {
        ensureCapacity();
        array[size++] = e;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = e;
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedElement;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = array[index];
        array[index] = e;
        return oldElement;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (e == null && array[i] == null) {
                return i;
            } else if (e != null && e.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        ArrayList<?> other = (ArrayList<?>) obj;
        if (size != other.size)
            return false;

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(array[i], other.array[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator<>(array, size);
    }
}
