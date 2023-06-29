/*
 * Created by JFormDesigner on Sun Jun 25 21:51:14 CST 2023
 */

package com.mingxiang.bookstore.view;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.entity.Customer;
import com.mingxiang.bookstore.entity.Merchant;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Mingxiang
 */
public class EditMerchantDialog extends JDialog {
    private final DatabaseDao dao;

    public EditMerchantDialog(Window owner, DatabaseDao theDao) {
        super(owner);
        dao = theDao;
        initComponents();
        setVisible(true);
    }

    private void modifyName(ActionEvent e) {
        int id;
        String idStr = merchantIdTextField.getText();
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
            dao.editMerchantName(id, merchantNameTextField.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);

    }

    private void modifyTel(ActionEvent e) {
        int id;
        String idStr = merchantTelTextField.getText();
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
            dao.editMerchantTel(id, merchantTelTextField.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);

    }

    private void modifyContext(ActionEvent e) {
        int id;
        String idStr = merchantContextTextArea.getText();
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
            dao.editMerchantContext(id, merchantContextTextArea.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);

    }

    private void modifyAll(ActionEvent e) {

        int id;
        String idStr = merchantIdTextField.getText();
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
        String name = merchantNameTextField.getText();
        String tel = merchantTelTextField.getText();
        String context = merchantContextTextArea.getText();
        try {
            dao.editMerchant(id, new Merchant(name, tel, context));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
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
        label2 = new JLabel();
        merchantIdTextField = new JTextField();
        panel2 = new JPanel();
        label3 = new JLabel();
        merchantNameTextField = new JTextField();
        confirmModifyNameButton = new JButton();
        panel3 = new JPanel();
        label4 = new JLabel();
        merchantTelTextField = new JTextField();
        confirmModifyTelButton = new JButton();
        panel4 = new JPanel();
        panel5 = new JPanel();
        label5 = new JLabel();
        confirmModifyContextButton = new JButton();
        scrollPane1 = new JScrollPane();
        merchantContextTextArea = new JTextArea();
        buttonBar = new JPanel();
        label1 = new JLabel();
        modifyAllButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("\u7f16\u8f91\u6279\u53d1\u5546\u4fe1\u606f");
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

                    //---- label2 ----
                    label2.setText("\u8f93\u5165\u8981\u7f16\u8f91\u7684\u6279\u53d1\u5546\u7684ID\u53f7\u7801");
                    panel1.add(label2);

                    //---- merchantIdTextField ----
                    merchantIdTextField.setColumns(20);
                    panel1.add(merchantIdTextField);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout());

                    //---- label3 ----
                    label3.setText("\u8f93\u5165\u4fee\u6539\u4e4b\u540e\u7684\u540d\u5b57");
                    panel2.add(label3);

                    //---- merchantNameTextField ----
                    merchantNameTextField.setColumns(20);
                    panel2.add(merchantNameTextField);

                    //---- confirmModifyNameButton ----
                    confirmModifyNameButton.setText("\u4fee\u6539");
                    confirmModifyNameButton.addActionListener(e -> modifyName(e));
                    panel2.add(confirmModifyNameButton);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new FlowLayout());

                    //---- label4 ----
                    label4.setText("\u8f93\u5165\u4fee\u6539\u4e4b\u540e\u7684\u8054\u7cfb\u65b9\u5f0f");
                    panel3.add(label4);

                    //---- merchantTelTextField ----
                    merchantTelTextField.setColumns(20);
                    panel3.add(merchantTelTextField);

                    //---- confirmModifyTelButton ----
                    confirmModifyTelButton.setText("\u4fee\u6539");
                    confirmModifyTelButton.addActionListener(e -> modifyTel(e));
                    panel3.add(confirmModifyTelButton);
                }
                contentPanel.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

                    //======== panel5 ========
                    {
                        panel5.setLayout(new FlowLayout());

                        //---- label5 ----
                        label5.setText("\u8f93\u5165\u5907\u6ce8");
                        panel5.add(label5);

                        //---- confirmModifyContextButton ----
                        confirmModifyContextButton.setText("\u4fee\u6539");
                        confirmModifyContextButton.addActionListener(e -> modifyContext(e));
                        panel5.add(confirmModifyContextButton);
                    }
                    panel4.add(panel5);

                    //======== scrollPane1 ========
                    {

                        //---- merchantContextTextArea ----
                        merchantContextTextArea.setRows(2);
                        merchantContextTextArea.setColumns(50);
                        merchantContextTextArea.setTabSize(4);
                        scrollPane1.setViewportView(merchantContextTextArea);
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

                //---- label1 ----
                label1.setText("\u5907\u6ce8\uff1a\u4e00\u6b21\u6027\u5168\u90e8\u8f93\u5165\u4ee5\u4e0a\u5185\u5bb9\uff0c\u7136\u540e\u70b9\u51fb\u201c\u4e00\u6b21\u6027\u786e\u8ba4\u4fee\u6539\u201d\u6309\u94ae");
                buttonBar.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- modifyAllButton ----
                modifyAllButton.setText("\u4e00\u6b21\u6027\u786e\u8ba4\u4fee\u6539");
                modifyAllButton.addActionListener(e -> modifyAll(e));
                buttonBar.add(modifyAllButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JLabel label2;
    private JTextField merchantIdTextField;
    private JPanel panel2;
    private JLabel label3;
    private JTextField merchantNameTextField;
    private JButton confirmModifyNameButton;
    private JPanel panel3;
    private JLabel label4;
    private JTextField merchantTelTextField;
    private JButton confirmModifyTelButton;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel label5;
    private JButton confirmModifyContextButton;
    private JScrollPane scrollPane1;
    private JTextArea merchantContextTextArea;
    private JPanel buttonBar;
    private JLabel label1;
    private JButton modifyAllButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
