/*
 * Created by JFormDesigner on Sat Jun 24 16:35:13 CST 2023
 */

package com.mingxiang.bookstore.view;

import java.awt.event.*;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.entity.Book;
import com.mingxiang.bookstore.entity.Customer;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Mingxiang
 */
public class EditCustomerDialog extends JDialog {
    private final DatabaseDao dao;

    public EditCustomerDialog(Window owner, DatabaseDao theDao) {
        super(owner);
        dao = theDao;
        initComponents();
        setVisible(true);
    }

    private void cancel(ActionEvent e) {
        dispose();
    }


    private void confirmModifyContext(ActionEvent e) {
        int id;
        String idStr = customerIdTextField.getText();
        if (idStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "请填写id号码再执行修改操作", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的数据类型，id号必须是整数字符串", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            dao.editCustomerContext(id, contextTextArea.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);

    }

    private void confirmAll(ActionEvent e) {
        int id;
        String idStr = customerIdTextField.getText();
        if (idStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "请填写id号码再执行修改操作", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的数据类型，书的id号必须是整数字符串", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String name = customerNameTextField.getText();
        String tel = customerTelTextField.getText();
        String context = contextTextArea.getText();
        try {
            dao.editCustomer(id, new Customer(name, tel, context));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }


    private void confirmModifyName(ActionEvent e) {
        int id;
        String idStr = customerIdTextField.getText();
        if (idStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "请填写id号码再执行修改操作", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的数据类型，id号必须是整数字符串", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            dao.editCustomerName(id, customerNameTextField.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmModifyTel(ActionEvent e) {
        int id;
        String idStr = customerTelTextField.getText();
        if (idStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "请填写id号码再执行修改操作", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的数据类型，id号必须是整数字符串", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            dao.editCustomerTel(id, idStr);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    private void comfirmModifyName(ActionEvent e) {
        // TODO add your code here
    }

    private void comfirmModifyTel(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        customerIdTextField = new JTextField();
        panel2 = new JPanel();
        label2 = new JLabel();
        customerNameTextField = new JTextField();
        comfirmModifyNameButton = new JButton();
        panel3 = new JPanel();
        label3 = new JLabel();
        customerTelTextField = new JTextField();
        comfirmModifyTelButton = new JButton();
        panel4 = new JPanel();
        panel5 = new JPanel();
        label4 = new JLabel();
        confirmModifyContextButton = new JButton();
        scrollPane1 = new JScrollPane();
        contextTextArea = new JTextArea();
        buttonBar = new JPanel();
        label7 = new JLabel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setTitle("\u7f16\u8f91\u5ba2\u6237\u4fe1\u606f");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout(4, 1, 5, 5));

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout());

                    //---- label1 ----
                    label1.setText("\u8f93\u5165\u8981\u4fee\u6539\u7684\u5ba2\u6237ID");
                    panel1.add(label1);

                    //---- customerIdTextField ----
                    customerIdTextField.setColumns(20);
                    panel1.add(customerIdTextField);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout());

                    //---- label2 ----
                    label2.setText("\u8f93\u5165\u8981\u4fee\u6539\u7684\u59d3\u540d");
                    panel2.add(label2);

                    //---- customerNameTextField ----
                    customerNameTextField.setColumns(20);
                    panel2.add(customerNameTextField);

                    //---- comfirmModifyNameButton ----
                    comfirmModifyNameButton.setText("\u786e\u8ba4\u4fee\u6539");
                    comfirmModifyNameButton.addActionListener(e -> comfirmModifyName(e));
                    panel2.add(comfirmModifyNameButton);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new FlowLayout());

                    //---- label3 ----
                    label3.setText("\u8f93\u5165\u8981\u4fee\u6539\u7684\u7535\u8bdd\u53f7\u7801");
                    panel3.add(label3);

                    //---- customerTelTextField ----
                    customerTelTextField.setColumns(20);
                    panel3.add(customerTelTextField);

                    //---- comfirmModifyTelButton ----
                    comfirmModifyTelButton.setText("\u786e\u8ba4\u4fee\u6539");
                    comfirmModifyTelButton.addActionListener(e -> comfirmModifyTel(e));
                    panel3.add(comfirmModifyTelButton);
                }
                contentPanel.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

                    //======== panel5 ========
                    {
                        panel5.setLayout(new FlowLayout());

                        //---- label4 ----
                        label4.setText("\u8f93\u5165\u8981\u4fee\u6539\u7684\u5907\u6ce8");
                        panel5.add(label4);

                        //---- confirmModifyContextButton ----
                        confirmModifyContextButton.setText("\u786e\u8ba4\u4fee\u6539");
                        confirmModifyContextButton.addActionListener(e -> confirmModifyContext(e));
                        panel5.add(confirmModifyContextButton);
                    }
                    panel4.add(panel5);

                    //======== scrollPane1 ========
                    {

                        //---- contextTextArea ----
                        contextTextArea.setColumns(50);
                        contextTextArea.setRows(2);
                        contextTextArea.setTabSize(4);
                        scrollPane1.setViewportView(contextTextArea);
                    }
                    panel4.add(scrollPane1);
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

                //---- label7 ----
                label7.setText("\u5907\u6ce8\uff1a\u4e00\u6b21\u6027\u5168\u90e8\u8f93\u5165\u4ee5\u4e0a\u5185\u5bb9\uff0c\u7136\u540e\u70b9\u51fb\u201c\u4e00\u6b21\u6027\u786e\u8ba4\u4fee\u6539\u201d\u6309\u94ae");
                buttonBar.add(label7, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- okButton ----
                okButton.setText("\u4e00\u6b21\u6027\u786e\u8ba4\u4fee\u6539");
                okButton.addActionListener(e -> confirmAll(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JTextField customerIdTextField;
    private JPanel panel2;
    private JLabel label2;
    private JTextField customerNameTextField;
    private JButton comfirmModifyNameButton;
    private JPanel panel3;
    private JLabel label3;
    private JTextField customerTelTextField;
    private JButton comfirmModifyTelButton;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel label4;
    private JButton confirmModifyContextButton;
    private JScrollPane scrollPane1;
    private JTextArea contextTextArea;
    private JPanel buttonBar;
    private JLabel label7;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
