package com.company;

import java.util.Objects;

// can be chained to other nodes to create a linked list of HashNodes
public class HashNode<K, V> {
    K key;
    V value;

    final int hashCode;

    HashNode<K, V> next;

    public HashNode(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}
