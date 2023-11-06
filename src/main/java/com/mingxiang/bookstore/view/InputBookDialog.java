/*
 * Created by JFormDesigner on Mon Jun 26 14:23:26 CST 2023
 */

package com.mingxiang.bookstore.view;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.entity.Book;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Mingxiang
 */
public class InputBookDialog extends JDialog {
    private final DatabaseDao dao;

    public InputBookDialog(Window owner, DatabaseDao theDao) {
        super(owner);
        dao = theDao;
        initComponents();
        setVisible(true);
    }

    private void ok(ActionEvent e) {
        String bookName = bookNameTextField.getText();
        String bookType = typeTextField.getText();
        String author = authorTextField.getText();
        String publisher = publisherTextField.getText();
        String context = contextTextArea.getText();
        if (bookName.length() == 0 || bookType.length() == 0 || author.length() == 0 || publisher.length() == 0) {
            int result = JOptionPane.showConfirmDialog(this, "必须全部输入内容才能录入", "警告", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.OK_OPTION)
                return;
        }
        try {
            Book book = new Book(bookName, author, bookType, publisher, context);
            dao.inputBook(book);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误，插入书籍时发生错误", JOptionPane.ERROR_MESSAGE);
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
        // Generated using JFormDesigner Evaluation license - JackStanton
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        bookLabel = new JLabel();
        bookNameTextField = new JTextField();
        panel2 = new JPanel();
        authorLabel = new JLabel();
        authorTextField = new JTextField();
        panel3 = new JPanel();
        bookTypeLabel = new JLabel();
        typeTextField = new JTextField();
        panel4 = new JPanel();
        publisherLabel = new JLabel();
        publisherTextField = new JTextField();
        panel9 = new JPanel();
        panel10 = new JPanel();
        label6 = new JLabel();
        scrollPane2 = new JScrollPane();
        contextTextArea = new JTextArea();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("\u5f55\u5165\u4e66\u7c4d\u57fa\u672c\u4fe1\u606f");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
            .border.EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder
            .CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.
            awt.Font.BOLD,12),java.awt.Color.red),dialogPane. getBorder()))
            ;dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("bord\u0065r".equals(e.getPropertyName()))throw new RuntimeException();}})
            ;
            dialogPane.setLayout(new BoxLayout(dialogPane, BoxLayout.Y_AXIS));

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout(5, 1, 5, 5));

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout());

                    //---- bookLabel ----
                    bookLabel.setText("\u8f93\u5165\u4e66\u540d");
                    panel1.add(bookLabel);

                    //---- bookNameTextField ----
                    bookNameTextField.setColumns(20);
                    panel1.add(bookNameTextField);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout());

                    //---- authorLabel ----
                    authorLabel.setText("\u8f93\u5165\u4f5c\u8005\u540d");
                    panel2.add(authorLabel);

                    //---- authorTextField ----
                    authorTextField.setColumns(20);
                    panel2.add(authorTextField);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new FlowLayout());

                    //---- bookTypeLabel ----
                    bookTypeLabel.setText("\u8f93\u5165\u4e66\u7684\u7c7b\u578b");
                    panel3.add(bookTypeLabel);

                    //---- typeTextField ----
                    typeTextField.setColumns(20);
                    panel3.add(typeTextField);
                }
                contentPanel.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new FlowLayout());

                    //---- publisherLabel ----
                    publisherLabel.setText("\u8f93\u5165\u51fa\u7248\u5546\u540d");
                    panel4.add(publisherLabel);

                    //---- publisherTextField ----
                    publisherTextField.setColumns(20);
                    panel4.add(publisherTextField);
                }
                contentPanel.add(panel4);

                //======== panel9 ========
                {
                    panel9.setLayout(new BoxLayout(panel9, BoxLayout.Y_AXIS));

                    //======== panel10 ========
                    {
                        panel10.setLayout(new FlowLayout());

                        //---- label6 ----
                        label6.setText("\u8f93\u5165\u4e66\u7c4d\u5907\u6ce8");
                        panel10.add(label6);
                    }
                    panel9.add(panel10);

                    //======== scrollPane2 ========
                    {

                        //---- contextTextArea ----
                        contextTextArea.setColumns(50);
                        contextTextArea.setRows(2);
                        contextTextArea.setTabSize(4);
                        scrollPane2.setViewportView(contextTextArea);
                    }
                    panel9.add(scrollPane2);
                }
                contentPanel.add(panel9);
            }
            dialogPane.add(contentPanel);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("\u786e\u8ba4");
                okButton.addActionListener(e -> ok(e));
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
            dialogPane.add(buttonBar);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - JackStanton
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel bookLabel;
    private JTextField bookNameTextField;
    private JPanel panel2;
    private JLabel authorLabel;
    private JTextField authorTextField;
    private JPanel panel3;
    private JLabel bookTypeLabel;
    private JTextField typeTextField;
    private JPanel panel4;
    private JLabel publisherLabel;
    private JTextField publisherTextField;
    private JPanel panel9;
    private JPanel panel10;
    private JLabel label6;
    private JScrollPane scrollPane2;
    private JTextArea contextTextArea;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
