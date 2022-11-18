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

import static backend.Main.buyOrders;
import static backend.Main.clients;
import static backend.Main.sales;
import static backend.Main.showErrorDialog;
import static backend.Main.showInformationDialog;
import static backend.Main.showYesNoDialog;
import frontend.tables.ClientsCellRenderer;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.NO_OPTION;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chonk
 */
public class ManageClients extends javax.swing.JPanel {

    private final JFrame window;

    /**
     * Creates new form ManageClients
     */
    public ManageClients(JFrame window) {
        initComponents();
        this.window = window;
        this.drawTable(DrawType.Both);
    }

    private void drawTable(DrawType t) {
        var node = clients.getFirst();
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        model.setRowCount(0);
        var formatter = new SimpleDateFormat("E d/M/y");
        int idx = 0;

        while (node != null) {
            var client = node.getData();

            if (t == DrawType.Sales || t == DrawType.Both) {
                var s = sales.getSalesByClient(client);
                var temps = s.getFirst();
                while (temps != null) {
                    Object[] curr = new Object[5];
                    var sale = temps.getData();
                    curr[0] = "Sale";
                    curr[1] = client.getName();
                    curr[2] = sale.getOrders().accumulatePrice();
                    curr[3] = sale.getOrders().accumulateStock();
                    curr[4] = formatter.format(sale.getDate());
                    model.addRow(curr);
                    temps = temps.getNext();
                    ids[idx] = sale.getId();
                    idx++;
                }
            }

            if (t == DrawType.BuyOrders || t == DrawType.Both) {
                var b = buyOrders.getOrdersByClient(client);
                var tempb = b.getFirst();
                while (tempb != null) {
                    Object[] curr = new Object[5];
                    var buyOrder = tempb.getData();
                    curr[0] = "Buy Order";
                    curr[1] = client.getName();
                    curr[2] = buyOrder.getOrders().accumulatePrice();
                    curr[3] = buyOrder.getOrders().accumulateStock();
                    curr[4] = formatter.format(buyOrder.getDate());
                    model.addRow(curr);
                    tempb = tempb.getNext();
                    ids[idx] = buyOrder.getId();
                    idx++;
                }
            }
            node = node.getNext();
        }

        clientsTable.setModel(model);
    }

    private void drawTable(DrawType t, String name) {
        var node = clients.getFirst();
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        model.setRowCount(0);
        var formatter = new SimpleDateFormat("E d/M/y");
        int idx = 0;

        while (node != null) {
            var client = node.getData();
            if (!client.getName().toUpperCase().startsWith(name.toUpperCase())) {
                node = node.getNext();
                continue;
            }

            if (t == DrawType.Sales || t == DrawType.Both) {
                var s = sales.getSalesByClient(client);
                var temps = s.getFirst();
                while (temps != null) {
                    Object[] curr = new Object[5];
                    var sale = temps.getData();
                    curr[0] = "Sale";
                    curr[1] = client.getName();
                    curr[2] = sale.getOrders().accumulatePrice();
                    curr[3] = sale.getOrders().accumulateStock();
                    curr[4] = formatter.format(sale.getDate());
                    model.addRow(curr);
                    temps = temps.getNext();
                    ids[idx] = sale.getId();
                    idx++;
                }
            }

            if (t == DrawType.BuyOrders || t == DrawType.Both) {
                var b = buyOrders.getOrdersByClient(client);
                var tempb = b.getFirst();
                while (tempb != null) {
                    Object[] curr = new Object[5];
                    var buyOrder = tempb.getData();
                    curr[0] = "Buy Order";
                    curr[1] = client.getName();
                    curr[2] = buyOrder.getOrders().accumulatePrice();
                    curr[3] = buyOrder.getOrders().accumulateStock();
                    curr[4] = formatter.format(buyOrder.getDate());
                    model.addRow(curr);
                    tempb = tempb.getNext();
                    ids[idx] = buyOrder.getId();
                    idx++;
                }
            }
            node = node.getNext();
        }

        clientsTable.setModel(model);
    }

