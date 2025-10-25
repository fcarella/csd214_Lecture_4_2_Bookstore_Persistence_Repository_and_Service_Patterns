package csd214.lab1.f25.pojos;

import java.util.Objects;

public class Book extends Publication {
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

