package service.impl;

import classees.Client;
import classees.Taxi;
import dao.Database;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class ClientService implements service.ClientService {
    Database database = new Database();


    @Override
    public String addClient(Client client) {
        List<Client> clients = new ArrayList<>(List.of(client));
        database.setClients(clients);
        return "Client added successfully!";
    }

    @Override
    public String addClient(List<Client> clients) {
        database.setClients(clients);
        return "Clients created successfully!!";
    }

    @Override
    public List<Client> getClientByName(String name) {
        List<Client> newClients = new ArrayList<>();
        for (Client client : database.getClients()) {
            String[] names = client.getFullName().split(" ");
            if (names[0].equals(name)) {
                newClients.add(client);
            }
        }
        return newClients;
    }

    @Override
    public Client removeClientById(Long id) {
        Client removeClient = database.getClients().stream().filter(client -> client.getId().equals(id)).findFirst().get();
        database.getClients().remove(removeClient);
        System.out.println("Client " + id + " removed successfully!");
        return removeClient;
    }


    @Override
    public Taxi orderTaxi(Long clientId, String taxiType) {
        for (Client client : database.getClients()) {
            if (Objects.equals(client.getId(), clientId)){
                for (Taxi taxi : database.getTaxiList()) {
                    if (taxi.getTaxiType().name().equals(taxiType)){
                        int t = client.getMoney().intValue();
                        if (t >= taxi.getTaxiType().getPriceForLanding().intValue()){
                            return taxi;
                        }else {
                            System.out.println("Ne hvataet dengi");
                        }
                    }else {
                        System.out.println("Vy ne pravilno vveli order.");
                    }
                }
            }else {
                System.out.println("Ne pravilnyi id.");
            }
        }
        return null;
    }

    @Override
    public Map<Integer, Client> getClientAge() {
        Map<Integer, Client> clientMap = new HashMap<>();
        for (Client client : database.getClients()) {
                clientMap.put(client.getDateOfBirth().minusYears(LocalDate.now().getYear()).getYear(), client);
        }
        return clientMap;
    }

    @Override
    public void universalSorting(String word) {
        switch (word) {
            case "fullName" ->
                    database.getClients().stream().sorted(Comparator.comparing(Client::getFullName)).toList().forEach(System.out::println);
            case "dataOfBirth" ->
                    database.getClients().stream().sorted(Comparator.comparing(Client::getDateOfBirth)).toList().forEach(System.out::println);
            case "money" ->
                    database.getClients().stream().sorted(Comparator.comparing(Client::getMoney)).toList().forEach(System.out::println);
            default -> System.out.println("Error!");

        }
    }
}
