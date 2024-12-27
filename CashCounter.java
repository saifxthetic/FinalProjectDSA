import java.io.*;
import java.util.Scanner;



public class CashCounter {
    private static final String FILE_NAME = "customers.txt";

    public static void saveToFile(LinkedList servedCustomers) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            Node temp = servedCustomers.head;
            while (temp != null) {
                writer.write(temp.data.id + "," + temp.data.name + "," + temp.data.amount + "\n");
                temp = temp.next;
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    public static void loadFromFile(LinkedList servedCustomers) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("File not found: " + FILE_NAME);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("Loading data from file...");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double amount = Double.parseDouble(parts[2].trim());
                servedCustomers.add(new Customer(id, name, amount));
            }
            System.out.println("Data successfully loaded from file.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue queue = new Queue();
        LinkedList servedCustomers = new LinkedList();

        int choice;
        do {
            System.out.println("\nCash Counter System:");
            System.out.println("1. Add Customer to Queue");
            System.out.println("2. Serve Customer");
            System.out.println("3. Search for a Customer");
            System.out.println("4. Display Queue");
            System.out.println("5. Display Served Customers");
            System.out.println("6. Save Served Customers to File");
            System.out.println("7. Load Served Customers from File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Transaction Amount: ");
                    double amount = scanner.nextDouble();
                    queue.enqueue(new Customer(id, name, amount));
                    break;

                case 2:
                    Customer served = queue.dequeue();
                    if (served == null) {
                        System.out.println("No customers in the queue.");
                    } else {
                        System.out.println("Served Customer: " + served.name);
                        servedCustomers.add(served);
                    }
                    break;

                case 3:
                    System.out.print("Enter Customer ID to Search: ");
                    int searchId = scanner.nextInt();
                    Customer found = servedCustomers.search(searchId);
                    if (found != null) {
                        System.out.println("Customer Found - ID: " + found.id + ", Name: " + found.name + ", Amount: " + found.amount);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 4:
                    queue.display();
                    break;

                case 5:
                    servedCustomers.display();
                    break;

                case 6:
                    saveToFile(servedCustomers);
                    break;

                case 7:
                    loadFromFile(servedCustomers);
                    break;

                case 8:
                    System.out.println("Exiting the system.");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 8);

        scanner.close();
    }
}

