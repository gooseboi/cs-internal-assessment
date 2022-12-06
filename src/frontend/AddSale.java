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
package frontend;

import backend.Client;
import backend.ClientList;
import backend.ClientNode;
import static backend.Main.sales;
import static backend.Main.clients;
import javax.swing.JFrame;
import backend.OrderList;
import backend.Sale;
import java.util.Date;
import javax.swing.DefaultListModel;
import static backend.Main.showErrorDialog;
import static backend.Main.showInformationDialog;
import static backend.Main.showYesNoDialog;
import static backend.Main.stocks;
import backend.OrderNode;
import backend.Plant;
import backend.Stock;
import static javax.swing.JOptionPane.YES_OPTION;

/**
 *
 * @author chonk
 */
public class AddSale extends javax.swing.JPanel {

    private final JFrame window;
    private final OrderList orders;

    /**
     * Creates new form DeleteSale
     */
    public AddSale(JFrame window) {
        initComponents();
        this.window = window;
        this.orders = new OrderList();
        drawList(clients);
    }

    public AddSale(JFrame window, OrderList orders) {
        initComponents();
        this.window = window;
        this.orders = orders;
        drawList(clients);
    }

    public AddSale(JFrame window, OrderList orders, Date date) {
        initComponents();
        this.window = window;
        this.orders = orders;
        if (date != null) {
            datePerformedDatePicker.setDate(date);
        }
        drawList(clients);
    }

    private void drawList() {
        drawList(localClients);
    }

    private void drawList(ClientList cs) {
        ClientNode node = cs.getFirst();
        DefaultListModel model = (DefaultListModel) clientList.getModel();
        model.clear();
        int i = 0;
        while (node != null) {
            Client c = node.getData();
            model.addElement(c.getName() + ' ' + c.getSurname() + '-' + c.getEmailAddress());
            ids[i] = node.getData().getID();
            node = node.getNext();
            i++;
        }
        size = i;

        clientList.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        ordersLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        editOrders = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientList = new javax.swing.JList<>();
        clientSearchLabel = new javax.swing.JLabel();
        datePerformedDatePicker = new org.jdesktop.swingx.JXDatePicker();
        clientSearchTextField = new javax.swing.JTextField();

        titleLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Add Sale");
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        ordersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordersLabel.setText("Orders:");

        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLabel.setText("Date:");

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        editOrders.setText("Edit...");
        editOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editOrdersActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        clientList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        clientList.setModel(new DefaultListModel());
        jScrollPane1.setViewportView(clientList);

        clientSearchLabel.setText("Search for a client:");

        clientSearchTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                clientSearchTextFieldCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datePerformedDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ordersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(clientSearchLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(clientSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(155, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ordersLabel)
                    .addComponent(editOrders)
                    .addComponent(clientSearchLabel)
                    .addComponent(clientSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datePerformedDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(saveButton))
                .addGap(41, 41, 41))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.window.setContentPane(new ManageSales(window));
        this.window.pack();
    }//GEN-LAST:event_backButtonActionPerformed

    private void editOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editOrdersActionPerformed
        Date date = datePerformedDatePicker.getDate();
        this.window.setContentPane(new EditOrder(window, orders, date, "addsale"));
        this.window.pack();
    }//GEN-LAST:event_editOrdersActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        Client c = getSelectedClient();
        if (c == null) {
            showErrorDialog(this, "You must select a client to associate the sale with!");
            return;
        }

        if (orders.size() == 0) {
            showErrorDialog(this, "You must add some orders!");
            return;
        }

        Date d = datePerformedDatePicker.getDate();
        if (d == null) {
            showErrorDialog(this, "You must select a date for the sale!");
            return;
        } else if (d.after(new Date())) {
            showErrorDialog(this, "Date cannot be in the future!");
            return;
        }

        Sale sale = new Sale(orders, c, d);
        if (sales.insert(sale)) {
            showInformationDialog(this, "Sale successfully added!");
        } else {
            showErrorDialog(this, "Sale could not be added");
        }

        OrderNode node = orders.getFirst();
        while (node != null) {
            Plant p = node.getData().getPlant();
            Stock s = stocks.getStockByPlant(p);
            int currStock = s.getStock();
            int num = node.getData().getNum();
            if (currStock < num) {
                currStock = 0;
            } else {
                currStock -= num;
            }
            s.setStock(currStock);
            stocks.modify(s);
            node = node.getNext();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void clientSearchTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_clientSearchTextFieldCaretUpdate
        String text = clientSearchTextField.getText();
        if (text == null || text.isBlank() || text.isEmpty()) {
            drawList(clients);
            return;
        }

        localClients = clients.searchAllFields(text);
        size = localClients.size();
        if (size == 0) { // Empty list
            if (showYesNoDialog(this, "Client has not been found!\n Would you like to add them?\n(This will keep the added orders)") == YES_OPTION) {
                this.window.setContentPane(new AddClient(window, text, orders));
                this.window.pack();
            }
            return;
        }
        drawList();
        if (size == 1) {
            clientList.setSelectedIndex(0);
        }
}//GEN-LAST:event_clientSearchTextFieldCaretUpdate

    private Client getSelectedClient() {
        int selected = clientList.getSelectedIndex();
        if (selected == -1)
            return null;

        return clients.getByID(ids[selected]);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JList<String> clientList;
    private javax.swing.JLabel clientSearchLabel;
    private javax.swing.JTextField clientSearchTextField;
    private javax.swing.JLabel dateLabel;
    private org.jdesktop.swingx.JXDatePicker datePerformedDatePicker;
    private javax.swing.JButton editOrders;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel ordersLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
    // Wrong
    private ClientList localClients;
    int size;
    private int[] ids = new int[clients.size()];
    // Actual end of variable declaration
}
