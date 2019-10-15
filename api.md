# API

## 商品列表

GET /sell/buyer/product/list

/sell 项目名

```
{
    "code": 0,
    "msg": "成功",
    // 商品列表
    "data": 
    [
        // 根据商品类 分类 的商品
        {
            // 商品类目名
            "name": "热榜",
            // 商品类型
            "type": 1,
            // 该类目商品列表
            "foods": 
            [
                // 商品详细信息
                {   
                    // 商品信息
                    "id": "123456",
                    // 商品名称
                    "name": "皮蛋粥",
                    // 商品价格
                    "price": 1.2,
                    // 商品描述
                    "description": "好吃的皮蛋粥",
                    // 图片地址
                    "icon": "http://xxx.com"
                }
            ]
        },
        {
            "name": "好吃的",
            "type": 2,
            "foods": [
                {
                    "id": "123457",
                    "name": "慕斯蛋糕",
                    "price": 10.9,
                    "description": "美味爽口",
                    "icon": "http://xxx.com"
                }
            ]
        }
    ]
}
```

## Web

```
product

/seller/product/index
page
size

/seller/product/info
productId

/seller/product/set
productId

/seller/product/save

/serller/product/on
productId

/seller/product/off
productId

```