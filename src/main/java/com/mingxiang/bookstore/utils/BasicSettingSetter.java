package com.mingxiang.bookstore.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BasicSettingSetter {
    /**
     * 将位置设置在中间，并指定大小，且开启点击关闭功能
     *
     * @param frame 框架
     */
    public static void set(JFrame frame,int width,int height,String iconPath){
        /*// 获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width; // 屏幕宽度
        int screenHeight = screenSize.height; // 屏幕高度
        frame.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);*/
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);//设置是否能手动调整窗口

        try {
            frame.setIconImage(ImageIO.read(new File(iconPath)));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "主页面左上角的图标路径不存在");
            frame.dispose();
        }

    }
}
