package csd214.lab1.f25.pojos;

import java.io.Serializable;
import java.util.Objects;
import csd214.lab1.f25.pojos.SaleableItem;


// Ticket does not extend Editable, so it remains largely the same, but implements Serializable
class Ticket implements SaleableItem, Serializable { // Explicitly Serializable
    private Long id; // Tickets might also have IDs
    private String description;
    private double price;

    public Ticket(String description, double price) {
        this.description = description;
        this.price = price;
    }
    public Ticket(Long id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) { this.price = price; }

    @Override
    public void sellItem() {
        System.out.println("Sold Ticket: " + description + " for $" + String.format("%.2f", price) + (id != null ? " (ID: " + id + ")" : ""));
    }

    @Override
    public String toString() {
        return "Ticket [" + (id != null ? "id=" + id + ", " : "") +
                "description='" + description + '\'' + ", price=" + String.format("%.2f", price) + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.price, price) == 0 &&
                Objects.equals(id, ticket.id) &&
                Objects.equals(description, ticket.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price);
    }
}

