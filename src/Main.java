import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport("resources/y.2021.csv");

        MonthlyReport[] monthlyReport = new MonthlyReport[3];
        for (int i = 0; i < monthlyReport.length; i++) {
            monthlyReport[i] = new MonthlyReport("resources/m.20210" + (i + 1) + ".csv");
        }

        printMenu();
        int userInput = scanner.nextInt();

        while (userInput != 0) {
            /*System.out.println("Выберете действие:");
            System.out.println("1 - Считать все месячные отчеты");
            System.out.println("2 - Считать годовой отчет");
            System.out.println("3 - Сверить отчеты");
            System.out.println("4 - Вывести информацию о всех месячных отчетах");
            System.out.println("5 - Вывести информацию о годовом отчете");
            System.out.println("0 - Выход из приложения");*/

            if (userInput == 1) {
                for (int i = 0; i < monthlyReport.length; i++) {
                    if (!monthlyReport[i].monthsData.isEmpty()) {
                        System.out.println("Считан месячный отчет " + (i + 1));
                    }
                }
            } else if (userInput == 2) {
                if (!yearlyReport.monthsData.isEmpty()) {
                    System.out.println("Считан годовой отчет");
                }
            } else if (userInput == 3) {
                for (int i = 0; i < monthlyReport.length; i++) {
                    if (!monthlyReport[i].monthsData.isEmpty() && !yearlyReport.monthsData.isEmpty()) {
                        if (monthlyReport[i].reconciliationOfIncome() == yearlyReport.reconciliationOfIncomeMonth(i)) {
                            System.out.println("Доход по месяцу " + (i + 1) + " совпадает");
                        } else {
                            System.out.println("Доход по месяцу " + (i + 1) + " НЕ совпадает");
                        }
                        if (monthlyReport[i].reconciliationOfExpenses() == yearlyReport.reconciliationOfExpensesMonth(i)) {
                            System.out.println("Расходы по месяцу " + (i + 1) + " совпадают");
                        } else {
                            System.out.println("Расходы по месяцу " + (i + 1) + " НЕ совпадают");
                        }
                    }
                }
            } else if (userInput == 4) {
                for (int i = 0; i < monthlyReport.length; i++) {
                    System.out.println("Статистика за " + (i + 1) + " месяц");
                    monthlyReport[i].expensesProduct();
                    monthlyReport[i].profitableProduct();
                }
            } else if (userInput == 5) {
                yearlyReport.printingTheAnnualReport();
            } else if (userInput == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
            printMenu();
            userInput = scanner.nextInt();


        }
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию о всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("0 - Выход из приложения");
    }
}



