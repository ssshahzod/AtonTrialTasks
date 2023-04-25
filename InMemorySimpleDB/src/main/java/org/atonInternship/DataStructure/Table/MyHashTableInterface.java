package org.atonInternship.DataStructure.Table;

public interface MyHashTableInterface<K, V> {

    int insert(K key, V bucket);
    int hash(K key);
    void delete(K key);
    V find(K key);

}
