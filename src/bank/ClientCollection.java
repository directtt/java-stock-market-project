package bank;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientCollection {

    public ArrayList<Client> clients;

    public ClientCollection(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void setClientsId() {
        for (Client client : clients) {
            client.setId(clients.indexOf(client));
        }
    }

    public void setClientsAccount() {
        for (Client client : clients) {
            client.setAccount(new BrokerageAccount());
            client.getAccount().setClientHoldings(new HashMap<String, float[]>());
        }
    }

    public void readClients() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("D:/Java Projects/UAM-PO/src/bank/clientList.txt"));

            String line;
            while ( (line = in.readLine()) != null) {
                String vals[] = line.split(",");
                String name = vals[0];
                String surname = vals[1];
                String id = vals[2];

                if (id.length() == 10) {
                    Client client = new BusinessClient(name, surname, id);
                    clients.add(client);
                }
                else {
                    Client client = new PrivateClient(name, surname, id);
                    clients.add(client);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveClients() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/bank/data.txt"));
            for (Client client : clients) {
                os.writeObject(client);
                os.writeObject(client.getAccount().getClientHoldings());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
