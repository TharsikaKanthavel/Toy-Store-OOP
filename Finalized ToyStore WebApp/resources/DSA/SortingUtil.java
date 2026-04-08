package com.toystore.app.util;

import com.toystore.app.model.Toy;

/**
 * Utility class for sorting algorithms
 * Contains implementation of Selection Sort algorithm for toys
 */
public class SortingUtil {

    /**
     * Sort toys by age group using the Selection Sort algorithm
     * @param toys The array of toys to sort
     */
    public static void selectionSortByAgeGroup(Toy[] toys) {
        int n = toys.length;

        // Selection Sort algorithm
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in the unsorted part of the array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (toys[j].getAgeGroup() < toys[minIndex].getAgeGroup()) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            if (minIndex != i) {
                Toy temp = toys[i];
                toys[i] = toys[minIndex];
                toys[minIndex] = temp;
            }
        }
    }
}