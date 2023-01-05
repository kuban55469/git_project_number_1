package service.impl;

import classees.Taxi;
import dao.Database;
import enums.TaxiType;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaxiService implements service.TaxiService {

    Database database = new Database();

    @Override
    public StringBuilder add(Taxi taxi) {
        if (taxi.getYear().isAfter(LocalDate.of(2010, 12, 31))) {
            List<Taxi> taxiList = new ArrayList<>(List.of(taxi));
            database.setTaxiList(taxiList);
            return new StringBuilder("Taxi added successfully!");
        } else {
            return new StringBuilder("Taxi invalid! ");
        }

    }

    @Override
    public StringBuilder add(List<Taxi> taxis) {
        boolean trueOrFalse = false;
        for (Taxi taxi : taxis) {
            if (taxi.getYear().isAfter(LocalDate.of(2010, 12, 31))) {
                trueOrFalse = true;
                database.getTaxiList().add(taxi);
            }
        }
        if (!trueOrFalse) return new StringBuilder("Taxi invalid! ");
        return new StringBuilder("Taxi created successfully!!");
    }

    @Override
    public List<Taxi> findByInitialLetter(String model) {
        model = String.valueOf(model.toUpperCase().charAt(0));
        String finalModel = model;
        return database.getTaxiList().stream().filter(taxi -> taxi.getModel().startsWith(finalModel)).toList();
    }

    @Override
    public Map<TaxiType, List<Taxi>> grouping() {
        return database.getTaxiList().stream().collect(Collectors.groupingBy(Taxi::getTaxiType));
    }

    @Override
    public List<Taxi> filterByTaxiType(String taxiType) {
        return database.getTaxiList().stream().filter(taxi -> taxi.getTaxiType().name().equals(taxiType)).toList();
    }

    @Override
    public void update(Long id) {
        Scanner scanner = new Scanner(System.in);
        for (Taxi taxi : database.getTaxiList()) {
            if (Objects.equals(taxi.getId(), id)) {
                System.out.println("What do want to change?");
                String namr = new Scanner(System.in).nextLine();
                if (namr.equals("Id")) {
                    System.out.print("Write new id:");
                    taxi.setId(new Scanner(System.in).nextLong());
                }
                if (namr.equals("Colour")) {
                    System.out.print("Enter new colour: ");
                    taxi.setColour(new Scanner(System.in).nextLine());
                }
                if (namr.equals("Number")) {
                    System.out.println("Write new number: ");
                    taxi.setNumber(new Scanner(System.in).nextLine());
                }
                if (namr.equals("taxiType")) {
                    System.out.println("Write new taxiType: ");
                    taxi.setTaxiType(TaxiType.valueOf(new Scanner(System.in).nextLine().toUpperCase()));
                }
                if (namr.equals("Year")) {
                    System.out.print("Write taxis year: ");
                    int year = new Scanner(System.in).nextInt();
                    System.out.print("Write taxis month: ");
                    int month = new Scanner(System.in).nextInt();
                    System.out.print("Write taxis day: ");
                    int day = new Scanner(System.in).nextInt();
                    taxi.setYear(LocalDate.of(year, month, day));
                }
            }
        }
    }
}
