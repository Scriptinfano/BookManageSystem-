/*
 * Created by JFormDesigner on Mon Jun 26 13:40:52 CST 2023
 */

package com.mingxiang.bookstore.view;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.entity.SaleDetailOrder;
import com.mingxiang.bookstore.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mingxiang
 */
public class SaleDetailOrderDialog extends JDialog {
    private final DatabaseDao dao;
    private final int saleOrderId;

    public SaleDetailOrderDialog(Window owner, DatabaseDao theDao, int purchaseOrderId) {
        super(owner);
        dao = theDao;
        initSelfListeners();//初始化自定义的监听器
        initComponents();
        initBookInfoTable();//初始化需要查询的数据表
        this.saleOrderId = purchaseOrderId;
        setVisible(true);
    }

    private void initBookInfoTable() {
        try {
            ResultSet resultSet = dao.query("SELECT * FROM newbookstore.booksale_view");
            Utils.setTable(bookInfoTable,resultSet);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "数据库错误，在加载booksale_view时发生错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void initSelfListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                SaleOrderDialog father = (SaleOrderDialog) getOwner();
                father.dispose();
            }
        });
    }

    private void addItems(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) saleOrderTable.getModel();
        model.addRow(new Object[model.getColumnCount()]);
    }

    private void deleteItem(ActionEvent e) {
        int rowIndex = saleOrderTable.getSelectedRow();
        if (rowIndex != -1) {
            int realRow = saleOrderTable.convertRowIndexToModel(rowIndex);
            DefaultTableModel model = (DefaultTableModel) saleOrderTable.getModel();
            model.removeRow(realRow);
            model.fireTableDataChanged();
        }
    }

    private void ok(ActionEvent e) {
        int saleId = ((SaleOrderDialog) getOwner()).getSaleOrderId();

        //如果表格中没有任何条目则提示用户输入相关条目
        TableModel model = saleOrderTable.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            int bookId = 0, bookCount = 0;
            for (int column = 0; column < columnCount; column++) {
                Object value;
                try {
                    value = model.getValueAt(row, column);
                    if (value == null) throw new RuntimeException();
                    else {
                        if (column == 0) {
                            bookId = (Integer) value;
                        } else if (column == 1) {
                            bookCount = (Integer) value;
                        }
                    }
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(this, "请填写表格中的所有单元格", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }

            }
            dao.setAutoCommit(false);
            try {
                dao.createSaleDetailOrder(new SaleDetailOrder(saleId, bookId, bookCount));
                dao.updateSaleOrder(saleId);//更新销售订单中的总售价
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
                dao.rollback();
                dao.setAutoCommit(true);
                return;
            }
            dispose();
        }
    }

    private void cancel(ActionEvent e) {
        try {
            dao.deleteSaleOrder(saleOrderId);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误，在取消创建详单时无法删除对应的订单", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel3 = new JPanel();
        panel2 = new JPanel();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        saleOrderTable = new JTable();
        panel1 = new JPanel();
        addItemButton = new JButton();
        deleteItemButton = new JButton();
        panel4 = new JPanel();
        panel5 = new JPanel();
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        bookInfoTable = new JTable();
        panel6 = new JPanel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("\u65b0\u5efa\u9500\u552e\u8ba2\u5355\u8be6\u5355");
        setModal(true);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

                //======== panel3 ========
                {
                    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                    //======== panel2 ========
                    {
                        panel2.setLayout(new FlowLayout());

                        //---- label3 ----
                        label3.setText("\u8ba2\u8d27\u8be6\u5355\u8868");
                        panel2.add(label3);
                    }
                    panel3.add(panel2);

                    //======== scrollPane1 ========
                    {

                        //---- saleOrderTable ----
                        saleOrderTable.setModel(new DefaultTableModel(
                            new Object[][] {
                            },
                            new String[] {
                                "\u4e66\u53f7", "\u6570\u91cf"
                            }
                        ) {
                            Class<?>[] columnTypes = new Class<?>[] {
                                Integer.class, Integer.class
                            };
                            @Override
                            public Class<?> getColumnClass(int columnIndex) {
                                return columnTypes[columnIndex];
                            }
                        });
                        {
                            TableColumnModel cm = saleOrderTable.getColumnModel();
                            cm.getColumn(0).setResizable(false);
                            cm.getColumn(1).setResizable(false);
                        }
                        scrollPane1.setViewportView(saleOrderTable);
                    }
                    panel3.add(scrollPane1);

                    //======== panel1 ========
                    {
                        panel1.setLayout(new FlowLayout());

                        //---- addItemButton ----
                        addItemButton.setText("\u70b9\u51fb\u65b0\u589e\u6761\u76ee");
                        addItemButton.addActionListener(e -> addItems(e));
                        panel1.add(addItemButton);

                        //---- deleteItemButton ----
                        deleteItemButton.setText("\u5220\u9664\u9009\u4e2d\u884c");
                        deleteItemButton.addActionListener(e -> deleteItem(e));
                        panel1.add(deleteItemButton);
                    }
                    panel3.add(panel1);
                }
                contentPanel.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

                    //======== panel5 ========
                    {
                        panel5.setLayout(new FlowLayout());

                        //---- label1 ----
                        label1.setText("\u5f85\u9500\u552e\u7684\u4e66\u5217\u8868");
                        panel5.add(label1);
                    }
                    panel4.add(panel5);

                    //======== scrollPane2 ========
                    {

                        //---- bookInfoTable ----
                        bookInfoTable.setEnabled(false);
                        bookInfoTable.setRowSelectionAllowed(false);
                        scrollPane2.setViewportView(bookInfoTable);
                    }
                    panel4.add(scrollPane2);

                    //======== panel6 ========
                    {
                        panel6.setLayout(new FlowLayout());
                    }
                    panel4.add(panel6);
                }
                contentPanel.add(panel4);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("\u786e\u8ba4\u521b\u5efa");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88\u521b\u5efa");
                cancelButton.addActionListener(e -> cancel(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel3;
    private JPanel panel2;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTable saleOrderTable;
    private JPanel panel1;
    private JButton addItemButton;
    private JButton deleteItemButton;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel label1;
    private JScrollPane scrollPane2;
    private JTable bookInfoTable;
    private JPanel panel6;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

