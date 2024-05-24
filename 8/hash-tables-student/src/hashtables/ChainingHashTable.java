package hashtables;

import java.util.Iterator;
import java.util.LinkedList;

public class ChainingHashTable<E> implements HashTable<E> {
    private Object[] table; // Using Object array to avoid type safety warning
    private int size;
    private int capacity;

    public ChainingHashTable() {
        this(7); // Default capacity
    }

    public ChainingHashTable(int n) {
        capacity = nextPowerOfTwoMinusOne(n);
        table = new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<E>();
        }
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public double loadFactor() {
        return (double) size / capacity;
    }
    @SuppressWarnings("unchecked")

    @Override
    public boolean add(E e) {
        if (loadFactor() > 0.75) {
            resize();
        }
        int index = getIndex(e);
        LinkedList<E> bucket = (LinkedList<E>) table[index];
        if (!bucket.contains(e)) {
            bucket.add(e);
            size++;
            return true;
        }
        return false;
    }
    @SuppressWarnings("unchecked")

    @Override
    public boolean remove(E e) {
        int index = getIndex(e);
        LinkedList<E> bucket = (LinkedList<E>) table[index];
        boolean removed = bucket.remove(e);
        if (removed) {
            size--;
        }
        return removed;
    }
    @SuppressWarnings("unchecked")

    @Override
    public boolean contains(E e) {
        int index = getIndex(e);
        LinkedList<E> bucket = (LinkedList<E>) table[index];
        return bucket.contains(e);
    }
    @SuppressWarnings("unchecked")

    @Override
    public E get(E e) {
        int index = getIndex(e);
        LinkedList<E> bucket = (LinkedList<E>) table[index];
        for (E element : bucket) {
            if (element.equals(e)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new ChainingHashTableIterator();
    }

    private int getIndex(E e) {
        return Math.abs(e.hashCode()) % capacity;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = nextPowerOfTwoMinusOne(capacity * 2 + 1);
        Object[] newTable = new Object[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new LinkedList<E>();
        }
        for (Object bucket : table) {
            LinkedList<E> bucketList = (LinkedList<E>) bucket;
            for (E e : bucketList) {
                int index = Math.abs(e.hashCode()) % newCapacity;
                ((LinkedList<E>) newTable[index]).add(e);
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    private int nextPowerOfTwoMinusOne(int n) {
        if (n <= 2) {
            return 3; // The smallest capacity greater than 1, following 2^n - 1
        } else if (n <= 7) {
            return 7; // Already at 2^3 - 1
        }
        
        int power = 2;
        while (power - 1 < n) {
            power *= 2;
        }

        return power - 1; // One less than the power of two
    }
    @SuppressWarnings("unchecked")
    
    private class ChainingHashTableIterator implements Iterator<E> {
        private int currentBucket;
        private Iterator<E> bucketIterator;

        public ChainingHashTableIterator() {
            currentBucket = -1;
            moveToNextBucket();
        }

        private void moveToNextBucket() {
            currentBucket++;
            while (currentBucket < capacity && ((LinkedList<E>) table[currentBucket]).isEmpty()) {
                currentBucket++;
            }
            if (currentBucket < capacity) {
                bucketIterator = ((LinkedList<E>) table[currentBucket]).iterator();
            }
        }

        @Override
        public boolean hasNext() {
            if (bucketIterator == null) {
                return false;
            }
            if (bucketIterator.hasNext()) {
                return true;
            } else {
                moveToNextBucket();
                return currentBucket < capacity;
            }
        }

        @Override
        public E next() {
            return bucketIterator.next();
        }
    }
}
