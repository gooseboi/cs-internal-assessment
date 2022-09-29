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
public class StockList {

    StockNode first;

    public StockList getStockByAvailable(int stock) {
        StockList ret = new StockList();
        StockNode aux = first;
        while (aux != null) {
            if (aux.data.stock == stock) {
                ret.insert(aux.data);
            }
            aux = aux.next;
        }
        return ret;
    }

    public int size() {
        StockNode aux = first;
        int counter = 0;
        while (aux != null) {
            counter++;
            aux = aux.next;
        }
        return counter;
    }

    public boolean insert(Stock stock) {
        if (first == null) {
            first = new StockNode(stock);
            return true;
        }

        StockNode aux = first;
        while (aux.next != null) {
            if (aux.data.equals(stock))
                return false;
            aux = aux.next;
        }
        aux.next = new StockNode(stock);
        return true;
    }

    public boolean delete(Stock stock) {
        if (first == null)
            return false;

        StockNode aux = first;
        while (aux.next != null) {
            if (aux.next.data.equals(stock)) {
                aux.next = aux.next.next;
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public boolean exists(Stock stock) {
        StockNode aux = first;
        while (aux != null) {
            if (aux.data.equals(stock)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }
    
    public boolean modify(Stock stock) {
        StockNode aux = first;
        while (aux != null) {
            if (aux.data.equals(stock)) {
                aux.data = stock;
                return true;
            }
            aux = aux.next;
        }
        return false;
    }
}
