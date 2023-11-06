/*
 * Created by JFormDesigner on Sat Jun 24 16:33:55 CST 2023
 */

package com.mingxiang.bookstore.view;

import java.awt.event.*;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.entity.Book;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Mingxiang
 */
public class EditBookDialog extends JDialog {
    private final DatabaseDao dao;

    public EditBookDialog(Window owner, DatabaseDao theDao) {
        super(owner);
        dao = theDao;
        initComponents();
        setVisible(true);
    }

    private void cancel(ActionEvent e) {
        dispose();
    }


    private void confirmModifyBookName(ActionEvent e) {
        int id;
        String idStr=bookIdTextField.getText();
        if(idStr.length()==0){
            JOptionPane.showMessageDialog(this,"请填写id号码再执行修改操作","提示",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的数据类型，书的id号必须是整数字符串", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            dao.editBookName(id, bookNameTextField.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误，修改书名时发生错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmModifyBookType(ActionEvent e) {
        int id;
        String idStr=bookIdTextField.getText();
        if(idStr.length()==0){
            JOptionPane.showMessageDialog(this,"请填写id号码再执行修改操作","提示",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的数据类型，书的id号必须是整数字符串", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            dao.editBookType(id, bookTypeTextField.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误，修改书的类型时发生错误", JOptionPane.ERROR_MESSAGE);
           return;
        }
        JOptionPane.showMessageDialog(this,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmModifyBookAuthor(ActionEvent e) {
        int id;
        String idStr=bookIdTextField.getText();
        if(idStr.length()==0){
            JOptionPane.showMessageDialog(this,"请填写id号码再执行修改操作","提示",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的数据类型，书的id号必须是整数字符串", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            dao.editBookAuthor(id, bookAuthorTextField.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误，修改书籍作者时发生异常", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmModifyPublisher(ActionEvent e) {
        int id;
        String idStr=bookIdTextField.getText();
        if(idStr.length()==0){
            JOptionPane.showMessageDialog(this,"请填写id号码再执行修改操作","提示",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的数据类型，书的id号必须是整数字符串", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            dao.editBookPublisher(id, bookPublisherTextField.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误，修改书籍出版商时出现异常", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmModifyBookContext(ActionEvent e) {
        int id;
        String idStr=bookIdTextField.getText();
        if(idStr.length()==0){
            JOptionPane.showMessageDialog(this,"请填写id号码再执行修改操作","提示",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的数据类型，书的id号必须是整数字符串", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            dao.editBookContext(id, bookContextTextArea.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误，修改书籍的备注时发生异常", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
    }
    private void modifyAll(ActionEvent e) {
        int id;
        String idStr=bookIdTextField.getText();
        if(idStr.length()==0){
            JOptionPane.showMessageDialog(this,"请填写id号码再执行修改操作","提示",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的数据类型，书的id号必须是整数字符串", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String name = bookNameTextField.getText();
        String type = bookTypeTextField.getText();
        String author = bookAuthorTextField.getText();
        String publisher = bookPublisherTextField.getText();
        String context = bookContextTextArea.getText();
        try {
            dao.editBook(id, new Book(name, author, type, publisher, context));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "数据库错误，一次性修改书籍所有信息时发生错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        bookIdTextField = new JTextField();
        panel2 = new JPanel();
        label2 = new JLabel();
        bookNameTextField = new JTextField();
        confirmModifyBookName = new JButton();
        panel3 = new JPanel();
        label3 = new JLabel();
        bookTypeTextField = new JTextField();
        confirmModifyBookType = new JButton();
        panel4 = new JPanel();
        label4 = new JLabel();
        bookAuthorTextField = new JTextField();
        confirmModifyBookAuthor = new JButton();
        panel5 = new JPanel();
        label5 = new JLabel();
        bookPublisherTextField = new JTextField();
        confirmModifyBookPublisher = new JButton();
        panel6 = new JPanel();
        panel7 = new JPanel();
        label6 = new JLabel();
        confirmModifyContext = new JButton();
        scrollPane1 = new JScrollPane();
        bookContextTextArea = new JTextArea();
        buttonBar = new JPanel();
        label7 = new JLabel();
        modifyAllButton = new JButton();
        finishButton = new JButton();

        //======== this ========
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setTitle("\u7f16\u8f91\u4e66\u7c4d\u4fe1\u606f");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout(6, 1, 5, 5));

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout());

                    //---- label1 ----
                    label1.setText("\u8f93\u5165\u8981\u7f16\u8f91\u7684\u4e66\u7684ID\u53f7\u7801");
                    panel1.add(label1);

                    //---- bookIdTextField ----
                    bookIdTextField.setColumns(20);
                    panel1.add(bookIdTextField);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout());

                    //---- label2 ----
                    label2.setText("\u8f93\u5165\u4fee\u6539\u4e4b\u540e\u7684\u4e66\u540d");
                    panel2.add(label2);

                    //---- bookNameTextField ----
                    bookNameTextField.setColumns(20);
                    panel2.add(bookNameTextField);

                    //---- confirmModifyBookName ----
                    confirmModifyBookName.setText("\u786e\u8ba4\u4fee\u6539");
                    confirmModifyBookName.addActionListener(e -> confirmModifyBookName(e));
                    panel2.add(confirmModifyBookName);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new FlowLayout());

                    //---- label3 ----
                    label3.setText("\u8f93\u5165\u4fee\u6539\u4e4b\u540e\u7684\u4e66\u7684\u7c7b\u578b");
                    panel3.add(label3);

                    //---- bookTypeTextField ----
                    bookTypeTextField.setColumns(20);
                    panel3.add(bookTypeTextField);

                    //---- confirmModifyBookType ----
                    confirmModifyBookType.setText("\u786e\u8ba4\u4fee\u6539");
                    confirmModifyBookType.addActionListener(e -> confirmModifyBookType(e));
                    panel3.add(confirmModifyBookType);
                }
                contentPanel.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new FlowLayout());

                    //---- label4 ----
                    label4.setText("\u8f93\u5165\u4fee\u6539\u4e4b\u540e\u7684\u4e66\u7684\u4f5c\u8005");
                    panel4.add(label4);

                    //---- bookAuthorTextField ----
                    bookAuthorTextField.setColumns(20);
                    panel4.add(bookAuthorTextField);

                    //---- confirmModifyBookAuthor ----
                    confirmModifyBookAuthor.setText("\u786e\u8ba4\u4fee\u6539");
                    confirmModifyBookAuthor.addActionListener(e -> confirmModifyBookAuthor(e));
                    panel4.add(confirmModifyBookAuthor);
                }
                contentPanel.add(panel4);

                //======== panel5 ========
                {
                    panel5.setLayout(new FlowLayout());

                    //---- label5 ----
                    label5.setText("\u8f93\u5165\u4fee\u6539\u4e4b\u540e\u7684\u4e66\u7684\u51fa\u7248\u5546");
                    panel5.add(label5);

                    //---- bookPublisherTextField ----
                    bookPublisherTextField.setColumns(20);
                    panel5.add(bookPublisherTextField);

                    //---- confirmModifyBookPublisher ----
                    confirmModifyBookPublisher.setText("\u786e\u8ba4\u4fee\u6539");
                    confirmModifyBookPublisher.addActionListener(e -> confirmModifyPublisher(e));
                    panel5.add(confirmModifyBookPublisher);
                }
                contentPanel.add(panel5);

                //======== panel6 ========
                {
                    panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));

                    //======== panel7 ========
                    {
                        panel7.setLayout(new FlowLayout());

                        //---- label6 ----
                        label6.setText("\u8f93\u5165\u5907\u6ce8");
                        panel7.add(label6);

                        //---- confirmModifyContext ----
                        confirmModifyContext.setText("\u786e\u8ba4\u4fee\u6539");
                        confirmModifyContext.addActionListener(e -> confirmModifyBookContext(e));
                        panel7.add(confirmModifyContext);
                    }
                    panel6.add(panel7);

                    //======== scrollPane1 ========
                    {

                        //---- bookContextTextArea ----
                        bookContextTextArea.setRows(2);
                        bookContextTextArea.setTabSize(4);
                        bookContextTextArea.setColumns(50);
                        scrollPane1.setViewportView(bookContextTextArea);
                    }
                    panel6.add(scrollPane1);
                }
                contentPanel.add(panel6);
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

                //---- modifyAllButton ----
                modifyAllButton.setText("\u4e00\u6b21\u6027\u786e\u8ba4\u4fee\u6539");
                modifyAllButton.addActionListener(e -> modifyAll(e));
                buttonBar.add(modifyAllButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- finishButton ----
                finishButton.setText("\u53d6\u6d88");
                finishButton.addActionListener(e -> cancel(e));
                buttonBar.add(finishButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
    private JTextField bookIdTextField;
    private JPanel panel2;
    private JLabel label2;
    private JTextField bookNameTextField;
    private JButton confirmModifyBookName;
    private JPanel panel3;
    private JLabel label3;
    private JTextField bookTypeTextField;
    private JButton confirmModifyBookType;
    private JPanel panel4;
    private JLabel label4;
    private JTextField bookAuthorTextField;
    private JButton confirmModifyBookAuthor;
    private JPanel panel5;
    private JLabel label5;
    private JTextField bookPublisherTextField;
    private JButton confirmModifyBookPublisher;
    private JPanel panel6;
    private JPanel panel7;
    private JLabel label6;
    private JButton confirmModifyContext;
    private JScrollPane scrollPane1;
    private JTextArea bookContextTextArea;
    private JPanel buttonBar;
    private JLabel label7;
    private JButton modifyAllButton;
    private JButton finishButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
