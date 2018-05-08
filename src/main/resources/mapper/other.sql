# 业务管理员
DROP TABLE IF EXISTS SenManager;
CREATE TABLE SenManager
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  name     VARCHAR(255) NULL,
  password VARCHAR(255) NULL,
  status   VARCHAR(255) NULL
)
  AUTO_INCREMENT = 1,
  ENGINE = InnoDB;
# 设备表.
DROP TABLE IF EXISTS DeviceForm;
CREATE TABLE DeviceForm
(
  deviceId    INT(11) AUTO_INCREMENT
    PRIMARY KEY,
  deviceName  VARCHAR(255) NULL,
  useStatus   VARCHAR(255) NULL,
  storageTime VARCHAR(255) NULL
)
  AUTO_INCREMENT = 1,
  ENGINE = InnoDB;
# 申请设备表
DROP TABLE IF EXISTS ApplyForm;
CREATE TABLE ApplyForm
(
  id         INT AUTO_INCREMENT
  COMMENT '申请单id'
    PRIMARY KEY,
  unitId     VARCHAR(255) NULL
  COMMENT '申请单位号',
  deviceId   VARCHAR(255) NULL
  COMMENT '设备号',
  applyName  VARCHAR(255) NULL
  COMMENT '申请人姓名',
  deviceName VARCHAR(255) NULL
  COMMENT '仪器名称',
  unitName   VARCHAR(255) NULL
  COMMENT '申请单位名',
  auditName  VARCHAR(255) NULL
  COMMENT '审核人',
  auditTime  VARCHAR(255) NULL
  COMMENT '审核时间',
  applyTime  VARCHAR(255) NULL
  COMMENT '申请时间'
)
  ENGINE = InnoDB;
# 设备信息表
DROP TABLE IF EXISTS DeviceForm;
CREATE TABLE DeviceForm
(
  deviceId    INT(11) AUTO_INCREMENT
    PRIMARY KEY,
  deviceName  VARCHAR(255) NULL,
  useStatus   VARCHAR(255) NULL,
  storageTime VARCHAR(255) NULL
)
  ENGINE = InnoDB;
# 退还表
DROP TABLE IF EXISTS RefundForm;
CREATE TABLE RefundForm
(
  id         INT(11) AUTO_INCREMENT
    PRIMARY KEY,
  unitId     VARCHAR(255) NULL,
  deviceName VARCHAR(255) NULL,
  deviceId   VARCHAR(255) NULL,
  applyName  VARCHAR(255) NULL,
  unitName   VARCHAR(255) NULL,
  auditName  VARCHAR(255) NULL,
  auditTime  VARCHAR(255) NULL,
  refundTime VARCHAR(255) NULL
)
  ENGINE = InnoDB;
# 报修表
DROP TABLE IF EXISTS RepairForm;
CREATE TABLE RepairForm
(
  id         INT(11) AUTO_INCREMENT
    PRIMARY KEY,
  unitId     VARCHAR(255) NULL,
  deviceId   VARCHAR(255) NULL,
  applyName  VARCHAR(255) NULL,
  deviceName VARCHAR(255) NULL,
  unitName   VARCHAR(255) NULL,
  auditName  VARCHAR(255) NULL,
  auditTime  VARCHAR(255) NULL,
  repairTime VARCHAR(255) NULL
)
  ENGINE = InnoDB;
#单位表
DROP TABLE IF EXISTS Unit;
CREATE TABLE Unit
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  unitId   VARCHAR(255) NULL,
  unitName VARCHAR(255) NULL
)
  ENGINE = InnoDB;
#老师表
CREATE TABLE Teacher
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  name     VARCHAR(255) NULL,
  password VARCHAR(255) NULL,
  unitId   VARCHAR(255) NULL
)
  ENGINE = InnoDB;
