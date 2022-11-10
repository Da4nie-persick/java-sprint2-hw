import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;


public class MonthlyReport {
    public HashMap<String, MonthlyReporterMonth> monthsData = new HashMap<>();

    public MonthlyReport (String path) {

        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);


            if (!monthsData.containsKey(itemName)) {
                monthsData.put(itemName, new MonthlyReporterMonth());
            }
            MonthlyReporterMonth monthData = monthsData.get(itemName);
            if (isExpense) {
                monthData.expenses = quantity * sumOfOne;
            } else {
                monthData.income = quantity * sumOfOne;
            }

        }
    }
    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    void profitableProduct() {
        int profit = 0;
        String nameProfit = "";
        for (String itemName: monthsData.keySet()) {
            MonthlyReporterMonth mon = monthsData.get(itemName);
            if (profit < mon.income) {
                profit = mon.income;
                nameProfit = itemName;
            }
        }
        System.out.println ("Самый прибыльный товар:" + nameProfit + ", стоимостью " + profit);
    }

    void expensesProduct() {
        int expenses = 0;
        String nameExpenses = "";
        for (String itemName : monthsData.keySet()) {
            MonthlyReporterMonth mon = monthsData.get(itemName);
            if (expenses < mon.expenses) {
                expenses = mon.expenses;
                nameExpenses = itemName;
            }
        }
        System.out.println("Самая большая трата: " + nameExpenses + ", стоимостью " + expenses);
    }
    int reconciliationOfIncome() {
        int income = 0;
        for (String itemName : monthsData.keySet()) {
            MonthlyReporterMonth mon = monthsData.get(itemName);
            income += mon.income;
        }
        return income;
    }

    int reconciliationOfExpenses() {
        int exp = 0;
        for (String itemName : monthsData.keySet()) {
            MonthlyReporterMonth mon = monthsData.get(itemName);
            exp += mon.expenses;
        }
        return exp;
    }





}

