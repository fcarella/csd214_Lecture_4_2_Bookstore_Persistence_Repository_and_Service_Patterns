package csd214.bookstore.pojos;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author fcarella
 */

public class DiscMag extends Magazine {

    private boolean hasDisc;

    public DiscMag() {
    }

    public DiscMag(Magazine m, boolean hasDisc) {
        setTitle(m.getTitle());
        setPrice(m.getPrice());
        setCopies(m.getCopies());
        setIsbn_10(m.getIsbn_10());
        setDescription(m.getDescription());
        setOrderQty(m.getOrderQty());
        setCurrIssue(m.getCurrIssue());
        this.hasDisc = hasDisc;
    }

    public DiscMag(String title, double price, int copies, String isbn_10, String description,
                   int orderQty, LocalDate currIssue, boolean hasDisc) {
        super(title, price, copies, isbn_10, description, orderQty, currIssue);
        this.hasDisc = hasDisc;
    }

    public boolean getHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    @Override
    public void edit() {
        super.edit();
        System.out.println("Edit Has Disc (" + getHasDisc() + " [enter for no changes | True | False])");
        setHasDisc(getInput(getHasDisc()));
    }

    @Override
    public void initialize() {
        super.initialize();
        System.out.println("Enter Has Disc:");
        setHasDisc(getInput(false));
    }

    @Override
    public String toString() {
        String format="Has Disc: %-10b";
        return super.toString() + " " + String.format(format, hasDisc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscMag discMag)) return false;
        if (!super.equals(o)) return false;
        return hasDisc == discMag.hasDisc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasDisc);
    }
}