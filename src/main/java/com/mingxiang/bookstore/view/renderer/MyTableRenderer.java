package com.mingxiang.bookstore.view.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MyTableRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // 根据行号设置不同的背景色
        if (row % 2 == 0) {
            component.setBackground(Color.WHITE);
        } else {
            component.setBackground(Color.LIGHT_GRAY);
        }

        return component;
    }
}
