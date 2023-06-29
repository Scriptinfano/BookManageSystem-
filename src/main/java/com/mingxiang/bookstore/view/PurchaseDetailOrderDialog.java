/*
 * Created by JFormDesigner on Sun Jun 25 14:37:29 CST 2023
 */

package com.mingxiang.bookstore.view;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.entity.PurchaseDetailOrder;
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
public class PurchaseDetailOrderDialog extends JDialog {
    private final DatabaseDao dao;
    private final int purchaseOrderId;

    public PurchaseDetailOrderDialog(Window owner, DatabaseDao theDao, int purchaseOrderId) {
        super(owner);
        dao = theDao;
        initSelfListeners();//初始化自定义的监听器
        initComponents();//初始化JFormDesigner定义的组件
        initBookInfoTable();//初始化需要查询的数据表
        this.purchaseOrderId = purchaseOrderId;
        setVisible(true);
    }

    /**
     * 初始化自定义的监听器
     */
    private void initSelfListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                PurchaseOrderDialog father = (PurchaseOrderDialog) getOwner();
                father.dispose();
            }
        });
    }

    private void initBookInfoTable() {
        try {
            ResultSet resultSet = dao.query("SELECT * FROM newbookstore.bookpurchase_view");
            Utils.setTable(bookInfoTable,resultSet);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            try {
                dao.deletePurchaseOrder(purchaseOrderId);
            } catch (SQLException exception2) {
                JOptionPane.showMessageDialog(this, exception2.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        }
    }

    private void ok(ActionEvent e) {
        int purchaseId = ((PurchaseOrderDialog) getOwner()).getPurchaseId();

        //如果表格中没有任何条目则提示用户输入相关条目
        TableModel model = purchaseOrderTable.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            int bookId = 0, bookCount = 0;
            float purchasePrice = 0, price = 0;
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
                        } else if (column == 2) {
                            purchasePrice = (Float) value;
                        } else if (column == 3) {
                            price = (Float) value;
                        }
                    }
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(this, "请填写表格中的所有单元格", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }

            }
            try {
                dao.getConnection().setAutoCommit(false);
                dao.createPurchaseDetailOrder(new PurchaseDetailOrder(purchaseId, bookId, bookCount, purchasePrice));
                dao.updateSalePrice(bookId, price);//更新图书的售价
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage()+"错误码："+exception.getErrorCode()+"，数据库错误状态："+exception.getSQLState(), "数据库错误", JOptionPane.ERROR_MESSAGE);
                try {
                    dao.getConnection().rollback();
                } catch (SQLException exception1) {
                    JOptionPane.showMessageDialog(this, exception1.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
                }
                return;
            }
            //关闭子窗口之前先设置在该窗口关闭之后可以关闭父窗口
            try {
                dao.getConnection().commit();
                dao.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            dispose();

        }
    }


    private void addItem(ActionEvent e) {
        //向表中新增一行以为用户提供输入
        DefaultTableModel model = (DefaultTableModel) purchaseOrderTable.getModel();
        model.addRow(new Object[model.getColumnCount()]);
    }

    private void deleteRow(ActionEvent e) {

        int rowIndex = purchaseOrderTable.getSelectedRow();
        if (rowIndex != -1) {
            int realRow = purchaseOrderTable.convertRowIndexToModel(rowIndex);
            DefaultTableModel model = (DefaultTableModel) purchaseOrderTable.getModel();
            model.removeRow(realRow);
            model.fireTableDataChanged();
        }
    }

    private void cancel(ActionEvent e) {
        try {
            dao.deletePurchaseOrder(purchaseOrderId);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel3 = new JPanel();
        panel2 = new JPanel();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        purchaseOrderTable = new JTable();
        panel1 = new JPanel();
        newItemButton = new JButton();
        deleteRowButton = new JButton();
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
        setTitle("\u65b0\u589e\u8fdb\u8d27\u8ba2\u5355\u8be6\u5355re");
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setModal(true);
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

                        //---- purchaseOrderTable ----
                        purchaseOrderTable.setModel(new DefaultTableModel(
                            new Object[][] {
                            },
                            new String[] {
                                "\u4e66\u53f7", "\u6570\u91cf", "\u8fdb\u4ef7", "\u552e\u4ef7"
                            }
                        ) {
                            Class<?>[] columnTypes = new Class<?>[] {
                                Integer.class, Integer.class, Float.class, Float.class
                            };
                            @Override
                            public Class<?> getColumnClass(int columnIndex) {
                                return columnTypes[columnIndex];
                            }
                        });
                        {
                            TableColumnModel cm = purchaseOrderTable.getColumnModel();
                            cm.getColumn(0).setResizable(false);
                            cm.getColumn(1).setResizable(false);
                            cm.getColumn(2).setResizable(false);
                        }
                        purchaseOrderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        scrollPane1.setViewportView(purchaseOrderTable);
                    }
                    panel3.add(scrollPane1);

                    //======== panel1 ========
                    {
                        panel1.setLayout(new FlowLayout());

                        //---- newItemButton ----
                        newItemButton.setText("\u70b9\u51fb\u65b0\u589e\u6761\u76ee");
                        newItemButton.addActionListener(e -> addItem(e));
                        panel1.add(newItemButton);

                        //---- deleteRowButton ----
                        deleteRowButton.setText("\u70b9\u51fb\u5220\u9664\u5f53\u524d\u9009\u4e2d\u884c");
                        deleteRowButton.addActionListener(e -> deleteRow(e));
                        panel1.add(deleteRowButton);
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
                        label1.setText("\u76ee\u524d\u5df2\u6709\u7684\u4e66\u7c4d\u4fe1\u606f\u8868");
                        panel5.add(label1);
                    }
                    panel4.add(panel5);

                    //======== scrollPane2 ========
                    {

                        //---- bookInfoTable ----
                        bookInfoTable.setRowSelectionAllowed(false);
                        bookInfoTable.setModel(new DefaultTableModel());
                        bookInfoTable.setEnabled(false);
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
    private JTable purchaseOrderTable;
    private JPanel panel1;
    private JButton newItemButton;
    private JButton deleteRowButton;
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
