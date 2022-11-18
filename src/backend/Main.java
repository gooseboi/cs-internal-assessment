/*
Copyright (c) 2022 Guzman Zugnoni <gooseiman@protonmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package backend;

import java.awt.Component;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author chonk
 */
public class Main {

    public static JFrame mainWindow;

    public static String stocksFile = "stocks";
    public static String clientsFile = "clients";
    public static String salesFile = "sales";
    public static String buyOrdersFile = "buy_orders";
    public static SalesList sales = new SalesList(true);
    public static BuyOrderList buyOrders = new BuyOrderList(true);
    public static StockList stocks = new StockList(true);
    public static ClientList clients = new ClientList(true);

    public static int GLOBAL_SALE_ID = 0;
    public static int GLOBAL_BUYORDER_ID = 0;
    public static int GLOBAL_CLIENT_ID = 0;

    public static boolean didChange = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create the main menu window
        mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setContentPane(new frontend.MainMenu(mainWindow));
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

        var saver = new Saver();
        try {
            var list = saver.readStocks(stocksFile);
            if (list != null) {
                stocks = new StockList(true, list);
            }
        } catch (IOException e) {
            showErrorDialog(mainWindow, "Error reading stock file!");
        }

        try {
            var list = saver.readClients(clientsFile);
            if (list != null) {
                clients = new ClientList(true, list);
            }
        } catch (IOException e) {
            showErrorDialog(mainWindow, "Error reading clients file!");
        }

        if (!stocks.empty() && !clients.empty()) {
            try {
                var list = saver.readSales(salesFile);
                if (list != null) {
                    sales = new SalesList(true, list);
                }
            } catch (IOException e) {
                showErrorDialog(mainWindow, "Error reading sales file!");
            }

            try {
                var list = saver.readBuyOrders(buyOrdersFile);
                if (list != null) {
                    buyOrders = new BuyOrderList(true, list);
                }
            } catch (IOException e) {
                showErrorDialog(mainWindow, "Error reading buy orders file!");
            }
        } else {
            showErrorDialog(mainWindow, "Failed reading stocks and clients file,\nso sales and buy orders cannot be read!");
        }

        mainWindow.addWindowListener(saver);
    }

    public static void showInformationDialog(Component window, String message) {
        JOptionPane.showMessageDialog(window, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showErrorDialog(Component window, String message) {
        JOptionPane.showMessageDialog(window, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static int showYesNoDialog(Component window, String message) {
        return JOptionPane.showConfirmDialog(window, message, "CONFIRM", JOptionPane.YES_NO_OPTION);
    }

    public static int showYesNoCancelDialog(Component window, String message) {
        return JOptionPane.showConfirmDialog(window, message, "CONFIRM", JOptionPane.YES_NO_CANCEL_OPTION);
    }
}
