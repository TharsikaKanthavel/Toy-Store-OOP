package com.toystore.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * Custom LinkedList implementation
 * @param <T> the type of elements in this list
 */
public class LinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Constructs an empty list
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in this list
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this list contains no elements
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Appends the specified element to the end of this list
     * @param data element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public boolean add(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

        size++;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * @param index index at which the specified element is to be inserted
     * @param data element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == size) {
            add(data);
            return;
        }

        Node<T> newNode = new Node<>(data);

        if (index == 0) {
            // Insert at the beginning
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        } else {
            // Insert in the middle
            Node<T> current = getNodeAt(index);
            Node<T> previous = current.getPrev();

            newNode.setNext(current);
            newNode.setPrev(previous);
            previous.setNext(newNode);
            current.setPrev(newNode);
        }

        size++;
    }

    /**
     * Returns the element at the specified position in this list
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return getNodeAt(index).getData();
    }

    /**
     * Replaces the element at the specified position in this list with the specified element
     * @param index index of the element to replace
     * @param data element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> node = getNodeAt(index);
        T oldData = node.getData();
        node.setData(data);

        return oldData;
    }

    /**
     * Removes the element at the specified position in this list
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> nodeToRemove;

        if (index == 0) {
            // Remove first element
            nodeToRemove = head;
            head = head.getNext();

            if (head != null) {
                head.setPrev(null);
            } else {
                // List is now empty
                tail = null;
            }
        } else if (index == size - 1) {
            // Remove last element
            nodeToRemove = tail;
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            // Remove from the middle
            nodeToRemove = getNodeAt(index);
            Node<T> prevNode = nodeToRemove.getPrev();
            Node<T> nextNode = nodeToRemove.getNext();

            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }

        size--;
        return nodeToRemove.getData();
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present
     * @param data element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    public boolean remove(T data) {
        if (isEmpty()) {
            return false;
        }

        if (head.getData().equals(data)) {
            // Remove first element
            head = head.getNext();

            if (head != null) {
                head.setPrev(null);
            } else {
                // List is now empty
                tail = null;
            }

            size--;
            return true;
        }

        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                // Found element to remove
                Node<T> prevNode = current.getPrev();
                Node<T> nextNode = current.getNext();

                prevNode.setNext(nextNode);

                if (nextNode != null) {
                    nextNode.setPrev(prevNode);
                } else {
                    // Removing tail
                    tail = prevNode;
                }

                size--;
                return true;
            }

            current = current.getNext();
        }

        return false; // Element not found
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element
     * @param data element to search for
     * @return the index of the first occurrence of the specified element in this list,
     *         or -1 if this list does not contain the element
     */
    public int indexOf(T data) {
        int index = 0;
        Node<T> current = head;

        while (current != null) {
            if (current.getData().equals(data)) {
                return index;
            }

            current = current.getNext();
            index++;
        }

        return -1; // Element not found
    }

    /**
     * Returns {@code true} if this list contains the specified element
     * @param data element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    public boolean contains(T data) {
        return indexOf(data) >= 0;
    }

    /**
     * Removes all of the elements from this list
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence
     * @return an array containing all of the elements in this list in proper sequence
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (Node<T> current = head; current != null; current = current.getNext()) {
            array[i++] = current.getData();
        }

        return array;
    }

    /**
     * Returns a list of elements that match the given predicate
     * @param predicate a predicate to apply to each element to determine if it should be included
     * @return a new list containing only the elements that match the predicate
     */
    public LinkedList<T> filter(Predicate<T> predicate) {
        LinkedList<T> result = new LinkedList<>();

        for (T element : this) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }

        return result;
    }

    /**
     * Returns the node at the specified index
     * @param index index of the node to return
     * @return the node at the specified position
     */
    private Node<T> getNodeAt(int index) {
        // Optimize by starting from tail if index is closer to the end
        if (index > size / 2) {
            Node<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
            return current;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current;
        }
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }

    /**
     * Returns a string representation of this list
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<T> current = head;
        while (current != null) {
            sb.append(current.getData());

            if (current.getNext() != null) {
                sb.append(", ");
            }

            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }
}