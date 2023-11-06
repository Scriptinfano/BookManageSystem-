package com.mingxiang.bookstore.view;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.utils.BasicSettingSetter;
import com.mingxiang.bookstore.utils.PathUtils;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录页面
 *
 * @author Mingxiang
 */
public class LoginFrame extends JFrame {
    private final JPasswordField passwordField = new JPasswordField(20);

    private final JLabel infoLabel = new JLabel("密码");

    private final JPanel buttonPanel = new JPanel();

    private final JPanel contentPanel = new JPanel();
    private final JButton okButton = new JButton("确认");
    private final JButton cancelButton = new JButton("退出");

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    private static final String theTitle = "登录";


    public LoginFrame() {
        setTitle(theTitle);
        BasicSettingSetter.set(this, WIDTH, HEIGHT, PathUtils.getLoginLogoPath());
    }

    public void init() {
        passwordField.setEchoChar('*');

        //组装组件

        // 将按钮添加到按钮面板
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // 为确定按钮添加事件监听器
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 调用验证密码的方法
                char[] words = passwordField.getPassword();
                String password = new String(words);
                if (!(password.length() > 0)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "密码不能为空");
                    return;
                }
                validatePassword(password);
            }
        });

        // 为取消按钮添加事件监听器
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 关闭登录界面
            }
        });

        contentPanel.add(infoLabel);
        contentPanel.add(passwordField);

        // 将组件添加到界面
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private void validatePassword(String password) {
        // 在这里进行密码验证逻辑
        if (!DatabaseDao.testConnection("root", password)) {
            JOptionPane.showMessageDialog(this, "密码错误，登录失败", "数据库登录", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "登录成功！");
            new MainFrame().init();//跳转到主页面
            dispose();//关闭当前登录页面
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 设置观感
                    //UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel());
                    //UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel());
                    //UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel());

                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
//            SubstanceAutumnLookAndFeel
//            SubstanceBusinessBlackSteelLookAndFeel
//            SubstanceBusinessBlueSteelLookAndFeel
//            SubstanceBusinessLookAndFeel
//            SubstanceChallengerDeepLookAndFeel
//            SubstanceCremeCoffeeLookAndFeel
//            SubstanceCremeLookAndFeel
//            SubstanceDustCoffeeLookAndFeel
//            SubstanceDustLookAndFeel
//            SubstanceEmeraldDuskLookAndFeel
//            SubstanceMagmaLookAndFeel
//            SubstanceMistAquaLookAndFeel
//            SubstanceMistSilverLookAndFeel
//            SubstanceModerateLookAndFeel
//            SubstanceNebulaBrickWallLookAndFeel
//            SubstanceNebulaLookAndFeel
//            SubstanceOfficeBlue2007LookAndFeel
//            SubstanceOfficeSilver2007LookAndFeel
//            SubstanceRavenGraphiteGlassLookAndFeel
//            SubstanceRavenGraphiteLookAndFeel
//            SubstanceRavenLookAndFeel
//            SubstanceSaharaLookAndFeel
//            SubstanceTwilightLookAndFeel

                    JFrame.setDefaultLookAndFeelDecorated(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new LoginFrame().init();
            }
        });
    }
}