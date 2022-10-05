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

import javax.swing.JFrame;

/**
 * @author chonk
 */
public class Main {

    public static JFrame mainWindow;
    public static SalesList sales = new SalesList();
    public static BuyOrderList buyOrders = new BuyOrderList();
    public static StockList stocks = new StockList();

    public static int GLOBAL_SALE_ID = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create the main menu window
        mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setContentPane(new frontend.MainMenu(mainWindow));
        mainWindow.setSize(700, 500);
        mainWindow.setVisible(true);
    }
}
