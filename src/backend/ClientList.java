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

/**
 *
 * @author chonk
 */
public class ClientList {

    private ClientNode first;
    private boolean isGlobal;

    public ClientList(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public ClientList(boolean isGlobal, ClientList list) {
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

    public ClientNode getFirst() {
        return first;
    }

    public int size() {
        var aux = first;
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

    public boolean insert(Client client) {
        if (this.contains(client)) {
            return false;
        }

        if (first == null) {
            first = new ClientNode(client);
            if (isGlobal)
                didChange = true;
            return true;
        }

        var aux = first;
        while (aux.getNext() != null) {
            if (aux.getData().all_equal(client)) {
                return false;
            }
            aux = aux.getNext();
        }

        if (aux.getData().equals(client)) {
            return false;
        } else {
            aux.setNext(new ClientNode(client));
            if (isGlobal)
                didChange = true;
            return true;
        }
    }

    public boolean delete(int id) {
        if (first == null)
            return false;
        else if (first.getData().equals(id)) {
            first = first.getNext();
            didChange = true;
            return true;
        }

        var aux = first;
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

    public boolean contains(Client c) {
        var node = this.first;
        while (node != null) {
            var d = node.getData();
            if (d.all_equal(c)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    public ClientList getByName(String clientName) {
        var ret = new ClientList(false);
        var node = first;
        while (node != null) {
            var c = node.getData();
            if (c.equals(clientName)) {
                ret.insert(c);
            }
            node = node.getNext();
        }
        return ret;
    }

    public ClientList getByNameSearch(String start) {
        var ret = new ClientList(false);
        var node = first;
        while (node != null) {
            var c = node.getData();
            if (c.getName().startsWith(start) || c.getSurname().startsWith(start)) {
                ret.insert(c);
            }
            node = node.getNext();
        }
        return ret;
    }

    public Client getByID(int id) {
        var node = first;
        while (node != null) {
            var c = node.getData();
            if (c.equals(id)) {
                return c;
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    public ClientList clone() {
        var ret = new ClientList(false);
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
