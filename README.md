# Lecture 4.2 Bookstore Persistence
## Repository and Service Patterns and Inversion of Control

### From Coupled Code to a Modern Architecture

This project demonstrates the evolution of a Java application from a simple, tightly-coupled design to a modern, layered architecture using professional design patterns. We will explore how to build applications that are flexible, maintainable, and, most importantly, testable.

The core concepts we will cover are:
1.  **The Problem:** Tightly-Coupled Persistence Logic.
2.  **The Solution:** The **Repository Pattern** for abstracting data access.
3.  **The Business Logic:** The **Service Layer** for centralizing application rules.
4.  **The Glue:** **Inversion of Control (IoC)** and **Dependency Injection (DI)** for decoupling components.

Each concept is demonstrated by a runnable application (`App.java` through `App5.java`).

---

## Part 1: The Problem - Tightly-Coupled Data Access (`App.java`)

In our initial `App.java`, the business logic (what the application does) is mixed directly with the data access logic (how it talks to the database).

```java
// From App.java
public class App {
    private EntityManagerFactory emf = null;
    // ...
    private void createBook(EntityManagerFactory emf) {
        // Business logic is mixed with persistence details
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BookEntity b = new BookEntity();
        b.setTitle("Title");
        // ...
        em.persist(b); // <-- Data access logic
        em.getTransaction().commit(); // <-- Data access logic
    }
}
```

This approach has serious disadvantages:
*   **It's Brittle:** If we wanted to switch from MySQL to a different database, we would have to find and rewrite every piece of code that uses the `EntityManager`.
*   **It's Hard to Test:** How can you unit test the `createBook` logic without a running database? Your tests become slow, complex, and dependent on an external system.
*   **It Violates the Single Responsibility Principle:** The `App` class is responsible for both business rules and managing database connections, making it bloated and difficult to understand.

---

## Part 2: The Solution - The Repository Pattern

The Repository Pattern solves this by creating an abstraction layer that isolates the data access logic. Think of it as a contract that defines *what* data operations can be performed, but not *how*.

### Step 2.1: The Contract (The `Repository` Interface)

We start by defining a generic interface. This is the heart of the pattern.

**File: `repositories/Repository.java`**
```java
public interface Repository<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    T save(T entity);
    void deleteById(Long id);
}
```
Our business logic will only ever interact with this interface, never with the `EntityManager` or a specific database implementation.

### Step 2.2: The Concrete Implementations (The "Strategies")

Now we can create multiple implementations of this interface, each with a different data storage strategy.

1.  **In-Memory (`InMemoryProductRepository`) - Demonstrated in `App2.java`**
    *   Uses a simple `HashMap` to store data.
    *   **Use Case:** Perfect for fast, simple unit tests. No database required.

2.  **H2 Database (`H2ProductRepository`) - Demonstrated in `App3.java`**
    *   Uses JPA and a real, in-memory H2 SQL database. The database is created on startup and destroyed on shutdown.
    *   **Use Case:** Ideal for integration tests that need to verify real SQL and JPA behavior without the overhead of an external database.

3.  **MySQL Database (`MySQLProductRepository`) - Demonstrated in `App4.java`**
    *   Uses JPA to connect to our persistent, production MySQL database.
    *   **Use Case:** The "real" implementation for the final application.

Crucially, all three classes **implement the same `Repository<T>` interface**. This means they can be used interchangeably without the client code knowing the difference.

---

## Part 3: The Business Logic - The Service Layer

The Service Layer is responsible for containing the application's business logic. It orchestrates calls to one or more repositories to fulfill a business operation.

**File: `services/ProductService.java`**
```java
public class ProductService {
    // The service depends on the INTERFACE, not a concrete class.
    private final Repository<ProductEntity> productRepository;

    public ProductService(Repository<ProductEntity> productRepository) {
        this.productRepository = productRepository;
    }

    public BookEntity createBook(String title, double price, /*...*/) {
        // Business logic: create a book object
        BookEntity newBook = new BookEntity(title, price, /*...*/);
        // Orchestration: ask the repository to save it
        return (BookEntity) productRepository.save(newBook);
    }
}
```
Notice that `ProductService` has no idea if it's talking to a `HashMap`, an H2 database, or a MySQL database. It is completely decoupled. But how does it get the correct repository?

---

## Part 4: Tying It All Together - Inversion of Control (IoC)

**Inversion of Control (IoC)** is a design principle where the control over object creation and dependencies is "inverted"—it's moved from your component to an external source.

*   **Traditional Control:** The `ProductService` would create its own dependency, like `new MySQLProductRepository()`. This would tightly couple it to MySQL.
*   **Inverted Control:** The `ProductService` gives up control. It simply states that it *needs* a `Repository<ProductEntity>`, and something else provides it.

This is achieved using the **Dependency Injection (DI)** pattern. In our code, we use **Constructor Injection**: the dependency is "injected" through the constructor.

### The Payoff (`App5.java`)

`App5.java` is the ultimate demonstration of this architecture. It acts as the "controller" or "assembler" that builds and runs our application.

```java
public class App5 {
    public void run() {
        // === Block 1: Using the H2 Repository ===
        // App5 is in control: It creates the H2 repository.
        H2ProductRepository h2Repository = new H2ProductRepository();
        // App5 injects the dependency into the service.
        ProductService h2ProductService = new ProductService(h2Repository);
        // The service runs, completely unaware it's using H2.
        h2ProductService.createBook(...);
        h2Repository.close();

        // === Block 2: Using the MySQL Repository ===
        // App5 is in control: It creates the MySQL repository.
        MySQLProductRepository mySQLRepository = new MySQLProductRepository();
        // App5 injects a DIFFERENT dependency into a new service instance.
        ProductService mySQLProductService = new ProductService(mySQLRepository);
        // The EXACT SAME service logic now runs against a different database.
        mySQLProductService.createBook(...);
        mySQLRepository.close();
    }
}
```
Because of IoC, the `ProductService` is completely reusable. We can swap its entire data layer without changing a single line of its code. This makes the system incredibly flexible and easy to test.

---

## How to Run the Demonstrations

Run `Main.java` and select an option to see each stage of the architecture in action.

1.  **Option 1 (`App.java`):** The legacy, tightly-coupled approach.
2.  **Option 2 (`App2.java`):** The Repository Pattern with a non-database `HashMap`.
3.  **Option 3 (`App3.java`):** The Repository Pattern with an in-memory H2 SQL database.
4.  **Option 4 (`App4.java`):** The Repository Pattern with the production MySQL database.
5.  **Option 5 (`App5.java`):** The complete, modern architecture, demonstrating the **Service Layer** and **Inversion of Control** by using the same business logic with both H2 and MySQL repositories.

### Final Benefits

This layered architecture provides three key advantages that are essential for professional software development:
*   **Maintainability:** Code is organized by responsibility (`entities`, `repositories`, `services`), making it easy to navigate and modify.
*   **Flexibility:** The data source is a pluggable component. We can easily add a new repository for a cloud database or a file system with minimal changes.
*   **Testability:** Our business logic in the `ProductService` can be unit-tested with the `InMemoryProductRepository`, making tests fast, reliable, and independent of any database.