package com.toystore.model;

/**
 * Represents a toy in the store
 */
public class Toy {

    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String ageGroup;
    private int stockQuantity;
    private String imageUrl;
    private boolean featured;

    /**
     * Default constructor
     */
    public Toy() {
        this.id = "";
        this.name = "";
        this.description = "";
        this.price = 0.0;
        this.category = "";
        this.ageGroup = "";
        this.stockQuantity = 0;
        this.imageUrl = "";
        this.featured = false;
    }

    /**
     * Constructor with all fields
     */
    public Toy(String id, String name, String description, double price,
               String category, String ageGroup, int stockQuantity,
               String imageUrl, boolean featured) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.ageGroup = ageGroup;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.featured = featured;
    }

    /**
     * Returns the ID of this toy
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of this toy
     * @param id the ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of this toy
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this toy
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of this toy
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this toy
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the price of this toy
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of this toy
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the category of this toy
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of this toy
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the age group for this toy
     * @return the age group
     */
    public String getAgeGroup() {
        return ageGroup;
    }

    /**
     * Sets the age group for this toy
     * @param ageGroup the age group to set
     */
    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    /**
     * Returns the stock quantity of this toy
     * @return the stock quantity
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets the stock quantity of this toy
     * @param stockQuantity the stock quantity to set
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Returns the image URL of this toy
     * @return the image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL of this toy
     * @param imageUrl the image URL to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Returns whether this toy is featured
     * @return true if this toy is featured, false otherwise
     */
    public boolean isFeatured() {
        return featured;
    }

    /**
     * Sets whether this toy is featured
     * @param featured true if this toy should be featured, false otherwise
     */
    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    /**
     * Returns a formatted price string
     * @return formatted price string
     */
    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }

    /**
     * Returns whether this toy is in stock
     * @return true if this toy is in stock, false otherwise
     */
    public boolean isInStock() {
        return stockQuantity > 0;
    }

    /**
     * Returns whether this toy is low on stock
     * @return true if this toy is low on stock, false otherwise
     */
    public boolean isLowStock() {
        return stockQuantity > 0 && stockQuantity <= 5;
    }

    /**
     * Convert toy to a delimited string for file storage
     * @return delimited string representation
     */
    public String toFileString() {
        return String.join("|",
                id,
                name,
                description,
                String.valueOf(price),
                category,
                ageGroup,
                String.valueOf(stockQuantity),
                imageUrl,
                String.valueOf(featured)
        );
    }

    /**
     * Create a toy from a delimited string from file storage
     * @param fileString delimited string from file
     * @return new Toy object
     */
    public static Toy fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");

        if (parts.length != 9) {
            throw new IllegalArgumentException("Invalid file string format");
        }

        return new Toy(
                parts[0],                          // id
                parts[1],                          // name
                parts[2],                          // description
                Double.parseDouble(parts[3]),      // price
                parts[4],                          // category
                parts[5],                          // ageGroup
                Integer.parseInt(parts[6]),        // stockQuantity
                parts[7],                          // imageUrl
                Boolean.parseBoolean(parts[8])     // featured
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Toy toy = (Toy) obj;
        return id.equals(toy.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Toy{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", ageGroup='" + ageGroup + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}