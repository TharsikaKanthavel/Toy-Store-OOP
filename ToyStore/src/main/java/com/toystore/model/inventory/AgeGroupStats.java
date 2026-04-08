package com.toystore.model.inventory;

/**
 * Statistics for a specific age group of toys
 */
public class AgeGroupStats {

    private String name;
    private int productCount;
    private int stockCount;

    /**
     * Default constructor
     */
    public AgeGroupStats() {
        this.name = "";
        this.productCount = 0;
        this.stockCount = 0;
    }

    /**
     * Constructor with all fields
     * @param name the age group name
     * @param productCount the number of products in this age group
     * @param stockCount the total stock of products in this age group
     */
    public AgeGroupStats(String name, int productCount, int stockCount) {
        this.name = name;
        this.productCount = productCount;
        this.stockCount = stockCount;
    }

    /**
     * Returns the name of this age group
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this age group
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of products in this age group
     * @return the product count
     */
    public int getProductCount() {
        return productCount;
    }

    /**
     * Sets the number of products in this age group
     * @param productCount the product count to set
     */
    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    /**
     * Returns the total stock of products in this age group
     * @return the stock count
     */
    public int getStockCount() {
        return stockCount;
    }

    /**
     * Sets the total stock of products in this age group
     * @param stockCount the stock count to set
     */
    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    /**
     * Returns the average stock per product in this age group
     * @return the average stock per product
     */
    public double getAverageStockPerProduct() {
        return productCount > 0 ? (double) stockCount / productCount : 0;
    }

    @Override
    public String toString() {
        return "AgeGroupStats{" +
                "name='" + name + '\'' +
                ", productCount=" + productCount +
                ", stockCount=" + stockCount +
                '}';
    }
}