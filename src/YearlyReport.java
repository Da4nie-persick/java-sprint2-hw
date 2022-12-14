import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class YearlyReport {
    String[] month = new String[] {"январе", "феврале", "марте"};
    public HashMap<Integer, YearlyReportMonth> monthsData = new HashMap<>();
    int allMonth = 3;

    void readYearlyReport(String path) {



        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int sum = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            if (!monthsData.containsKey(month)) {
                monthsData.put(month, new YearlyReportMonth(month));
            }
            YearlyReportMonth oneMonthData = monthsData.get(month);
            if (isExpense) {
                oneMonthData.expenses += sum;
            } else {
                oneMonthData.income +=sum;
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
    void printingTheAnnualReport () {
        System.out.println("Отчет за 2021 год");
        for (int j = 0; j < month.length; j++) {
            System.out.println("Прибыль в " + month[j] + " = " + sumProfit(j));
        }
        System.out.println("Средний доход в этом году " + allSumProfit());
        System.out.println("Средний расход в этом году " + allSumExpense());

    }


    int sumProfit(int i) {
        int profit = 0;
        YearlyReportMonth oneMonthData = monthsData.get(i + 1);
        profit = oneMonthData.income - oneMonthData.expenses;
            return profit;
        }

        int allSumProfit () {
            int allProfit = 0;
            for (YearlyReportMonth oneMonthData : monthsData.values()) {
                allProfit += oneMonthData.income;
            }
            return allProfit / allMonth;
        }

        int allSumExpense () {
            int allExpense = 0;
            for (YearlyReportMonth oneMonthData : monthsData.values()) {
                allExpense += oneMonthData.expenses;
            }
            return allExpense / allMonth;
        }

        int reconciliationOfIncomeMonth (int i){
            int income = 0;
            YearlyReportMonth oneMonthData = monthsData.get(i + 1);
            income = oneMonthData.income;
            return income;
        }


        int reconciliationOfExpensesMonth ( int i){
            int exp = 0;
            YearlyReportMonth oneMonthData = monthsData.get(i + 1);
            exp = oneMonthData.expenses;
            return exp;
        }

    }






