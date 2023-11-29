import java.util.ArrayList;
import java.util.List;

public class ExpenseRecordManager {
    /*
     * This class makes sure that the methods work properly
     */
    private final List<ExpenseRecord> expenses = new ArrayList<>();

    // Method to get all expenses
    public List<ExpenseRecord> getAllExpenses() {
        return expenses;
    }

    // Method to add an expense
    public void addExpense(ExpenseRecord expense) {
        expenses.add(expense);
    }

    // Method to delete an expense based on name
    public void deleteExpense(String name) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).name().equalsIgnoreCase(name)) {
                expenses.remove(i);
                break; // Exit the loop after removing the expense
            }
        }
    }

    // Method to find an expense by name
    public ExpenseRecord findExpenseByName(String name) {
        for (ExpenseRecord expense : expenses) {
            if (expense.name().equalsIgnoreCase(name)) {
                return expense;
            }
        }
        return null; // Return null if no matching expense is found
    }

    // Method to update an expense
    public void updateExpense(String name, ExpenseRecord updatedExpense) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).name().equalsIgnoreCase(name)) {
                expenses.set(i, updatedExpense);
                break;
                // end loop when finished
            }
        }
    }
}
