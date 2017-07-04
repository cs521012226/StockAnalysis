/* 营业部表 */
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`(
`cmp_code` char(32) not null, /* 代码 */
`cmp_name` varchar (50) not null, /* 名字 */
`style` varchar(20) , /* 机构风格 */
PRIMARY KEY (`cmp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `company` ADD INDEX IK_company ( `cmp_name`);


/* 股票表 */
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`(
`stock_code` varchar(6) not null, /* 代码 */
`stock_name` varchar (6) not null, /* 名字 */
PRIMARY KEY (`stock_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `stock` ADD INDEX IK_stock ( `stock_name`);

/* 龙虎榜数据 */
DROP TABLE IF EXISTS `board_data`;
CREATE TABLE `board_data`(
`stock_code` varchar(6) not null, /* 股票代码 */
`cmp_code` char (32) not null, /* 营业部代码 */
`reason` varchar (50) not null, /* 上榜原因 */
`buy_money` decimal(10,2) not null, /* 买入额（万） */
`sale_money` decimal(10,2) not null, /* 卖出额（万） */
`rank_type` varchar(1) not null, /* 排名类型 */
`board_date` date not null, /* 上榜日期 */
`create_date` date not null /* 添加日期 */
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `board_data` ADD INDEX IK_board_data ( `board_date`, `stock_code`, `rank_type`);


/* 系统参数表 */
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param`(
`code` varchar(50) not null, /*  */
`value` varchar(100) not null, /*  */
`text` varchar(100) not null, /*  */
`create_date` datetime not null, /* 添加日期 */
UNIQUE KEY (`code`,`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 操作日志表 */
DROP TABLE IF EXISTS `opr_logs`;
CREATE TABLE `opr_logs`(
`log_type`	varchar(20)	not null,	/*类型*/
`code`	varchar(20) not null,		/*异常码*/
`content`	text,	/*内容*/
`create_date` datetime not null	/*日期*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `opr_logs` ADD INDEX IK_opr_logs (`create_date`);




/* 营业部排名表 */
DROP TABLE IF EXISTS `company_rank`;
CREATE TABLE `company_rank`(
`cmp_code` char(32) not null, /* 代码 */
`rank_count` integer ,	/*上榜次数 */
`amount` decimal(10,2) ,		/*合计动用资金（万）*/
`rank_count_year` integer ,	/*年内上榜次数*/
`buy_stock_count` integer,		/*年内买入股票只数*/
`period`	varchar(10),		/* 日期类型*/
`create_date` date not null		/* 创建日期 */
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `company_rank` ADD INDEX IK_company_rank (`create_date`,`period`, `rank_count`);

/* 大单追踪记录表 */
DROP TABLE IF EXISTS `big_trade`;
CREATE TABLE `big_trade`(
`trade_date` date not null, /*成交时间*/
`stock_code` varchar(6) not null, /*股票代码*/
`stock_name` varchar(6), /*股票简称*/
`price` decimal(10,2), /*成交价格*/
`volume` integer, /*成交量(股)*/
`amount` decimal(10,2), /*成交额(万元)*/
`type` varchar(2), /*大单性质*/
`updown_percent` varchar(8), /*涨跌幅*/
`updown_price` decimal(10,2), /*涨跌额*/
`create_date` date not null /* 添加日期 */
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `big_trade` ADD INDEX IK_big_trade (`create_date`,`trade_date`,`type`,`amount`);


/* 概念资金记录表 */
DROP TABLE IF EXISTS `concept_fund_rank`;
CREATE TABLE `concept_fund_rank`(
`industry` varchar(30), /*行业*/
`company_count` integer, /*公司家数*/
`industry_index` decimal(10, 2), /*行业指数*/
`updown_percent` varchar(8), /*阶段涨跌幅*/
`in_fund` decimal(10,2), /*流入资金(亿)*/
`out_fund` decimal(10,2), /*流出资金(亿)*/
`net_amount` decimal(10,2), /*净额(亿)*/
`period`	varchar(10),		/* 日期类型*/
`create_date` date not null		/* 创建日期 */
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `concept_fund_rank` ADD INDEX IK_concept_fund_rank (`create_date`,`period`,`in_fund`,`out_fund`);


/* 个股资金流向记录表 */
DROP TABLE IF EXISTS `ind_stock_fund`;
CREATE TABLE `ind_stock_fund`(
`stock_code` varchar(6), /*股票代码*/
`stock_name` varchar(6), /*股票简称*/
`new_price` decimal(10,2), /*最新价*/
`updown_percent` varchar(8), /*涨跌幅*/
`turnover_rate` varchar(8), /*换手率*/
`in_fund` decimal(10,2), /*流入资金(元)*/
`out_fund` decimal(10,2), /*流出资金(元)*/
`net_amount` decimal(10,2), /*净额(元)*/
`amount` decimal(10,2), /*成交额(元)*/
`in_big_trade` decimal(10,2), /*大单流入(元)*/
`create_date` date not null		/* 创建日期 */
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `ind_stock_fund` ADD INDEX IK_ind_stock_fund (`create_date`,`in_fund`,`out_fund`);



/* 创新高记录表 */
DROP TABLE IF EXISTS `new_top`;
CREATE TABLE `new_top`(
`stock_code` varchar(6), /*股票代码*/
`stock_name` varchar(6), /*股票简称*/
`updown_percent` varchar(8), /*涨跌幅*/
`turnover_rate` varchar(8), /*换手率*/
`new_price` decimal(10,2), /*最新价(元)*/
`prev_top` decimal(10,2), /*前期高点*/
`prev_top_date` date, /*前期高点日期*/
`create_date` date not null		/* 创建日期 */
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `new_top` ADD INDEX IK_new_top (`create_date`);

/* 向上突破股票记录表 */
DROP TABLE IF EXISTS `break_through`;
CREATE TABLE `break_through`(
`stock_code` varchar(6), /*股票代码*/
`stock_name` varchar(6), /*股票简称*/
`new_price` decimal(10,2), /*最新价（元）*/
`amount` decimal(10,2), /*成交额（万）*/
`volume` integer, /*成交量（股）*/
`updown_percent` varchar(8), /*涨跌幅（%）*/
`turnover_rate` varchar(8), /*换手率（%）*/
`period`	varchar(10),		/* 日期类型*/
`create_date` date not null		/* 创建日期 */
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `break_through` ADD INDEX IK_break_through (`create_date`);