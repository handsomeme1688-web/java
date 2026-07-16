-- ============================================================
-- day01.sql —— Day 1 产出：建表 + 20 条练习 SQL
-- ============================================================
-- 【SQL 文件怎么“生成”？】
-- 不需要任何工具生成，它就是个普通文本文件，后缀是 .sql 而已。
-- 你在 IDEA 里敲 SQL 的那个查询控制台，IDEA 其实已经自动保存了：
--   Project 面板最下面 → Scratches and Consoles → Database Consoles
--   → 打开你的 console → 全选复制 → 粘贴到本文件，替换下面的示例。
-- 以后每天：项目里右键 New → File → 输入 dayXX.sql → 粘语句 → git 提交。
--
-- 【补充】想把数据库里“已有的表”反向导出成 .sql：
--   IDEA 的 Database 面板右键表 → SQL Scripts → SQL Generator（复制出来存文件）
--   或命令行：mysqldump -uroot -p 库名 > 导出.sql
-- ============================================================

CREATE DATABASE IF NOT EXISTS practice DEFAULT CHARSET utf8mb4;
USE practice;

-- ---------- 1. DDL：建表（id 主键，username 唯一） ----------
CREATE TABLE user (
    id          INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    username    VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名，唯一约束',
    password    VARCHAR(100) NOT NULL COMMENT '密码',
    nickname    VARCHAR(50) COMMENT '昵称',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '用户表';

-- ---------- 2~6. INSERT ×5 ----------
INSERT INTO user (username, password, nickname) VALUES ('zhangsan', '123456', '张三');
INSERT INTO user (username, password, nickname) VALUES ('lisi',     '123456', '李四');
INSERT INTO user (username, password, nickname) VALUES ('wangwu',   'abc123', '王五');
INSERT INTO user (username, password, nickname) VALUES ('zhaoliu',  'abc123', '赵六');
INSERT INTO user (username, password, nickname) VALUES ('sunqi',    '111111', '孙七');

-- ---------- 7~16. SELECT ×10（条件/模糊/排序/分页/聚合） ----------
SELECT * FROM user;                                          -- 7. 查全部
SELECT username, nickname FROM user WHERE id = 1;            -- 8. WHERE 等值
SELECT * FROM user WHERE id > 2;                             -- 9. WHERE 比较
SELECT * FROM user WHERE username LIKE 'z%';                 -- 10. LIKE：z 开头
SELECT * FROM user WHERE nickname LIKE '%三%';               -- 11. LIKE：包含“三”
SELECT * FROM user WHERE id IN (1, 3, 5);                    -- 12. IN
SELECT * FROM user ORDER BY create_time DESC;                -- 13. 排序
SELECT * FROM user ORDER BY id DESC LIMIT 2;                 -- 14. 排序 + 取前 2
SELECT * FROM user LIMIT 0, 2;                               -- 15. 分页：第 1 页每页 2 条
SELECT COUNT(*) AS total FROM user;                          -- 16. 聚合计数

-- ---------- 17~18. UPDATE ×2 ----------
UPDATE user SET nickname = '张三丰' WHERE username = 'zhangsan';  -- 17
UPDATE user SET password = '654321' WHERE id = 2;                 -- 18

-- ---------- 19~20. DELETE ×2 ----------
DELETE FROM user WHERE id = 5;                               -- 19
DELETE FROM user WHERE username = 'wangwu';                  -- 20
