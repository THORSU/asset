package asset.pojo;

/**
 * Author:QuincySu
 * Date:Created in 2018/3/26
 */
public class DeviceForm {
    //设备号
    private String deviceId;
    //设备名
    private String deviceName;
    //使用状态
    private String useStatus;
    //入库时间
    private String storageTime;

    public String getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(String storageTime) {
        this.storageTime = storageTime;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
