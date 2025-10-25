package csd214.lab1.f25;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable; // From java.io, already in Editable
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

// Your provided Editable.java
// (I'll place it here for a single code block, but in a project it'd be Editable.java)
// <<abstract>> Editable (from your code)
abstract class Editable implements Serializable { // Already Serializable
    private Long id;
    public Scanner input = new Scanner(System.in); // Made public in your version

    public abstract void edit();
    public abstract void initialize();

    public void setSystemInput(ByteArrayInputStream testIn){
        System.setIn(testIn);
        input=new Scanner(System.in);
    }
    public void setSystemOutput(ByteArrayOutputStream testOut){
        System.setOut(new PrintStream(testOut));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInput(String s) { // s is now the default value
        String ss = input.nextLine();
        if (ss.trim().isEmpty()) {
            return s;
        }
        // Scanner in2 = new Scanner(ss); // No need for new Scanner if just taking the line
        return ss; // Return the non-empty input
    }

    public int getInput(int i) { // i is now the default value
        String s = input.nextLine();
        if (s.trim().isEmpty()) {
            return i;
        }
        Scanner in2 = new Scanner(s); // New scanner for the single line to parse
        try {
            return in2.nextInt();
        } catch (Exception e) {
            System.err.println("Invalid integer input, returning default: " + i);
            return i; // Return default on parsing error
        }
    }

    public double getInput(double i) { // i is now the default value
        String s = input.nextLine();
        if (s.trim().isEmpty()) {
            return i;
        }
        Scanner in2 = new Scanner(s);
        try {
            return in2.nextDouble();
        } catch (Exception e) {
            System.err.println("Invalid double input, returning default: " + i);
            return i;
        }
    }
    public boolean getInput(boolean b) { // b is now the default value
        String s = input.nextLine();
        if (s.trim().isEmpty()) {
            return b;
        }
        Scanner in2 = new Scanner(s);
        try {
            return in2.nextBoolean();
        } catch (Exception e) {
            System.err.println("Invalid boolean input (true/false), returning default: " + b);
            return b;
        }
    }

    public Date getInput(Date date) { // date is now the default value
        String s = input.nextLine();
        if (s.trim().isEmpty()) {
            return date;
        }
        // Scanner in2 = new Scanner(s); // No need, s is the string
        // String dateInString = in2.nextLine(); // s is already the dateInString
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Date d;
        try {
            d = formatter.parse(s);
        } catch (ParseException e) {
            System.err.println("Invalid date input (dd-MMM-yyyy), returning default. Error: " + e.getMessage());
            return date; // Return default on parsing error
            // Or rethrow: throw new RuntimeException(e);
        }
        return d;
    }
    public LocalDate getInput(LocalDate date) { // date is now the default value
        String s = input.nextLine();
        if (s.trim().isEmpty()) {
            return date;
        }
        // Scanner in2 = new Scanner(s); // No need
        // String dateInString = in2.nextLine(); // s is already the dateInString
        LocalDate d;
        try {
            d = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            System.err.println("Invalid LocalDate input (dd-MMM-yyyy), returning default. Error: " + e.getMessage());
            return date; // Return default on parsing error
            // Or rethrow: throw e;
        }
        return d;
    }

    @Override
    public String toString() {
        return "Editable{" +
                "id=" + id +
                '}';
    }
}


// <<interface>> SaleableItem
interface SaleableItem {
    void sellItem();
    double getPrice();
}

// <<Abstract>> Publication
// Publication no longer needs to explicitly implement Serializable, as Editable does.
abstract class Publication extends Editable implements SaleableItem {
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

class Book extends Publication {
    private String author;

    public Book() {
        super();
    }

