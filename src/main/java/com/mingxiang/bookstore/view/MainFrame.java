package com.mingxiang.bookstore.view;

import com.mingxiang.bookstore.dao.DatabaseDao;
import com.mingxiang.bookstore.utils.BasicSettingSetter;
import com.mingxiang.bookstore.utils.PathUtils;
import com.mingxiang.bookstore.utils.Utils;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 系统主页面
 *
 * @author Mingxiang
 */
public class MainFrame extends JFrame {
    /**
     * 数据库交互对象
     */
    private final DatabaseDao dao = new DatabaseDao();

    /**********组件声明***************************************/

    private final JMenuBar bar = new JMenuBar();
    private final JMenu setting = new JMenu("设置");
    private final JMenuItem exit = new JMenuItem("退出");

    private final JSplitPane splitPane = new JSplitPane();

    private final DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
    private final DefaultMutableTreeNode merchantNode = new DefaultMutableTreeNode("批发商管理");

    private final DefaultMutableTreeNode customerNode = new DefaultMutableTreeNode("客户管理");
    private final DefaultMutableTreeNode bookNode = new DefaultMutableTreeNode("图书管理");

    private final DefaultMutableTreeNode saleNode = new DefaultMutableTreeNode("销售管理");
    private final DefaultMutableTreeNode saleInputNode = new DefaultMutableTreeNode("新建销售订单");
    private final DefaultMutableTreeNode saleShowNode = new DefaultMutableTreeNode("查看销售订单");
    private final DefaultMutableTreeNode purchaseNode = new DefaultMutableTreeNode("进货管理");
    private final DefaultMutableTreeNode purchaseInputNode = new DefaultMutableTreeNode("新建进货订单");
    private final DefaultMutableTreeNode purchaseShowNode = new DefaultMutableTreeNode("查看进货订单");

    private final DefaultMutableTreeNode inoutNode = new DefaultMutableTreeNode("明细管理");

    private final DefaultMutableTreeNode customerInputNode = new DefaultMutableTreeNode("新建客户");

    private final DefaultMutableTreeNode customerEditNode = new DefaultMutableTreeNode("编辑客户");

    private final DefaultMutableTreeNode customerShowNode = new DefaultMutableTreeNode("查看客户");

    private final DefaultMutableTreeNode bookInputNode = new DefaultMutableTreeNode("新建图书");

    private final DefaultMutableTreeNode bookEditNode = new DefaultMutableTreeNode("编辑图书");

    private final DefaultMutableTreeNode bookShowNode = new DefaultMutableTreeNode("查看图书");

    private final DefaultMutableTreeNode merchantShowNode = new DefaultMutableTreeNode("查看批发商");

    private final DefaultMutableTreeNode merchantEditNode = new DefaultMutableTreeNode("编辑批发商");

    private final DefaultMutableTreeNode merchantInputNode = new DefaultMutableTreeNode("新建批发商");

    /**
     * step1节点:第一步：新建图书
     */
    private final DefaultMutableTreeNode step1Node = new DefaultMutableTreeNode("第一步：新建图书");
    /**
     * 步骤2节点:第二步：新建批发商
     */
    private final DefaultMutableTreeNode step2Node = new DefaultMutableTreeNode("第二步：新建批发商");

    /**
     * step3节点:第三步：进货
     */
    private final DefaultMutableTreeNode step3Node = new DefaultMutableTreeNode("第三步：进货");

    /**
     * step4节点：第四步：新建客户
     */
    private final DefaultMutableTreeNode step4Node = new DefaultMutableTreeNode("第四步：新建客户");

    /**
     * step5节点：第五步：销售
     */
    private final DefaultMutableTreeNode step5Node = new DefaultMutableTreeNode("第五步：销售");

    private final DefaultMutableTreeNode showInoutScheduleNode = new DefaultMutableTreeNode("查看所有明细");

    private final DefaultMutableTreeNode selectInoutScheduleNode = new DefaultMutableTreeNode("管理明细");

    //private final static DefaultMutableTreeNode user_search

    private final JTree tree = new JTree(root);
    private final JTable bookTable = new JTable();

    private final JTable customerTable = new JTable();
    private final JTable merchantTable = new JTable();

    private final JTable saleTable = new JTable();
    private final JTable purchaseTable = new JTable();

    private final JTable inoutTable = new JTable();

    private final JScrollPane scrollPane = new JScrollPane();


    /*******************其他类属性********************************************/

    private static final String dbPassword = "200329";

    private static final String theTitle = "书店管理系统主页面";


    /**
     * 主页面的宽度
     */
    private static final int WIDTH = 1000;

    /**
     * 主页面的高度
     */
    private static final int HEIGHT = 500;

