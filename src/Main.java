import classees.License;
import classees.Taxi;
import enums.TaxiType;
import service.impl.TaxiService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Taxi> taxiList = new ArrayList<>(Arrays.asList(
                new Taxi(1L, "Honda", "666", "White", LocalDate.of(2011, 1, 1), TaxiType.STANDART),
                new Taxi(2L, "Toyota", "777", "Red", LocalDate.of(2012, 2, 2), TaxiType.COMFORT),
                new Taxi(3L, "Lexus", "888", "Black", LocalDate.of(2020, 5, 10), TaxiType.BUSINESS)));
        System.out.println(taxiList);
        Taxi taxi = new Taxi(3L, "Lexus", "888", "Black", LocalDate.of(2020, 5, 10), TaxiType.BUSINESS);
        TaxiService taxiService = new TaxiService();
        while (true) {
            System.out.println("""
                    1 -add taxi
                    2 -add all taxis
                    3 -find By Initial Letter
                    4 -grouping
                    5 -filter By TaxiType
                    6 -update
                    0 - program finished!
                    """);
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            switch (number) {
                case 1 -> {
                    System.out.println(" ");
                    System.out.println(taxiService.add(taxi));
                }
                case 2 -> {
                    System.out.println("добавить все такси: ");
                    System.out.println(taxiService.add(taxiList));
                }
                case 3 -> {
                    System.out.print("Enter model: ");
                    String model = new Scanner(System.in).nextLine();
                    System.out.println(taxiService.findByInitialLetter(model));
                }
                case 4 -> System.out.println(taxiService.grouping());
                case 5 -> {
                    System.out.print("Enter taxi type: ");
                    String string = new Scanner(System.in).nextLine();
                    System.out.println(taxiService.filterByTaxiType(string));
                }
                case 6 -> {
                    System.out.print("Enter id: ");
                    Long id = new Scanner(System.in).nextLong();
                    taxiService.update(id);
                }
            }
            if (number == 0) {
                System.out.println(" PROGRAM FINISHED!");
                break;
            }
        }
    }
}