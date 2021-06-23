package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BloombergTerminal extends Thread implements ActionListener  { // ta klasa printuje

    boolean isRunning;

    public void printMarketLive(StockMarket stockMarket) {

        JFrame frame = new JFrame();
        JButton button = new JButton("Exit Terminal");
        button.setFont(new Font("Courier New", Font.BOLD, 35));
        button.setFocusPainted(false);
        button.addActionListener(this);

        frame.getContentPane().add(button);
        frame.setSize(330, 130);
        frame.setVisible(true);

        isRunning = true;

        while (isRunning) {

            for (int i = 0; i < 20; i++) {
                System.out.println("");
            }

            System.out.println("===================================");
            System.out.println("Name      Price");

            for (Stock stock : stockMarket.stocks) {
                System.out.println(stock.getName() + "    $" + stock.getPrice());
            }
            System.out.println("===================================");



            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        frame.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isRunning = false;
    }
}
