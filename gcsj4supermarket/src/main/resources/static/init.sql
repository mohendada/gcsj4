-- 初始化数据库
drop database if exists warehouse;
create database warehouse;

-- 创建用户表
CREATE TABLE user
(
    user_Id      INT PRIMARY KEY,       -- 用户ID
    user_account VARCHAR(255) NOT NULL, -- 账号
    password     VARCHAR(255) NOT NULL, -- 密码
    role_ID      INT          NOT NULL, -- 角色ID
    user_name    VARCHAR(255) NOT NULL, -- 用户名
    sex          VARCHAR(10)  NOT NULL, -- 性别
    phone_number VARCHAR(20)  NOT NULL, -- 联系电话
    email        VARCHAR(255) NOT NULL, -- 邮箱
    address      VARCHAR(255) NOT NULL  -- 送货地址
);

-- 插入示例数据到用户表
INSERT INTO user (user_Id, user_account, password, role_ID, user_name, sex, phone_number, email, address)
VALUES (1, 'john_doe', 'password123', 1, 'John Doe', 'Male', '1234567890', 'john@example.com', '123 Main St'), -- 示例用户1
       (2, 'jane_smith', 'password456', 2, 'Jane Smith', 'Female', '0987654321', 'jane@example.com', '456 Elm St');
-- 示例用户2

-- 创建商品表
CREATE TABLE Goods
(
    goods_Id    INT PRIMARY KEY,       -- 商品ID
    goods_name  VARCHAR(255) NOT NULL, -- 商品名称
    goods_price FLOAT        NOT NULL, -- 商品价格
    goods_photo VARCHAR(255) NOT NULL, -- 商品图片
    supplier_id INT          NOT NULL  -- 供货商编号
);

-- 插入示例数据到商品表
INSERT INTO Goods (goods_Id, goods_name, goods_price, goods_photo, supplier_id)
VALUES (1, 'Laptop', 999.99, 'laptop.jpg', 1), -- 示例商品1
       (2, 'Smartphone', 499.99, 'smartphone.jpg', 2);
-- 示例商品2

-- 创建存储表
CREATE TABLE Store
(
    goods_shelf_id    INT PRIMARY KEY,       -- 存储ID
    goods_id          INT          NOT NULL, -- 商品ID
    goods_number      INT          NOT NULL, -- 商品数量
    goods_kind        VARCHAR(255) NOT NULL, -- 商品种类
    goods_store_id    INT          NOT NULL, -- 存储仓库编号
    goods_shelf_floor INT          NOT NULL  -- 层号
);

-- 插入示例数据到存储表
INSERT INTO Store (goods_shelf_id, goods_id, goods_number, goods_kind, goods_store_id, goods_shelf_floor)
VALUES (1, 1, 50, 'Electronics', 101, 1), -- 示例存储1
       (2, 2, 100, 'Electronics', 102, 2);
-- 示例存储2

-- 创建进货表
CREATE TABLE Restock
(
    restock_id           INT PRIMARY KEY,       -- 进货编号ID
    restock_time         DATETIME     NOT NULL, -- 进货时间
    restock_goods_name   VARCHAR(255) NOT NULL, -- 货品名称
    restock_goods_number INT          NOT NULL, -- 货品数量
    supplier_id          INT          NOT NULL, -- 供货商编号
    restock_statue       INT          NOT NULL  -- 订单状态（0：未完成1：已完成）
);

-- 插入示例数据到进货表
INSERT INTO Restock (restock_id, restock_time, restock_goods_name, restock_goods_number, supplier_id, restock_statue)
VALUES (1, '2024-01-01 10:00:00', 'Laptop', 20, 1, 1), -- 示例进货1
       (2, '2024-01-02 11:00:00', 'Smartphone', 50, 2, 0);
-- 示例进货2

