/*
 * Created by JFormDesigner on Thu Jun 29 09:48:05 CST 2023
 */

package com.mingxiang.bookstore.view;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.utils.Utils;
import com.mingxiang.bookstore.view.renderer.MyTableRenderer;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.spi.CalendarDataProvider;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Mingxiang
 */
public class SelectInoutScheduleDialog extends JDialog {
    private final DatabaseDao dao;

    public SelectInoutScheduleDialog(Window owner, DatabaseDao theDao) {
        super(owner);
        dao = theDao;
        initComponents();
        selfSetComponents();
        setVisible(true);
    }

    /**
     * 自定义设置组件的相关内容，非designer生成的代码
     */
    private void selfSetComponents() {
        logTable.setDefaultRenderer(Object.class, new MyTableRenderer());
    }

    private static final String[] dates_31 = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28",
            "29",
            "30",
            "31"
    };
    private static final String[] dates_30 = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28",
            "29",
            "30"
    };

    private static final String[] dates_29 = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28",
            "29"
    };
    private static final String[] dates_28 = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28"
    };


    private void searchLog(ActionEvent e) {

        if (Objects.equals(saleOrPurchaseComboBox.getSelectedItem(), "") || beginYearTextField.getText().equals("") || Objects.equals(beginMonthComboBox.getSelectedItem(), "") || Objects.equals(beginDayComboBox.getSelectedItem(), "") || Objects.equals(beginHourComboBox.getSelectedItem(), "") || Objects.equals(beignMiniuteComboBox.getSelectedItem(), "") || Objects.equals(beginSecondComboBox.getSelectedItem(), "") ||
                endYearTextField.getText().equals("") || Objects.equals(endMonthComboBox.getSelectedItem(), "") || Objects.equals(endDayComboBox.getSelectedItem(), "") || Objects.equals(endHourComboBox.getSelectedItem(), "") || Objects.equals(endMiniuteComboBox.getSelectedItem(), "") || Objects.equals(endSecondComboBox.getSelectedItem(), "")) {
            JOptionPane.showMessageDialog(this, "请设定全部选项后再执行查询操作");
            return;
        }

        String[] beginTimeStampStr = Utils.getTimeStampStr(beginYearTextField, beginMonthComboBox, beginDayComboBox, beginHourComboBox, beignMiniuteComboBox, beginSecondComboBox);
        String[] endTimeStampStr = Utils.getTimeStampStr(endYearTextField, endMonthComboBox, endDayComboBox, endHourComboBox, endMiniuteComboBox, endSecondComboBox);

        LocalDateTime beginTime = LocalDateTime.of(Integer.parseInt(beginTimeStampStr[0]), Integer.parseInt(beginTimeStampStr[1]), Integer.parseInt(beginTimeStampStr[2]), Integer.parseInt(beginTimeStampStr[3]), Integer.parseInt(beginTimeStampStr[4]), Integer.parseInt(beginTimeStampStr[5]), 0);
        long beginTimeMillis = beginTime.toEpochSecond(ZoneOffset.UTC) * 1000;
        LocalDateTime endTime = LocalDateTime.of(Integer.parseInt(endTimeStampStr[0]), Integer.parseInt(endTimeStampStr[1]), Integer.parseInt(endTimeStampStr[2]), Integer.parseInt(endTimeStampStr[3]), Integer.parseInt(endTimeStampStr[4]), Integer.parseInt(endTimeStampStr[5]), 0);
        long enbTimeMillis = endTime.toEpochSecond(ZoneOffset.UTC) * 1000;

        Timestamp beginStamp = new Timestamp(beginTimeMillis);
        Timestamp endStamp = new Timestamp(enbTimeMillis);
        Timestamp nowStamp = new Timestamp(System.currentTimeMillis());

        if (beginStamp.getTime() > endStamp.getTime()) {
            JOptionPane.showMessageDialog(this, "起始时间不能大于终止时间", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (endStamp.getTime() > nowStamp.getTime()) {
            JOptionPane.showMessageDialog(this, "终止时间不能大于现在的时间", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ResultSet set;
            if (Objects.equals(saleOrPurchaseComboBox.getSelectedItem(), "销售")) {
                set = dao.getInoutSchedule_out(beginStamp, endStamp);
                Utils.setTable(logTable, set);
            } else if (Objects.equals(saleOrPurchaseComboBox.getSelectedItem(), "进货")) {
                set = dao.getInoutSchedule_in(beginStamp, endStamp);
                Utils.setTable(logTable, set);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    private void cancel(ActionEvent e) {
        dispose();
    }

    private void changeDayComboBox(JTextField yearComboBox, JComboBox<String> monthComboBox, JComboBox<String> dayComboBox) {
        String selectedItem = (String) monthComboBox.getSelectedItem();

        if (selectedItem.equals("1") ||
                selectedItem.equals("3") ||
                selectedItem.equals("5") ||
                selectedItem.equals("7") ||
                selectedItem.equals("8") ||
                selectedItem.equals("10") ||
                selectedItem.equals("12")
        ) {
            //将一个月的天数设为31天
            dayComboBox.removeAllItems();
            for (String s : dates_31) dayComboBox.addItem(s);
        } else if (selectedItem.equals("4") ||
                selectedItem.equals("6") ||
                selectedItem.equals("9") ||
                selectedItem.equals("11")
        ) {
            //将一个月的天数设为30天
            dayComboBox.removeAllItems();
            for (String s : dates_30) dayComboBox.addItem(s);
        } else if (selectedItem.equals("2")) {
            if (Utils.isLeapYear(yearComboBox.getText())) {
                //当前年份是闰年，二月为29天
                dayComboBox.removeAllItems();
                for (String s : dates_29) dayComboBox.addItem(s);
            } else {
                //当前年份不是闰年，二月为28天
                dayComboBox.removeAllItems();
                for (String s : dates_28) dayComboBox.addItem(s);
            }
        }
    }


    private void endMonthChange(ActionEvent e) {
        // 根据月份的改变修改日期的选择，每个月的天数不一
        changeDayComboBox(endYearTextField, endMonthComboBox, endDayComboBox);
    }

    private void beginMonthChange(ActionEvent e) {
        changeDayComboBox(beginYearTextField, beginMonthComboBox, beginDayComboBox);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel19 = new JPanel();
        panel21 = new JPanel();
        label16 = new JLabel();
        panel5 = new JPanel();
        label1 = new JLabel();
        saleOrPurchaseComboBox = new JComboBox<>();
        panel20 = new JPanel();
        panel1 = new JPanel();
        panel3 = new JPanel();
        label4 = new JLabel();
        beginYearTextField = new JTextField();
        panel4 = new JPanel();
        label5 = new JLabel();
        beginMonthComboBox = new JComboBox<>();
        panel8 = new JPanel();
        label6 = new JLabel();
        beginDayComboBox = new JComboBox<>();
        panel9 = new JPanel();
        label7 = new JLabel();
        beginHourComboBox = new JComboBox<>();
        panel10 = new JPanel();
        label8 = new JLabel();
        beignMiniuteComboBox = new JComboBox<>();
        panel11 = new JPanel();
        label9 = new JLabel();
        beginSecondComboBox = new JComboBox<>();
        panel12 = new JPanel();
        panel13 = new JPanel();
        label10 = new JLabel();
        endYearTextField = new JTextField();
        panel14 = new JPanel();
        label11 = new JLabel();
        endMonthComboBox = new JComboBox<>();
        panel15 = new JPanel();
        label12 = new JLabel();
        endDayComboBox = new JComboBox<>();
        panel16 = new JPanel();
        label13 = new JLabel();
        endHourComboBox = new JComboBox<>();
        panel17 = new JPanel();
        label14 = new JLabel();
        endMiniuteComboBox = new JComboBox<>();
        panel18 = new JPanel();
        label15 = new JLabel();
        endSecondComboBox = new JComboBox<>();
        panel22 = new JPanel();
        searchButton = new JButton();
        panel2 = new JPanel();
        panel7 = new JPanel();
        label3 = new JLabel();
        panel6 = new JPanel();
        scrollPane1 = new JScrollPane();
        logTable = new JTable();
        buttonBar = new JPanel();
        exitButton = new JButton();

        //======== this ========
        setTitle("\u67e5\u8be2\u660e\u7ec6");
        setResizable(false);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

                //======== panel19 ========
                {
                    panel19.setLayout(new BoxLayout(panel19, BoxLayout.Y_AXIS));

                    //======== panel21 ========
                    {
                        panel21.setLayout(new FlowLayout());

                        //---- label16 ----
                        label16.setText("\u5de6\u4fa7\u8f93\u5165\u8d77\u59cb\u65f6\u95f4  \u53f3\u4fa7\u8f93\u5165\u7ec8\u6b62\u65f6\u95f4");
                        panel21.add(label16);
                    }
                    panel19.add(panel21);

                    //======== panel5 ========
                    {
                        panel5.setLayout(new FlowLayout());

                        //---- label1 ----
                        label1.setText("\u9009\u62e9\u67e5\u8be2\u8fdb\u8d27\u8ba2\u5355\u8fd8\u662f\u9500\u552e\u8ba2\u5355");
                        panel5.add(label1);

                        //---- saleOrPurchaseComboBox ----
                        saleOrPurchaseComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                            "\u9500\u552e",
                            "\u8fdb\u8d27"
                        }));
                        panel5.add(saleOrPurchaseComboBox);
                    }
                    panel19.add(panel5);

                    //======== panel20 ========
                    {
                        panel20.setLayout(new FlowLayout());

                        //======== panel1 ========
                        {
                            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                            //======== panel3 ========
                            {
                                panel3.setLayout(new FlowLayout());

                                //---- label4 ----
                                label4.setText("\u5e74");
                                panel3.add(label4);

                                //---- beginYearTextField ----
                                beginYearTextField.setColumns(5);
                                panel3.add(beginYearTextField);
                            }
                            panel1.add(panel3);

                            //======== panel4 ========
                            {
                                panel4.setLayout(new FlowLayout());

                                //---- label5 ----
                                label5.setText("\u6708");
                                panel4.add(label5);

                                //---- beginMonthComboBox ----
                                beginMonthComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1",
                                    "2",
                                    "3",
                                    "4",
                                    "5",
                                    "6",
                                    "7",
                                    "8",
                                    "9",
                                    "10",
                                    "11",
                                    "12"
                                }));
                                beginMonthComboBox.addActionListener(e -> beginMonthChange(e));
                                panel4.add(beginMonthComboBox);
                            }
                            panel1.add(panel4);

                            //======== panel8 ========
                            {
                                panel8.setLayout(new FlowLayout());

                                //---- label6 ----
                                label6.setText("\u65e5");
                                panel8.add(label6);

                                //---- beginDayComboBox ----
                                beginDayComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1"
                                }));
                                panel8.add(beginDayComboBox);
                            }
                            panel1.add(panel8);

                            //======== panel9 ========
                            {
                                panel9.setLayout(new FlowLayout());

                                //---- label7 ----
                                label7.setText("\u5c0f\u65f6");
                                panel9.add(label7);

                                //---- beginHourComboBox ----
                                beginHourComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1",
                                    "2",
                                    "3",
                                    "4",
                                    "5",
                                    "6",
                                    "7",
                                    "8",
                                    "9",
                                    "10",
                                    "11",
                                    "12",
                                    "13",
                                    "14",
                                    "15",
                                    "16",
                                    "17",
                                    "18",
                                    "19",
                                    "20",
                                    "21",
                                    "22",
                                    "23",
                                    "24"
                                }));
                                panel9.add(beginHourComboBox);
                            }
                            panel1.add(panel9);

                            //======== panel10 ========
                            {
                                panel10.setLayout(new FlowLayout());

                                //---- label8 ----
                                label8.setText("\u5206\u949f");
                                panel10.add(label8);

                                //---- beignMiniuteComboBox ----
                                beignMiniuteComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1",
                                    "2",
                                    "3",
                                    "4",
                                    "5",
                                    "6",
                                    "7",
                                    "8",
                                    "9",
                                    "10",
                                    "11",
                                    "12",
                                    "13",
                                    "14",
                                    "15",
                                    "16",
                                    "17",
                                    "18",
                                    "19",
                                    "20",
                                    "21",
                                    "22",
                                    "23",
                                    "24",
                                    "25",
                                    "26",
                                    "27",
                                    "28",
                                    "29",
                                    "30",
                                    "31",
                                    "32",
                                    "33",
                                    "34",
                                    "35",
                                    "36",
                                    "37",
                                    "38",
                                    "39",
                                    "40",
                                    "41",
                                    "42",
                                    "43",
                                    "44",
                                    "45",
                                    "46",
                                    "47",
                                    "48",
                                    "49",
                                    "50",
                                    "51",
                                    "52",
                                    "53",
                                    "54",
                                    "55",
                                    "56",
                                    "57",
                                    "58",
                                    "59",
                                    "60"
                                }));
                                panel10.add(beignMiniuteComboBox);
                            }
                            panel1.add(panel10);

                            //======== panel11 ========
                            {
                                panel11.setLayout(new FlowLayout());

                                //---- label9 ----
                                label9.setText("\u79d2");
                                panel11.add(label9);

                                //---- beginSecondComboBox ----
                                beginSecondComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1",
                                    "2",
                                    "3",
                                    "4",
                                    "5",
                                    "6",
                                    "7",
                                    "8",
                                    "9",
                                    "10",
                                    "11",
                                    "12",
                                    "13",
                                    "14",
                                    "15",
                                    "16",
                                    "17",
                                    "18",
                                    "19",
                                    "20",
                                    "21",
                                    "22",
                                    "23",
                                    "24",
                                    "25",
                                    "26",
                                    "27",
                                    "28",
                                    "29",
                                    "30",
                                    "31",
                                    "32",
                                    "33",
                                    "34",
                                    "35",
                                    "36",
                                    "37",
                                    "38",
                                    "39",
                                    "40",
                                    "41",
                                    "42",
                                    "43",
                                    "44",
                                    "45",
                                    "46",
                                    "47",
                                    "48",
                                    "49",
                                    "50",
                                    "51",
                                    "52",
                                    "53",
                                    "54",
                                    "55",
                                    "56",
                                    "57",
                                    "58",
                                    "59",
                                    "60"
                                }));
                                panel11.add(beginSecondComboBox);
                            }
                            panel1.add(panel11);
                        }
                        panel20.add(panel1);

                        //======== panel12 ========
                        {
                            panel12.setLayout(new BoxLayout(panel12, BoxLayout.Y_AXIS));

                            //======== panel13 ========
                            {
                                panel13.setLayout(new FlowLayout());

                                //---- label10 ----
                                label10.setText("\u5e74");
                                panel13.add(label10);

                                //---- endYearTextField ----
                                endYearTextField.setColumns(5);
                                panel13.add(endYearTextField);
                            }
                            panel12.add(panel13);

                            //======== panel14 ========
                            {
                                panel14.setLayout(new FlowLayout());

                                //---- label11 ----
                                label11.setText("\u6708");
                                panel14.add(label11);

                                //---- endMonthComboBox ----
                                endMonthComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1",
                                    "2",
                                    "3",
                                    "4",
                                    "5",
                                    "6",
                                    "7",
                                    "8",
                                    "9",
                                    "10",
                                    "11",
                                    "12"
                                }));
                                endMonthComboBox.addActionListener(e -> endMonthChange(e));
                                panel14.add(endMonthComboBox);
                            }
                            panel12.add(panel14);

                            //======== panel15 ========
                            {
                                panel15.setLayout(new FlowLayout());

                                //---- label12 ----
                                label12.setText("\u65e5");
                                panel15.add(label12);

                                //---- endDayComboBox ----
                                endDayComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1"
                                }));
                                panel15.add(endDayComboBox);
                            }
                            panel12.add(panel15);

                            //======== panel16 ========
                            {
                                panel16.setLayout(new FlowLayout());

                                //---- label13 ----
                                label13.setText("\u5c0f\u65f6");
                                panel16.add(label13);

                                //---- endHourComboBox ----
                                endHourComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1",
                                    "2",
                                    "3",
                                    "4",
                                    "5",
                                    "6",
                                    "7",
                                    "8",
                                    "9",
                                    "10",
                                    "11",
                                    "12",
                                    "13",
                                    "14",
                                    "15",
                                    "16",
                                    "17",
                                    "18",
                                    "19",
                                    "20",
                                    "21",
                                    "22",
                                    "23",
                                    "24"
                                }));
                                panel16.add(endHourComboBox);
                            }
                            panel12.add(panel16);

                            //======== panel17 ========
                            {
                                panel17.setLayout(new FlowLayout());

                                //---- label14 ----
                                label14.setText("\u5206\u949f");
                                panel17.add(label14);

                                //---- endMiniuteComboBox ----
                                endMiniuteComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1",
                                    "2",
                                    "3",
                                    "4",
                                    "5",
                                    "6",
                                    "7",
                                    "8",
                                    "9",
                                    "10",
                                    "11",
                                    "12",
                                    "13",
                                    "14",
                                    "15",
                                    "16",
                                    "17",
                                    "18",
                                    "19",
                                    "20",
                                    "21",
                                    "22",
                                    "23",
                                    "24",
                                    "25",
                                    "26",
                                    "27",
                                    "28",
                                    "29",
                                    "30",
                                    "31",
                                    "32",
                                    "33",
                                    "34",
                                    "35",
                                    "36",
                                    "37",
                                    "38",
                                    "39",
                                    "40",
                                    "41",
                                    "42",
                                    "43",
                                    "44",
                                    "45",
                                    "46",
                                    "47",
                                    "48",
                                    "49",
                                    "50",
                                    "51",
                                    "52",
                                    "53",
                                    "54",
                                    "55",
                                    "56",
                                    "57",
                                    "58",
                                    "59",
                                    "60"
                                }));
                                panel17.add(endMiniuteComboBox);
                            }
                            panel12.add(panel17);

                            //======== panel18 ========
                            {
                                panel18.setLayout(new FlowLayout());

                                //---- label15 ----
                                label15.setText("\u79d2");
                                panel18.add(label15);

                                //---- endSecondComboBox ----
                                endSecondComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                                    "1",
                                    "2",
                                    "3",
                                    "4",
                                    "5",
                                    "6",
                                    "7",
                                    "8",
                                    "9",
                                    "10",
                                    "11",
                                    "12",
                                    "13",
                                    "14",
                                    "15",
                                    "16",
                                    "17",
                                    "18",
                                    "19",
                                    "20",
                                    "21",
                                    "22",
                                    "23",
                                    "24",
                                    "25",
                                    "26",
                                    "27",
                                    "28",
                                    "29",
                                    "30",
                                    "31",
                                    "32",
                                    "33",
                                    "34",
                                    "35",
                                    "36",
                                    "37",
                                    "38",
                                    "39",
                                    "40",
                                    "41",
                                    "42",
                                    "43",
                                    "44",
                                    "45",
                                    "46",
                                    "47",
                                    "48",
                                    "49",
                                    "50",
                                    "51",
                                    "52",
                                    "53",
                                    "54",
                                    "55",
                                    "56",
                                    "57",
                                    "58",
                                    "59",
                                    "60"
                                }));
                                panel18.add(endSecondComboBox);
                            }
                            panel12.add(panel18);
                        }
                        panel20.add(panel12);
                    }
                    panel19.add(panel20);

                    //======== panel22 ========
                    {
                        panel22.setLayout(new FlowLayout());

                        //---- searchButton ----
                        searchButton.setText("\u67e5\u8be2");
                        searchButton.addActionListener(e -> searchLog(e));
                        panel22.add(searchButton);
                    }
                    panel19.add(panel22);
                }
                contentPanel.add(panel19);

                //======== panel2 ========
                {
                    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

                    //======== panel7 ========
                    {
                        panel7.setLayout(new FlowLayout());

                        //---- label3 ----
                        label3.setText("\u7b5b\u9009\u4e4b\u540e\u7684\u660e\u7ec6\u8868");
                        panel7.add(label3);
                    }
                    panel2.add(panel7);

                    //======== panel6 ========
                    {
                        panel6.setLayout(new FlowLayout());

                        //======== scrollPane1 ========
                        {
                            scrollPane1.setViewportView(logTable);
                        }
                        panel6.add(scrollPane1);
                    }
                    panel2.add(panel6);
                }
                contentPanel.add(panel2);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- exitButton ----
                exitButton.setText("\u9000\u51fa\u67e5\u8be2");
                exitButton.addActionListener(e -> cancel(e));
                buttonBar.add(exitButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JPanel panel19;
    private JPanel panel21;
    private JLabel label16;
    private JPanel panel5;
    private JLabel label1;
    private JComboBox<String> saleOrPurchaseComboBox;
    private JPanel panel20;
    private JPanel panel1;
    private JPanel panel3;
    private JLabel label4;
    private JTextField beginYearTextField;
    private JPanel panel4;
    private JLabel label5;
    private JComboBox<String> beginMonthComboBox;
    private JPanel panel8;
    private JLabel label6;
    private JComboBox<String> beginDayComboBox;
    private JPanel panel9;
    private JLabel label7;
    private JComboBox<String> beginHourComboBox;
    private JPanel panel10;
    private JLabel label8;
    private JComboBox<String> beignMiniuteComboBox;
    private JPanel panel11;
    private JLabel label9;
    private JComboBox<String> beginSecondComboBox;
    private JPanel panel12;
    private JPanel panel13;
    private JLabel label10;
    private JTextField endYearTextField;
    private JPanel panel14;
    private JLabel label11;
    private JComboBox<String> endMonthComboBox;
    private JPanel panel15;
    private JLabel label12;
    private JComboBox<String> endDayComboBox;
    private JPanel panel16;
    private JLabel label13;
    private JComboBox<String> endHourComboBox;
    private JPanel panel17;
    private JLabel label14;
    private JComboBox<String> endMiniuteComboBox;
    private JPanel panel18;
    private JLabel label15;
    private JComboBox<String> endSecondComboBox;
    private JPanel panel22;
    private JButton searchButton;
    private JPanel panel2;
    private JPanel panel7;
    private JLabel label3;
    private JPanel panel6;
    private JScrollPane scrollPane1;
    private JTable logTable;
    private JPanel buttonBar;
    private JButton exitButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
