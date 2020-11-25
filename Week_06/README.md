CREATE TABLE `w_goods` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `sku` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `shop_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `attribute` json DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `w_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(60) NOT NULL DEFAULT '' COMMENT '名字',
  `real_name` varchar(60) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '用户状态',
  `address_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '默认地址 ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `w_order` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户 ID',
  `type` tinyint unsigned NOT NULL COMMENT '订单类型',
  `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '订单状态',
  `goods_amount` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `add_time` int unsigned NOT NULL DEFAULT '0' COMMENT '订单创建时间 ',
  `pay_time` int unsigned NOT NULL DEFAULT '0' COMMENT '订单支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `w_sku` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `goods_id` bigint NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL,
  `brand_id` int NOT NULL,
  `category_id` int NOT NULL DEFAULT '0',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `weight` decimal(6,4) NOT NULL,
  `quantity` int NOT NULL DEFAULT '0',
  `status` tinyint NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
