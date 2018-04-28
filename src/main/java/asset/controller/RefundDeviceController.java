package asset.controller;

import asset.pojo.DeviceForm;
import asset.pojo.RefundForm;
import asset.pojo.Unit;
import asset.service.IDeviceService;
import asset.service.IUnitService;
import asset.utils.DataUtil;
import asset.utils.RandomAccessUtil;
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
 * Date:Created in 2018/4/12
 */
@Controller
@RequestMapping("/asset")
public class RefundDeviceController {
    private static Logger logger = Logger.getLogger(RefundDeviceController.class);
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IUnitService unitService;

    private Unit unit;
    private RefundForm refundForm;

    @RequestMapping(value = "/refundDevice.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object refundDevice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String unitName = new String(request.getParameter("unitName").getBytes("iso-8859-1"), "utf-8");
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        String deviceId = new String(request.getParameter("deviceId").getBytes("iso-8859-1"), "utf-8");
        refundForm=new RefundForm();

        final Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
            }
        }
//        todo 此处由于考虑到退还设备时要输入设备id，故取消此功能
//        deviceForm = new DeviceForm();
//        deviceForm.setDeviceName(deviceName);
//        DeviceForm deviceForm1 = deviceService.getDevice(deviceForm);
        unit = new Unit();
        unit.setUnitName(unitName);
        Unit unit1 = unitService.getUnitId(unit);

        refundForm.setDeviceName(deviceName);
        refundForm.setUnitName(unitName);
        refundForm.setApplyName(username);
        if (unit1 != null) {
            refundForm.setUnitId(unit1.getUnitId());
        }
        refundForm.setDeviceId(deviceId);
        DeviceForm deviceForm1=deviceService.getDevice(deviceId);
        refundForm.setId(RandomAccessUtil.getRandom("Refund"));
        String time = DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        refundForm.setRefundTime(time);
        if ("0".equals(deviceForm1.getUseStatus())) {
            Integer num = deviceService.refundDevice(refundForm);
            deviceForm1.setUseStatus("5");
            int num1 = deviceService.modifyStatus(deviceForm1);
            if (num == 1 && num1 == 1) {
                return "refund success";
            } else {
                return "refund fail";
            }
        } else {
            return "useStatus error";
        }
    }
}
