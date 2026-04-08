package com.toystore.app.model;

public enum Category {

    ACTION_FIGURES("Action Figures"),
    DOLLS("Dolls"),
    EDUCATIONAL("Educational"),
    ELECTRONIC("Electronic"),
    BOARD_GAMES("Games & Puzzles"),
    OUTDOOR("Outdoor"),
    PLUSH("Plush Toys"),
    VEHICLES("Vehicles"),
    CONSTRUCTION("Construction Toys"),
    ART_CRAFT("Arts & Crafts"),
    MUSICAL("Musical Toys"),
    BUILDING_SETS("Building Sets"),
    ROLE_PLAY("Role Play");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Category fromDisplayName(String displayName) {
        for (Category category : Category.values()) {
            if (category.getDisplayName().equalsIgnoreCase(displayName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No category found with display name: " + displayName);
    }
}