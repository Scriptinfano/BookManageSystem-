/********创建一些存储过程用于在java上层调用以显示相关的数据*/
use newbookstore;

-- 创建指定销售订单号的视图的存储过程
create procedure createSaleView(in theSaleId int)
begin
    select bookName         as '书名',
           bookInfo.context as '备注',
           price            as '单价',
           salesDate        as '售卖日期',
           salesPrice       as '销售总价',
           salesNum         as '售出数量'
    from salesorder,
         detailSalesOrder,
         bookInfo
    where salesOrder.salesOrderId = detailSalesOrder.salesOrderId
      and detailSalesOrder.bookId = bookInfo.bookId
      and salesOrder.salesOrderId = theSaleId;
end;


create procedure createPurchaseView(in thePurchaseId int)
begin

end;