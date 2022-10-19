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

import static backend.Main.didChange;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

/**
 *
 * @author chonk
 */
public class StockList {

    private StockNode first;
    private boolean isGlobal;

    public StockList(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public StockList(Stock[] arr) {
        if (arr.length > 0 && arr[0] != null) {
            this.first = new StockNode(arr[0]);
        } else {
            return;
        }

        var node = this.first;
        for (int i = 1; i < arr.length; i++) {
            node.setNext(new StockNode(arr[i]));
            node = node.getNext();
        }
    }

    public StockList getStockByAvailable(int stock) {
        StockList ret = new StockList(false);
        StockNode aux = first;
        while (aux != null) {
            if (aux.getData().getStock() == stock) {
                ret.insert(aux.getData());
            }
            aux = aux.getNext();
        }
        return ret;
    }

    public StockList getStockByName(String plantName) {
        StockList ret = new StockList(false);
        StockNode aux = first;
        while (aux != null) {
            var p = aux.getData().getPlant();
            if (p.getName().toUpperCase().startsWith(plantName.toUpperCase())) {
                ret.insert(aux.getData());
            }
            aux = aux.getNext();
        }
        return ret;
    }

    public int size() {
        StockNode aux = first;
        int counter = 0;
        while (aux != null) {
            counter++;
            aux = aux.getNext();
        }
        return counter;
    }

    public StockNode getFirst() {
        return first;
    }

    public boolean insert(Stock stock) {
        if (first == null) {
            first = new StockNode(stock);
            if (isGlobal)
                didChange = true;
            return true;
        }

        StockNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getData().equals(stock))
                return false;
            aux = aux.getNext();
        }

        if (aux.getData().equals(stock)) {
            return false;
        } else {
            aux.setNext(new StockNode(stock));
            if (isGlobal)
                didChange = true;
            return true;
        }
    }

    public boolean delete(Stock stock) {
        if (first == null)
            return false;
        else if (first.getData().equals(stock)) {
            first = first.getNext();
            didChange = true;
            return true;
        }

        StockNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getNext().getData().equals(stock)) {
                aux.setNext(aux.getNext().getNext());
                didChange = true;
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean delete(String plantName) {
        if (first == null)
            return false;
        else if (first.getData().equals(plantName)) {
            first = first.getNext();
            didChange = true;
            return true;
        }

        StockNode aux = first;
        while (aux.getNext() != null) {
            if (aux.getNext().getData().equals(plantName)) {
                aux.setNext(aux.getNext().getNext());
                didChange = true;
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean delete(int idx) {
        if (first == null)
            return false;
        if (idx == 0) {
            first = first.getNext();
            didChange = true;
            return true;
        }

        StockNode aux = first;
        int i = 0;
        while (aux.getNext() != null && i != idx - 1) {
            aux = aux.getNext();
            i++;
        }
        if (i == idx - 1) {
            if (aux.getNext() != null) {
                aux.setNext(aux.getNext().getNext());
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean exists(Stock stock) {
        StockNode aux = first;
        while (aux != null) {
            if (aux.getData().equals(stock)) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean modify(Stock stock) {
        StockNode aux = first;
        while (aux != null) {
            if (aux.getData().equals(stock)) {
                aux.setData(stock);
                didChange = true;
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public Plant findPlant(String plantName) {
        StockNode aux = first;
        while (aux != null) {
            Plant p = aux.getData().getPlant();
            if (p.equals(plantName)) {
                return p;
            }
            aux = aux.getNext();
        }
        return null;
    }

    @Override
    public StockList clone() {
        var ret = new StockList(false);
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

    public Stock[] toArray() {
        var node = first;
        int s = this.size();
        Stock[] ret = new Stock[s];
        int top = 0;
        while (node != null) {
            ret[top++] = node.getData();
            node = node.getNext();
        }
        return ret;
    }

    public void sortByName(boolean orderDescending) {
        BiPredicate<Stock, Stock> pred;
        if (orderDescending) {
            pred = (s1, s2) -> {
                var n1 = s1.getPlant().getName();
                var n2 = s2.getPlant().getName();
                boolean res = n1.compareTo(n2) > 0;
                return res;
            };
        } else {
            pred = (s1, s2) -> {
                var n1 = s1.getPlant().getName();
                var n2 = s2.getPlant().getName();
                boolean res = n1.compareTo(n2) < 0;
                return res;
            };
        }
        this.sortBy(pred);
    }

    public void sortByPrice(boolean orderDescending) {
        BiPredicate<Stock, Stock> pred;
        if (orderDescending) {
            pred = (s1, s2) -> {
                var p1 = s1.getPlant().getPrice();
                var p2 = s2.getPlant().getPrice();
                return p1 > p2;
            };
        } else {
            pred = (s1, s2) -> {
                var p1 = s1.getPlant().getPrice();
                var p2 = s2.getPlant().getPrice();
                return p1 < p2;
            };
        }
        this.sortBy(pred);
    }

    public void sortByAvailable(boolean orderDescending) {
        BiPredicate<Stock, Stock> pred;
        if (orderDescending) {
            pred = (s1, s2) -> {
                var n1 = s1.getStock();
                var n2 = s2.getStock();
                return n1 > n2;
            };
        } else {
            pred = (s1, s2) -> {
                var n1 = s1.getPlant().getPrice();
                var n2 = s2.getPlant().getPrice();
                return n1 < n2;
            };
        }
        this.sortBy(pred);
    }

    public void sortByBuyOrders(boolean orderDescending) {
        throw new UnsupportedOperationException("sortByBuyOrders");
    }

    public void sortBy(BiPredicate<Stock, Stock> pred) {
        Stock[] arr = this.toArray();
        if (arr.length == 0) {
            return;
        }

        BiConsumer<Integer, Integer> swap = (Integer i, Integer j) -> {
            var temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        };

        boolean swapped = true;
        while (swapped) {
            int i = 0;
            swapped = false;
            while (i != arr.length - 1) {
                if (pred.test(arr[i], arr[i + 1])) {
                    swap.accept(i, i + 1);
                    swapped = true;
                }
                i++;
            }
        }

        StockList temp = new StockList(arr);
        this.first = temp.first;
    }

}
