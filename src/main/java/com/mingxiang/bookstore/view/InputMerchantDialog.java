/*
 * Created by JFormDesigner on Sun Jun 25 21:51:27 CST 2023
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
public class InputMerchantDialog extends JDialog {
    private final DatabaseDao dao;

    public InputMerchantDialog(Window owner, DatabaseDao theDao) {
        super(owner);
        dao = theDao;
        initComponents();
        setVisible(true);
    }

    private void ok(ActionEvent e) {
        String merchantName = merchantNameTextField.getText();
        String merchantTel = merchantTelTextField.getText();
        String merchantContext = merchantContextTextArea.getText();
        if (merchantName.length() == 0 || merchantTel.length() == 0 || merchantContext.length() == 0) {
            JOptionPane.showMessageDialog(this, "必须全部输入内容才能录入", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Merchant merchant = new Merchant(merchantName,merchantTel,merchantContext);
            dao.inputWholesaler(merchant);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
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
        merchantNameTextField = new JTextField();
        panel2 = new JPanel();
        label2 = new JLabel();
        merchantTelTextField = new JTextField();
        panel3 = new JPanel();
        panel4 = new JPanel();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        merchantContextTextArea = new JTextArea();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("\u65b0\u5efa\u6279\u53d1\u5546");
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
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
                    label1.setText("\u8f93\u5165\u6279\u53d1\u5546\u540d\u79f0");
                    panel1.add(label1);

                    //---- merchantNameTextField ----
                    merchantNameTextField.setColumns(20);
                    panel1.add(merchantNameTextField);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout());

                    //---- label2 ----
                    label2.setText("\u8f93\u5165\u6279\u53d1\u5546\u8054\u7cfb\u65b9\u5f0f");
                    panel2.add(label2);

                    //---- merchantTelTextField ----
                    merchantTelTextField.setColumns(20);
                    panel2.add(merchantTelTextField);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                    //======== panel4 ========
                    {
                        panel4.setLayout(new FlowLayout());

                        //---- label3 ----
                        label3.setText("\u8f93\u5165\u6279\u53d1\u5546\u5907\u6ce8");
                        panel4.add(label3);
                    }
                    panel3.add(panel4);

                    //======== scrollPane1 ========
                    {

                        //---- merchantContextTextArea ----
                        merchantContextTextArea.setRows(2);
                        merchantContextTextArea.setColumns(50);
                        merchantContextTextArea.setTabSize(4);
                        scrollPane1.setViewportView(merchantContextTextArea);
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
    private JPanel panel1;
    private JLabel label1;
    private JTextField merchantNameTextField;
    private JPanel panel2;
    private JLabel label2;
    private JTextField merchantTelTextField;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTextArea merchantContextTextArea;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
