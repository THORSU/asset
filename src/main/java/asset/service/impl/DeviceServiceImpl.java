package asset.service.impl;

import asset.mapper.DeviceMapper;
import asset.pojo.ApplyForm;
import asset.pojo.DeviceForm;
import asset.pojo.RefundForm;
import asset.pojo.RepairForm;
import asset.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:QuincySu
 * Date:Created in 2018/3/26
 */
@Service
public class DeviceServiceImpl implements IDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public DeviceForm getDevice(String deviceId) {
        return deviceMapper.getDevice(deviceId);
    }

    @Override
    public List<DeviceForm> getDeviceList(DeviceForm deviceForm) {
        return deviceMapper.getDeviceList(deviceForm);
    }

    @Override
    public int applyDevice(ApplyForm deviceForm) {
        return deviceMapper.applyDevice(deviceForm);
    }

    @Override
    public int modifyStatus(DeviceForm deviceForm) {
        return deviceMapper.modifyStatus(deviceForm);
    }

    @Override
    public int repairDevice(RepairForm repairForm) {
        return deviceMapper.repairDevice(repairForm);
    }

    @Override
    public int refundDevice(RefundForm refundForm) {
        return deviceMapper.refundDevice(refundForm);
    }

    @Override
    public ApplyForm getApplyForm(ApplyForm applyForm) {
        return deviceMapper.getApplyForm(applyForm);
    }

    @Override
    public RepairForm getRepairForm(RepairForm repairForm) {
        return deviceMapper.getRepairForm(repairForm);
    }

    @Override
    public RefundForm getRefundForm(RefundForm refundForm) {
        return deviceMapper.getRefundForm(refundForm);
    }

    @Override
    public int addApplyAuditName(ApplyForm applyForm) {
        return deviceMapper.addApplyAuditName(applyForm);
    }

    @Override
    public int addRepairAuditName(RepairForm repairForm) {
        return deviceMapper.addRepairAuditName(repairForm);
    }

    @Override
    public int addRefundAuditName(RefundForm refundForm) {
        return deviceMapper.addRefundAuditName(refundForm);
    }

    @Override
    public int addDevice(DeviceForm deviceForm) {
        return deviceMapper.addDevice(deviceForm);
    }
}
