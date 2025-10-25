package csd214.lab1.f25.pojos;


public class CashTill {
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