-- 创建退货表
CREATE TABLE Refound
(
    return_goods_id     INT PRIMARY KEY,       -- 退货编号ID
    return_goods_time   DATETIME     NOT NULL, -- 退货时间
    return_goods_name   VARCHAR(255) NOT NULL, -- 货品名称
    return_goods_number INT          NOT NULL, -- 货品数量
    return_id           INT          NOT NULL, -- 供货商编号
    return_statue       INT          NOT NULL  -- 订单状态（0：未完成1：已完成）
);

-- 插入示例数据到退货表
INSERT INTO Refound (return_goods_id, return_goods_time, return_goods_name, return_goods_number, return_id,
                    return_statue)
VALUES (1, '2024-01-05 12:00:00', 'Laptop', 5, 1, 1), -- 示例退货1
       (2, '2024-01-06 13:00:00', 'Smartphone', 10, 2, 0);
-- 示例退货2

-- 创建订单表
CREATE TABLE `Order`
(
    order_id         INT PRIMARY KEY,   -- 订单编号ID
    order_kind       INT      NOT NULL, -- 订单类型
    order_time       DATETIME NOT NULL, -- 订单创建时间
    order_status     INT      NOT NULL, -- 订单状态
    order_name       INT      NOT NULL, -- 订单商品名称
    order_number     INT      NOT NULL, -- 订单商品数量
    order_creater_id INT      NOT NULL, -- 订单创建者编号
    receiving_time   DATETIME NOT NULL  -- 收货时间（默认为创建时间）
);

-- 插入示例数据到订单表
INSERT INTO `Order` (order_id, order_kind, order_time, order_status, order_name, order_number, order_creater_id,
                     receiving_time)
VALUES (1, 1, '2024-01-10 14:00:00', 1, 1, 2, 1, '2024-01-11 14:00:00'), -- 示例订单1
       (2, 2, '2024-01-15 15:00:00', 0, 2, 1, 2, '2024-01-15 15:00:00');
-- 示例订单2

-- 创建员工表
CREATE TABLE Employee
(
    employee_id           INT PRIMARY KEY,       -- 员工编号ID
    employee_name         VARCHAR(255) NOT NULL, -- 员工姓名
    employee_age          INT          NOT NULL, -- 员工年龄
    employee_phone_number VARCHAR(20)  NOT NULL, -- 员工联系电话
    employee_work_status  INT          NOT NULL, -- 员工在职状态
    employee_lfap         INT          NOT NULL, -- 员工事假次数
    employee_sick_leave   INT          NOT NULL, -- 员工病假次数
    employee_daily_wage   FLOAT        NOT NULL, -- 员工每日工资
    employee_work_day     INT          NOT NULL, -- 员工工作时长
    employee_permission   INT          NOT NULL  -- 员工权限
);

-- 插入示例数据到员工表
INSERT INTO Employee (employee_id, employee_name, employee_age, employee_phone_number, employee_work_status,
                      employee_lfap, employee_sick_leave, employee_daily_wage, employee_work_day, employee_permission)
VALUES (1, 'Alice', 30, '1231231234', 1, 2, 1, 100.0, 20, 1), -- 示例员工1
       (2, 'Bob', 35, '4321432143', 0, 3, 2, 150.0, 15, 2);
-- 示例员工2

-- 创建供货商表
CREATE TABLE Supplier
(
    supplier_id           INT PRIMARY KEY,       -- 供货商编号ID
    supplier_phone_number VARCHAR(20)  NOT NULL, -- 联系电话
    supplier_address      VARCHAR(255) NOT NULL, -- 供货地址
    supplier_name         VARCHAR(255) NOT NULL  -- 公司名称
);

-- 插入示例数据到供货商表
INSERT INTO Supplier (supplier_id, supplier_phone_number, supplier_address, supplier_name)
VALUES (1, '1112223333', '789 Pine St', 'Supplier A'), -- 示例供货商1
       (2, '4445556666', '987 Oak St', 'Supplier B'); -- 示例供货商2
