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

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import static backend.Main.didChange;
import static backend.Main.stocks;
import static backend.Main.sales;
import static backend.Main.buyOrders;
import static backend.Main.clients;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static backend.Main.showYesNoCancelDialog;

/**
 *
 * @author chonk
 */
public class Saver implements WindowListener {

    public StockList readStocks(String filePath) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            var list = new StockList(false);
            var line = reader.readLine();
            while (line != null) {
                String[] vals = line.split(",");
                String name = vals[0];
                String growthConditions = vals[1];
                double price = Double.parseDouble(vals[2]);
                int available = Integer.parseInt(vals[3]);
                var stock = new Stock(new Plant(name, price, growthConditions), available);
                list.insert(stock);
                line = reader.readLine();
            }
            return list;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public ClientList readClients(String filePath) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            var list = new ClientList(false);
            var line = reader.readLine();
            while (line != null) {
                String[] vals = line.split(",");
                int id = Integer.parseInt(vals[0]);
                String name = vals[1];
                String surname = vals[2];
                String emailAddress = vals[3];
                String phoneNumber = vals[4];
                var client = new Client(name, surname, phoneNumber, emailAddress, id);
                list.insert(client);
                line = reader.readLine();
            }
            return list;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public SalesList readSales(String filePath) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            var list = new SalesList(false);
            var formatter = new SimpleDateFormat("d/M/y");

            var line = reader.readLine();
            while (line != null) {
                String[] vals = line.split(",");
                int clientID = Integer.parseInt(vals[0]);
                var client = clients.getByID(clientID);
                var orders = readOrder(vals[1]);
                var date = formatter.parse(vals[2]);
                var sale = new Sale(orders, client, date);
                list.insert(sale);
                line = reader.readLine();
            }
            return list;
        } catch (FileNotFoundException | ParseException e) {
            return null;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public BuyOrderList readBuyOrders(String filePath) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            var list = new BuyOrderList(false);
            var formatter = new SimpleDateFormat("d/M/y");

            var line = reader.readLine();
            while (line != null) {
                String[] vals = line.split(",");
                int clientID = Integer.parseInt(vals[0]);
                var client = clients.getByID(clientID);
                var orders = readOrder(vals[1]);
                var date = formatter.parse(vals[2]);
                var buyOrder = new BuyOrder(client, date, orders);
                list.insert(buyOrder);
                line = reader.readLine();
            }
            return list;
        } catch (FileNotFoundException | ParseException e) {
            return null;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public OrderList readOrder(String order) {
        String[] o = order.split("-");
        OrderList orders = new OrderList();
        for (int i = 0; i < o.length; i++) {
            String[] split = o[i].split(":");
            String name = split[0];
            int num = Integer.parseInt(split[1]);
            var p = stocks.getPlantByName(name);
            orders.insert(new Order(p, num));
        }
        return orders;
    }

    public boolean saveStock(String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            var node = stocks.getFirst();
            while (node != null) {
                var stock = node.getData();
                var p = stock.getPlant();
                String outLine = String.format("%s,%s,%s,%s\n", p.getName(), p.getGrowthCondition(), p.getPrice(), stock.getStock());
                writer.write(outLine);
                node = node.getNext();
            }

            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private String saveOrder(Sale sale) {
        var orders = sale.getOrders();

        var node = orders.getFirst();
        String ret = "";
        boolean first = true;
        while (node != null) {
            var order = node.getData();
            var orderString = String.format("%s:%s", order.getPlant().getName(), order.getNum());
            if (first) {
                ret = orderString;
                first = false;
            } else {
                ret = String.format("%s-%s", ret, orderString);
            }
            node = node.getNext();
        }
        return ret;
    }

    private String saveOrder(BuyOrder buyOrder) {
        var orders = buyOrder.getOrders();

        var node = orders.getFirst();
        String ret = "";
        boolean first = true;
        while (node != null) {
            var order = node.getData();
            var orderString = String.format("%s:%s", order.getPlant().getName(), order.getNum());
            if (first) {
                ret = orderString;
                first = false;
            } else {
                ret = String.format("%s-%s", ret, orderString);
            }
            node = node.getNext();
        }
        return ret;
    }

    public boolean saveSales(String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            var node = sales.getFirst();
            var formatter = new SimpleDateFormat("d/M/y");
            while (node != null) {
                var sale = node.getData();
                var client = sale.getClient();
                String outLine = String.format("%d,%s,%s\n", client.getID(), saveOrder(sale), formatter.format(sale.getDate()));
                writer.write(outLine);
                node = node.getNext();
            }

            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean saveBuyOrders(String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            var node = buyOrders.getFirst();
            var formatter = new SimpleDateFormat("d/M/y");
            while (node != null) {
                var sale = node.getData();
                var client = sale.getClient();
                String outLine = String.format("%d,%s,%s\n", client.getID(), saveOrder(sale), formatter.format(sale.getDate()));
                System.out.println(outLine);
                writer.write(outLine);
                node = node.getNext();
            }

            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean saveClients(String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            var node = clients.getFirst();
            while (node != null) {
                var client = node.getData();
                String outLine = String.format("%d,%s,%s,%s,%s\n", client.getID(), client.getName(), client.getSurname(), client.getEmailAddress(), client.getPhoneNumber());
                writer.write(outLine);
                node = node.getNext();
            }

            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean save() {
        if (!saveStock(backend.Main.stocksFile)) {
            return false;
        }
        if (!saveSales(backend.Main.salesFile)) {
            return false;
        }
        if (!saveBuyOrders(backend.Main.buyOrdersFile)) {
            return false;
        }
        if (!saveClients(backend.Main.clientsFile)) {
            return false;
        }
        return true;
    }

    @Override
    public void windowClosing(WindowEvent we) {
        Window window = we.getWindow();
        if (!didChange) {
            window.dispose();
            return;
        }

        int res = JOptionPane.showConfirmDialog(window, "Unsaved changes detected!\nWould you like to save?", "INFO", JOptionPane.YES_NO_CANCEL_OPTION);
        switch (res) {
            case JOptionPane.YES_OPTION:
                boolean stop = false;
                while (!stop && !save()) {
                    int res2 = showYesNoCancelDialog(window, "Saving failed, would you like to try again?");
                    switch (res) {
                        case JOptionPane.YES_OPTION:
                            break;
                        case JOptionPane.NO_OPTION:
                            window.dispose();
                            stop = true;
                            break;
                        case JOptionPane.CANCEL_OPTION:
                            stop = true;
                            break;
                    }
                }
                break;
            case JOptionPane.NO_OPTION:
                window.dispose();
                break;
            case JOptionPane.CANCEL_OPTION:
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

}
