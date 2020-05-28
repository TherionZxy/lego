
-- 水果测试数据
insert into `tb_fruit`(fruit_name,norm_price,discount_price,fruit_intro,fruit_pic,max_num)
values('苹果',12,5,'香香的口味，你吃过没','www_5_2',10);

insert into `tb_fruit`(fruit_name,norm_price,discount_price,fruit_intro,fruit_pic,max_num)
values('梨子',10,3,'好吃好吃','www_5_3',10);

insert into `tb_fruit`(fruit_name,norm_price,discount_price,fruit_intro,fruit_pic,max_num)
values('香蕉',11,4,'非一般的感觉','www_4_2',10);

insert into `tb_fruit`(fruit_name,norm_price,discount_price,fruit_intro,fruit_pic,max_num)
values('杨桃',3,1,'六六六','www_5_4',10);

insert into `tb_fruit`(fruit_name,norm_price,discount_price,fruit_intro,fruit_pic,max_num)
values('荔枝',9.2,2,'哈哈哈哈','www_2_2',10);

insert into `tb_fruit`(fruit_name,norm_price,discount_price,fruit_intro,fruit_pic,max_num)
values('西瓜',12,4,'哇哦','www_3_2',10);

-- 管理员测试数据
insert into `tb_admin`(admin_name,admin_pwd,admin_phone)
values ('小野','123456','15871384790');

insert into `tb_admin`(admin_name,admin_pwd,admin_phone)
values ('zxyono','123456','123456');

insert into `tb_admin`(admin_name,admin_pwd,admin_phone)
values ('admin','111111','123456');

-- 权限表测试数据
insert into `tb_role`(admin_id, `role_name`)
values (1, 'SUPERADMIN');

insert into `tb_role`(admin_id, `role_name`)
values (1, 'ADMIN');

insert into `tb_role`(admin_id, `role_name`)
values (2, 'ADMIN');

insert into `tb_role`(admin_id, `role_name`)
values (3, 'SUPERADMIN');