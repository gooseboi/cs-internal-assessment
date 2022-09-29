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

/**
 *
 * @author chonk
 */
public class OrderList {

    private OrderNode first;

    public int size() {
        OrderNode aux = first;
        int counter = 0;
        while (aux != null) {
            counter++;
            aux = aux.getNext();
        }
        return counter;
    }

    public boolean contains(Plant p) {
        int l = this.size();
        if (l == 0)
            return false;
        OrderNode aux = first;
        while (aux != null) {
            if (aux.getData().getPlant().equals(p)) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean insert(Order order) {
        if (first == null) {
            first = new OrderNode(order);
            return true;
        }

        OrderNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getData().equals(order))
                return false;
            aux = aux.getNext();
        }
        aux.setNext(new OrderNode(order));
        return true;
    }

    public void merge(Order order) {
        if (first == null) {
            first = new OrderNode(order);
            return;
        }

        OrderNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getData().equals(order)) {
                int num = order.getNum() + 1;
                aux.getData().setNum(num);
                order.setNum(num);
            }
            aux = aux.getNext();
        }
        aux.setNext(new OrderNode(order));
    }

    public boolean delete(Order order) {
        if (first == null)
            return false;

        OrderNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getNext().getData().equals(order)) {
                aux.setNext(aux.getNext().getNext());
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean delete(Plant p) {
        if (first == null)
            return false;

        OrderNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getNext().getData().equals(p)) {
                aux.setNext(aux.getNext().getNext());
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean exists(Order order) {
        OrderNode aux = first;
        while (aux != null) {
            if (aux.getData().equals(order)) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean exists(Plant p) {
        OrderNode aux = first;
        while (aux != null) {
            if (aux.getData().equals(p)) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean modify(Order order) {
        OrderNode aux = first;
        while (aux != null) {
            if (aux.getData().equals(order)) {
                aux.setData(order);
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }
}
