package bank;

import java.io.Serializable;
import java.util.HashMap;

public class BrokerageAccount extends GeneralAccount implements Serializable {

    private HashMap<String, float[]> clientHoldings;

    public HashMap<String, float[]> getClientHoldings() {
        return clientHoldings;
    }

    public void setClientHoldings(HashMap<String, float[]> clientHoldings) {
        this.clientHoldings = clientHoldings;
    }

    public void buy(String name, int quantity, StockMarket stockMarket) {
        try {

            Stock stockToBuy = stockMarket.getStockName(name);

            if (stockToBuy == null) {
                throw new IllegalArgumentException("Invalid stock name!");
            }
            else if (quantity <= 0) {
                throw new IllegalArgumentException("Invalid quantity!");
            }
            else {
                float[] arr = {stockToBuy.getPrice(), quantity};
                if (withdraw(stockToBuy.getPrice() * quantity)) { // check if withdraw was success
                    if (clientHoldings.containsKey(name)) {
                        float[] currArr = clientHoldings.get(name); // buy price averaging if stock already bought
                        float[] newArr = {(currArr[0] * currArr[1] + arr[0] * arr[1]) / (arr[1] + currArr[1]), arr[1] + currArr[1]};
                        clientHoldings.replace(name, newArr);
                    }
                    else {
                        clientHoldings.put(name, arr);
                    }
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sell(String name, int quantity, StockMarket stockMarket) {

        try {

            Stock stockToSell = stockMarket.getStockName(name);

            if (stockToSell == null) {
                throw new IllegalArgumentException("Invalid stock name!");
            }
            else if (!clientHoldings.containsKey(name)) {
                throw new IllegalArgumentException("Stock not found in your wallet!");
            }
            else if (quantity <= 0) {
                throw new IllegalArgumentException("Invalid quantity!");
            }
            else if (quantity > clientHoldings.get(name)[1]) {
                throw new IllegalArgumentException("Invalid quantity!");
            }
            else {
                float currPrice = stockToSell.getPrice();

                if (deposit(currPrice * quantity)) { // check if deposit was success
                    clientHoldings.get(name)[1] -= quantity;

                    if (clientHoldings.get(name)[1] == 0) {
                        clientHoldings.remove(name);
                    }
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
