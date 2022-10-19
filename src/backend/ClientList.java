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

    public ClientList() {
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

    public boolean insert(Client client) {
        if (first == null) {
            first = new ClientNode(client);
            didChange = true;
            return true;
        }

        var aux = first;
        while (aux.getNext() != null) {
            if (aux.getData().equals(client)) {
                return false;
            }
            aux = aux.getNext();
        }

        if (aux.getData().equals(client)) {
            return false;
        } else {
            aux.setNext(new ClientNode(client));
            didChange = true;
            return true;
        }
    }

    public ClientList getByName(String clientName) {
        var ret = new ClientList();
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
}