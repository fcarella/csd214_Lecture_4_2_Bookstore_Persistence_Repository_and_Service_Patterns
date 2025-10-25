package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.UUID;

/**
 * @author fcarella
 */
/**
 * DTO for {@link csd214.bookstore.entities.BookEntity}
 */
public class Book extends Publication {

    private String author;

    public Book() {
        setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
    }

    public Book(Long id, String author) {
        this.author = author;
        setId(id);
    }

    public Book(Long id, String title, double price, int copies, String isbn_10, String description, String author) {
        super(title, price, copies, isbn_10, description);
        this.author = author;
        setId(id);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void edit() {
        System.out.println("Edit Title (" + getTitle() + " [enter for no changes])");
        setTitle(getInput(getTitle()));
        System.out.println("Edit Author (" + getAuthor() + " [enter for no changes])");
        setAuthor(getInput(getAuthor()));
        System.out.println("Edit copies (" + getCopies() + " [enter for no changes])");
        setCopies(getInput(getCopies()));
        System.out.println("Edit price (" + getPrice() + " [enter for no changes])");
        setPrice(getInput(getPrice()));
    }

    @Override
    public void initialize() {
        System.out.println("Enter Title:");
        setTitle(getInput("no title"));
        System.out.println("Enter Author:");
        setAuthor(getInput("no author"));
        System.out.println("Enter copies:");
        setCopies(getInput(0));
        System.out.println("Enter price:");
        setPrice(getInput(0.0d));

    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                "} " + super.toString();
    }
//    @Override
//    public String toString() {
//        String format="Author: %-20s";
//        return super.toString() + " " + String.format(format, getAuthor());
//    }

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
