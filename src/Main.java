

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        YearlyReport yearlyReport = new YearlyReport();


        MonthlyReport[] monthlyReport = new MonthlyReport[3];

        String[] month = new String[] {"январь", "февраль", "март"};

        boolean m = false;
        boolean y = false;

        printMenu();
        int userInput = scanner.nextInt();

        while (userInput != 0) {


            if (userInput == 1) {
                for (int i = 0; i < monthlyReport.length; i++) {
                    monthlyReport[i] = new MonthlyReport();
                    monthlyReport[i].readMonthly("resources/m.20210" + (i + 1) + ".csv");
                    System.out.println("Считан отчет за " + (month[i]));
                }
                m = true;

            } else if (userInput == 2) {
                yearlyReport = new YearlyReport();
                yearlyReport.readYearlyReport("resources/y.2021.csv");
                System.out.println("Считан годовой отчет");
                y = true;

            } else if (userInput == 3) {
                if (m && y) {
                    for (int i = 0; i < monthlyReport.length; i++) {
                        if (!monthlyReport[i].monthsData.isEmpty() && !yearlyReport.monthsData.isEmpty()) {
                            if (monthlyReport[i].reconciliationOfIncome() == yearlyReport.reconciliationOfIncomeMonth(i)) {
                                System.out.println("Доход за " + (month[i]) + " совпадает");
                            } else {
                                System.out.println("Доход за " + (month[i]) + " НЕ совпадает");
                            }
                            if (monthlyReport[i].reconciliationOfExpenses() == yearlyReport.reconciliationOfExpensesMonth(i)) {
                                System.out.println("Расходы за " + (month[i]) + " совпадают");
                            } else {
                                System.out.println("Расходы за " + (month[i]) + " НЕ совпадают");
                            }
                        }
                    }
                } else {
                    System.out.println("Месячные и годовой отчет не считаны!!!");
                }
            } else if (userInput == 4) {
                if (m) {
                    for (int j = 0; j < monthlyReport.length; j++) {
                        System.out.println("Статистика за " + (month[j]));
                        monthlyReport[j].all();
                    }
                } else {
                    System.out.println("Считайте месячные отчеты!!!");
                }

            } else if (userInput == 5) {
                if (y) {
                    yearlyReport.printingTheAnnualReport();
                } else {
                    System.out.println("Считайте годовой отчет!!!");
                }
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