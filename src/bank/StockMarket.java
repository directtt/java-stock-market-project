package bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class StockMarket extends Thread  {

    public ArrayList<Stock> stocks;
    boolean isRunning;

    public StockMarket(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (Stock stock : stocks) {
                    stock.priceHistory.add(stock.getPrice());
                    stock.changePrice();
                }

                Thread.sleep(200);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Stock getStockName(String name) {
        Stock resultStock = null;

        for (Stock stock : stocks) {
            if (stock.getName().equals(name)) {
                resultStock = stock;
                break;
            }
        }
        return resultStock;
    }

    public void readStocks() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("D:/Java Projects/UAM-PO/src/bank/stockLists.txt"));

            String line;
            while ( (line = in.readLine()) != null) {
                String vals[] = line.split(",");
                String name = vals[0];
                float price = Float.parseFloat(vals[1]);

                Stock stock = new Stock(name, price, new ArrayList<Float>());
                stocks.add(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}