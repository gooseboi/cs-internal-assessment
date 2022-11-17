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

import javax.swing.JFrame;
import backend.OrderList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import static backend.Main.stocks;
import backend.Order;
import backend.Plant;
import backend.BuyOrder;
import backend.Sale;
import static backend.Main.showErrorDialog;
import static backend.Main.showInformationDialog;

/**
 *
 * @author chonk
 */
public class EditOrder extends javax.swing.JPanel {

    private final JFrame window;
    private final String parent;
    private final OrderList orders;
    private Sale sale = null;
    private BuyOrder buyOrder = null;

    /**
     * Creates new form EditOrder
     */
    public EditOrder(JFrame window, OrderList orders, String parent) {
        initComponents();
        this.window = window;
        this.orders = orders;
        this.parent = parent;

        this.drawTable();
        DefaultListModel<String> model = new DefaultListModel();
        stockList.setModel(model);
        this.drawList();
    }

    public EditOrder(JFrame window, Sale sale, OrderList orders) {
        initComponents();
        parent = null;

        this.window = window;
        this.orders = orders;
        this.sale = sale;

        this.drawTable();
        DefaultListModel<String> model = new DefaultListModel();
        stockList.setModel(model);
        this.drawList();
    }

    public EditOrder(JFrame window, BuyOrder buyOrder, OrderList orders) {
        initComponents();
        parent = null;

        this.window = window;
        this.orders = orders;
        this.buyOrder = buyOrder;

        this.drawTable();
        DefaultListModel<String> model = new DefaultListModel();
        stockList.setModel(model);
        this.drawList();
    }

    private void drawTable() {
        var node = orders.getFirst();
        var model = (DefaultTableModel) ordersTable.getModel();
        model.setRowCount(0);
        while (node != null) {
            String[] curr = new String[2];
            curr[0] = node.getData().getPlant().getName();
            curr[1] = String.valueOf(node.getData().getNum());

            model.addRow(curr);
            node = node.getNext();
        }

        ordersTable.setModel(model);
    }

    private void drawList() {
        var node = stocks.getFirst();
        DefaultListModel<String> model = (DefaultListModel<String>) stockList.getModel();
        model.clear();
        while (node != null) {
            var p = node.getData().getPlant();
            if (!orders.contains(p)) {
                model.addElement(p.getName());
            }
            node = node.getNext();
        }

        stockList.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        titleLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        amountSpinner = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        stockList = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        addButton.setText(">>");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        ordersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Plant Name", "Number Ordered"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ordersTable.setRowSelectionAllowed(false);
        ordersTable.getModel().addTableModelListener(
            new TableModelListener()
            {
                public void tableChanged(TableModelEvent evt)
                {
                    onCellChanged(evt);
                }
            });
            jScrollPane1.setViewportView(ordersTable);
            if (ordersTable.getColumnModel().getColumnCount() > 0) {
                ordersTable.getColumnModel().getColumn(0).setResizable(false);
                ordersTable.getColumnModel().getColumn(1).setResizable(false);
            }

            titleLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
            titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            titleLabel.setText("Edit Orders");
            titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

            deleteButton.setText("<<");
            deleteButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    deleteButtonActionPerformed(evt);
                }
            });

            amountSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

            stockList.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
            });
            stockList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            jScrollPane2.setViewportView(stockList);

            jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField1.setText("Order Amount");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(130, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1)
                                .addComponent(amountSpinner, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(118, 118, 118)
                            .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(69, 69, 69))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(93, 93, 93)
                            .addComponent(addButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deleteButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(amountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                    .addComponent(backButton)
                    .addGap(40, 40, 40))
            );
        }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String value = stockList.getSelectedValue();
        int idx = stockList.getSelectedIndex();
        if (value == null) {
            showErrorDialog(this, "No item selected!");
            return;
        }

        int amount = (int) amountSpinner.getValue();
        if (amount == 0) {
            showErrorDialog(this, "Must add 1 or more of the item!");
            return;
        }

        ((DefaultListModel<String>) stockList.getModel()).remove(idx);
        Plant p = stocks.findPlant(value);
        orders.insert(new Order(p, amount));
        amountSpinner.setValue(0);
        this.drawTable();
    }//GEN-LAST:event_addButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if (this.sale != null) {
            this.window.setContentPane(new EditSale(window, sale, orders));
            this.window.pack();
        } else if (this.buyOrder != null) {
            this.window.setContentPane(new EditBuyOrder(window, buyOrder, orders));
            this.window.pack();
        } else if (this.parent.equals("addsale")) {
            this.window.setContentPane(new AddSale(window, orders));
            this.window.pack();
        } else if (this.parent.equals("addbuyorder")) {
            this.window.setContentPane(new AddBuyOrder(window, orders));
            this.window.pack();
        } else if (this.parent.equals("managesales")) {
            this.window.setContentPane(new ManageSales(window));
            this.window.pack();
        } else if (this.parent.equals("managebuyorder")) {
            this.window.setContentPane(new ManageBuyOrders(window));
            this.window.pack();
        } else {
            throw new UnsupportedOperationException("Invalid parent window in EditOrder");
        }
    }//GEN-LAST:event_backButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int row = ordersTable.getSelectedRow();
        if (row == -1) {
            showErrorDialog(this, "No item selected!");
            return;
        }

        String name = (String) ordersTable.getValueAt(row, 0);
        orders.delete(name);
        ((DefaultTableModel) ordersTable.getModel()).removeRow(row);
        this.drawList();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void onCellChanged(TableModelEvent evt) {
        if (evt.getType() == TableModelEvent.UPDATE) {
            int row = evt.getFirstRow();
            assert evt.getFirstRow() == evt.getLastRow() : "First row is not last row";
            String name = (String) ordersTable.getValueAt(row, 0);
            int num;
            try {
                num = Integer.parseInt((String) ordersTable.getValueAt(row, 1));
                if (num < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                showErrorDialog(this, "Amount entered must be a positive whole number!");
                this.drawTable();
                return;
            }

            if (orders.modify(name, num)) {
                showInformationDialog(this, "Order modified successfully");
            } else {
                showErrorDialog(this, "Failed modifying order!(What?)");
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JSpinner amountSpinner;
    private javax.swing.JButton backButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable ordersTable;
    private javax.swing.JList<String> stockList;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
