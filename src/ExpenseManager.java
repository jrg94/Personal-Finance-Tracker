import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    /*
     * This class makes sure that the methods work properly
     */
    private final List<Expense> expenses = new ArrayList<>();

    // Method to get all expenses
    public List<Expense> getAllExpenses() {
        return expenses;
    }

    // Method to add an expense
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    // Method to delete an expense based on name
    public void deleteExpense(String name) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getName().equalsIgnoreCase(name)) {
                expenses.remove(i);
                break; // Exit the loop after removing the expense
            }
        }
    }

    // Method to find an expense by name
    public Expense findExpenseByName(String name) {
        for (Expense expense : expenses) {
            if (expense.getName().equalsIgnoreCase(name)) {
                return expense;
            }
        }
        return null; // Return null if no matching expense is found
    }

    // Method to update an expense
    public void updateExpense(String name, Expense updatedExpense) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getName().equalsIgnoreCase(name)) {
                expenses.set(i, updatedExpense);
                break;
                // end loop when finished
            }
        }
    }
}
