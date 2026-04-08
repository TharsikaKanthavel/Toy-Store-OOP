package com.toystore.util;

import com.toystore.model.Toy;

import java.util.List;

/**
 * Utility class for sorting algorithms
 */
public class SortingUtil {

    /**
     * Sort toys by age group using selection sort
     * @param toys the list of toys to sort
     */
    public static void selectionSortByAgeGroup(List<Toy> toys) {
        if (toys == null || toys.size() <= 1) {
            return;
        }

        int n = toys.size();

        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in the unsorted part
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (compareAgeGroups(toys.get(j).getAgeGroup(), toys.get(minIndex).getAgeGroup()) < 0) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the element at index i
            if (minIndex != i) {
                Toy temp = toys.get(i);
                toys.set(i, toys.get(minIndex));
                toys.set(minIndex, temp);
            }
        }
    }

    /**
     * Compare two age group strings
     * @param ageGroup1 the first age group
     * @param ageGroup2 the second age group
     * @return negative if ageGroup1 < ageGroup2, 0 if equal, positive if ageGroup1 > ageGroup2
     */
    private static int compareAgeGroups(String ageGroup1, String ageGroup2) {
        // Extract the starting age from age group strings
        int startAge1 = extractStartingAge(ageGroup1);
        int startAge2 = extractStartingAge(ageGroup2);

        return Integer.compare(startAge1, startAge2);
    }

    /**
     * Extract the starting age from an age group string
     * @param ageGroup the age group string (e.g., "0-2 years", "3-5 years")
     * @return the starting age as an integer
     */
    private static int extractStartingAge(String ageGroup) {
        if (ageGroup == null || ageGroup.isEmpty()) {
            return 0;
        }

        // Try to extract a number from the beginning of the string
        StringBuilder numberBuilder = new StringBuilder();
        for (char c : ageGroup.toCharArray()) {
            if (Character.isDigit(c)) {
                numberBuilder.append(c);
            } else if (!numberBuilder.isEmpty()) {
                // Stop when we encounter a non-digit after finding some digits
                break;
            }
        }

        if (numberBuilder.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(numberBuilder.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Sort toys by price using bubble sort
     * @param toys the list of toys to sort
     * @param ascending true for ascending order, false for descending
     */
    public static void bubbleSortByPrice(List<Toy> toys, boolean ascending) {
        if (toys == null || toys.size() <= 1) {
            return;
        }

        int n = toys.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                double price1 = toys.get(j).getPrice();
                double price2 = toys.get(j + 1).getPrice();

                boolean shouldSwap = ascending ? price1 > price2 : price1 < price2;

                if (shouldSwap) {
                    // Swap toys[j] and toys[j+1]
                    Toy temp = toys.get(j);
                    toys.set(j, toys.get(j + 1));
                    toys.set(j + 1, temp);
                    swapped = true;
                }
            }

            // If no swapping occurred in this pass, the array is sorted
            if (!swapped) {
                break;
            }
        }
    }
}