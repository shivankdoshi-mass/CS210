package hashmaps;

import hashtables.ChainingHashTable;
import java.util.HashSet;
import java.util.Set;

public class SimpleHashMap<K, V> implements SimpleMap<K, V> {
    private ChainingHashTable<SimpleMapEntry<K, V>> table;

    public SimpleHashMap() {
        table = new ChainingHashTable<>();
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public void put(K key, V value) {
        SimpleMapEntry<K, V> newEntry = new SimpleMapEntry<>(key, value);
        // Remove existing entry if present
        table.remove(newEntry);
        // Add new entry
        table.add(newEntry);
    }

    @Override
    public V get(K key) {
        SimpleMapEntry<K, V> entry = new SimpleMapEntry<>(key, null);
        SimpleMapEntry<K, V> foundEntry = table.get(entry);
        return foundEntry != null ? foundEntry.v : null;
    }

    @Override
    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }

    @Override
    public V remove(K key) {
        SimpleMapEntry<K, V> entryToRemove = new SimpleMapEntry<>(key, null);
        SimpleMapEntry<K, V> currentEntry = table.get(entryToRemove);

        if (currentEntry == null) {
            return null;
        }

        boolean isRemoved = table.remove(entryToRemove);
        return isRemoved ? currentEntry.v : null;
    }

    @Override
    public Set<K> keys() {
        Set<K> keySet = new HashSet<>();
        for (SimpleMapEntry<K, V> entry : table) {
            keySet.add(entry.k);
        }
        return keySet;
    }
}

