package service.impl;

import classees.Driver;
import classees.Taxi;
import dao.Database;

import java.util.*;

public class DriverService implements service.DriverService {
    private Database database;

    @Override
    public Driver add(Driver driver) {
        try {
            for (Driver d : database.getDrivers()) {
                if (!Objects.equals(d.getId(), driver.getId())) {
                    database.getDrivers().add(driver);
                } else throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }

    @Override
    public List<Driver> add(List<Driver> drivers) {
        database.getDrivers().addAll(drivers);
        return drivers;
    }

    @Override
    public Driver findById(Long id) {
        for (Driver driver : database.getDrivers()) {
            if (Objects.equals(driver.getId(), id)) return driver;
        }
        return null;
    }

    @Override
    public List<Driver> findByName(String name) {
        List<Driver> results = new ArrayList<>();
        try {
            if (name.isEmpty()) throw new Exception("Name can't be Empty.");
            for (Driver driver : database.getDrivers()) {
                if (driver.getName().equals(name))
                    results.add(driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }


    @Override
    public String assignTaxiToDriver(String taxiName, Long driverId) {
        boolean isChanged = false;
        try {
            if(taxiName.isEmpty()) throw new Exception("Taxi name can't be Empty.");
            if(driverId < 0) throw new Exception("ID can't be a negative number.");

            for (Driver driver : database.getDrivers()) {
                if (driver.getId().equals(driverId)) {
                    for (Taxi taxi : database.getTaxiList())
                        if (taxi.getModel().equals(taxiName)) {
                            driver.setTaxi(taxi);
                            isChanged = true;
                        }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isChanged ? "Taxi & Driver assigned successfully!" : "Something went wrong. (AssignTaxiToDriver Method).";
    }

    @Override
    public String changeTaxiOrDriver(Long driverId, Long taxiId) {
        Driver activeDriver = null;
        Taxi activeTaxi = null;

        Database database = new Database();
        for (Taxi taxi1 : database.getTaxiList()) {
            if (Objects.equals(taxi1.getId(), taxiId)) {
                activeTaxi = taxi1;
            }
        }
        for (Driver driver1 : database.getDrivers()) {
            if (Objects.equals(driver1.getId(), driverId)) {
                activeDriver = driver1;
            }
        }
        assert activeDriver != null;
        activeDriver.setTaxi(activeTaxi);

        return "the change was successful!!";
    }



    @Override
    public List<Driver> getDriverByTaxiModel(String model) {
        List<Driver> results = new ArrayList<>();
        try{
            if(model.isEmpty()) throw  new Exception("Model cannot be empty.");
            for (Driver driver : database.getDrivers()) {
                if(driver.getTaxi().getModel().equals(model))
                    results.add(driver);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void update(String driverName) {
        try{
            if(driverName.isEmpty()) throw new Exception(" Driver name can't be Empty");
            for (Driver driver : database.getDrivers()) {
                if (driver.getName().equals(driverName)) {
                    System.out.println(driver);
                }

            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}