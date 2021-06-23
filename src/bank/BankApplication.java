package bank;

import java.util.ArrayList;

public class BankApplication {

    public static void main(String[] args) {

        ClientCollection clientCollection = new ClientCollection(new ArrayList<Client>());
        clientCollection.readClients();
        clientCollection.setClientsId();
        clientCollection.setClientsAccount();

        StockMarket stockMarket = new StockMarket(new ArrayList<Stock>());
        stockMarket.readStocks();
        stockMarket.start();

        GUI gui = new GUI();
        gui.printAllClients(clientCollection.clients);
        int currID = gui.loginValidation(clientCollection);

        while (true) { // main loop
            gui.runApp(clientCollection, currID, stockMarket);
            currID = gui.loginValidation(clientCollection);
        }
    }
}
