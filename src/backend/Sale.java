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

import static backend.Main.GLOBAL_SALE_ID;
import java.util.Date;

/**
 *
 * @author chonk
 */
public class Sale {

    private OrderList orders;
    private Client client;
    private Date date;
    private int id;

    public Sale(OrderList orders, Client client, Date date, int id) {
        this(orders, client, date);
        this.id = id;
    }

    public Sale(OrderList orders, Client client, Date date) {
        this.orders = orders;
        this.client = client;
        this.date = date;
        this.id = GLOBAL_SALE_ID++;
    }

    public boolean equals(Sale sale) {
        return this.id == sale.id;
    }

    public boolean equals(int id) {
        return this.id == id;
    }

    public OrderList getOrders() {
        return orders;
    }

    public void setOrders(OrderList orders) {
        this.orders = orders;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
