package csd214.lab1.f25.pojos;


import java.time.LocalDate;
import java.util.Objects;

public class DiscMag extends Magazine {
    private boolean hasDisc;

    public DiscMag() {
        super();
    }

    public DiscMag(boolean hasDisc, int orderQty, LocalDate currentIssue, String title, double price, int copies) {
        super(orderQty, currentIssue, title, price, copies);
        this.hasDisc = hasDisc;
    }

    public boolean hasDisc() { return hasDisc; }
    public void setHasDisc(boolean hasDisc) { this.hasDisc = hasDisc; }

    @Override
    public void edit() {
        System.out.println("--- Editing Disc Magazine " + (getTitle() != null ? "'" + getTitle() + "'" : "") + (getId() != null ? " (ID: " + getId() + ")" : "") + " ---");
        super.edit(); // Edit common Magazine properties
        System.out.print("Does it have a disc? (true/false) (current: " + this.hasDisc + "): ");
        setHasDisc(super.getInput(this.hasDisc));
        System.out.println("Disc Magazine specific details updated.");
    }

    @Override
    public void initialize() {
        System.out.println("--- Initializing New Disc Magazine ---");
        super.initialize(); // Initialize common Magazine properties
        System.out.print("Does it have a disc? (true/false): ");
        setHasDisc(super.getInput(false)); // Default to false
        System.out.println("Disc Magazine specific details initialized.");
    }

    @Override
    public void sellItem() {
        if (getCopies() > 0) {
            setCopies(getCopies() - 1);
            System.out.println("Sold DiscMag: '" + getTitle() + "' (Issue: " + (getCurrentIssue() != null ? getCurrentIssue().format(LOCAL_DATE_FORMAT) : "N/A") + ")" +
                    (hasDisc ? " with disc" : " without disc") + ". Copies left: " + getCopies());
        } else {
            System.out.println("DiscMag '" + getTitle() + "' is out of stock.");
        }
    }

    @Override
    public String toString() {
        return "DiscMag [" + super.toString() + ", hasDisc=" + hasDisc + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscMag discMag = (DiscMag) o;
        return hasDisc == discMag.hasDisc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasDisc);
    }
}

