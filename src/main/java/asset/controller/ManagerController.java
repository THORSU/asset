package asset.controller;

import asset.pojo.ApplyForm;
import asset.pojo.DeviceForm;
import asset.pojo.RefundForm;
import asset.pojo.RepairForm;
import asset.service.IDeviceService;
import asset.service.IUnitService;
import asset.utils.DataUtil;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Author:QuincySu
 * Date:Created in 2018/4/12
 */
@Controller
@RequestMapping("/asset")
public class ManagerController {
    private static Logger logger = Logger.getLogger(ManagerController.class);
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IUnitService unitService;
    private DeviceForm deviceForm=new DeviceForm();
    private ApplyForm applyForm=new ApplyForm();
    private RefundForm refundForm=new RefundForm();
    private RepairForm repairForm=new RepairForm();
    //审核申请
    @RequestMapping(value = "/auditApply.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object auditApply(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //审核人
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
        //申请人
        String applyName = new String(request.getParameter("applyName").getBytes("iso-8859-1"), "utf-8");
        //todo 考虑到设备的不唯一性，此处改为设备id
        String deviceId = new String(request.getParameter("deviceId").getBytes("iso-8859-1"), "utf-8");
        String time= DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        applyForm.setApplyName(applyName);
        ApplyForm applyForm1=deviceService.getApplyForm(applyForm);
        if (applyForm1!=null){
            applyForm1.setAuditName(username);
            applyForm1.setAuditTime(time);
        }else{
            return "apply error";
        }
        DeviceForm deviceForm1=deviceService.getDevice(deviceId);
        String status=deviceForm1.getUseStatus();
        if ("3"==status){
            deviceForm1.setUseStatus("0");
            int num=deviceService.modifyStatus(deviceForm1);
            int num1=deviceService.addApplyAuditName(applyForm1);
            if (num==1&&num1==1){
                return "audit success";
            }else {
                return "audit fail";
            }
        }else {
            return "useStatus error";
        }
    }
    //审核报修
    @RequestMapping(value = "/auditRepair.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object auditRepair(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        //审核人
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
        //申请人
        String applyName = new String(request.getParameter("applyName").getBytes("iso-8859-1"), "utf-8");
        //todo 考虑到设备的不唯一性，此处改为设备id
        String deviceId = new String(request.getParameter("deviceId").getBytes("iso-8859-1"), "utf-8");
        repairForm.setApplyName(applyName);
        String time= DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        RepairForm repairForm1=deviceService.getRepairForm(repairForm);
        if (repairForm1!=null){
            repairForm1.setAuditName(username);
            repairForm1.setAuditTime(time);
        }else {
            return "apply error";
        }
        DeviceForm deviceForm1=deviceService.getDevice(deviceId);
        String status=deviceForm1.getUseStatus();
        if ("4"==status){
            deviceForm1.setUseStatus("2");
            int num=deviceService.modifyStatus(deviceForm1);
            int num1=deviceService.addRepairAuditName(repairForm1);
            if (num==1&&num1==1){
                return "audit success";
            }else {
                return "audit fail";
            }
        }else {
            return "useStatus error";
        }
    }

    //审核退还
    @RequestMapping(value = "/auditRefund.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object auditRefund(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        //审核人
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
        //申请人
        String applyName = new String(request.getParameter("applyName").getBytes("iso-8859-1"), "utf-8");
        //todo 考虑到设备的不唯一性，此处改为设备id
        String deviceId = new String(request.getParameter("deviceId").getBytes("iso-8859-1"), "utf-8");
        String time= DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        refundForm.setApplyName(applyName);
        RefundForm refundForm1=deviceService.getRefundForm(refundForm);
        if (refundForm1!=null){
            refundForm1.setAuditName(username);
            refundForm1.setAuditTime(time);
        }else {
            return "apply error";
        }
        DeviceForm deviceForm1=deviceService.getDevice(deviceId);
        String status=deviceForm1.getUseStatus();
        if ("5"==status){
            deviceForm1.setUseStatus("1");
            int num=deviceService.modifyStatus(deviceForm1);
            int num1=deviceService.addRefundAuditName(refundForm1);
            if (num==1&&num1==1){
                return "audit success";
            }else {
                return "audit fail";
            }
        }else {
            return "useStatus error";
        }
    }

    //设备入库
    @RequestMapping(value = "/addDevice.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object addDevice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        String deviceName=new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        deviceForm.setDeviceName(deviceName);
        //todo 此处考虑到设备id过于复杂，放弃UUID，该用数据库id列自增
//        String id=RandomAccessUtil.getRandom("Device");
//        deviceForm.setDeviceId(id);
        deviceForm.setUseStatus("1");
        String time= DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        deviceForm.setStorageTime(time);
        System.out.println(JSON.toJSONString(deviceName));
        int num=deviceService.addDevice(deviceForm);
        System.out.println(num);
        if (num == 1) {
            return "add success";
        }else {
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
}
