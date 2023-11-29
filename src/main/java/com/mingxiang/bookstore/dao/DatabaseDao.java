package com.mingxiang.bookstore.dao;

import com.mingxiang.bookstore.entity.*;

import java.sql.*;
import java.util.HashMap;

/**
 * 数据库交互类，负责上层应用和数据库交换数据
 * 要切换登录用户，请在此修改
 *
 * @author Mingxiang
 */
public class DatabaseDao {

    private static final String userName = "root";
    private static final String ipAddress = "localhost";
    private static final String url = "jdbc:mysql://" + ipAddress + ":3306/newbookstore";

    private static final String driverPath = "com.mysql.cj.jdbc.Driver";
    private Connection connection;
    private Statement sqlStatement;

    static {
        //注册驱动

        try {
            Class.forName(driverPath);
        } catch (ClassNotFoundException e) {
            System.err.println("驱动加载失败:" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }

    }

    private final HashMap<String, CallableStatement> callableStatements = new HashMap<>();
    private final HashMap<String, PreparedStatement> preparedStatements = new HashMap<>();

    public Connection getConnection() {
        return connection;
    }

    public void setAutoCommit(boolean commit) {
        try {
            connection.setAutoCommit(commit);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }


    /**
     * 测试连接
     *
     * @return boolean
     */
    public static boolean testConnection(String user, String thePassword) {
        String url = "jdbc:mysql://localhost:3306/newbookstore";
        Connection con;
        try {
            con = DriverManager.getConnection(url, user, thePassword);
            con.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public void connect(String thePassword) throws SQLException {
        if (connection != null) return;

        //获取连接
        connection = DriverManager.getConnection(url, userName, thePassword);
        //也可以仅传入url参数，但url参数中要包括user和passward, 例如：jdbc:mysql://ip:port/name?user=root&passward=root

        //获取执行sql语句的对象
        sqlStatement = connection.createStatement();

        //初始化存储过程执行对象池
        callableStatements.put("createPurchaseDetailOrder", connection.prepareCall("{call newbookstore.createPurchaseDetailOrder(?,?,?,?)}"));
        callableStatements.put("createPurchaseOrder", connection.prepareCall("{call newbookstore.createPurchaseOrder(?,?,?)}"));
        callableStatements.put("createSaleDetailOrder", connection.prepareCall("{call newbookstore.createSaleDetailOrder(?,?,?)}"));
        callableStatements.put("createSaleOrder", connection.prepareCall("{call newbookstore.createSaleOrder(?,?,?)}"));
        callableStatements.put("inputBook", connection.prepareCall("{call newbookstore.inputBook(?,?,?,?,?)}"));
        callableStatements.put("inputUser", connection.prepareCall("{call newbookstore.inputUser(?,?,?)}"));
        callableStatements.put("inputWholesaler", connection.prepareCall("{call newbookstore.inputWholesaler(?,?,?)}"));
        callableStatements.put("updateSaleOrder", connection.prepareCall("{call newbookstore.updateSaleOrder(?)}"));
        callableStatements.put("updateSalePrice", connection.prepareCall("{call newbookstore.updateSalePrice(?,?)}"));
        callableStatements.put("editBook", connection.prepareCall("{call newbookstore.editBook(?,?,?,?,?,?)}"));
        callableStatements.put("editCustomer", connection.prepareCall("{call newbookstore.editCustomer(?,?,?,?)}"));
        callableStatements.put("editMerchant", connection.prepareCall("{call newbookstore.editMerchant(?,?,?,?)}"));
        callableStatements.put("selectInoutSchedule_in", connection.prepareCall("{call newbookstore.selectInoutSchedule_in(?,?)}"));
        callableStatements.put("selectInoutSchedule_out", connection.prepareCall("{call newbookstore.selectInoutSchedule_out(?,?)}"));

        preparedStatements.put("editBookName", connection.prepareStatement("update newbookstore.bookinfo set bookName = ? where bookId=?"));
        preparedStatements.put("editBookType", connection.prepareStatement("update newbookstore.bookinfo set bookType = ? where bookId=?"));
        preparedStatements.put("editBookAuthor", connection.prepareStatement("update newbookstore.bookinfo set bookAuthor = ? where bookId=?"));
        preparedStatements.put("editBookPublisher", connection.prepareStatement("update newbookstore.bookinfo set bookPublisher = ? where bookId=?"));
        preparedStatements.put("editBookContext", connection.prepareStatement("update newbookstore.bookinfo set bookContext=? where bookId=?"));

        preparedStatements.put("editCustomerContext", connection.prepareStatement("update newbookstore.customer set customerContext=? where customerId=?"));
        preparedStatements.put("editCustomerName", connection.prepareStatement("update newbookstore.customer set customerName=? where customerId=?"));
        preparedStatements.put("editCustomerTel", connection.prepareStatement("update newbookstore.customer set customerTel=? where customerId=?"));

        preparedStatements.put("editMerchantContext", connection.prepareStatement("update newbookstore.wholesaler set wholesalerContext=? where wholesalerId=?"));
        preparedStatements.put("editMerchantName", connection.prepareStatement("update newbookstore.wholesaler set wholesalerName=? where wholesalerId=?"));
        preparedStatements.put("editMerchantTel", connection.prepareStatement("update newbookstore.wholesaler set wholesalerTel=? where wholesalerId=?"));

        preparedStatements.put("deletePurchaseOrder", connection.prepareStatement("delete from newbookstore.purchaseorder where purchaseOrderId=?;"));
        preparedStatements.put("deleteSaleOrder", connection.prepareStatement("delete from newbookstore.salesorder where salesOrderId=?;"));

        //TODO 在这里更改要显示的视图
        preparedStatements.put("bookView", connection.prepareStatement("select * from newbookstore.bookinfo_view"));
        preparedStatements.put("customerView", connection.prepareStatement("select * from newbookstore.customer_view"));
        preparedStatements.put("merchantView", connection.prepareStatement("select * from newbookstore.merchant_view"));
        preparedStatements.put("purchaseView", connection.prepareStatement("select * from newbookstore.purchase_view"));
        preparedStatements.put("saleView", connection.prepareStatement("select * from newbookstore.sale_view"));
        preparedStatements.put("inoutView", connection.prepareStatement("select * from newbookstore.inoutschedule"));

    }


    public void editBookName(int id, String name) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editBookName");
        statement.setInt(2, id);
        statement.setString(1, name);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editBookType(int id, String type) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editBookType");
        statement.setInt(2, id);
        statement.setString(1, type);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editBookAuthor(int id, String author) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editBookAuthor");
        statement.setInt(2, id);
        statement.setString(1, author);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editBookPublisher(int id, String publisher) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editBookPublisher");
        statement.setInt(2, id);
        statement.setString(1, publisher);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editBookContext(int id, String context) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editBookContext");
        statement.setInt(2, id);
        statement.setString(1, context);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editCustomerContext(int id, String context) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editCustomerContext");
        statement.setInt(2, id);
        statement.setString(1, context);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editCustomerName(int id, String name) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editCustomerName");
        statement.setInt(2, id);
        statement.setString(1, name);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editCustomerTel(int id, String tel) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editCustomerTel");
        statement.setInt(2, id);
        statement.setString(1, tel);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editMerchantContext(int id, String context) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editMerchantContext");
        statement.setInt(2, id);
        statement.setString(1, context);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editMerchantName(int id, String name) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editMerchantName");
        statement.setInt(2, id);
        statement.setString(1, name);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editMerchantTel(int id, String tel) throws SQLException {
        PreparedStatement statement = preparedStatements.get("editMerchantTel");
        statement.setInt(2, id);
        statement.setString(1, tel);
        if(statement.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }


    public void deletePurchaseOrder(int orderId) throws SQLException {
        PreparedStatement statement = preparedStatements.get("deletePurchaseOrder");
        statement.setInt(1, orderId);
        statement.execute();
    }

    public void deleteSaleOrder(int orderId) throws SQLException {
        PreparedStatement statement = preparedStatements.get("deleteSaleOrder");
        statement.setInt(1, orderId);
        statement.execute();
    }

    public CallableStatement getCallableStatement(String operateName) {
        return callableStatements.get(operateName);
    }

    /**
     * 在调用预编译好的SQL语句时使用此接口，以下是已经预编译好的sql语句
     *
     * @param operateName 操作名称
     * @return {@link PreparedStatement}
     */
    public PreparedStatement getPreparedStatement(String operateName) {
        return preparedStatements.get(operateName);
    }


    /**
     * 执行查询语句并输出查询结果
     *
     * @param sql sql
     * @throws SQLException sqlexception异常
     */
    public ResultSet query(String sql) throws SQLException {
        return sqlStatement.executeQuery(sql);
    }


    /**
     * 增加条目操作和删除条目操作
     *
     * @param sql sql
     * @throws SQLException sqlexception异常
     */
    public void update(String sql) throws SQLException {
        sqlStatement.executeUpdate(sql);
    }

    public void inputBook(Book book) throws SQLException {
        CallableStatement sta = callableStatements.get("inputBook");
        sta.setString(1, book.getBookName());
        sta.setString(2, book.getAuthor());
        sta.setString(3, book.getType());
        sta.setString(4, book.getPublisher());
        sta.setString(5, book.getContext());
        sta.execute();

    }

    public void inputWholesaler(Merchant merchant) throws SQLException {
        CallableStatement sta = callableStatements.get("inputWholesaler");
        sta.setString(1, merchant.getName());
        sta.setString(2, merchant.getContact());
        sta.setString(3, merchant.getContext());
        sta.execute();

    }

    public void inputCustomer(Customer customer) throws SQLException {
        CallableStatement sta = callableStatements.get("inputUser");
        sta.setString(1, customer.getName());
        sta.setString(2, customer.getPhone());
        sta.setString(3, customer.getContext());
        sta.execute();

    }

    public void createPurchaseDetailOrder(PurchaseDetailOrder detailOrder) throws SQLException {
        CallableStatement sta = callableStatements.get("createPurchaseDetailOrder");
        sta.setInt(1, detailOrder.getThePurchaseId());
        sta.setInt(2, detailOrder.getTheBookId());
        sta.setInt(3, detailOrder.getTheOrderNum());
        sta.setFloat(4, detailOrder.getThePrice());
        sta.executeUpdate();

    }

    public void createPurchaseOrder(PurchaseOrder purchaseOrder) throws SQLException {
        CallableStatement sta = callableStatements.get("createPurchaseOrder");
        sta.setInt(1, purchaseOrder.getMerchantId());
        sta.setTimestamp(2, purchaseOrder.getStamp());
        sta.setString(3, purchaseOrder.getContext());
        sta.executeUpdate();

    }

    public void createSaleDetailOrder(SaleDetailOrder saleDetailOrder) throws SQLException {
        CallableStatement sta = callableStatements.get("createSaleDetailOrder");
        sta.setInt(1, saleDetailOrder.getTheSaleId());
        sta.setInt(2, saleDetailOrder.getTheBookId());
        sta.setInt(3, saleDetailOrder.getTheBookCount());
        sta.execute();

    }

    public void createSaleOrder(SaleOrder saleOrder) throws SQLException {
        CallableStatement sta = callableStatements.get("createSaleOrder");
        sta.setInt(1, saleOrder.getTheCustomerId());
        sta.setTimestamp(2, saleOrder.getTheDate());
        sta.setString(3, saleOrder.getTheContext());
        sta.execute();

    }

    /**
     * 自动更新指定销售订单的总售价属性，请在所有销售详单创建之后执行，务必在详单导入数据库之后执行
     *
     * @param saleOrderId 销售订单id
     */
    public void updateSaleOrder(int saleOrderId) throws SQLException {
        CallableStatement sta = callableStatements.get("updateSaleOrder");
        sta.setInt(1, saleOrderId);
        if(sta.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");

    }


    /**
     * 指定某种图书的销售价格，在进货完成之后立即执行
     *
     * @param bookId 书id
     * @param price  价格
     * @throws SQLException sqlexception异常
     */
    public void updateSalePrice(int bookId, float price) throws SQLException {
        CallableStatement sta = callableStatements.get("updateSalePrice");
        sta.setInt(1, bookId);
        sta.setFloat(2, price);
        if(sta.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");

    }

    public void editBook(int id, Book book) throws SQLException {
        CallableStatement sta = callableStatements.get("editBook");
        sta.setInt(1, id);
        sta.setString(2, book.getBookName());
        sta.setString(3, book.getType());
        sta.setString(4, book.getAuthor());
        sta.setString(5, book.getContext());
        if(sta.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editCustomer(int id, Customer customer) throws SQLException {
        CallableStatement sta = callableStatements.get("editCustomer");
        sta.setInt(1, id);
        sta.setString(2, customer.getName());
        sta.setString(3, customer.getPhone());
        sta.setString(4, customer.getContext());
        if(sta.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }

    public void editMerchant(int id, Merchant merchant) throws SQLException {
        CallableStatement sta = callableStatements.get("editMerchant");
        sta.setInt(1, id);
        sta.setString(2, merchant.getContext());
        sta.setString(3, merchant.getName());
        sta.setString(4, merchant.getContact());
        if(sta.executeUpdate()==0)
            throw new SQLException("没有找到对应的条目");
    }


    /**
     * 在数据库中取得最后一个进货订单id号码
     *
     * @return int
     */
    public int getLastPurchaseId() throws SQLException {
        ResultSet set = sqlStatement.executeQuery("select purchaseOrderId from newbookstore.purchaseorder order by purchaseOrderId desc limit 1;");
        set.next();
        int index = set.findColumn("purchaseOrderId");
        return set.getInt(index);
    }

    public int getLastSaleOrderId() throws SQLException {
        ResultSet set = sqlStatement.executeQuery("select salesOrderId from newbookstore.salesorder order by salesOrderId desc limit 1;");
        set.next();
        int index = set.findColumn("salesOrderId");
        return set.getInt(index);
    }

    public ResultSet getInoutSchedule_in(Timestamp begin, Timestamp end) throws SQLException {
        CallableStatement sta = callableStatements.get("selectInoutSchedule_in");
        sta.setTimestamp(1, begin);
        sta.setTimestamp(2, end);
        return sta.executeQuery();
    }

    public ResultSet getInoutSchedule_out(Timestamp begin, Timestamp end) throws SQLException {
        CallableStatement sta = callableStatements.get("selectInoutSchedule_out");
        sta.setTimestamp(1, begin);
        sta.setTimestamp(2, end);
        return sta.executeQuery();
    }
}
