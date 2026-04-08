package com.toystore.app.datastructure;

import com.toystore.app.model.Toy;

/**
 * Node class for Linked List implementation
 * Represents a single node in the linked list containing a toy and a reference to the next node
 */
public class Node {
    private Toy data;
    private Node next;

    /**
     * Constructor to create a new node with the given toy data
     * @param data The toy to store in this node
     */
    public Node(Toy data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Get the toy data stored in this node
     * @return The toy object
     */
    public Toy getData() {
        return data;
    }

    /**
     * Set the toy data for this node
     * @param data The toy object to store
     */
    public void setData(Toy data) {
        this.data = data;
    }

    /**
     * Get the next node in the linked list
     * @return The next node
     */
    public Node getNext() {
        return next;
    }

    /**
     * Set the next node in the linked list
     * @param next The node to set as next
     */
    public void setNext(Node next) {
        this.next = next;
    }
}