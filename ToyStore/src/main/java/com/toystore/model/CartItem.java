package com.toystore.model;

/**
 * Represents an item in a shopping cart
 */
public class CartItem {

    private Toy toy;
    private int quantity;

    /**
     * Default constructor
     */
    public CartItem() {
        this.toy = null;
        this.quantity = 0;
    }

    /**
     * Constructor with toy and quantity
     * @param toy the toy
     * @param quantity the quantity
     */
    public CartItem(Toy toy, int quantity) {
        this.toy = toy;
        this.quantity = quantity;
    }

    /**
     * Returns the toy in this cart item
     * @return the toy
     */
    public Toy getToy() {
        return toy;
    }

    /**
     * Sets the toy in this cart item
     * @param toy the toy to set
     */
    public void setToy(Toy toy) {
        this.toy = toy;
    }

    /**
     * Returns the quantity of this cart item
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of this cart item
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = Math.max(0, quantity);
    }

    /**
     * Returns the total price of this cart item (price * quantity)
     * @return the total price
     */
    public double getTotalPrice() {
        return toy != null ? toy.getPrice() * quantity : 0;
    }

    /**
     * Returns a formatted total price string
     * @return formatted total price string
     */
    public String getFormattedTotalPrice() {
        return String.format("$%.2f", getTotalPrice());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CartItem cartItem = (CartItem) obj;
        return toy != null && toy.equals(cartItem.toy);
    }

    @Override
    public int hashCode() {
        return toy != null ? toy.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "toy=" + (toy != null ? toy.getName() : "null") +
                ", quantity=" + quantity +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}