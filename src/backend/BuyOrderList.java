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
public class BuyOrderList {

    private BuyOrderNode first;

    public BuyOrderList getOrdersByClient(Client c) {
        BuyOrderList ret = new BuyOrderList();
        BuyOrderNode aux = first;
        while (aux != null) {
            if (aux.getData().getClient().equals(c)) {
                ret.insert(aux.getData());
            }
            aux = aux.getNext();
        }
        return ret;
    }

    public BuyOrderNode getFirst() {
        return this.first;
    }

    public BuyOrderList getOrdersByDate(Date date) {
        return this.getOrdersByDateRange(date, date);
    }

    public BuyOrderList getOrdersByDateRange(Date start, Date end) {
        BuyOrderList ret = new BuyOrderList();
        BuyOrderNode aux = first;
        while (aux != null) {
            Date date = aux.getData().getDate();
            if (date.before(end) && date.after(start)) {
                ret.insert(aux.getData());
            }
            aux = aux.getNext();
        }
        return ret;
    }

    public OrderList getOrdersByPlant(Plant p) {
        OrderList ret = new OrderList();
        BuyOrderNode aux = first;
        while (aux != null) {
            Order found = aux.getData().getOrders().find(p);
            if (found != null) {
                ret.insert(found);
            }
            aux = aux.getNext();
        }
        return ret;
    }

    public int size() {
        BuyOrderNode aux = first;
        int counter = 0;
        while (aux != null) {
            counter++;
            aux = aux.getNext();
        }
        return counter;
    }

    public boolean insert(BuyOrder order) {
        if (first == null) {
            first = new BuyOrderNode(order);
            return true;
        }

        BuyOrderNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getData().equals(order))
                return false;
            aux = aux.getNext();
        }
        aux.setNext(new BuyOrderNode(order));
        return true;
    }

    public boolean delete(BuyOrder order) {
        return this.delete(order.getId());
    }

    public boolean delete(int id) {
        if (first == null)
            return false;

        BuyOrderNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getNext().getData().equals(id)) {
                aux.setNext(aux.getNext().getNext());
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean exists(int id) {
        BuyOrderNode aux = first;
        while (aux != null) {
            if (aux.getData().getId() == id) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public void clear() {
        this.first = null;
    }
}
