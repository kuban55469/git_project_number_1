package service.impl;

import classees.Client;
import dao.Database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ClientService implements service.ClientService {
    private Database database;

    @Override
    public String addClient(Client client) {
        for (Client databaseClient : database.getClients()) {
            databaseClient = client;
        }
        return "Client added successfully!";
    }

    @Override
    public String addClient(List<Client> clients) {
        for (Client client : database.getClients()) {
            for (Client client1 : clients) {
                client = client1;
            }
        }
        return "Clients created successfully!!";
    }

    @Override
    public List<Client> getClientByName(String name) {
        List<String> names = new ArrayList<>();
        for (Client client : database.getClients()) {
            names.add(Arrays.toString(client.getFullName().split(" ")));
        }
        return null;
    }

    @Override
    public Client removeClientById(Long id) {
        return null;
    }

    @Override
    public Taxi orderTaxi(Long clientId, String taxiType) {
        return null;
    }

    @Override
    public Map<Integer, Client> getClientAge() {
        return null;
    }

    @Override
    public void universalSorting(String word) {

    }
}
