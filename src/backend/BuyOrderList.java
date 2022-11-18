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
import static backend.Main.didChange;

/**
 *
 * @author chonk
 */
public class BuyOrderList {

    private BuyOrderNode first;
    private boolean isGlobal;

    public BuyOrderList(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public BuyOrderList(boolean isGlobal, BuyOrderList list) {
        this.isGlobal = isGlobal;

        if (list.first == null) {
            return;
        }

        this.first = list.first.clone();
        var node = this.first;
        var list_node = list.first.getNext();
        while (list_node != null) {
            node.setNext(list_node.clone());
            node = node.getNext();
            list_node = list_node.getNext();
        }
    }

    public BuyOrderList getOrdersByClient(Client c) {
        BuyOrderList ret = new BuyOrderList(false);
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
        BuyOrderList ret = new BuyOrderList(false);
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

    public BuyOrder getByID(int id) {
        var aux = first;
        while (aux != null) {
            var bo = aux.getData();
            if (bo.getId() == id) {
                return bo;
            }
            aux = aux.getNext();
        }
        return null;
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

    public boolean empty() {
        return this.first == null;
    }

    public boolean insert(BuyOrder order) {
        if (first == null) {
            first = new BuyOrderNode(order);
            if (isGlobal)
                didChange = true;
            return true;
        }

        BuyOrderNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getData().equals(order))
                return false;
            aux = aux.getNext();
        }

        if (aux.getData().equals(order)) {
            return false;
        } else {
            aux.setNext(new BuyOrderNode(order));
            if (isGlobal)
                didChange = true;
            return true;
        }
    }

    public boolean delete(BuyOrder order) {
        return this.delete(order.getId());
    }

    public boolean delete(int id) {
        if (first == null)
            return false;
        else if (first.getData().equals(id)) {
            first = first.getNext();
            didChange = true;
            return true;
        }

        BuyOrderNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getNext().getData().equals(id)) {
                aux.setNext(aux.getNext().getNext());
                didChange = true;
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean modify(BuyOrder buyOrder) {
        var aux = first;
        while (aux != null) {
            if (aux.getData().equals(buyOrder)) {
                aux.setData(buyOrder);
                didChange = true;
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
                didChange = true;
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public void clear() {
        this.first = null;
        didChange = true;
    }

    public ClientList findClient(String clientName) {
        var aux = first;
        ClientList ret = new ClientList(false);
        while (aux != null) {
            var c = aux.getData().getClient();
            if (c.equals(clientName))
                ret.insert(c);
            aux = aux.getNext();
        }
        return ret;
    }

    @Override
    public BuyOrderList clone() {
        var ret = new BuyOrderList(false);
        var node = first;
        if (node == null) {
            return ret;
        }

        var ret_node = ret.first;
        ret_node = new BuyOrderNode(node.getData());
        while (node != null) {
            ret_node.setNext(node.clone());
        }
        return ret;
    }
}
