package com.uts.strukturdata;

import java.util.Comparator;
// Struktur data utama dinamis berbasis Singly Linked List
public class SinglyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public boolean removeIf(java.util.function.Predicate<T> predicate) {
        Node<T> current = head;
        Node<T> prev = null;
        boolean removed = false;

        while (current != null) {
            if (predicate.test(current.getData())) {
                if (prev == null) {
                    head = current.getNext();
                    if (head == null) tail = null;
                } else {
                    prev.setNext(current.getNext());
                    if (current == tail) tail = prev;
                }
                size--;
                removed = true;
                break; // Hapus elemen pertama yang cocok
            } else {
                prev = current;
            }
            current = current.getNext();
        }
        return removed;
    }

    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }
    public Node<T> getHead() { return head; }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Dynamic Array conversion for non-destructive operations & sorting
    @SuppressWarnings("unchecked")
    public T[] toArray(T[] template) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(template.getClass().getComponentType(), size);
        Node<T> curr = head;
        int idx = 0;
        while (curr != null) {
            array[idx++] = curr.getData();
            curr = curr.getNext();
        }
        return array;
    }

    public void sort(Comparator<T> comparator) {
        if (size <= 1) return;
        // Merge Sort on Linked List
        head = mergeSort(head, comparator);
        // Recalculate tail
        Node<T> curr = head;
        while (curr != null && curr.getNext() != null) {
            curr = curr.getNext();
        }
        tail = curr;
    }

    private Node<T> mergeSort(Node<T> headNode, Comparator<T> comparator) {
        if (headNode == null || headNode.getNext() == null) {
            return headNode;
        }

        Node<T> middle = getMiddle(headNode);
        Node<T> nextOfMiddle = middle.getNext();
        middle.setNext(null);

        Node<T> left = mergeSort(headNode, comparator);
        Node<T> right = mergeSort(nextOfMiddle, comparator);

        return sortedMerge(left, right, comparator);
    }

    private Node<T> sortedMerge(Node<T> a, Node<T> b, Comparator<T> comparator) {
        if (a == null) return b;
        if (b == null) return a;

        Node<T> result;
        if (comparator.compare(a.getData(), b.getData()) <= 0) {
            result = a;
            result.setNext(sortedMerge(a.getNext(), b, comparator));
        } else {
            result = b;
            result.setNext(sortedMerge(a, b.getNext(), comparator));
        }
        return result;
    }

    private Node<T> getMiddle(Node<T> headNode) {
        if (headNode == null) return headNode;
        Node<T> slow = headNode;
        Node<T> fast = headNode;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }
}
