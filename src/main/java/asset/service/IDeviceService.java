package asset.service;

import asset.pojo.ApplyForm;
import asset.pojo.DeviceForm;
import asset.pojo.RefundForm;
import asset.pojo.RepairForm;

import java.util.List;

/**
 * Author:QuincySu
 * Date:Created in 2018/3/26
 */
public interface IDeviceService {
    //查询设备对应关系
    public DeviceForm getDevice(String deviceId);
    //获取设备列表
    public List<DeviceForm> getDeviceList(DeviceForm deviceForm);
    //获取设备列表详细信息
    public List<DeviceForm> getDeviceLists();
    //申请设备
    public int applyDevice(ApplyForm deviceForm);
    //申请修改状态位
    public int modifyStatus(DeviceForm deviceForm);
    //报修设备
    public int repairDevice(RepairForm repairForm);
    //退还设备
    public int refundDevice(RefundForm refundForm);
    //查询申请人
    public ApplyForm getApplyForm(ApplyForm applyForm);
    //查询报修人
    public RepairForm getRepairForm(RepairForm repairForm);
    //查询退还人
    public RefundForm getRefundForm(RefundForm refundForm);
    //添加申请的审核人
    public int addApplyAuditName(ApplyForm applyForm);
    //添加报修的审核人
    public int addRepairAuditName(RepairForm repairForm);
    //添加退还的审核人
    public int addRefundAuditName(RefundForm refundForm);
    //设备入库
    public int addDevice(DeviceForm deviceForm);
}
