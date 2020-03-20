###接口文档

#### 商品列表

#### 创建订单

POST /api/order/post

数据
```json
{
    "description": "订单备注",
    "seatId": "座位id",
    "item": [{
        "goodsId": "商品id",
        "count": "商品数量"
    }]
    
}
```
返回
```json
{
  "code": 200,
  "message": "OK",
  "data": {
    "orderNo": "xxxxxxxxxxx"
  } 
}
```