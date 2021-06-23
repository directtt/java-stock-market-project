package bank;

import java.util.ArrayList;
import java.util.Random;

public class Stock {

    private String name;
    private float price;
    public ArrayList<Float> priceHistory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Stock(String name, float price, ArrayList<Float> priceHistory) {
        this.name = name;
        this.price = price;
        this.priceHistory = priceHistory;
    }

    public void changePrice() {
        Random random = new Random();
        float randomValue = random.nextFloat();
        randomValue = (float) (randomValue * 0.01 * price); // zmienność od 1% aktualnej ceny akcji

        boolean sign = random.nextBoolean();

        if (sign) {
            price += randomValue;
        }
        else {
            price -= randomValue;
        }
    }
}
