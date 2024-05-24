package lists;

public class ArrayList<E> implements List<E> {
    // Note: do not declare any additional instance variables
    E[] array;
    int size;

    public ArrayList() {
        size = 0;
        array = (E[]) new Object[10];
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = prime * result + (array[i] == null ? 0 : array[i].hashCode());
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
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                if (other.get(i) != null)
                    return false;
            } else if (!array[i].equals(other.get(i))) {
                return false;
            }
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
        return array[index];
    }

    @Override
    public void add(E e) {
        ensureCapacity();
        array[size++] = e;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            array = java.util.Arrays.copyOf(array, array.length * 2);
        }
    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = e;
        size++;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldValue = array[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null; // clear to let GC do its work
        return oldValue;
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldValue = array[index];
        array[index] = e;
        return oldValue;
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
}
