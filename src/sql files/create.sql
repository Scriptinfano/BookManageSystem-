create database NewBookStore;
use NewBookStore;
/*********************1 图书信息表***********************/
CREATE TABLE bookInfo
(
    bookId            int PRIMARY KEY AUTO_INCREMENT comment '图书编号', #图书编号
    bookName          nvarchar(20)      not null comment '图书名称',     #图书名称
    bookType          nvarchar(20)      not null comment '图书类别',     #图书类别
    bookAuthor        nvarchar(20)      NOT NULL comment '作者',         #作者
    bookPublisher     nvarchar(20)      NOT NULL comment '出版社',       #出版社
    bookContext       nvarchar(100) comment '描述',                      #描述
    bookPrice         float comment '售价',                              #售价
    bookPurchasePrice float comment '进价',                              #进价
    bookInventory     int(20) default 0 not null comment '库存',         #库存

    constraint chk__price check ( bookPrice > bookInfo.bookPurchasePrice and bookPurchasePrice > 0),
    constraint chk__inventory check ( bookInventory >= 0 )
) comment '图书信息表';

/*********************2 批发商表***********************/
CREATE TABLE wholesaler
(
    wholesalerId      int AUTO_INCREMENT PRIMARY KEY comment '批发商编号',#批发商编号
    wholesalerName    nvarchar(20) NOT NULL comment '批发商名称',#批发商名称
    wholesalerTel     varchar(11)  NOT NULL comment '批发商电话号码',     #批发商电话号码
    wholesalerContext nvarchar(100) comment '批发商描述'                  #批发商描述
) comment '批发商表';

/*********************5 顾客联系表***********************/
CREATE TABLE Customer
(
    customerId      int AUTO_INCREMENT PRIMARY KEY comment '顾客编号',#顾客编号
    customerName    nvarchar(20) NOT NULL comment '顾客名',#顾客名
    customerTel     varchar(11)  NOT NULL comment '顾客电话号码',     #顾客电话号码
    customerContext nvarchar(100) comment '备注',

    constraint uk__customerPhoneNumber unique key (customerTel)
) comment '顾客联系表';

/*********************3 进货订单表***********************/
CREATE TABLE purchaseOrder
(
    purchaseOrderId int AUTO_INCREMENT PRIMARY KEY comment '进货单号',#进货单号
    orderDate       Datetime NOT NULL comment '下单日期',             #下单日期
    wholesalerId    int      not null comment '批发商编号',           #批发商编号
    context         nvarchar(100) comment '备注',                     #备注

    CONSTRAINT fk__purchaseOrder_wholesalerId
        FOREIGN KEY (wholesalerId) REFERENCES wholesaler (wholesalerId)
) comment '进货订单表';


/*********************4 进货详单表***********************/
CREATE TABLE detailPurchaseOrder
(
    purchaseDetailOrderId int AUTO_INCREMENT PRIMARY KEY comment '进货详单编号',
    purchaseOrderId       int   NOT NULL comment '进货单号', #进货单号
    bookId                int   not null comment '图书编号', #图书编号
    orderNum              int   NOT NULL comment '进货数量', #进货数量
    purchasePrice         float not null comment '进货单价',# 进货单价
    purchaseTotalPrice    float not null comment '进货总价', #进货总价

    constraint uk__purchaseId unique key (purchaseOrderId, bookId),
    constraint chk__orderNum check ( OrderNum > 0 ),
    constraint chk__purchasePrice check ( purchasePrice > 0 ),
    constraint fk__purchaseId foreign key (purchaseOrderId) references purchaseOrder (purchaseOrderId),
    constraint fk__bookId foreign key (bookId) references bookInfo (bookId)
) comment '进货详单表';


/*********************6 销售订单表***********************/
CREATE TABLE salesOrder
(
    salesOrderId int AUTO_INCREMENT PRIMARY KEY comment '销售单号',#销售单号
    salesDate    Datetime NOT NULL comment '销售日期',             #销售日期
    customerId   int      NOT NULL comment '顾客编号',             #顾客编号
    totalPrice   float comment '该订单的总消费',
    context      nvarchar(20) comment '订单备注',


    CONSTRAINT fk__salesOrder_CustomerId
        FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId)

) comment '销售订单表';

/*********************7 销售详单表***********************/
CREATE TABLE detailSalesOrder
(
    detailSalesOrderId int AUTO_INCREMENT PRIMARY KEY comment '销售详单编号',#销售详单编号
    salesOrderId       int   NOT NULL comment '销售单号',                    #销售单号
    bookId             int   NOT NULL comment '图书编号',                    #图书编号
    salesNum           int   NOT NULL comment '销售该书的数量',              #销售数量
    bookPrice          float not null comment '书的单价',                    #书的单价
    salesPrice         float not null comment '详单的总价',                  #该详单总价

    constraint uk__detailSalesOrder_saleOrderId_bookId unique key (salesOrderId, bookId),
    CONSTRAINT fk__detailSalesOrder_SalesOrderId
        FOREIGN KEY (SalesOrderId) REFERENCES SalesOrder (SalesOrderId),
    constraint fk__detailSalesOrder_bookId
        FOREIGN KEY (bookId) REFERENCES bookInfo (bookId),
    constraint chk__salesNum check ( SalesNum > 0 ),
    constraint chk__salesPrice check ( salesPrice > 0 ),
    constraint chk__bookPrice check (bookPrice > 0 )
) comment '销售详单表';

