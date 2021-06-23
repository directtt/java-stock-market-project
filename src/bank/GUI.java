package bank;

import java.util.ArrayList;
import java.util.Scanner;

public class GUI { // w tej klasie input / output

    int loginValidation(ClientCollection c) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome in our bank system! Type in your client ID: ");
        boolean valid = false;
        String currUser;
        int currUserInt = 0;
        while (!valid) {
            try {
                currUser = scanner.nextLine();
                currUserInt = Integer.parseInt(currUser);
                greetClient(currUserInt, c.clients);
                valid = true;
            } catch (Exception e) {
                System.out.print("Invalid ID! Try again: ");
            }
        }

        return currUserInt;
    }

    void errorMessage(int errNum) {
        switch (errNum) {
            case 0:
                System.out.println("Invalid user ID!");
                break;

            default:
                break;
        }
    }

    public void showMenu() {
        System.out.println("==================");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer Money");
        System.out.println("M. Menu");
        System.out.println("L. Bloomberg Terminal");
        System.out.println("B. Buy Stock");
        System.out.println("S. Sell Stock");
        System.out.println("H. Show Holdings");
        System.out.println("G. Stock Chart");
        System.out.println("E. Logout");
        System.out.println("==================");
    }

    public void greetClient(int id, ArrayList<Client> clients) {
        System.out.println("Welcome " + clients.get(id).getName() + " " + clients.get(id).getSurname() + "!");
    }

    public void printAllClients(ArrayList<Client> clients) {
        System.out.println("===================================");
        System.out.println("ID - Name - Surname   - NIP/PESEL");
        for (Client client : clients) {
            if (client instanceof BusinessClient) {
                System.out.println(client.getId() + "  - " + client.getName() + " - " + client.getSurname() + " - " + ((BusinessClient) client).getNip());
            }
            else if (client instanceof PrivateClient){
                System.out.println(client.getId() + "  - " + client.getName() + " - " + client.getSurname() + "   - " + ((PrivateClient) client).getPesel());
            }
        }
        System.out.println("===================================");
    }

    public void runApp(ClientCollection c, int id, StockMarket s) {

        showMenu();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) { // sub-main loop

            System.out.print("Input option: ");
            char option = scanner.next().charAt(0);
            switch (option) {
                case '1':
                    System.out.println("Balance: $" + c.clients.get(id).getAccount().getBalance());
                    break;

                case '2':
                    System.out.print("Enter an amount to deposit: ");
                    float amount = scanner.nextFloat();
                    c.clients.get(id).getAccount().deposit(amount);
                    break;

                case '3':
                    System.out.print("Enter an amount to withdraw: ");
                    float amount2 = scanner.nextFloat();
                    c.clients.get(id).getAccount().withdraw(amount2);
                    break;

                case '4':
                    System.out.print("Enter user ID you want to transfer money to: ");
                    int id2 = scanner.nextInt();
                    System.out.print("Enter an amount to transfer: ");
                    float amount3 = scanner.nextFloat();
                    c.clients.get(id).getAccount().transfer(amount3, id2, c);
                    break;

                case 'M':
                    showMenu();
                    break;

                case 'E':
                    System.out.println("Logging out..");
                    c.saveClients();
                    isRunning = false;
                    break;

                case 'L':
                    BloombergTerminal bloombergTerminal = new BloombergTerminal();
                    bloombergTerminal.printMarketLive(s);
                    break;

                case 'B':
                    System.out.print("Enter stock name you want to buy: ");
                    String name = scanner.next();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    c.clients.get(id).getAccount().buy(name, quantity, s);
                    break;

                case 'S':
                    System.out.print("Enter stock name you want to sell: ");
                    String name1 = scanner.next();
                    System.out.print("Enter quantity: ");
                    int quantity1 = scanner.nextInt();
                    c.clients.get(id).getAccount().sell(name1, quantity1, s);
                    break;

                case 'H':
                    ClientStockStats clientStats = new ClientStockStats();
                    clientStats.printHolding(s, c.clients.get(id).getAccount());
                    break;

                case 'G':
                    System.out.print("Enter stock name you want to view chart: ");
                    String chartName = scanner.next();
                    StockChart stockChart = new StockChart();

                    stockChart.startChart(chartName, s);
                    break;

                default:
                    System.out.println("Invalid operation! Try again.");
                    break;
            }
        }
    }
}
