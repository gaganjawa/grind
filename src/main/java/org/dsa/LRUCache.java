package org.dsa;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            add(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {

        if (cache.containsKey(key)) {
            Node oldNode = cache.get(key);
            remove(oldNode);
        }

        Node node = new Node(key, value);
        cache.put(key, node);
        add(node);

        if (cache.size() > capacity) {
            Node del = head.next;
            remove(del);
            cache.remove(del.key);
        }

    }

    private void add(Node node) {
        Node prevTail = tail.prev;
        prevTail.next = node;
        node.prev = prevTail;
        node.next = tail;
        tail.prev = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
