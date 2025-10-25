package csd214.lab1.f25.pojos;

// Main class for testing
public class ShopDemo {
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