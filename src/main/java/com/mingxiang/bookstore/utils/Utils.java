package com.mingxiang.bookstore.utils;

import com.mingxiang.bookstore.view.renderer.MyTableRenderer;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 封装一些全局函数
 *
 * @author Mingxiang
 */
public class Utils {

    public static void setTable(JTable table, ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metaData.getColumnName(i + 1);
        }
        DefaultTableModel tableDataModel = new DefaultTableModel();
        for (String columnName : columnNames) {
            tableDataModel.addColumn(columnName);
        }
        while (resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                rowData[i] = resultSet.getObject(i + 1);
            }
            tableDataModel.addRow(rowData);
        }
        table.setModel(tableDataModel);
        table.setDefaultRenderer(Object.class, new MyTableRenderer());

    }

    public static boolean isLeapYear(String year)throws NumberFormatException{
        int theYear=Integer.parseInt(year);
        if (theYear % 4 == 0) {
            if (theYear % 100 == 0) {
                return theYear % 400 == 0; // 整百年能被400整除的是闰年
            } else {
                return true; // 非整百年能被4整除的是闰年
            }
        } else {
            return false;
        }
    }

    public static String[] getTimeStampStr(JTextField yearTextField,
                                         JComboBox<String>monthComboBox,
                                         JComboBox<String>dayComboBox,
                                         JComboBox<String>hourComboBox,
                                         JComboBox<String>minuteComboBox,
                                         JComboBox<String>secondComboBox
    ){
        String year = yearTextField.getText();
        String month = (String) monthComboBox.getSelectedItem();
        String day = (String) dayComboBox.getSelectedItem();
        String hour = (String) hourComboBox.getSelectedItem();
        String minute = (String) minuteComboBox.getSelectedItem();
        String second = (String) secondComboBox.getSelectedItem();

        return new String[]{
                year,
                month,
                day,
                hour,
                minute,
                second
        };
    }
}