    private void drawTable(DrawType t, double price, OrderType type) {
        var node = clients.getFirst();
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        model.setRowCount(0);
        var formatter = new SimpleDateFormat("E d/M/y");
        int idx = 0;

        while (node != null) {
            var client = node.getData();

            if (t == DrawType.Sales || t == DrawType.Both) {
                var s = sales.getSalesByClient(client);
                var temps = s.getFirst();
                while (temps != null) {
                    Object[] curr = new Object[5];
                    var sale = temps.getData();

                    if (type == OrderType.Max) {
                        if (sale.getOrders().accumulatePrice() < price) {
                            temps = temps.getNext();
                            continue;
                        }
                    } else if (type == OrderType.Min) {
                        if (sale.getOrders().accumulatePrice() > price) {
                            temps = temps.getNext();
                            continue;
                        }
                    }

                    curr[0] = "Sale";
                    curr[1] = client.getName();
                    curr[2] = sale.getOrders().accumulatePrice();
                    curr[3] = sale.getOrders().accumulateStock();
                    curr[4] = formatter.format(sale.getDate());
                    model.addRow(curr);
                    temps = temps.getNext();
                    ids[idx] = sale.getId();
                    idx++;
                }
            }

            if (t == DrawType.BuyOrders || t == DrawType.Both) {
                var b = buyOrders.getOrdersByClient(client);
                var tempb = b.getFirst();
                while (tempb != null) {
                    Object[] curr = new Object[5];
                    var buyOrder = tempb.getData();

                    if (type == OrderType.Max) {
                        if (buyOrder.getOrders().accumulatePrice() < price) {
                            tempb = tempb.getNext();
                            continue;
                        }
                    } else if (type == OrderType.Min) {
                        if (buyOrder.getOrders().accumulatePrice() > price) {
                            tempb = tempb.getNext();
                            continue;
                        }
                    }

                    curr[0] = "Buy Order";
                    curr[1] = client.getName();
                    curr[2] = buyOrder.getOrders().accumulatePrice();
                    curr[3] = buyOrder.getOrders().accumulateStock();
                    curr[4] = formatter.format(buyOrder.getDate());
                    model.addRow(curr);
                    tempb = tempb.getNext();
                    ids[idx] = buyOrder.getId();
                    idx++;
                }
            }
            node = node.getNext();
        }

        clientsTable.setModel(model);
    }

    private void drawTable(DrawType t, int stock, OrderType type) {
        var node = clients.getFirst();
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        model.setRowCount(0);
        var formatter = new SimpleDateFormat("E d/M/y");
        int idx = 0;

        while (node != null) {
            var client = node.getData();

            if (t == DrawType.Sales || t == DrawType.Both) {
                var s = sales.getSalesByClient(client);
                var temps = s.getFirst();
                while (temps != null) {
                    Object[] curr = new Object[5];
                    var sale = temps.getData();

                    if (type == OrderType.Max) {
                        if (sale.getOrders().accumulateStock() > stock) {
                            temps = temps.getNext();
                            continue;
                        }
                    } else if (type == OrderType.Min) {
                        if (sale.getOrders().accumulateStock() < stock) {
                            temps = temps.getNext();
                            continue;
                        }
                    }

                    curr[0] = "Sale";
                    curr[1] = client.getName();
                    curr[2] = sale.getOrders().accumulatePrice();
                    curr[3] = sale.getOrders().accumulateStock();
                    curr[4] = formatter.format(sale.getDate());
                    model.addRow(curr);
                    temps = temps.getNext();
                    ids[idx] = sale.getId();
                    idx++;
                }
            }

            if (t == DrawType.BuyOrders || t == DrawType.Both) {
                var b = buyOrders.getOrdersByClient(client);
                var tempb = b.getFirst();
                while (tempb != null) {
                    Object[] curr = new Object[5];
                    var buyOrder = tempb.getData();

                    if (type == OrderType.Max) {
                        if (buyOrder.getOrders().accumulateStock() > stock) {
                            tempb = tempb.getNext();
                            continue;
                        }
                    } else if (type == OrderType.Min) {
                        if (buyOrder.getOrders().accumulateStock() < stock) {
                            tempb = tempb.getNext();
                            continue;
                        }
                    }

                    curr[0] = "Buy Order";
                    curr[1] = client.getName();
                    curr[2] = buyOrder.getOrders().accumulatePrice();
                    curr[3] = buyOrder.getOrders().accumulateStock();
                    curr[4] = formatter.format(buyOrder.getDate());
                    model.addRow(curr);
                    tempb = tempb.getNext();
                    ids[idx] = buyOrder.getId();
                    idx++;
                }
            }
            node = node.getNext();
        }

        clientsTable.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inclusionButtonGroup = new javax.swing.ButtonGroup();
        titleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientsTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        markAsSaleButton = new javax.swing.JButton();
        searchLabel = new javax.swing.JLabel();
        searchTypeComboBox = new javax.swing.JComboBox<>();
        includeLabel = new javax.swing.JLabel();
        salesRadioButton = new javax.swing.JRadioButton();
        bothRadioButton = new javax.swing.JRadioButton();
        searchTextField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        buyOrderRadioButton = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(700, 500));

        titleLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Manage Clients");
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        clientsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        clientsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Type", "Client Name", "Total Price", "Number Ordered", "Date Ordered"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        clientsTable.getColumnModel().getColumn(0).setCellRenderer(new ClientsCellRenderer());
        clientsTable.getColumnModel().getColumn(1).setCellRenderer(new ClientsCellRenderer());
        clientsTable.getColumnModel().getColumn(2).setCellRenderer(new ClientsCellRenderer());
        clientsTable.getColumnModel().getColumn(3).setCellRenderer(new ClientsCellRenderer());
        clientsTable.getColumnModel().getColumn(4).setCellRenderer(new ClientsCellRenderer());
        jScrollPane1.setViewportView(clientsTable);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        markAsSaleButton.setText("Mark as Sold");
        markAsSaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markAsSaleButtonActionPerformed(evt);
            }
        });

        searchLabel.setText("Search by:");

        searchTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Minimum price", "Maximum price", "Minimum ordered", "Maximum ordered"}));
        searchTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTypeComboBoxActionPerformed(evt);
            }
        });

        includeLabel.setText("Include:");

        inclusionButtonGroup.add(salesRadioButton);
        salesRadioButton.setText("Sales");
        salesRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesRadioButtonActionPerformed(evt);
            }
        });

        inclusionButtonGroup.add(bothRadioButton);
        bothRadioButton.setText("Both");
        bothRadioButton.setSelected(true);
        bothRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bothRadioButtonActionPerformed(evt);
            }
        });

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        inclusionButtonGroup.add(buyOrderRadioButton);
        buyOrderRadioButton.setText("Buy Orders");
        buyOrderRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyOrderRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(backButton)
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(searchTextField))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(includeLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(salesRadioButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buyOrderRadioButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(bothRadioButton)
                            .addGap(27, 27, 27)
                            .addComponent(markAsSaleButton))))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(includeLabel)
                    .addComponent(salesRadioButton)
                    .addComponent(buyOrderRadioButton)
                    .addComponent(bothRadioButton)
                    .addComponent(markAsSaleButton))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(backButton)
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int idx = clientsTable.getSelectedRow();
        if (idx == -1) {
            showErrorDialog(this, "You must select a sale or buy order to delete!");
            return;
        }

        if (showYesNoDialog(this, "Are you sure you want to delete the selected movement?") == NO_OPTION) {
            return;
        }

        String type = (String) clientsTable.getValueAt(idx, 0);
        int id = ids[idx];
        if (type.equals("Sale")) {
            if (sales.delete(id)) {
                showInformationDialog(this, "Successfully deleted sale");
                var model = (DefaultTableModel) clientsTable.getModel();
                model.removeRow(id);
            } else {
                showErrorDialog(this, "Could not delete sale!");
            }
        } else {
            if (buyOrders.delete(id)) {
                showInformationDialog(this, "Successfully deleted buy order");
                var model = (DefaultTableModel) clientsTable.getModel();
                model.removeRow(id);
            } else {
                showErrorDialog(this, "Could not delete buy order!");
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.window.setContentPane(new MainMenu(window));
        this.window.pack();
    }//GEN-LAST:event_backButtonActionPerformed

    private void markAsSaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markAsSaleButtonActionPerformed
        int idx = clientsTable.getSelectedRow();
        if (idx == -1) {
            showErrorDialog(this, "You must select a buy order to mark!");
            return;
        }

        if (((String) clientsTable.getValueAt(idx, 0)).equals("Sale")) {
            showErrorDialog(this, "Cannot mark a sale as a sale!");
            return;
        }

        var b = buyOrders.getByID(ids[idx]);
        var s = b.toSale();

        if (!buyOrders.delete(b)) {
            showErrorDialog(this, "Could not delete buy order!");
            return;
        }

        if (sales.insert(s)) {
            showInformationDialog(this, "Succesfully marked as a sale!");
        } else {
            showErrorDialog(this, "Could not mark as a sale!");
            return;
        }
        this.drawTable(this.getDrawType());
    }//GEN-LAST:event_markAsSaleButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        this.window.setContentPane(new AddClient(window));
        this.window.pack();
    }//GEN-LAST:event_addButtonActionPerformed

    private void buyOrderRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyOrderRadioButtonActionPerformed
        this.drawTable(DrawType.BuyOrders);
    }//GEN-LAST:event_buyOrderRadioButtonActionPerformed

    private void searchTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTypeComboBoxActionPerformed
    }//GEN-LAST:event_searchTypeComboBoxActionPerformed

    private void salesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesRadioButtonActionPerformed
        this.drawTable(DrawType.Sales);
    }//GEN-LAST:event_salesRadioButtonActionPerformed

    private void bothRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bothRadioButtonActionPerformed
        this.drawTable(DrawType.Both);
    }//GEN-LAST:event_bothRadioButtonActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        String val = searchTextField.getText();
        if (val == null || val.isEmpty() || val.isBlank()) {
            this.drawTable(this.getDrawType());
            return;
        }
        var type = this.getDrawType();
        switch ((String) searchTypeComboBox.getSelectedItem()) {
            case "Name" ->
                this.drawTable(type, val);
            case "Minimum price" -> {
                double p;
                try {
                    p = Double.parseDouble(val);
                } catch (NumberFormatException e) {
                    showErrorDialog(this, "Invalid number entered!");
                    return;
                }
                this.drawTable(type, p, OrderType.Max);
            }
            case "Maximum price" -> {
                double p;
                try {
                    p = Double.parseDouble(val);
                } catch (NumberFormatException e) {
                    showErrorDialog(this, "Invalid number entered!");
                    return;
                }
                this.drawTable(type, p, OrderType.Min);
            }
            case "Maximum ordered" -> {
                int s;
                try {
                    s = Integer.parseInt(val);
                } catch (NumberFormatException e) {
                    showErrorDialog(this, "Invalid number entered!");
                    return;
                }
                this.drawTable(type, s, OrderType.Max);
            }
            case "Minimum ordered" -> {
                int s;
                try {
                    s = Integer.parseInt(val);
                } catch (NumberFormatException e) {
                    showErrorDialog(this, "Invalid number entered!");
                    return;
                }
                this.drawTable(type, s, OrderType.Min);
            }

        }
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private DrawType getDrawType() {
        if (salesRadioButton.isSelected()) {
            return DrawType.Sales;
        } else if (buyOrderRadioButton.isSelected()) {
            return DrawType.BuyOrders;
        } else if (bothRadioButton.isSelected()) {
            return DrawType.Both;
        }
        return null;
    }

    enum DrawType {
        BuyOrders,
        Sales,
        Both,
    }

    enum OrderType {
        Max,
        Min,
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JRadioButton bothRadioButton;
    private javax.swing.JRadioButton buyOrderRadioButton;
    private javax.swing.JTable clientsTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel includeLabel;
    private javax.swing.ButtonGroup inclusionButtonGroup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton markAsSaleButton;
    private javax.swing.JRadioButton salesRadioButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JComboBox<String> searchTypeComboBox;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    // Wrong
    private int[] ids = new int[sales.size() + buyOrders.size()];
    // Actual end of variable declaration
}
