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
public class SalesList {

    private SalesNode first;
    private boolean isGlobal;

    public SalesList(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public SalesList(boolean isGlobal, SalesList list) {
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

    public SalesList getSalesByClient(Client c) {
        SalesList ret = new SalesList(false);
        SalesNode aux = first;
        while (aux != null) {
            if (aux.getData().getClient().equals(c)) {
                ret.insert(aux.getData());
            }
            aux = aux.getNext();
        }
        return ret;
    }

    public SalesNode getFirst() {
        return this.first;
    }

    public SalesList getSalesByDate(Date date) {
        return this.getSalesByDateRange(date, date);
    }

    public SalesList getSalesByDateRange(Date start, Date end) {
        SalesList ret = new SalesList(false);
        SalesNode aux = first;
        while (aux != null) {
            Date date = aux.getData().getDate();
            if (date.before(end) && date.after(start)) {
                ret.insert(aux.getData());
            }
            aux = aux.getNext();
        }
        return ret;
    }

    public SalesList getSalesByPlant(Plant p) {
        SalesList ret = new SalesList(false);
        SalesNode aux = first;
        while (aux != null) {
            if (aux.getData().getOrders().contains(p)) {
                ret.insert(aux.getData());
            }
            aux = aux.getNext();
        }
        return ret;
    }

    public Sale getByID(int id) {
        var aux = first;
        while (aux != null) {
            var s = aux.getData();
            if (s.equals(id)) {
                return s;
            }
            aux = aux.getNext();
        }
        return null;
    }

    public int size() {
        SalesNode aux = first;
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

    public boolean insert(Sale sale) {
        if (first == null) {
            first = new SalesNode(sale);
            if (isGlobal)
                didChange = true;
            return true;
        }

        var aux = first;
        while (aux.getNext() != null) {
            if (aux.getData().equals(sale))
                return false;
            aux = aux.getNext();
        }

        if (aux.getData().equals(sale)) {
            return false;
        } else {
            aux.setNext(new SalesNode(sale));
            if (isGlobal)
                didChange = true;
            return true;
        }
    }

    public boolean delete(Sale sale) {
        return this.delete(sale.getId());
    }

    public boolean delete(int id) {
        if (first == null)
            return false;
        else if (first.getData().equals(id)) {
            first = first.getNext();
            didChange = true;
            return true;
        }

        SalesNode aux = first;
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

    public boolean exists(int id) {
        SalesNode aux = first;
        while (aux != null) {
            if (aux.getData().getId() == id) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean modify(Sale sale) {
        SalesNode aux = first;
        while (aux != null) {
            if (aux.getData().equals(sale)) {
                aux.setData(sale);
                didChange = true;
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public void clear() {
        this.first = null;
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
    public SalesList clone() {
        var ret = new SalesList(false);
        if (first == null) {
            return ret;
        }

        ret.first = first.clone();
        var node = first.getNext();
        var ret_node = ret.first;
        while (node != null) {
            ret_node.setNext(node.clone());
            node = node.getNext();
            ret_node = ret_node.getNext();
        }
        return ret;
    }
}