    public MainFrame() {
        setTitle(theTitle);
        BasicSettingSetter.set(this, WIDTH, HEIGHT, PathUtils.getBookLogoPath());
    }

    /**
     * 添加动作侦听器，所有监听器代码
     */
    private void addActionListeners() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainFrame.this, "再见");
                MainFrame.this.dispose();
            }
        });

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                //当条目选中变化之后，这个方法会执行
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                //TODO 加入执行顺序检测功能
                if (bookInputNode.equals(lastPathComponent)) {
                    //打开录入图书对话框
                    new InputBookDialog(MainFrame.this, dao);
                } else if (bookEditNode.equals(lastPathComponent)) {
                    //打开编辑图书对话框
                    new EditBookDialog(MainFrame.this, dao);
                } else if (bookShowNode.equals(lastPathComponent)) {
                    //直接在右侧显示图书信息
                    flushTable(bookTable);
                    scrollPane.setViewportView(bookTable);
                    splitPane.setRightComponent(scrollPane);
                } else if (customerInputNode.equals(lastPathComponent)) {
                    //打开新建用户对话框
                    new InputCustomerDialog(MainFrame.this, dao);
                } else if (customerEditNode.equals(lastPathComponent)) {
                    //打开编辑用户对话框
                    new EditCustomerDialog(MainFrame.this, dao);
                } else if (customerShowNode.equals(lastPathComponent)) {
                    //直接在页面右侧显示用户信息表格
                    flushTable(customerTable);
                    scrollPane.setViewportView(customerTable);
                    splitPane.setRightComponent(scrollPane);
                } else if (merchantInputNode.equals(lastPathComponent)) {
                    //打开新建批发商对话框
                    new InputMerchantDialog(MainFrame.this, dao);
                } else if (merchantEditNode.equals(lastPathComponent)) {
                    //打开编辑批发商对话框
                    new EditMerchantDialog(MainFrame.this, dao);
                } else if (merchantShowNode.equals(lastPathComponent)) {
                    //直接在页面右侧显示批发商信息表格
                    flushTable(merchantTable);
                    scrollPane.setViewportView(merchantTable);
                    splitPane.setRightComponent(scrollPane);
                } else if (saleInputNode.equals(lastPathComponent)) {
                    //打开新建销售订单页面
                    new SaleOrderDialog(MainFrame.this, dao);
                } else if (saleShowNode.equals(lastPathComponent)) {
                    //在右侧显示销售订单表
                    flushTable(saleTable);
                    scrollPane.setViewportView(saleTable);
                    splitPane.setRightComponent(scrollPane);
                } else if (purchaseInputNode.equals(lastPathComponent)) {
                    //打开新建进货订单窗口
                    new PurchaseOrderDialog(MainFrame.this, dao);
                } else if (purchaseShowNode.equals(lastPathComponent)) {
                    //在右侧显示进货订单表
                    flushTable(purchaseTable);
                    scrollPane.setViewportView(purchaseTable);
                    splitPane.setRightComponent(scrollPane);
                } else if (showInoutScheduleNode.equals(lastPathComponent)) {
                    //打开明细管理界面
                    flushTable(inoutTable);
                    scrollPane.setViewportView(inoutTable);
                    splitPane.setRightComponent(scrollPane);
                }else if (selectInoutScheduleNode.equals(lastPathComponent)) {
                    new SelectInoutScheduleDialog(MainFrame.this,dao);
                }


            }
        });
    }

    /**
     * 结合组件
     */
    private void combineComponents() {
        //组装组件
        setJMenuBar(bar);
        setting.add(exit);
        bar.add(setting);

        splitPane.setContinuousLayout(true);
        splitPane.setDividerLocation(150);
        splitPane.setDividerSize(7);
        add(splitPane);

        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
            @Override
            public java.awt.Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                                   boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

                // 根据节点的文本设置不同的图标
                if (node == root) {
                    ImageIcon icon=new ImageIcon(PathUtils.getStartLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == customerNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getCustomersLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == bookNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getSingleBookLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == merchantNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getMerchantLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == customerEditNode || node == merchantEditNode || node == bookEditNode||node==selectInoutScheduleNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getEditLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == customerInputNode || node == merchantInputNode || node == bookInputNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getAddLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == customerShowNode || node == merchantShowNode || node == bookShowNode||node==showInoutScheduleNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getShowLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == purchaseShowNode || node == saleShowNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getShowPurchaseOrSaleLogo());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == purchaseInputNode || node == saleInputNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getAddLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == purchaseNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getPurchaseLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == saleNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getSaleLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == inoutNode) {
                    ImageIcon icon=new ImageIcon(PathUtils.getLogLogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == step1Node) {
                    ImageIcon icon=new ImageIcon(PathUtils.getStep1LogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == step2Node) {
                    ImageIcon icon=new ImageIcon(PathUtils.getStep2LogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == step3Node) {
                    ImageIcon icon=new ImageIcon(PathUtils.getStep3LogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == step4Node) {
                    ImageIcon icon=new ImageIcon(PathUtils.getStep4LogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                } else if (node == step5Node) {
                    ImageIcon icon=new ImageIcon(PathUtils.getStep5LogoPath());
                    Image scaledImage = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    icon=new ImageIcon(scaledImage);
                    setIcon(icon);
                }

                return this;
            }
        };

        tree.setCellRenderer(renderer);


        root.add(step1Node);
        root.add(step2Node);
        root.add(step3Node);
        root.add(step4Node);
        root.add(step5Node);
        root.add(inoutNode);

        step1Node.add(bookNode);
        step2Node.add(merchantNode);
        step3Node.add(purchaseNode);
        step4Node.add(customerNode);
        step5Node.add(saleNode);

        bookNode.add(bookInputNode);
        bookNode.add(bookEditNode);
        bookNode.add(bookShowNode);

        customerNode.add(customerInputNode);
        customerNode.add(customerEditNode);
        customerNode.add(customerShowNode);

        merchantNode.add(merchantInputNode);
        merchantNode.add(merchantEditNode);
        merchantNode.add(merchantShowNode);

        saleNode.add(saleInputNode);
        saleNode.add(saleShowNode);

        purchaseNode.add(purchaseInputNode);
        purchaseNode.add(purchaseShowNode);

        inoutNode.add(showInoutScheduleNode);
        inoutNode.add(selectInoutScheduleNode);

        JScrollPane leftScrollPane = new JScrollPane(tree);
        splitPane.setLeftComponent(leftScrollPane);
        //tree默认选中图书管理
        splitPane.setRightComponent(new JTable());
        tree.setSelectionRow(1);
    }


    /**
     * 初始化，组装组件，创建监听器
     */
    public void init() {
        try {
            dao.connect(dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setResizable(false);
        combineComponents();//组合组件
        addActionListeners();//添加监听器
        initTables();
        //pack();
        setVisible(true);
    }

    private static final String bookTableName = "bookView";
    private static final String customerTableName = "customerView";
    private static final String merchantTableName = "merchantView";
    private static final String saleTableName = "saleView";
    private static final String purchaseTableName = "purchaseView";
    private static final String inoutTableName = "inoutView";

    private void initTables() {

        bookTable.setName(bookTableName);
        customerTable.setName(customerTableName);
        merchantTable.setName(merchantTableName);
        saleTable.setName(saleTableName);
        purchaseTable.setName(purchaseTableName);
        inoutTable.setName(inoutTableName);
        try {
            Utils.setTable(bookTable, dao.getPreparedStatement(bookTableName).executeQuery());
            Utils.setTable(customerTable, dao.getPreparedStatement(customerTableName).executeQuery());
            Utils.setTable(merchantTable, dao.getPreparedStatement(merchantTableName).executeQuery());
            Utils.setTable(saleTable, dao.getPreparedStatement(saleTableName).executeQuery());
            Utils.setTable(purchaseTable, dao.getPreparedStatement(purchaseTableName).executeQuery());
            Utils.setTable(inoutTable, dao.getPreparedStatement(inoutTableName).executeQuery());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "初始化右侧总表错误，具体信息：" + e.getMessage());
            System.exit(0);
        }

        bookTable.setEnabled(false);
        customerTable.setEnabled(false);
        merchantTable.setEnabled(false);
        saleTable.setEnabled(false);
        purchaseTable.setEnabled(false);
        inoutTable.setEnabled(false);
    }

    /**
     * 刷新表的内容
     *
     * @param table 表
     */
    private void flushTable(JTable table) {
        try {
            if (table.getName().equals(bookTableName)) {
                Utils.setTable(bookTable, dao.getPreparedStatement(bookTableName).executeQuery());
            } else if (table.getName().equals(customerTableName)) {
                Utils.setTable(customerTable, dao.getPreparedStatement(customerTableName).executeQuery());
            } else if (table.getName().equals(merchantTableName)) {
                Utils.setTable(merchantTable, dao.getPreparedStatement(merchantTableName).executeQuery());
            } else if (table.getName().equals(saleTableName)) {
                Utils.setTable(saleTable, dao.getPreparedStatement(saleTableName).executeQuery());
            } else if (table.getName().equals(purchaseTableName)) {
                Utils.setTable(purchaseTable, dao.getPreparedStatement(purchaseTableName).executeQuery());
            } else if (table.getName().equals(inoutTableName)) {
                Utils.setTable(inoutTable, dao.getPreparedStatement(inoutTableName).executeQuery());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "刷新表时发生错误，具体信息：" + e.getMessage());
            System.exit(0);
        }
    }
}
