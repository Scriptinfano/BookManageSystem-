/*
 * Created by JFormDesigner on Sun Jun 25 14:24:53 CST 2023
 */

package com.mingxiang.bookstore.view;

import java.awt.event.*;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.entity.PurchaseOrder;

import java.awt.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Mingxiang
 */
public class PurchaseOrderDialog extends JDialog {
    private final DatabaseDao dao;

    public PurchaseOrderDialog(Window owner, DatabaseDao theDao) {
        super(owner);
        dao = theDao;
        initComponents();
        setVisible(true);
    }


    private int purchaseId;

    public int getPurchaseId() {
        return purchaseId;
    }


    private void ok(ActionEvent event) {
        int theMerchantId;
        String idStr = merchantIdTextField.getText();
        if (idStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "进货商的id号不能为空，请输入进货商的id号", "错误", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            theMerchantId = Integer.parseInt(idStr);
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "输入ID号时请输入整型数据，请重新输入", "错误", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String context = contextTextArea.getText();
        String stampString = dateTextField.getText();
        if (stampString.length() == 0) {
            JOptionPane.showMessageDialog(this, "请输入正确的创建日期或勾选自动写入日期框，请重新输入", "错误", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Timestamp timestamp;
        try {
            timestamp = Timestamp.valueOf(stampString);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "输入的日期不符合要求，请重新输入", "错误", JOptionPane.WARNING_MESSAGE);
            return;
        }
        PurchaseOrder order = new PurchaseOrder(theMerchantId, timestamp, context);
        dao.setAutoCommit(false);
        try {
            dao.createPurchaseOrder(order);
            purchaseId = dao.getLastPurchaseId();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "数据库错误，在创建销售订单时发生错误，重新检查填写项目", JOptionPane.ERROR_MESSAGE);
            dao.rollback();
            dao.setAutoCommit(true);
            return;
        }
        dao.commit();
        dao.setAutoCommit(true);
        new PurchaseDetailOrderDialog(this, dao,purchaseId);
    }

    private void cancel(ActionEvent e) {
        dispose();
    }

    private void checkBoxStateChanged(ItemEvent e) {
        if (checkBox.isSelected()) {
            dateTextField.setEnabled(false);
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            dateTextField.setText(stamp.toString());
        } else {
            dateTextField.setText("");
            dateTextField.setEnabled(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        merchantIdTextField = new JTextField();
        panel2 = new JPanel();
        checkBox = new JCheckBox();
        dateTextField = new JTextField();
        label2 = new JLabel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        contextTextArea = new JTextArea();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("\u65b0\u5efa\u8fdb\u8d27\u8ba2\u5355");
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
                    label1.setText("\u8f93\u5165\u8fdb\u8d27\u5546\u7684ID\u53f7\u7801");
                    panel1.add(label1);

                    //---- merchantIdTextField ----
                    merchantIdTextField.setColumns(10);
                    panel1.add(merchantIdTextField);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout());

                    //---- checkBox ----
                    checkBox.setText("\u662f\u5426\u81ea\u52a8\u751f\u6210\u65e5\u671f");
                    checkBox.addItemListener(e -> checkBoxStateChanged(e));
                    panel2.add(checkBox);

                    //---- dateTextField ----
                    dateTextField.setColumns(10);
                    panel2.add(dateTextField);

                    //---- label2 ----
                    label2.setText("\u52fe\u9009\u8be5\u9009\u9879\u5c06\u5f53\u524d\u65f6\u95f4\u8bbe\u4e3a\u8ba2\u5355\u521b\u5efa\u65f6\u95f4");
                    panel2.add(label2);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                    //======== panel4 ========
                    {
                        panel4.setLayout(new FlowLayout());

                        //---- label3 ----
                        label3.setText("\u8f93\u5165\u5907\u6ce8");
                        panel4.add(label3);
                    }
                    panel3.add(panel4);

                    //======== scrollPane1 ========
                    {

                        //---- contextTextArea ----
                        contextTextArea.setRows(2);
                        contextTextArea.setColumns(50);
                        contextTextArea.setTabSize(4);
                        scrollPane1.setViewportView(contextTextArea);
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

                //---- okButton ----
                okButton.setText("\u786e\u8ba4\u5e76\u9009\u62e9\u8fdb\u8d27\u7684\u4e66\u53f7\u53ca\u6570\u91cf");
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
    private JPanel panel1;
    private JLabel label1;
    private JTextField merchantIdTextField;
    private JPanel panel2;
    private JCheckBox checkBox;
    private JTextField dateTextField;
    private JLabel label2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTextArea contextTextArea;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


    public JTextField getMerchantIdTextField() {
        return merchantIdTextField;
    }

    public JTextField getDateTextField() {
        return dateTextField;
    }
}
