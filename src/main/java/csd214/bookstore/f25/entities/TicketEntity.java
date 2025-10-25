package csd214.bookstore.f25.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class TicketEntity extends ProductEntity {
    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void sellCopy() {
        System.out.println(this);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public void edit() {
        System.out.println("Edit ticket text");
        setDescription(getInput(getDescription()));
        System.out.println("Edit ticket price");
        setPrice(getInput(getPrice()));
    }

    @Override
    public void initialize() {
        System.out.println("Enter ticket text");
        setDescription(getInput(getDescription()));
        System.out.println("Edit ticket price");
        setPrice(getInput(getPrice()));
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "description='" + description + '\'' +
                ", price=" + price +
                "} " + super.toString();
    }
//    public String toString() {
//        String format = "%-20s Description: %-250s";
//        return String.format(format, this.getClass().getSimpleName(), getDescription());
//    }
}