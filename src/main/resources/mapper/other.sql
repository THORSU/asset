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