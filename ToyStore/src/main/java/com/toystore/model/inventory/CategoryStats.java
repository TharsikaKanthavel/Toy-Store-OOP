package com.toystore.model.inventory;

/**
 * Statistics for a specific category of toys
 */
public class CategoryStats {

    private String name;
    private int productCount;
    private int stockCount;

    /**
     * Default constructor
     */
    public CategoryStats() {
        this.name = "";
        this.productCount = 0;
        this.stockCount = 0;
    }

    /**
     * Constructor with all fields
     * @param name the category name
     * @param productCount the number of products in this category
     * @param stockCount the total stock of products in this category
     */
    public CategoryStats(String name, int productCount, int stockCount) {
        this.name = name;
        this.productCount = productCount;
        this.stockCount = stockCount;
    }

    /**
     * Returns the name of this category
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this category
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of products in this category
     * @return the product count
     */
    public int getProductCount() {
        return productCount;
    }

    /**
     * Sets the number of products in this category
     * @param productCount the product count to set
     */
    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    /**
     * Returns the total stock of products in this category
     * @return the stock count
     */
    public int getStockCount() {
        return stockCount;
    }

    /**
     * Sets the total stock of products in this category
     * @param stockCount the stock count to set
     */
    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    /**
     * Returns the average stock per product in this category
     * @return the average stock per product
     */
    public double getAverageStockPerProduct() {
        return productCount > 0 ? (double) stockCount / productCount : 0;
    }

    @Override
    public String toString() {
        return "CategoryStats{" +
                "name='" + name + '\'' +
                ", productCount=" + productCount +
                ", stockCount=" + stockCount +
                '}';
    }
}