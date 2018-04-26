DROP PROCEDURE IF EXISTS `hive_tv_new`.`pbs_tmp`;

DELIMITER $$

CREATE PROCEDURE `hive_tv_new`.`pbs_tmp` (number_ INT) 
BEGIN

DECLARE i INT;
DECLARE new_id_ INT;
DECLARE new2_id_ INT;
SET i = 0;

WHILE i < number_ DO

INSERT INTO pbs_goods (
  goods_name,
  sale_style,
  price_style,
  is_booking,
  sale_start_time,
  sale_end_time,
  templet_id,
  is_effective
) 
VALUES
  (
    CONCAT(
      'pac',i,
      FLOOR(RAND() * 10000),
      DATE_FORMAT(NOW(), '%Y%m%d'),
      FLOOR(RAND() * 10000000)
    ),
    1,
    1,
    0,
    '2018-03-14 17:14:38',
    '2018-03-30 17:14:41',
    1,
    1
  ) ;
  
  SET new_id_ = (SELECT MAX(id) FROM pbs_goods);

INSERT INTO pbs_goods_image(goods_id,`type`,img_url)VALUES(new_id_,1,'http://211.103.138.119/tvimg/2018/03/14/17/14/44/1521018884586.jpg');
INSERT INTO pbs_goods_image(goods_id,`type`,img_url)VALUES(new_id_,2,'http://211.103.138.119/tvimg/2018/03/14/17/14/48/1521018888663.jpg');
INSERT INTO pbs_goods_image(goods_id,`type`,img_url)VALUES(new_id_,3,'http://211.103.138.119/tvimg/2018/03/14/17/14/52/1521018892129.jpg');
INSERT INTO pbs_goods_image(goods_id,`type`,img_url)VALUES(new_id_,4,'http://211.103.138.119/tvimg/2018/03/14/17/15/00/1521018900842.jpg');

INSERT INTO pbs_goods_content (
  goods_id,
  content_id,
  content_type,
  content_category,
  content_name,
  seq,
  single_set_minutes,
  multi_set_numbers,
  vip_type,
  apk_bag_name
) 
VALUES
  (
    new_id_,
    113757,
    1,
    0,
    '星夜相声会馆 徐德亮王文林相声',
    1,
    6,
    5,
    3,
    'com.hiveview.cloudscreen.py'
  ) ;
  
  INSERT INTO pbs_goods_price (
  goods_id,
  price_style,
  goods_price,
  effective_hours,
  seq,
  automatic_renewal
) 
VALUES
  (new_id_, 1, 5.00, 5, 1, 0) ;
  
  SET new2_id_ = (SELECT MAX(id) FROM pbs_goods_price WHERE goods_id = new_id_);
  
INSERT INTO pbs_goods_price_strategy(goods_price_id,strategy_formula_id,strategy_name,strategy_desc,strategy_start_time,strategy_end_time,strategy_style,now_price)VALUES(new2_id_,2,'免费','“原价”=“0”','2018-03-15 09:52:10','2018-03-29 09:52:12',1,0.00);
INSERT INTO pbs_goods_price_strategy(goods_price_id,strategy_formula_id,strategy_name,strategy_desc,strategy_start_time,strategy_end_time,strategy_style,now_price)VALUES(new2_id_,3,'降价至','“原价”=“现价”','2018-03-15 09:52:10','2018-03-29 09:52:12',0,206.00);



SET i = i+1;

END WHILE;

END $$

DELIMITER ;