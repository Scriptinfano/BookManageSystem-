/*
 * Created by JFormDesigner on Mon Jun 26 14:26:10 CST 2023
 */

package com.mingxiang.bookstore.view;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.entity.Book;
import com.mingxiang.bookstore.entity.Customer;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Mingxiang
 */
public class InputCustomerDialog extends JDialog {
    private final DatabaseDao dao;

    public InputCustomerDialog(Window owner, DatabaseDao theDao) {
        super(owner);
        dao = theDao;
        initComponents();
        setVisible(true);
    }

    private void confirm(ActionEvent e) {
        String customerName = nameTextField.getText();
        String customerTel = phoneTextField.getText();
        String context = contextArea.getText();
        if (customerName.length() == 0 || customerTel.length() == 0 || context.length() == 0) {
            JOptionPane.showMessageDialog(this, "必须全部输入内容才能录入", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Customer customer = new Customer(customerName,customerTel,context);
            dao.inputCustomer(customer);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误，插入客户时发生错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "录入成功");
        dispose();
    }

    private void cancel(ActionEvent e) {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        nameTextField = new JTextField();
        panel2 = new JPanel();
        label2 = new JLabel();
        phoneTextField = new JTextField();
        panel3 = new JPanel();
        panel5 = new JPanel();
        label5 = new JLabel();
        scrollPane1 = new JScrollPane();
        contextArea = new JTextArea();
        buttonBar = new JPanel();
        confirmAllButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("\u5f55\u5165\u5ba2\u6237\u57fa\u672c\u4fe1\u606f");
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout());

                    //---- label1 ----
                    label1.setText("\u8f93\u5165\u5ba2\u6237\u59d3\u540d");
                    panel1.add(label1);

                    //---- nameTextField ----
                    nameTextField.setColumns(20);
                    panel1.add(nameTextField);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout());

                    //---- label2 ----
                    label2.setText("\u8f93\u5165\u5ba2\u6237\u8054\u7cfb\u65b9\u5f0f");
                    panel2.add(label2);

                    //---- phoneTextField ----
                    phoneTextField.setColumns(20);
                    panel2.add(phoneTextField);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                    //======== panel5 ========
                    {
                        panel5.setLayout(new FlowLayout());

                        //---- label5 ----
                        label5.setText("\u8f93\u5165\u5ba2\u6237\u5907\u6ce8");
                        panel5.add(label5);
                    }
                    panel3.add(panel5);

                    //======== scrollPane1 ========
                    {

                        //---- contextArea ----
                        contextArea.setColumns(50);
                        contextArea.setRows(2);
                        contextArea.setTabSize(4);
                        scrollPane1.setViewportView(contextArea);
                    }
                    panel3.add(scrollPane1);
                }
                contentPanel.add(panel3);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- confirmAllButton ----
                confirmAllButton.setText("\u786e\u8ba4");
                confirmAllButton.addActionListener(e -> confirm(e));
                buttonBar.add(confirmAllButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88");
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
    private JPanel panel1;
    private JLabel label1;
    private JTextField nameTextField;
    private JPanel panel2;
    private JLabel label2;
    private JTextField phoneTextField;
    private JPanel panel3;
    private JPanel panel5;
    private JLabel label5;
    private JScrollPane scrollPane1;
    private JTextArea contextArea;
    private JPanel buttonBar;
    private JButton confirmAllButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
