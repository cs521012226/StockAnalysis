
/** 添加搜索次数、使用次数生成文件配置 */
delete from sys_param where code in ('DATA_EXTRACT_FIRST_TIME','DATA_EXTRACT_PERIOD');
/** 数据获取第一次执行时间 */
insert into sys_param values ('DATA_EXTRACT_FIRST_TIME','20:00:00','数据获取第一次执行时间',now());
/** 数据获取执行间隔时间（单位小时） */
insert into sys_param values ('DATA_EXTRACT_PERIOD','24','数据获取执行间隔时间（单位小时）',now());