    public Book(String author, String title, double price, int copies) {
        super(title, price, copies);
        this.author = author;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public void edit() {
        System.out.println("--- Editing Book " + (getTitle() != null ? "'" + getTitle() + "'" : "") + (getId() != null ? " (ID: " + getId() + ")" : "") + " ---");
        System.out.print("Enter new title (current: '" + (getTitle() == null ? "" : getTitle()) + "'): ");
        setTitle(super.getInput(getTitle() == null ? "" : getTitle()));

        System.out.print("Enter new author (current: '" + (this.author == null ? "" : this.author) + "'): ");
        setAuthor(super.getInput(this.author == null ? "" : this.author));

        System.out.print("Enter new price (current: " + getPrice() + "): ");
        setPrice(super.getInput(getPrice()));

        System.out.print("Enter new copies (current: " + getCopies() + "): ");
        setCopies(super.getInput(getCopies()));
        System.out.println("Book updated.");
    }

    @Override
    public void initialize() {
        System.out.println("--- Initializing New Book ---");
        // ID is typically set by persistence layer or later, not usually during manual initialize
        // If you want to set ID here:
        // System.out.print("Enter ID (long): ");
        // setId(super.getInput(0L)); // Example default for ID

        System.out.print("Enter title: ");
        setTitle(super.getInput("")); // Default empty string

        System.out.print("Enter author: ");
        setAuthor(super.getInput("")); // Default empty string

        System.out.print("Enter price: ");
        setPrice(super.getInput(0.0)); // Default 0.0

        System.out.print("Enter copies: ");
        setCopies(super.getInput(0)); // Default 0
        System.out.println("Book initialized.");
    }

    @Override
    public void sellItem() {
        if (getCopies() > 0) {
            setCopies(getCopies() - 1);
            System.out.println("Sold Book: '" + getTitle() + "' by " + author + ". Copies left: " + getCopies());
        } else {
            System.out.println("Book '" + getTitle() + "' is out of stock.");
        }
    }

    @Override
    public String toString() {
        return "Book [" + super.toString() + ", author='" + author + '\'' + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }
}

class Magazine extends Publication {
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

class DiscMag extends Magazine {
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

class CashTill {
    private double runningTotal;

    public CashTill() {
        this.runningTotal = 0.0;
    }

    public void showTotal() {
        System.out.println("Current Till Total: $" + String.format("%.2f", runningTotal));
    }

    public void sellItem(SaleableItem item) {
        if (item != null) {
            System.out.println("\n--- Processing Sale via Cash Till ---");
            item.sellItem();
            this.runningTotal += item.getPrice();
            System.out.println("Added $" + String.format("%.2f", item.getPrice()) + " to till.");
            showTotal();
            System.out.println("--- End Sale ---");
        } else {
            System.err.println("Cannot sell a null item.");
        }
    }

    public double getRunningTotal() {
        return runningTotal;
    }
}

// Main class for testing
class ShopDemo {
    public static void main(String[] args) {
        System.out.println("--- Shop Demo with new Editable ---");

        Book book1 = new Book();
        book1.setId(101L); // Set ID if needed
        System.out.println("Initializing book1:");
        book1.initialize(); // User will be prompted
        System.out.println("Initialized book1: " + book1);

        System.out.println("\nEditing book1:");
        book1.edit(); // User will be prompted with current values as defaults
        System.out.println("Edited book1: " + book1);


        Magazine mag1 = new Magazine();
        mag1.setId(201L);
        System.out.println("\nInitializing mag1:");
        mag1.initialize();
        System.out.println("Initialized mag1: " + mag1);


        DiscMag discMag1 = new DiscMag();
        discMag1.setId(301L);
        System.out.println("\nInitializing discMag1:");
        discMag1.initialize();
        System.out.println("Initialized discMag1: " + discMag1);


        Ticket ticket1 = new Ticket(901L,"Cinema Ticket - Evening Show", 15.00);

        CashTill till = new CashTill();

        till.sellItem(book1);
        till.sellItem(mag1);
        till.sellItem(discMag1);
        till.sellItem(ticket1);

        System.out.println("\nFinal state of book1: " + book1);
        System.out.println("Final state of mag1: " + mag1);
        System.out.println("Final state of discMag1: " + discMag1);
        System.out.println("Final state of ticket1: " + ticket1);

        // Example of testing input (you'd typically do this in a JUnit test)
        // String simulatedUserInput = "Test Book Title\nTest Author\n19.99\n5\n";
        // ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
        // book1.setSystemInput(testIn); // Redirect System.in
        // book1.initialize(); // This would now read from testIn
        // System.setIn(System.in); // Reset System.in
        // System.out.println("Book after simulated input: " + book1);
    }
}