package csd214.bookstore.pojos;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author fcarella
 */

/**
 * DTO for {@link csd214.bookstore.entities.PublicationEntity}
 */
public abstract class Publication extends Editable implements SaleableItem, Serializable {
    private String title;
    private double price;
    private int copies;
    private String isbn_10;
    private String description;


    public Publication(String title, double price, int copies, String isbn_10, String description) {
        this.title = title;
        this.price = price;
        this.copies = copies;
        this.isbn_10 = isbn_10;
        this.description = description;
    }

    public Publication() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Double.compare(price, that.price) == 0 && copies == that.copies && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, copies);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn_10() {
        return isbn_10;
    }

    public void setIsbn_10(String isbn_10) {
        this.isbn_10 = isbn_10;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public void sellCopy() {
        copies--;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                ", isbn_10='" + isbn_10 + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
//    @Override
//    public String toString() {
//        String format="%-20s Title: %-20s Price: $%-4.2f Copies: %-2d";
//        return String.format(format,this.getClass().getSimpleName(), title, price, copies);
//
//    }
}