/*********************8 销售进货明细表***********************/
create table inoutSchedule
(
    inoutId       int primary key auto_increment comment '每一个进货订单或售出订单均对应于一个明细',
    inoutMark     tinyint(1) not null comment '标识进货还是售出(1代表进，0代表出)',
    bookId        int        not null comment '引进或销售出去的书',
    bookCount     int        not null comment '引进的或销售出去的书本的数目',
    financeChange float      not null comment '财务变化，引进为负数，卖出为正数',
    operateDate   datetime   not null comment '交易的日期'
) comment '进出库明细表';


-- 添加图书
use NewBookStore;

/************************存储过程**************************************/

-- 录入基本信息的存储过程：
-- 添加基本图书信息
create
    definer = root@localhost procedure inputBook(IN theName nvarchar(20), IN theAuthor nvarchar(20), IN theType nvarchar(20), IN thePublisherName nvarchar(20), in theContext nvarchar(100))
begin
    insert into NewBookStore.bookinfo(bookName, bookAuthor, bookType, bookPublisher, bookContext) values (theName, theAuthor, theType, thePublisherName, theContext);
end;


-- 添加批发商
create procedure inputWholesaler(in theName nvarchar(20), in theContact varchar(11), in theContext nvarchar(20))
begin
    insert into NewBookStore.wholesaler(wholesalerName, wholesalerTel, wholesalerContext) values (theName, theContact, theContext);
end;

-- 添加客户
create procedure inputUser(in theName nvarchar(20), in thePhoneNum varchar(11), in theContext nvarchar(20))
begin
    insert into NewBookStore.customer(customerName, customerTel, customerContext) values (theName, thePhoneNum, theContext);
end;

-- 创建订单的存储过程
-- 创建进货订单
create procedure createPurchaseOrder(in merchantId int, in theDate datetime, in theContext nvarchar(20))
begin
    insert into NewBookStore.purchaseorder(orderDate, wholesalerId, context) values (theDate, merchantId, theContext);
end;


-- 创建进货订单详情条目
create procedure createPurchaseDetailOrder(in thePurchaseId int, in theBookId int, in theOrderNum int, in thePrice float)
begin
    declare totalPrice float;
    set totalPrice = theOrderNum * thePrice;
    insert into NewBookStore.detailpurchaseorder(purchaseOrderId, bookId, OrderNum, purchasePrice, purchaseTotalPrice) values (thePurchaseId, theBookId, theOrderNum, thePrice, totalPrice);
end;

-- 创建销售订单
create procedure createSaleOrder(in theCustomerId int, in theDate datetime, in theContext nvarchar(20))
begin
    insert into NewBookStore.salesorder(salesDate, customerId, context) values (theDate, theCustomerId, theContext);
end;

-- 创建销售详单 返回该条目所售卖的总价格 每一个销售详单对应一个订单中的一个条目（一种书）
create procedure createSaleDetailOrder(in theSaleId int, in theBookId int, in theBookCount int)
begin
    declare thePrice float;
    declare theTotalPrice float;

    select bookPrice into thePrice from NewBookStore.bookinfo where bookId = theBookId;
    set theTotalPrice = thePrice * theBookCount;

    insert into NewBookStore.detailsalesorder(salesOrderId, bookId, salesNum, bookPrice, salesPrice) values (theSaleId, theBookId, theBookCount, thePrice, theTotalPrice);
end;

-- 更新销售订单中的总价格，这一步必须在所有销售详单创建完成之后执行
create procedure updateSaleOrder(in theSalesOrderId int)
begin
    declare theTotalPrice float;
    select sum(salesPrice) into theTotalPrice from detailsalesorder where salesOrderId = theSalesOrderId;
    update NewBookStore.salesorder set totalPrice=theTotalPrice where salesOrderId = theSalesOrderId;
end;

-- 更新销售价格，在进货完成之后，书籍的进价已经确定，需要调用该存储过程设定该图书的售价
create procedure updateSalePrice(in theBookId int, in thePrice float)
begin
    update NewBookStore.bookinfo
    set bookPrice=thePrice
    where bookId = theBookId;
end;

-- 修改图书信息
create procedure editBook(in theBookId int,
                          in theBookName nvarchar(20),
                          in theBookType nvarchar(20),
                          in theBookAuthor nvarchar(20),
                          in theBookPublisher nvarchar(20),
                          in theContext nvarchar(100))
begin
    update NewBookStore.bookinfo
    set bookContext=theContext,
        bookPublisher=theBookPublisher,
        bookAuthor=theBookAuthor,
        bookType=theBookType,
        bookName=theBookName
    where bookId = theBookId;
end;

