create table if not exists `tb_user` (
  user_id int(10) primary key auto_increment comment '用户ID',
  open_id varchar(32) unique not null comment '微信openid',
  default_phone varchar(20) default '' comment '默认手机号',
  default_name varchar(15) default '' comment '默认取件人姓名'
);

create table if not exists `tb_fruit` (
  fruit_id int(10) primary key auto_increment comment '水果ID',
  fruit_name varchar(20) not null unique comment '水果名',
  norm_price double not null comment '日常价格',
  discount_price double comment '抢购价格',
  fruit_intro varchar(512) comment '水果描述',
  display int(5) comment '展示图片',
  intro int(5) comment '详情图片',
  is_flash_sale tinyint default 0 comment '是否参与限时抢购：0——否，1——是' ,
  is_sale tinyint default 0 comment '是否上架：0——否，1——是',
  max_num int default 0 comment '限购个数',
  start_time datetime comment '抢购开始时间',
  end_time datetime comment '抢购结束时间'
);

create table if not exists `tb_order` (
  order_id int(10) primary key auto_increment comment '订单ID',
  user_id int(10) comment '用户ID（外键）',
  order_status tinyint default 0 comment '订单状态：0——待付款，1——已付款（待提货），2——已提货',
  order_content varchar(512) comment '订单内容',
  order_owner varchar(15) comment '客户名',
  order_phone varchar(20) comment '手机号',
  code varchar(8) comment '提货码',

  create_time datetime comment '下单日期',

  constraint order_fk_user foreign key (user_id) references `tb_user`(user_id)
);

create table if not exists `tb_order_item` (
  order_item_id int(10) comment '订单详情id',
  order_id int(10) comment '订单ID',
  fruit_id int(10) comment '水果ID',
  fruit_num int(5) comment '购买数量',
  fruit_price double comment '实际购买单价',

  primary key (order_id, fruit_id),
  constraint item_fk_order foreign key (order_id) references `tb_order`(order_id),
  constraint item_fk_fruit foreign key (fruit_id) references `tb_fruit`(fruit_id)
);

create table if not exists `tb_cart` (
  cart_id int(10) primary key auto_increment comment '购物车ID',
  user_id int(10) comment '客户ID',
  fruit_id int(10) comment '水果ID',
  fruit_num int comment '加购数量',

  constraint cart_fk_user foreign key (user_id) references `tb_user`(user_id),
  constraint cart_fk_fruit foreign key (fruit_id) references `tb_fruit`(fruit_id)
);

create table if not exists `tb_admin` (
  admin_id int(10) primary key auto_increment comment '管理员ID',
  admin_name varchar(15) unique comment '管理员用户名',
  admin_pwd varchar(18) comment '管理员密码',
  admin_phone varchar(20) comment '管理员手机号'
);

create table if not exists `tb_role` (
  role_id int(10) primary key auto_increment comment '权限ID',
  admin_id int(10) comment '管理员ID',
  role_name varchar(20) comment '角色名',

  constraint authorities_fk_admin foreign key (admin_id) references `tb_admin`(admin_id)
);

create table if not exists `tb_history` (
  history_id int(15) primary key auto_increment comment '历史操作记录ID',
  admin_name varchar(10) comment '管理员用户名',
  operation varchar(512) comment '操作记录',
  type varchar(20) comment '记录类型',
  time datetime comment '操作时间'
);