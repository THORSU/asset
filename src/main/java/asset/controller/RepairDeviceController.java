package asset.controller;

import asset.pojo.DeviceForm;
import asset.pojo.RepairForm;
import asset.pojo.Unit;
import asset.service.IDeviceService;
import asset.service.IUnitService;
import asset.service.IUserService;
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

/**
 * Author:QuincySu
 * Date:Created in 2018/4/11
 */
@Controller
@RequestMapping("/asset")
public class RepairDeviceController {
    private static Logger logger = Logger.getLogger(RepairDeviceController.class);
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IUnitService unitService;
    @Autowired
    private IUserService userService;

    private Unit unit = new Unit();

    private RepairForm repairForm = new RepairForm();

    @RequestMapping(value = "/repairDevice.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object repairDevice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        //todo 根据用户名获取单位
//        String unitName = new String(request.getParameter("unitName").getBytes("iso-8859-1"), "utf-8");
        String deviceId = new String(request.getParameter("deviceId").getBytes("iso-8859-1"), "utf-8");
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        final Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
            }
        }
//        todo 此处由于考虑到报修设备时要输入设备id，故取消此功能
//        deviceForm = new DeviceForm();
//        deviceForm.setDeviceName(deviceName);
//        DeviceForm deviceForm1 = deviceService.getDevice(deviceForm);
        //根据用户名获取单位id
        String unitId = userService.getUnitId(username);
        unit.setUnitId(unitId);
        //获取单位名
        Unit unit1 = unitService.getUnitName(unit);
        if (unit1 != null) {
            repairForm.setUnitName(unit1.getUnitName());
            repairForm.setUnitId(unit1.getUnitId());
        }
        repairForm.setDeviceName(deviceName);
        repairForm.setApplyName(username);

        repairForm.setDeviceId(deviceId);
        DeviceForm deviceForm1=deviceService.getDevice(deviceId);
        String time= DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        repairForm.setRepairTime(time);
//        repairForm.setId(RandomAccessUtil.getRandom("Repair"));
        System.out.println(deviceForm1.getUseStatus());
        if ("0".equals(deviceForm1.getUseStatus())){
            deviceForm1.setUseStatus("4");
            //修改状态
            int num1=deviceService.modifyStatus(deviceForm1);
            int num=deviceService.repairDevice(repairForm);
            if (num == 1 && num1 == 1) {
                return "apply success";
            } else {
                return "repair fail";
            }
        }else {
            return "useStatus error";
        }
    }
}
