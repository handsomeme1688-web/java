-- ============================================================
-- day03.sql —— Day 3 · slice-v1 练习库：user 表 + 50 条数据
-- 用法：IDEA 里打开本文件 → 右上角选中你的 8.4 数据源 → Ctrl+A 全选 → 执行
-- ============================================================

CREATE DATABASE IF NOT EXISTS slice_v1 DEFAULT CHARSET utf8mb4;
USE slice_v1;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    username    VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名，唯一',
    password    VARCHAR(100) NOT NULL COMMENT '密码（Day4 才换 BCrypt，现在先明文）',
    nickname    VARCHAR(50) COMMENT '昵称',
    age         INT COMMENT '年龄',
    email       VARCHAR(100) COMMENT '邮箱',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '用户表';

-- 50 条数据：id 固定 1~50，方便测试断言（id=1 永远是 user01/张伟/25）
INSERT INTO user (id, username, password, nickname, age, email, create_time) VALUES
(1,  'user01', '123456', '张伟', 25, 'user01@example.com', '2025-01-01 10:00:00'),
(2,  'user02', '123456', '王芳', 31, 'user02@example.com', '2025-01-03 11:20:00'),
(3,  'user03', '123456', '李娜', 22, 'user03@example.com', '2025-01-05 09:15:00'),
(4,  'user04', '123456', '刘洋', 28, 'user04@example.com', '2025-01-08 14:30:00'),
(5,  'user05', '123456', '陈静', 35, 'user05@example.com', '2025-01-10 16:45:00'),
(6,  'user06', '123456', '杨强', 19, 'user06@example.com', '2025-01-13 08:00:00'),
(7,  'user07', '123456', '赵敏', 27, 'user07@example.com', '2025-01-15 12:10:00'),
(8,  'user08', '123456', '黄磊', 42, 'user08@example.com', '2025-01-18 17:25:00'),
(9,  'user09', '123456', '周杰', 24, 'user09@example.com', '2025-01-20 10:40:00'),
(10, 'user10', '123456', '吴桐', 30, 'user10@example.com', '2025-01-23 13:55:00'),
(11, 'user11', '123456', '徐磊', 21, 'user11@example.com', '2025-01-26 09:05:00'),
(12, 'user12', '123456', '孙丽', 33, 'user12@example.com', '2025-01-28 15:20:00'),
(13, 'user13', '123456', '马超', 26, 'user13@example.com', '2025-02-01 11:35:00'),
(14, 'user14', '123456', '朱婷', 29, 'user14@example.com', '2025-02-03 16:50:00'),
(15, 'user15', '123456', '胡歌', 38, 'user15@example.com', '2025-02-06 08:30:00'),
(16, 'user16', '123456', '郭靖', 45, 'user16@example.com', '2025-02-08 14:00:00'),
(17, 'user17', '123456', '何琳', 23, 'user17@example.com', '2025-02-11 10:15:00'),
(18, 'user18', '123456', '高飞', 32, 'user18@example.com', '2025-02-13 12:45:00'),
(19, 'user19', '123456', '林冲', 36, 'user19@example.com', '2025-02-16 09:50:00'),
(20, 'user20', '123456', '罗云', 20, 'user20@example.com', '2025-02-18 15:10:00'),
(21, 'user21', '123456', '郑爽', 27, 'user21@example.com', '2025-02-21 11:25:00'),
(22, 'user22', '123456', '梁山', 41, 'user22@example.com', '2025-02-23 17:40:00'),
(23, 'user23', '123456', '谢娜', 25, 'user23@example.com', '2025-02-26 08:55:00'),
(24, 'user24', '123456', '宋江', 39, 'user24@example.com', '2025-02-28 14:05:00'),
(25, 'user25', '123456', '唐婉', 22, 'user25@example.com', '2025-03-03 10:20:00'),
(26, 'user26', '123456', '许仙', 28, 'user26@example.com', '2025-03-05 16:35:00'),
(27, 'user27', '123456', '韩梅', 34, 'user27@example.com', '2025-03-08 09:45:00'),
(28, 'user28', '123456', '冯军', 26, 'user28@example.com', '2025-03-10 13:00:00'),
(29, 'user29', '123456', '邓超', 37, 'user29@example.com', '2025-03-13 15:15:00'),
(30, 'user30', '123456', '曹操', 44, 'user30@example.com', '2025-03-15 11:30:00'),
(31, 'user31', '123456', '彭飞', 24, 'user31@example.com', '2025-03-18 08:40:00'),
(32, 'user32', '123456', '曾毅', 31, 'user32@example.com', '2025-03-20 14:50:00'),
(33, 'user33', '123456', '肖战', 23, 'user33@example.com', '2025-03-23 10:00:00'),
(34, 'user34', '123456', '田雨', 29, 'user34@example.com', '2025-03-25 16:10:00'),
(35, 'user35', '123456', '董梅', 35, 'user35@example.com', '2025-03-28 09:20:00'),
(36, 'user36', '123456', '袁华', 27, 'user36@example.com', '2025-03-30 15:30:00'),
(37, 'user37', '123456', '潘越', 40, 'user37@example.com', '2025-04-02 11:40:00'),
(38, 'user38', '123456', '蒋欣', 21, 'user38@example.com', '2025-04-04 17:50:00'),
(39, 'user39', '123456', '蔡明', 33, 'user39@example.com', '2025-04-07 08:10:00'),
(40, 'user40', '123456', '余欢', 26, 'user40@example.com', '2025-04-09 14:20:00'),
(41, 'user41', '123456', '杜鹃', 30, 'user41@example.com', '2025-04-12 10:30:00'),
(42, 'user42', '123456', '叶青', 24, 'user42@example.com', '2025-04-14 16:40:00'),
(43, 'user43', '123456', '程勇', 38, 'user43@example.com', '2025-04-17 09:00:00'),
(44, 'user44', '123456', '苏樱', 22, 'user44@example.com', '2025-04-19 13:10:00'),
(45, 'user45', '123456', '魏东', 36, 'user45@example.com', '2025-04-22 15:25:00'),
(46, 'user46', '123456', '吕布', 43, 'user46@example.com', '2025-04-24 11:35:00'),
(47, 'user47', '123456', '丁香', 25, 'user47@example.com', '2025-04-27 08:45:00'),
(48, 'user48', '123456', '任盈', 28, 'user48@example.com', '2025-04-29 14:55:00'),
(49, 'user49', '123456', '沈括', 32, 'user49@example.com', '2025-05-02 10:05:00'),
(50, 'user50', '123456', '姚明', 46, 'user50@example.com', '2025-05-04 16:15:00');

-- 验证：应返回 50
SELECT COUNT(*) FROM user;
