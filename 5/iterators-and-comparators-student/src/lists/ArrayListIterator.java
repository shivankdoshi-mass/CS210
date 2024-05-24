package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayListIterator<E> implements Iterator<E> {
    private final E[] array;
    private final int size;
    private int index = 0;

    public ArrayListIterator(E[] array, int size) {
        this.array = array;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}
