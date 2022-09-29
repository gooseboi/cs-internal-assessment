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

import java.util.Date;

/**
 *
 * @author chonk
 */
public class SalesList {

    public SalesNode first;

    public SalesList getSalesByClient(Client c) {
        SalesList ret = new SalesList();
        SalesNode aux = first;
        while (aux != null) {
            if (aux.data.client.equals(c)) {
                ret.insert(aux.data);
            }
            aux = aux.next;
        }
        return ret;
    }

    public SalesList getSalesByDate(Date date) {
        return this.getSalesByDateRange(date, date);
    }

    public SalesList getSalesByDateRange(Date start, Date end) {
        SalesList ret = new SalesList();
        SalesNode aux = first;
        while (aux != null) {
            Date date = aux.data.date;
            if (date.before(end) && date.after(start)) {
                ret.insert(aux.data);
            }
            aux = aux.next;
        }
        return ret;
    }

    public SalesList getSalesByPlant(Plant p) {
        SalesList ret = new SalesList();
        SalesNode aux = first;
        while (aux != null) {
            if (aux.data.orders.contains(p)) {
                ret.insert(aux.data);
            }
            aux = aux.next;
        }
        return ret;
    }

    public int size() {
        SalesNode aux = first;
        int counter = 0;
        while (aux != null) {
            counter++;
            aux = aux.next;
        }
        return counter;
    }

    public boolean insert(Sale sale) {
        if (first == null) {
            first = new SalesNode(sale);
            return true;
        }

        SalesNode aux = first;
        while (aux.next != null) {
            if (aux.data.equals(sale))
                return false;
            aux = aux.next;
        }
        aux.next = new SalesNode(sale);
        return true;
    }

    public boolean delete(Sale sale) {
        return this.delete(sale.id);
    }

    public boolean delete(int id) {
        if (first == null)
            return false;

        SalesNode aux = first;
        while (aux.next != null) {
            if (aux.next.data.equals(id)) {
                aux.next = aux.next.next;
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public boolean exists(int id) {
        SalesNode aux = first;
        while (aux != null) {
            if (aux.data.id == id) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public boolean modify(Sale sale) {
        SalesNode aux = first;
        while (aux != null) {
            if (aux.data.equals(sale)) {
                aux.data = sale;
                return true;
            }
            aux = aux.next;
        }
        return false;
    }
}
