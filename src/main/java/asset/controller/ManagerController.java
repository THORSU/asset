package asset.controller;

import asset.pojo.ApplyForm;
import asset.pojo.DeviceForm;
import asset.pojo.RefundForm;
import asset.pojo.RepairForm;
import asset.service.IDeviceService;
import asset.service.IUnitService;
import asset.utils.DataUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/asset")
public class ManagerController {
    private static Logger logger = Logger.getLogger(ManagerController.class);
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IUnitService unitService;
    private DeviceForm deviceForm = new DeviceForm();
    private ApplyForm applyForm = new ApplyForm();
    private RefundForm refundForm = new RefundForm();
    private RepairForm repairForm = new RepairForm();

    //审核申请
    @RequestMapping(value = "/auditApply.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object auditApply(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //todo 考虑到设备的不唯一性，此处改为设备id
        //设备id
        String deviceId = new String(request.getParameter("deviceId").getBytes("iso-8859-1"), "utf-8");
        //设备名
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        //生成审核时间
        String time = DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        //通过cookie获得username
        final Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
            }
        }
        //查看对应关系
        DeviceForm deviceForm1 = deviceService.getDevice(deviceId);
        if (deviceForm1.getDeviceName().equals(deviceName)) {
            //TODO 查看申请表的信息 由于设备的id唯一，所以此处采用设备id查询
            applyForm.setDeviceId(deviceId);
            ApplyForm applyForm1 = deviceService.getApplyForm(applyForm);
            //如果不为空说明有申请记录
            if (applyForm1 != null) {
                applyForm1.setAuditName(username);
                applyForm1.setAuditTime(time);
                //判断状态是否是申请状态？
                if ("3".equals(deviceForm1.getUseStatus())) {
                    //修改成（在用）状态
                    deviceForm1.setUseStatus("0");
                    //修改设备表状态
                    int num = deviceService.modifyStatus(deviceForm1);
                    //向申请表增加审核人信息
                    int num1 = deviceService.addApplyAuditName(applyForm1);
                    if (num == 1 && num1 == 1) {
                        return "audit success";
                    } else {
                        return "audit fail";
                    }
                } else {
                    //不是申请状态
                    return "useStatus error";
                }
            } else {
                //申请表没有申请记录
                return "apply error";
            }
        } else {
            //不匹配
            return "not match";
        }
    }

    //审核报修
    @RequestMapping(value = "/auditRepair.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object auditRepair(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //todo 考虑到设备名的不唯一性，此处改为设备id
        //设备id
        String deviceId = new String(request.getParameter("deviceId").getBytes("iso-8859-1"), "utf-8");
        //设备名
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        String time = DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        //通过cookie获得username
        final Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
            }
        }
        //查看对应关系
        DeviceForm deviceForm1 = deviceService.getDevice(deviceId);
        if (deviceForm1.getDeviceName().equals(deviceName)) {
            repairForm.setDeviceId(deviceId);
            RepairForm repairForm1 = deviceService.getRepairForm(repairForm);
            if (repairForm1 != null) {
                repairForm1.setAuditName(username);
                repairForm1.setAuditTime(time);
                if ("4".equals(deviceForm1.getUseStatus())) {
                    //修改成（毁坏）状态
                    deviceForm1.setUseStatus("2");
                    //修改设备表状态
                    int num = deviceService.modifyStatus(deviceForm1);
                    //向申请表增加审核人信息
                    int num1 = deviceService.addRepairAuditName(repairForm1);
                    if (num == 1 && num1 == 1) {
                        return "audit success";
                    } else {
                        return "audit fail";
                    }
                } else {
                    //不是申请状态
                    return "useStatus error";
                }
            } else {
                //申请表没有申请记录
                return "apply error";
            }
        } else {
            //不匹配
            return "not match";
        }
    }

    //审核退还
    @RequestMapping(value = "/auditRefund.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object auditRefund(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //todo 考虑到设备的不唯一性，此处改为设备id
        //设备id
        String deviceId = new String(request.getParameter("deviceId").getBytes("iso-8859-1"), "utf-8");
        //设备名
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        String time = DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        //通过cookie获得username
        final Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
            }
        }
        //查看对应关系
        DeviceForm deviceForm1 = deviceService.getDevice(deviceId);
        if (deviceForm1.getDeviceName().equals(deviceName)) {
            refundForm.setDeviceId(deviceId);
            RefundForm refundForm1 = deviceService.getRefundForm(refundForm);
            if (refundForm1 != null) {
                refundForm1.setAuditName(username);
                refundForm1.setAuditTime(time);
                if ("5".equals(deviceForm1.getUseStatus())) {
                    //修改成（闲置）状态
                    deviceForm1.setUseStatus("1");
                    //修改设备表状态
                    int num = deviceService.modifyStatus(deviceForm1);
                    //向退还表表增加审核人信息
                    int num1 = deviceService.addRefundAuditName(refundForm1);
                    if (num == 1 && num1 == 1) {
                        return "audit success";
                    } else {
                        return "audit fail";
                    }
                } else {
                    //不是申请状态
                    return "useStatus error";
                }
            } else {
                //申请表没有申请记录
                return "apply error";
            }
        } else {
            //不匹配
            return "not match";
        }
    }

    //设备入库
    @RequestMapping(value = "/addDevice.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object addDevice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        deviceForm.setDeviceName(deviceName);
        //todo 此处考虑到设备id过于复杂，放弃UUID，该用数据库id列自增
//        String id=RandomAccessUtil.getRandom("Device");
//        deviceForm.setDeviceId(id);
        deviceForm.setUseStatus("1");
        String time = DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        deviceForm.setStorageTime(time);
        logger.info(deviceName);
        int num = deviceService.addDevice(deviceForm);
        System.out.println(num);
        if (num == 1) {
            return "add success";
        } else {
            return "add fail";
        }
    }

    //设备出库
    @RequestMapping(value = "/delDevice.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object delDevice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String deviceId = request.getParameter("deviceId").trim();
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        DeviceForm deviceForm1 = deviceService.getDevice(deviceId);
        if (deviceForm1.getDeviceName().equals(deviceName)) {
            int num = deviceService.delDevice(deviceForm1);
            if (1 == num) {
                return "del success";
            } else {
                return "del fail";
            }
        } else {
            return "not match";
        }
    }

    //修改设备
    @RequestMapping(value = "/editDevice.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object editDevice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //设备名
        String deviceId = request.getParameter("deviceId").trim();
        //原设备名
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        //新设备名
        String newDeviceName = new String(request.getParameter("newDeviceName").getBytes("iso-8859-1"), "utf-8");

        DeviceForm res = deviceService.getDevice(deviceId);
        if (!res.getDeviceName().equals(deviceName)) {
            return "check error";
        }
        deviceForm.setDeviceId(deviceId);
        deviceForm.setDeviceName(newDeviceName);
        Integer num = deviceService.editDevice(deviceForm);
        if (num == 1) {
            return "edit success";
        } else {
            return "edit fail";
        }

    }
}
