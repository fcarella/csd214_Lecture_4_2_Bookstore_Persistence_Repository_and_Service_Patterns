package csd214.lab1.f25.pojos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

// Your provided Editable.java
// (I'll place it here for a single code block, but in a project it'd be Editable.java)
// <<abstract>> Editable (from your code)
public abstract class Editable implements Serializable { // Already Serializable
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


