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

    private void drawList() {
        drawList(localClients);
    }

    private void drawList(ClientList cs) {
        ClientNode node = cs.getFirst();
        DefaultListModel model = (DefaultListModel) clientList.getModel();
        model.clear();
        while (node != null) {
            Client c = node.getData();
            model.addElement(c.getName() + ' ' + c.getSurname() + '-' + c.getEmailAddress());
            node = node.getNext();
        }
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
        clientNameLabel = new javax.swing.JLabel();
        clientNameTextField = new javax.swing.JTextField();
        ordersLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        editOrders = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientList = new javax.swing.JList<>();
        foundClientsLabel = new javax.swing.JLabel();
        datePerformedDatePicker = new org.jdesktop.swingx.JXDatePicker();

        titleLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Add Sale");
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        clientNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clientNameLabel.setText("Client Name:");

        clientNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientNameTextFieldActionPerformed(evt);
            }
        });

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

        foundClientsLabel.setText("Found Clients");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ordersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clientNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clientNameTextField)
                    .addComponent(datePerformedDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(284, 284, 284))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(foundClientsLabel)
                        .addGap(214, 214, 214))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(foundClientsLabel)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clientNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ordersLabel)
                            .addComponent(editOrders))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datePerformedDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(121, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
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
        this.window.setContentPane(new EditOrder(window, orders, "addsale"));
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

    private void clientNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientNameTextFieldActionPerformed
        String text = clientNameTextField.getText();
        if (text == null || text.isBlank() || text.isEmpty()) {
            drawList(clients);
            return;
        }

        localClients = clients.getByNameSearch(text);
        ClientNode node = localClients.getFirst();
        int i = 0;
        while (node != null) {
            ids[i] = node.getData().getID();
            node = node.getNext();
            i++;
        }
        if (i == 0) { // Empty list
            if (showYesNoDialog(this, "Client has not been found!\n Would you like to add them?\n(This will keep the added orders)") == YES_OPTION) {
                this.window.setContentPane(new AddClient(window, text, orders));
                this.window.pack();
            }
            return;
        }
        drawList();
        if (i == 1) {
            clientList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_clientNameTextFieldActionPerformed

    private Client getSelectedClient() {
        int selected = clientList.getSelectedIndex();
        if (selected == -1)
            return null;

        return clients.getByID(ids[selected]);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JList<String> clientList;
    private javax.swing.JLabel clientNameLabel;
    private javax.swing.JTextField clientNameTextField;
    private javax.swing.JLabel dateLabel;
    private org.jdesktop.swingx.JXDatePicker datePerformedDatePicker;
    private javax.swing.JButton editOrders;
    private javax.swing.JLabel foundClientsLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel ordersLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
    // Wrong
    private ClientList localClients;
    private int[] ids = new int[clients.size()];
    // Actual end of variable declaration
}
