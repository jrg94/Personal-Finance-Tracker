public class Expense {


    /*
        * This class create the attributes (Constructors) of the expense and make the setters and getters
     */


    private String name;
    private int amount;
    private String category;
    private String date;

    // Constructor
    public Expense(String name, int amount, String category, String date) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters and setters
    public String getName() { return name; }
    public int getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public void setName(String name) { this.name = name; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setCategory(String category) { this.category = category; }
    public void setDate(String date) { this.date = date; }

    // Override toString for easy display
    @Override
    public String toString() {
        return "Expense{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
