package csd214.lab1.f25.pojos;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;


// <<Abstract>> Publication
// Publication no longer needs to explicitly implement Serializable, as Editable does.
public abstract class Publication extends Editable implements SaleableItem {
    private String title;
    private double price;
    private int copies;
    protected final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    protected final DateTimeFormatter LOCAL_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);


    public Publication() {
        super(); // Calls Editable's constructor
    }

    public Publication(String title, double price, int copies) {
        super();
        this.title = title;
        this.price = price;
        this.copies = copies;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public abstract void sellItem(); // Must be implemented by concrete subclasses

    // Getters and Setters
    public String getTitle() { return title; }
    protected void setTitle(String title) { this.title = title; }
    protected void setPrice(double price) { this.price = price; }
    public int getCopies() { return copies; }
    protected void setCopies(int copies) { this.copies = copies; }


    @Override
    public String toString() {
        // You might want to include id from Editable using super.toString() or getId()
        return  (getId() != null ? "id=" + getId() + ", " : "") +
                "title='" + title + '\'' +
                ", price=" + price +
                ", copies=" + copies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Double.compare(that.price, price) == 0 &&
                copies == that.copies &&
                Objects.equals(title, that.title) &&
                Objects.equals(getId(), that.getId()); // Include ID in equals
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), title, price, copies); // Include ID in hash
    }
}

