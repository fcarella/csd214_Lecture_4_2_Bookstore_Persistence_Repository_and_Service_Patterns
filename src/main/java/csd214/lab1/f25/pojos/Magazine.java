package csd214.lab1.f25.pojos;

import java.time.LocalDate;
import java.util.Objects;


public class Magazine extends Publication {
    private int orderQty;
    private LocalDate currentIssue; // Using LocalDate as per your Editable

    public Magazine() {
        super();
        this.currentIssue = LocalDate.now(); // Default to now
    }

    public Magazine(int orderQty, LocalDate currentIssue, String title, double price, int copies) {
        super(title, price, copies);
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
    }

    public int getOrderQty() { return orderQty; }
    public void setOrderQty(int orderQty) { this.orderQty = orderQty; }
    public LocalDate getCurrentIssue() { return currentIssue; }
    public void setCurrentIssue(LocalDate currentIssue) { this.currentIssue = currentIssue; }

    @Override
    public void edit() {
        System.out.println("--- Editing Magazine " + (getTitle() != null ? "'" + getTitle() + "'" : "") + (getId() != null ? " (ID: " + getId() + ")" : "") + " ---");
        System.out.print("Enter new title (current: '" + (getTitle() == null ? "" : getTitle()) + "'): ");
        setTitle(super.getInput(getTitle() == null ? "" : getTitle()));

        System.out.print("Enter new price (current: " + getPrice() + "): ");
        setPrice(super.getInput(getPrice()));

        System.out.print("Enter new copies (current: " + getCopies() + "): ");
        setCopies(super.getInput(getCopies()));

        System.out.print("Enter new order quantity (current: " + this.orderQty + "): ");
        setOrderQty(super.getInput(this.orderQty));

        System.out.print("Enter new current issue date (dd-MMM-yyyy) (current: " + (this.currentIssue != null ? this.currentIssue.format(LOCAL_DATE_FORMAT) : "N/A") + "): ");
        setCurrentIssue(super.getInput(this.currentIssue == null ? LocalDate.now() : this.currentIssue));
        System.out.println("Magazine updated.");
    }

    @Override
    public void initialize() {
        System.out.println("--- Initializing New Magazine ---");
        System.out.print("Enter title: ");
        setTitle(super.getInput(""));

        System.out.print("Enter price: ");
        setPrice(super.getInput(0.0));

        System.out.print("Enter copies: ");
        setCopies(super.getInput(0));

        System.out.print("Enter order quantity: ");
        setOrderQty(super.getInput(0));

        System.out.print("Enter current issue date (dd-MMM-yyyy): ");
        setCurrentIssue(super.getInput(LocalDate.now())); // Default to today
        System.out.println("Magazine initialized.");
    }

    @Override
    public void sellItem() {
        if (getCopies() > 0) {
            setCopies(getCopies() - 1);
            System.out.println("Sold Magazine: '" + getTitle() + "' (Issue: " + (currentIssue != null ? currentIssue.format(LOCAL_DATE_FORMAT) : "N/A") + "). Copies left: " + getCopies());
        } else {
            System.out.println("Magazine '" + getTitle() + "' is out of stock.");
        }
    }

    @Override
    public String toString() {
        return "Magazine [" + super.toString() +
                ", orderQty=" + orderQty +
                ", currentIssue=" + (currentIssue != null ? currentIssue.format(LOCAL_DATE_FORMAT) : "N/A") + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return orderQty == magazine.orderQty &&
                Objects.equals(currentIssue, magazine.currentIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderQty, currentIssue);
    }
}

