-- 类目
create table `product_category` (
    `category_id` int not null auto_increment,
    `category_name` varchar(64) not null comment '类目名字',
    -- 类目编号与id相同
    `category_type` int not null comment '类目编号',
    `has_delete` int not null default '0' comment '逻辑删除 1已删除0未删除',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`category_id`),
    unique key `uqe_category_type` (`category_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci comment '类目表';

-- 商品
create table `product_info` (
    `product_id` varchar(32) not null,
    `product_name` varchar(64) not null comment '商品名称',
    `product_price` decimal(8,2) not null comment '单价',
    `product_stock` int not null comment '库存',
    `product_description` varchar(64) comment '描述',
    -- 图片为链接
    `product_icon` varchar(512) comment '小图',
    `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
    -- 商品 类目 一对多
    `category_type` int not null comment '类目编号',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci comment '商品表';

-- 订单
create table `order_master` (
    `order_id` varchar(32) not null ,
    `buyer_name` varchar(32) not null comment '买家姓名',
    `buyer_phone` varchar(32) not null comment '买家电话',
    `buyer_address` varchar(128) not null comment '买家地址',
    `buyer_openid` varchar(64) not null comment '买家微信openid',
    `order_amount` decimal(8, 2) not null comment '订单总金额',
    `order_status` tinyint(3) not null default '0' comment '订单状态，默认为新下单',
    `pay_status` tinyint(3) not null default '0' comment '订单状态，默认为未支付',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`order_id`),
    -- 索引
    key `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci comment '订单';

-- 订单详情
create table `order_detail` (
    `detail_id` varchar(32) not null,
    `order_id` varchar(32) not null comment '订单id',
    `product_id` varchar(32) not null comment '商品id',
    `product_name` varchar(64) not null comment '商品名称',
    `product_price` decimal(8, 2) not null comment '当前价格',
    `product_quantity` int not null comment '数量',
    `product_icon` varchar(512) comment '图片',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`detail_id`),
    -- 索引
    key `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci comment '订单详情';
