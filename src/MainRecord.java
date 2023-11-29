import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Description: Main class for the project
public class MainRecord {

    /**
     * Adds a new expense to the ExpenseManager.
     * This method prompts the user to enter details for a new expense including
     * name, amount, category, and date, and then creates an Expense object
     * which is added to the ExpenseManager.
     *
     * @param input   Scanner object for reading user input.
     * @param manager Instance of ExpenseManager to manage expenses.
     */
    public static void addExpense(Scanner input, ExpenseRecordManager manager) {

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
        ExpenseRecord expense = new ExpenseRecord(name, amount, category, date);

        // Add the new Expense object to the ExpenseManager
        manager.addExpense(expense);

        // Inform the user that the expense was added successfully
        System.out.println("Expense added successfully.");
    }

    public static void viewExpenses(ExpenseRecordManager manager) {
        // Get all expenses from the ExpenseManager
        List<ExpenseRecord> expenses = manager.getAllExpenses();

        // Print the details of each expense
        // Think I want to print it to a html file instead of the console
        /**
         * TODO: I actually like that it prints to the console
         * here. Or at the very least, it prints a preview to
         * the user. I will say that the default layout isn't
         * great. That said, if you go the HTML route,
         * it might be a good idea to print a message
         * letting them know some simple statistics:
         * "printing 5 expenses for a total of $112 to
         * an HTML file"
         */
        for (ExpenseRecord expense : expenses) {
            System.out.println(expense);
        }
        /*
         * TODO: it should be more explicit to the user that
         * they need to enter something here. I just hit
         * ENTER and got the "not printing" message. 
         */
        System.out.println("Now printing to a html file");
        Scanner input = new Scanner(System.in);
        if (input.nextLine().equalsIgnoreCase("yes")) {
            htmlView(input, manager);
        } else {
            System.out.println("Not printing to a html file");
        }
    }

    // Going to make a method to print the expenses to a html file, view show up in
    // the console
    /**
     * Generates an HTML view of all expenses and writes them to a file.
     *
     * @param input   Scanner object for reading user input.
     * @param manager Instance of ExpenseManager to manage expenses.
     */
    public static void htmlView(Scanner input, ExpenseRecordManager manager) {
        String filePath = "Expense.html";

        // Using try-with-resources for automatic resource management
        // Why? My IDE said i need it
        /*
         * TODO: The try with resources feature is really nice.
         * It makes sure you don't have to manually close the
         * FileWriter. As for the HTML, it looks good! I think
         * some table headers and borders would go a long way
         * to making the output more readable.
         */
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
            for (ExpenseRecord expense : manager.getAllExpenses()) {
                writer.write("<td>" + expense.name() + "</td>\n");
                writer.write("<td>" + expense.amount() + "</td>\n");
                writer.write("<td>" + expense.category() + "</td>\n");
                writer.write("<td>" + expense.date() + "</td>\n");
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

    public static void editExpense(Scanner input, ExpenseRecordManager manager) {

    }

    public static void deleteExpense(Scanner input, ExpenseRecordManager manager) {

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ExpenseRecordManager manager = new ExpenseRecordManager(); // Create an instance of ExpenseManager

        /*
         * TODO: most of my feedback is around the User Interface (UI).
         * The messages are a little hard to follow. Something I usually
         * do for the user is print special characters at the start of
         * each input line like:
         * 
         * Select one of the following options to start: add, view, ..., exit
         * > view
         * Here are all of your expenses:
         *   - Tortillas ($3)
         *   - Cheese ($4)
         *   - Beef ($9)
         * What else would you like to do? Options: add, view, ..., exit
         * > ...
         * 
         * With that said, the software itself runs wonderfully! 
         * The logic is great, and it's really useful. Also,
         * the design is great, from how you're choosing to 
         * store your data to splitting the code into separate
         * files. 
         */
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