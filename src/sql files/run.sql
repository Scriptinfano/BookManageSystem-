-- 此sql文件用于录入一些基本信息，以试运行数据库
use newbookstore;
-- 录入基本信息

-- 录入批发商信息
call inputWholesaler('一号批发商', 16584521659);
call inputWholesaler('二号批发商', 14857954685);

-- 录入基本图书信息
call inputBook('海伯利安', '丹西蒙斯', '科幻', '读客出版社');
call inputBook('梦的解析', '弗洛伊德', '心理学', '未知出版社');

-- 录入客户信息
call inputUser('张三', '15487956485', 'VIP客户');
call inputUser('李四', '45879564856', '普通客户');

-- 开始进货
call createPurchaseOrder(1, now(), '第一次进货');
call createPurchaseDetailOrder(11, 2, 23, 23.5);
call createPurchaseDetailOrder(12, 1, 24, 34.2);

-- 进货完成，确定售价
call updateSalePrice(1, 50);
call updateSalePrice(2, 60);

-- 开始销售
call createSaleOrder(1, now(), '第一个销售订单');
-- 创建销售订单
-- 开始创建销售详单
call createSaleDetailOrder(1, 2, 2);
call createSaleDetailOrder(1, 1, 1);
-- 所有销售详单创建完成，开始更新刚创建的销售订单中的总售价
call updateSaleOrder(1);

call createSaleOrder(2, now(), '第二个销售订单');
call createSaleDetailOrder(2, 2, 1);
call createSaleDetailOrder(2, 1, 3);
call updateSaleOrder(2);

update bookinfo set bookContext='sd' where bookId=123;
/*
newBookstore存储过程使用指南：
1、先调用以下存储过程录入基础数据
    newbookstore.inputBook(书名，作者，类型，出版社名)、
    newbookstore.inputWholesaler(名字，电话号码)、
    newbookstore.inputUser(名字、电话号码、备注)
分别录入图书信息、进货商信息、客户信息
注意：此时图书的售价和进价尚未确定，进价由进货的时候自动根据指定的进价确定，而售价需要在进货完成之后确定
2、调用newbookstore.createPurchaseOrder（进货商id，下达日期，备注）新建一个进货订单
3、调用newbookstore.createPurchaseDetailOrder（进货订单id，要进的书籍的id，订购本书数目，书的进价）
注意：进货订单详单是属于进货订单的，每一个进货详单标识一种书店引进的一种图书
此时触发器newbookstore.detailpurchaseorder.trigger_update_price会被调用，会更新图书库中相应图书的进价；
此时触发器newbookstore.detailpurchaseorder.trigger_update_count会被调用，更新图书库中的库存，即相应图书的库存增加
4、在进货完成之后，需要立即确定图书的售价（如果是之前已经进过的书则不用确定，仅确定还没有确定的）
调用newbookstore.updateSalePrice（图书id,售价）确定图书的售价，确定售价的时候务必保证进价小于售价（已设置check约束）
5、调用newbookstore.createSaleOrder（用户id，下达日期，备注）创建售货清单
6、调用newbookstore.createSaleDetailOrder（售货清单id,图书号，图书数量）创建销售详单（该详单需要创建多次以添加多种图书）

7、在得到一个售货清单的总售价之后调用newbookstore.updateSaleOrder更新销售订单中的总价钱
注意：
    每创建一个进货详单或售货详单，均会触发执行newbookstore.detailpurchaseorder.trigger_updateInoutLog_purchase和
    newbookstore.detailsalesorder.trigger_updateInoutLog_sale，这两个触发器会向newbookstore.inoutschedule该表添加信息
    ，此表记录了所有的入库，销售记录，可以实现统计一个月的销售额，查看某段时间内的销售和进货情况
还可以调用createSaleView来创建一个包含了指定销售清单的售卖情况
 */


