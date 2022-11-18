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
import static backend.Main.stocks;
import static backend.Main.buyOrders;
import static backend.Main.sales;
import static backend.Main.showErrorDialog;
import static backend.Main.showInformationDialog;
import backend.OrderList;
import backend.StockList;
import backend.StockNode;
import frontend.tables.StocksCellRenderer;
import frontend.tables.StocksHeaderRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import static backend.Main.showYesNoDialog;
import static javax.swing.JOptionPane.NO_OPTION;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author chonk
 */
public class ManageStock extends javax.swing.JPanel {

    private final JFrame window;
    private final StockList localStock;
    private StockList filtered;

    /**
     * Creates new form ManageOrders
     */
    public ManageStock(JFrame window) {
        initComponents();
        this.window = window;

        localStock = stocks.clone();
        filtered = null;
        orderDescending = true;
        mode = StockOrderMode.Name;
        sortDirectionButton.setText("⬇");
        nameRadioButton.setSelected(true);

        this.sortList();
        this.drawTable();
    }

    private void drawTable() {
        StockNode node = (filtered == null ? localStock : filtered).getFirst();
        DefaultTableModel model = (DefaultTableModel) stocksTable.getModel();
        model.setRowCount(0);
        while (node != null) {
            String[] curr = new String[5];
            var data = node.getData();
            var p = data.getPlant();
            curr[0] = p.getName();
            curr[1] = String.valueOf(data.getStock());
            curr[2] = p.getGrowthCondition();
            curr[3] = String.valueOf(p.getPrice());
            OrderList l = buyOrders.getOrdersByPlant(p);
            curr[4] = String.valueOf(l.accumulateStock());

            model.addRow(curr);
            node = node.getNext();
        }

        stocksTable.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sortButtonGroup = new javax.swing.ButtonGroup();
        titleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stocksTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        searchLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        totalRadioButton = new javax.swing.JRadioButton();
        priceRadioButton = new javax.swing.JRadioButton();
        buyOrdersRadioButton = new javax.swing.JRadioButton();
        sortLabel = new javax.swing.JLabel();
        nameRadioButton = new javax.swing.JRadioButton();
        sortDirectionButton = new javax.swing.JButton();
        searchButtonActionPerformed = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 500));

        titleLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Manage Stock");
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        stocksTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        stocksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Plant Name", "Total Available", "Growth Conditions", "Price per unit", "Buy Orders"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        stocksTable.getColumnModel().getColumn(0).setCellRenderer(new StocksCellRenderer());
        stocksTable.getColumnModel().getColumn(1).setCellRenderer(new StocksCellRenderer());
        stocksTable.getColumnModel().getColumn(2).setCellRenderer(new StocksCellRenderer());
        stocksTable.getColumnModel().getColumn(3).setCellRenderer(new StocksCellRenderer());
        stocksTable.getColumnModel().getColumn(4).setCellRenderer(new StocksCellRenderer());
        JTableHeader h = stocksTable.getTableHeader();
        h.setDefaultRenderer(new StocksHeaderRenderer(stocksTable));

        stocksTable.getModel().addTableModelListener(
            new TableModelListener()
            {
                public void tableChanged(TableModelEvent evt)
                {
                    onCellChanged(evt);
                }
            });
            jScrollPane1.setViewportView(stocksTable);
            if (stocksTable.getColumnModel().getColumnCount() > 0) {
                stocksTable.getColumnModel().getColumn(0).setResizable(false);
                stocksTable.getColumnModel().getColumn(1).setResizable(false);
                stocksTable.getColumnModel().getColumn(2).setResizable(false);
                stocksTable.getColumnModel().getColumn(3).setResizable(false);
                stocksTable.getColumnModel().getColumn(4).setResizable(false);
            }

            backButton.setText("Back");
            backButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    backButtonActionPerformed(evt);
                }
            });

            deleteButton.setText("Delete");
            deleteButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    deleteButtonActionPerformed(evt);
                }
            });

            addButton.setText("Add");
            addButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    addButtonActionPerformed(evt);
                }
            });

            searchLabel.setText("Search:");

            searchTextField.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    searchTextFieldActionPerformed(evt);
                }
            });

            sortButtonGroup.add(totalRadioButton);
            totalRadioButton.setText("Available");
            totalRadioButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    totalRadioButtonActionPerformed(evt);
                }
            });

            sortButtonGroup.add(priceRadioButton);
            priceRadioButton.setText("Price");
            priceRadioButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    priceRadioButtonActionPerformed(evt);
                }
            });

            sortButtonGroup.add(buyOrdersRadioButton);
            buyOrdersRadioButton.setText("Buy Orders");
            buyOrdersRadioButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buyOrdersRadioButtonActionPerformed(evt);
                }
            });

            sortLabel.setText("Sort By:");

            sortButtonGroup.add(nameRadioButton);
            nameRadioButton.setText("Name");
            nameRadioButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    nameRadioButtonActionPerformed(evt);
                }
            });

            sortDirectionButton.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            sortDirectionButton.setText("⬇");
            sortDirectionButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    sortDirectionButtonActionPerformed(evt);
                }
            });

            searchButtonActionPerformed.setText("Go");
            searchButtonActionPerformed.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    searchButtonActionPerformedActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(258, 258, 258)
                            .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(backButton)
                                    .addGap(119, 119, 119)
                                    .addComponent(nameRadioButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(sortLabel)
                                            .addGap(18, 18, 18)
                                            .addComponent(sortDirectionButton))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(totalRadioButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(priceRadioButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(buyOrdersRadioButton)))))))
                    .addContainerGap(38, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(118, 118, 118)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(searchLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(searchButtonActionPerformed, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(115, 115, 115))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton)
                        .addComponent(deleteButton)
                        .addComponent(searchLabel)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButtonActionPerformed))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(backButton))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sortLabel)
                                .addComponent(sortDirectionButton))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(totalRadioButton)
                                .addComponent(priceRadioButton)
                                .addComponent(buyOrdersRadioButton)
                                .addComponent(nameRadioButton))))
                    .addGap(37, 37, 37))
            );
        }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.window.setContentPane(new MainMenu(window));
        this.window.pack();
    }//GEN-LAST:event_backButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selected = stocksTable.getSelectedRow();
        if (selected == -1) {
            showErrorDialog(this, "Please select a plant to delete!");
            return;
        }

        if (showYesNoDialog(this, "Are you sure you want to delete the selected stock?") == NO_OPTION) {
            return;
        }

        String name = (String) stocksTable.getValueAt(selected, 0);
        sales.deletePlant(name);
        buyOrders.deletePlant(name);
        localStock.delete(name);
        if (stocks.delete(name)) {
            showInformationDialog(this, "Stock successfully deleted");
        } else {
            showErrorDialog(this, "Could not delete stock");
        }
        DefaultTableModel model = (DefaultTableModel) stocksTable.getModel();
        model.removeRow(selected);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        this.window.setContentPane(new AddStock(window));
        this.window.pack();
    }//GEN-LAST:event_addButtonActionPerformed

    private void priceRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceRadioButtonActionPerformed
        mode = StockOrderMode.Price;
        sortList();
        drawTable();
    }//GEN-LAST:event_priceRadioButtonActionPerformed

    private void sortDirectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortDirectionButtonActionPerformed
        orderDescending = !orderDescending;
        if (orderDescending) {
            sortDirectionButton.setText("⬇");
        } else {
            sortDirectionButton.setText("⬆");
        }
        sortList();
        drawTable();
    }//GEN-LAST:event_sortDirectionButtonActionPerformed

    private void totalRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalRadioButtonActionPerformed
        mode = StockOrderMode.Available;
        sortList();
        drawTable();
    }//GEN-LAST:event_totalRadioButtonActionPerformed

    private void nameRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameRadioButtonActionPerformed
        mode = StockOrderMode.Name;
        sortList();
        drawTable();
    }//GEN-LAST:event_nameRadioButtonActionPerformed

    private void buyOrdersRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyOrdersRadioButtonActionPerformed
        mode = StockOrderMode.BuyOrders;
        sortList();
        drawTable();
    }//GEN-LAST:event_buyOrdersRadioButtonActionPerformed

    private void searchButtonActionPerformedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformedActionPerformed
        filter();
    }//GEN-LAST:event_searchButtonActionPerformedActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        filter();
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void filter() {
        String searchText = searchTextField.getText();
        if (searchText == null || searchText.equals("")) {
            filtered = null;
            this.sortList();
        } else {
            filtered = localStock.getStocksByName(searchText);
        }

        this.drawTable();
    }

    private void sortList() {
        StockList list = (filtered == null ? localStock : filtered);
        switch (mode) {
            case Name ->
                list.sortByName(orderDescending);
            case Available ->
                list.sortByAvailable(orderDescending);
            case Price ->
                list.sortByPrice(orderDescending);
            case BuyOrders -> {
                //localStock.sortByBuyOrders(orderDescending);
            }
        }
    }

    enum StockOrderMode {
        Name,
        Available,
        Price,
        BuyOrders,
    }

    private void onCellChanged(TableModelEvent evt) {
        if (evt.getType() == TableModelEvent.UPDATE) {
            int row = evt.getFirstRow();
            assert evt.getFirstRow() == evt.getLastRow() : "First row is not last row";
            int col = evt.getColumn();
            switch (col) {
                case 0 ->
                    showErrorDialog(this, "TODO");
                case 1 -> {
                    var name = (String) stocksTable.getValueAt(row, 0);
                    var s = stocks.getStockByName(name);
                    int stock = (int) stocksTable.getValueAt(row, col);

                    s.setStock(stock);
                    if (stocks.modify(s)) {
                        showInformationDialog(this, "Successfully modified stock!");
                    } else {
                        showErrorDialog(this, "Error modifying stock!");
                    }
                }
                case 2 -> {
                }
                case 3 -> {
                    var name = (String) stocksTable.getValueAt(row, 0);
                    var s = stocks.getStockByName(name);
                    double price = (double) stocksTable.getValueAt(row, col);

                    s.getPlant().setPrice(price);
                    if (stocks.modify(s)) {
                        showInformationDialog(this, "Successfully modified stock!");
                    } else {
                        showErrorDialog(this, "Error modifying stock!");
                    }
                }
            }
            this.drawTable();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JRadioButton buyOrdersRadioButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton nameRadioButton;
    private javax.swing.JRadioButton priceRadioButton;
    private javax.swing.JButton searchButtonActionPerformed;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.ButtonGroup sortButtonGroup;
    private javax.swing.JButton sortDirectionButton;
    private javax.swing.JLabel sortLabel;
    private javax.swing.JTable stocksTable;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JRadioButton totalRadioButton;
    // End of variables declaration//GEN-END:variables

    // Wrong
    private boolean orderDescending;
    private StockOrderMode mode;
    // Actual end of variable declarations
}
