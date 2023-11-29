import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Description: Main class for the project
public class Main {

    /**
     * Adds a new expense to the ExpenseManager.
     * This method prompts the user to enter details for a new expense including
     * name, amount, category, and date, and then creates an Expense object
     * which is added to the ExpenseManager.
     *
     * @param input   Scanner object for reading user input.
     * @param manager Instance of ExpenseManager to manage expenses.
     */
    public static void addExpense(Scanner input, ExpenseManager manager){

        System.out.print("Enter expense name: ");
        String name = input.nextLine();


        System.out.print("Enter amount: ");
        int amount = Integer.parseInt(input.nextLine());


        System.out.print("Enter category: ");
        System.out.println("Options: Food, Entertainment, Transportation, Utilities, Rent, Other");
        String category = input.nextLine();
        category = category.toLowerCase();


        System.out.print("Enter date (dd/mm/yyyy): ");
        String date = input.nextLine();

        // Create a new Expense object with the provided details
        Expense expense = new Expense(name, amount, category, date);

        // Add the new Expense object to the ExpenseManager
        manager.addExpense(expense);

        // Inform the user that the expense was added successfully
        System.out.println("Expense added successfully.");
    }

    public static void viewExpenses(ExpenseManager manager) {
        // Get all expenses from the ExpenseManager
        List<Expense> expenses = manager.getAllExpenses();

        // Print the details of each expense
        // Think I want to print it to a html file instead of the console
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
        System.out.println("Now printing to a html file");
        Scanner input = new Scanner(System.in);
        if (input.nextLine().equalsIgnoreCase("yes")) {
            htmlView(input, manager);
        } else {
            System.out.println("Not printing to a html file");
        }
    }
    // Going to make a method to print the expenses to a html file, view show up in the console
    /**
     * Generates an HTML view of all expenses and writes them to a file.
     *
     * @param input   Scanner object for reading user input.
     * @param manager Instance of ExpenseManager to manage expenses.
     */
    public static void htmlView(Scanner input, ExpenseManager manager) {
        String filePath = "Expense.html";

        // Using try-with-resources for automatic resource management
        // Why? My IDE said i need it
        try (FileWriter writer = new FileWriter(filePath)) {

            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<head>\n");
            writer.write("<title>Expense Tracker</title>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");
            writer.write("<h1>Expense Tracker</h1>\n");

            // Table
            writer.write("<table>\n");
            writer.write("<tr>\n"); // Start of the table row

            // Looping through each expense and writing its details in table data tags
            for (Expense expense : manager.getAllExpenses()) {
                writer.write("<td>" + expense.getName() + "</td>\n");
                writer.write("<td>" + expense.getAmount() + "</td>\n");
                writer.write("<td>" + expense.getCategory() + "</td>\n");
                writer.write("<td>" + expense.getDate() + "</td>\n");
                writer.write("</tr>\n");
            }

            // Closing the table and the HTML tags
            writer.write("</table>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
        } catch (IOException e) {
            throw new RuntimeException(e); // Throwing a runtime exception in case of an IO error
        }
    }

     public static void editExpense (Scanner input, ExpenseManager manager){

        }
        public static void deleteExpense (Scanner input, ExpenseManager manager){

        }





    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager(); // Create an instance of ExpenseManager

        while (true) {
            System.out.println("Welcome to your personal Financial Tracker");
            System.out.println("What would you like to do? ");
            System.out.println("Options: add, view, edit, delete, exit");
            String action = input.nextLine().toLowerCase();

            switch (action) {
                case "add":
                    // Handle adding an expense
                    addExpense(input, manager);
                    break;
                case "view":
                    // Handle viewing expenses
                    viewExpenses(manager);
                    break;
                case "edit":
                    // Handle editing an expense
                    editExpense(input, manager);
                    break;
                case "delete":
                    // Handle deleting an expense
                    deleteExpense(input, manager);
                    break;
                case "exit":
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }
}