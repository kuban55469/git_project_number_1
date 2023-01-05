import classees.Client;
import dao.Database;
import service.impl.ClientService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>(List.of(
                new Client(1L,"Kuban Kelsinbekov", LocalDate.of(2002,12,10),"996708281398", BigDecimal.valueOf(20000)),
                new Client(2L,"Ilyaz Sariev", LocalDate.of(1990,12,28),"+996222888999", BigDecimal.valueOf(30000)),
                new Client(3L,"Zhazgul Asanov", LocalDate.of(2003,9,11),"+996333444555", BigDecimal.valueOf(40000))
        ));
        Client client = new Client(3L,"Zhazgul Asanov", LocalDate.of(2003,9,11),"+996333444555", BigDecimal.valueOf(40000));
        ClientService clientService = new ClientService();

        while (true) {
            System.out.println("""
                    1: Add client.
                    2: Add clients.
                    3: Get client by name.
                    4: Remove client by id.
                    5: Order taxi.
                    6: Get client age.
                    7: Universal sorting.
                    """);
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> System.out.println(clientService.addClient(client));
                case 2 -> System.out.println(clientService.addClient(clients));
                case 3 -> {
                    System.out.print("Enter name: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.println(clientService.getClientByName(name));
                }
                case 4 -> {
                    System.out.print("Enter id: ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(clientService.removeClientById(id));
                }
                case 5 -> {
                    System.out.print("Enter id: ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.print("Enter order taxi: ");
                    String order = new Scanner(System.in).nextLine();
                    clientService.orderTaxi(id,order);
                }
                case 6 -> System.out.println(clientService.getClientAge());
                case 7 -> clientService.universalSorting(new Scanner(System.in).nextLine());
                default -> System.out.println("Error");
            }
        }
    }
}