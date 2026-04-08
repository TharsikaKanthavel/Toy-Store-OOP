package com.toystore.app.datastructure;

import com.toystore.app.model.Toy;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Custom Linked List implementation for toy inventory management
 * This class provides a dynamic data structure for storing toys with efficient operations
 */
public class LinkedList {
    private Node head;
    private int size;

    /**
     * Constructor to create an empty linked list
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Add a toy to the end of the linked list
     * @param toy The toy to add
     */
    public void add(Toy toy) {
        Node newNode = new Node(toy);

        if (head == null) {
            // If list is empty, set the new node as head
            head = newNode;
        } else {
            // Otherwise, traverse to the end and add the new node
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }

        size++;
    }

    /**
     * Get the toy at the specified index
     * @param index The index of the toy to retrieve
     * @return The toy at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Toy get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    /**
     * Get a toy by its ID
     * @param id The ID of the toy to find
     * @return The toy with the specified ID, or null if not found
     */
    public Toy getById(String id) {
        Node current = head;

        while (current != null) {
            if (current.getData().getId().equals(id)) {
                return current.getData();
            }
            current = current.getNext();
        }

        return null;
    }

    /**
     * Update a toy in the linked list
     * @param toy The toy with updated values
     * @return true if the toy was updated, false if not found
     */
    public boolean update(Toy toy) {
        Node current = head;

        while (current != null) {
            if (current.getData().getId().equals(toy.getId())) {
                current.setData(toy);
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    /**
     * Remove a toy from the linked list by its ID
     * @param id The ID of the toy to remove
     * @return true if the toy was removed, false if not found
     */
    public boolean remove(String id) {
        if (head == null) {
            return false;
        }

        // Special case: removing the head
        if (head.getData().getId().equals(id)) {
            head = head.getNext();
            size--;
            return true;
        }

        // Search for the node to remove
        Node current = head;
        while (current.getNext() != null && !current.getNext().getData().getId().equals(id)) {
            current = current.getNext();
        }

        // If found, remove it
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            size--;
            return true;
        }

        return false;
    }

    /**
     * Find toys that match the given predicate
     * @param predicate The predicate to test toys against
     * @return A list of toys that match the predicate
     */
    public List<Toy> findAll(Predicate<Toy> predicate) {
        List<Toy> result = new ArrayList<>();
        Node current = head;

        while (current != null) {
            if (predicate.test(current.getData())) {
                result.add(current.getData());
            }
            current = current.getNext();
        }

        return result;
    }

    /**
     * Convert the linked list to an array of toys
     * @return An array containing all toys in the linked list
     */
    public Toy[] toArray() {
        Toy[] toys = new Toy[size];
        Node current = head;

        for (int i = 0; i < size; i++) {
            toys[i] = current.getData();
            current = current.getNext();
        }

        return toys;
    }

    /**
     * Convert the linked list to a list of toys
     * @return A list containing all toys in the linked list
     */
    public List<Toy> toList() {
        List<Toy> toys = new ArrayList<>(size);
        Node current = head;

        while (current != null) {
            toys.add(current.getData());
            current = current.getNext();
        }

        return toys;
    }

    /**
     * Get the size of the linked list
     * @return The number of toys in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Check if the linked list is empty
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clear the linked list
     */
    public void clear() {
        head = null;
        size = 0;
    }
}