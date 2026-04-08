package com.toystore.datastructure;

/**
 * Node class for LinkedList
 * @param <T> the type of element held in this node
 */
public class Node<T> {

    private T data;
    private Node<T> next;
    private Node<T> prev;

    /**
     * Constructs a new node with the specified data
     * @param data the data to store in this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Returns the data stored in this node
     * @return the data stored in this node
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data for this node
     * @param data the new data to store in this node
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns the next node in the list
     * @return the next node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the next node in the list
     * @param next the next node
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Returns the previous node in the list
     * @return the previous node
     */
    public Node<T> getPrev() {
        return prev;
    }

    /**
     * Sets the previous node in the list
     * @param prev the previous node
     */
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}