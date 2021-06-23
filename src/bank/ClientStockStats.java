package bank;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ClientStockStats { // ta klasa tez printuje, jest od statystyk

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public void printHolding(StockMarket stockMarket, BrokerageAccount brokerageAccount) {
        HashMap<String, float[]> clientHoldings = brokerageAccount.getClientHoldings();

        System.out.println("===============================================================");
        System.out.println("Stock name - Buy price - Current Price - Profit / Loss [%]");
        for (Map.Entry<String, float[]> entry : clientHoldings.entrySet()) {
            String stockName = entry.getKey();
            float buyPrice = entry.getValue()[0];
            float buyQuantity = entry.getValue()[1];
            float totalPrice = buyPrice * buyQuantity;
            float currentPrice = getStockName(stockName, stockMarket).getPrice() * buyQuantity;
            double percentage = (currentPrice / totalPrice) * 100;
            String percFormat;
            if (percentage < 100) { percFormat = "-" + df2.format(100 - percentage) + "%"; }
            else { percFormat = "+" + df2.format(percentage - 100) + "%"; }

            System.out.println(stockName + "  -  $" + df2.format(totalPrice) + "  -  $" + df2.format(currentPrice) + "  -  " + percFormat);
        }
        System.out.println("===============================================================");
    }

    private Stock getStockName(String name, StockMarket stockMarket) {
        Stock resultStock = null;

        for (Stock stock : stockMarket.stocks) {
            if (stock.getName().equals(name)) {
                resultStock = stock;
                break;
            }
        }
        return resultStock;
    }
}