-- 修改客户信息
create procedure editCustomer(in theCustomerId int,
                              in theContext nvarchar(100),
                              in theName nvarchar(20),
                              in theTel varchar(11)
)
begin
    update NewBookStore.customer
    set customerContext=theContext,
        customerName=theName,
        customerTel=theTel
    where customerId = theCustomerId;
end;

-- 修改进货商信息
create procedure editMerchant(in theMerchantId int,
                              in theContext nvarchar(100),
                              in theName nvarchar(20),
                              in theTel varchar(11))
begin
    update NewBookStore.wholesaler
    set wholesalerContext=theContext,
        wholesalerName=theName,
        wholesalerTel=theTel
    where wholesalerId = theMerchantId;
end;

create procedure selectInoutSchedule_in(in dateBegin datetime, in dateEnd datetime)
begin
    SELECT *
    FROM newbookstore.inoutschedule
    WHERE operateDate BETWEEN dateBegin AND dateEnd and inoutMark=1;
end;

create procedure selectInoutSchedule_out(in dateBegin datetime, in dateEnd datetime)
begin
    SELECT *
    FROM newbookstore.inoutschedule
    WHERE operateDate BETWEEN dateBegin AND dateEnd and inoutMark=0;
end;

/*****************创建视图*********************/

-- 销售订单粗略视图
create definer = root@localhost view sale_view as
select newbookstore.salesorder.salesOrderId AS 销售订单号,
       newbookstore.bookinfo.bookName AS 书名,
       newbookstore.bookinfo.bookPrice AS 单价,
       newbookstore.salesorder.salesDate AS 售卖日期,
       newbookstore.detailsalesorder.salesPrice AS 销售总价,
       newbookstore.detailsalesorder.salesNum AS 售出数量
from newbookstore.salesorder
         join newbookstore.detailsalesorder
         join newbookstore.bookinfo
where ((newbookstore.salesorder.salesOrderId = newbookstore.detailsalesorder.salesOrderId) and (newbookstore.detailsalesorder.bookId = newbookstore.bookinfo.bookId));

-- 进货订单粗略视图
create view purchase_view as
select purchaseOrder.purchaseOrderId as '进货订单号',
       bookName                      as '书名',
       purchasePrice                 as '进货进货单价',
       orderDate                     as '进货日期',
       purchaseTotalPrice            as '进货总价',
       orderNum                      as '进货总数'
from purchaseorder,
     detailPurchaseOrder,
     bookInfo
where purchaseOrder.purchaseOrderId = detailPurchaseOrder.purchaseOrderId
  and detailPurchaseOrder.bookId = bookInfo.bookId;

-- 填写进货详单时可以从该视图中挑选进货项目
create view bookPurchase_view as
select bookId as '书籍id', bookName as '书名'
from NewBookStore.bookinfo;

-- 填写销售详单时可以从该视图中挑选销售项目
create view bookSale_view as
select bookId, bookName, bookPrice, bookInventory
from bookinfo;



/**********************************触发器*********************************************************/

-- 创建触发器：从最新的订货详单中更新图书的进价
delimiter //
create trigger trigger_update_price
    after insert
    on NewBookStore.detailpurchaseorder
    for each row
begin
    update NewBookStore.bookinfo
    set bookPurchasePrice=new.purchasePrice
    where bookId = new.bookId;
end //
delimiter ;


-- 创建触发器：根据进货详单中的条目，统计进了多少本书，并在图书表中更新相应的库存
delimiter //
create trigger trigger_update_count
    after insert
    on NewBookStore.detailpurchaseorder
    for each row
begin
    update NewBookStore.bookinfo
    set bookInventory=bookinfo.bookInventory + new.orderNum
    where bookId = new.bookId;
end //
delimiter ;

-- 创建触发器：根据销售详单中的售书量修改图书表中相应图书的库存
delimiter //
create trigger trigger_update_count2
    after insert
    on NewBookStore.detailsalesorder
    for each row
begin
    update NewBookStore.bookinfo
    set bookInventory=bookinfo.bookInventory - new.salesNum
    where bookId = new.bookId;
end //
delimiter ;

-- 创建触发器：根据销售详单更新库存变动表
delimiter //
create trigger trigger_updateInoutLog_sale
    after insert
    on NewBookStore.detailsalesorder
    for each row
begin
    declare theDate datetime;
    select salesDate into theDate from salesorder where salesOrderId = new.salesOrderId;
    insert into NewBookStore.inoutschedule(inoutMark, bookId, bookCount, financeChange, operateDate) values (0, new.bookId, new.salesNum, new.salesPrice, theDate);
end //
delimiter ;

-- 创建触发器：根据进货详单表更新库存变动表
delimiter //
create trigger trigger_updateInoutLog_purchase
    after insert
    on NewBookStore.detailpurchaseorder
    for each row
begin
    declare theDate datetime;
    select orderDate into theDate from purchaseorder where purchaseOrderId = new.purchaseOrderId;
    insert into NewBookStore.inoutschedule(inoutMark, bookId, bookCount, financeChange, operateDate) values (1, new.bookId, new.orderNum, 0 - new.purchaseTotalPrice, theDate);
end //
delimiter ;




